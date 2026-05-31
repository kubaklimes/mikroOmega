import javax.swing.*;
import java.awt.*;

public class ShopWindow extends JFrame {

    private Aquarium aquarium;
    private JFrame previousWindow;
    private JLabel coinsLabel;

    private static final Object[][] FISH_CATALOG = {
            {"Klaun", 100, "Vesela oranzova rybka"},
            {"Kapr", 60, "Robustni sladkovodni ryba"},
            {"Neon", 40, "Mala zarive modra rybka"},
            {"Zlata rybka", 80, "Klasicka zlata rybka"},
            {"Zralok", 200, "Velky a impozantni zralok"},
    };

    private static final Object[][] FOOD_CATALOG = {
            {"Male krmeni", 30, 5, "5 porci krmeni"},
            {"Velke krmeni", 75, 15, "15 porci krmeni"},
    };

    // Builds the shop window for buying fish and food.
    public ShopWindow(Aquarium aquarium, JFrame previousWindow) {
        this.aquarium = aquarium;
        this.previousWindow = previousWindow;

        setTitle("Obchod");
        setSize(520, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(15, 50, 100));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel title = new JLabel("Obchod");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(8));

        coinsLabel = new JLabel("Coiny: " + aquarium.getCoins());
        coinsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        coinsLabel.setForeground(new Color(255, 215, 0));
        coinsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(coinsLabel);
        panel.add(Box.createVerticalStrut(20));

        panel.add(makeSectionLabel("Ryby"));
        panel.add(Box.createVerticalStrut(8));

        for (Object[] fish : FISH_CATALOG) {
            String species = (String) fish[0];
            int price = (int) fish[1];
            String desc = (String) fish[2];
            int size = getStartingSizeForSpecies(species);
            panel.add(makeFishRow(species, price, size, desc));
            panel.add(Box.createVerticalStrut(8));
        }

        panel.add(Box.createVerticalStrut(16));

        panel.add(makeSectionLabel("Krmeni"));
        panel.add(Box.createVerticalStrut(8));

        JLabel foodInfo = new JLabel("Zasoby krmeni: " + aquarium.getFood() + " porci");
        foodInfo.setFont(new Font("Arial", Font.ITALIC, 12));
        foodInfo.setForeground(new Color(150, 220, 150));
        foodInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(foodInfo);
        panel.add(Box.createVerticalStrut(6));

        for (Object[] food : FOOD_CATALOG) {
            String name = (String) food[0];
            int price = (int) food[1];
            int portions = (int) food[2];
            String desc = (String) food[3];
            panel.add(makeFoodRow(name, price, portions, desc, foodInfo));
            panel.add(Box.createVerticalStrut(8));
        }

        panel.add(Box.createVerticalStrut(20));

        JButton btnBack = new JButton("Zpet do menu");
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.addActionListener(e -> goBack());
        btnBack.setFont(new Font("Arial", Font.BOLD, 20));
        btnBack.setBackground(new Color(5, 30, 65));
        btnBack.setForeground(new Color(150, 210, 255));
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        panel.add(btnBack);

        JScrollPane scroll = new JScrollPane(panel);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll);

        setVisible(true);
    }

    // Returns the first size for each fish species.
    private int getStartingSizeForSpecies(String species) {
        switch (species) {
            case "Zralok": return 85;
            case "Kapr": return 45;
            case "Klaun": return 30;
            case "Zlata rybka": return 22;
            case "Neon": return 16;
            default: return 25;
        }
    }

    // Creates one shop row for a fish type.
    private JPanel makeFishRow(String species, int price, int size, String desc) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setBackground(new Color(25, 70, 130));
        row.setMaximumSize(new Dimension(460, 70));

        JPanel info = new JPanel(new GridLayout(2, 1));
        info.setBackground(new Color(25, 70, 130));

        JLabel nameLabel = new JLabel(species + "  -  " + price + " coinu");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 13));
        info.add(nameLabel);

        JLabel descLabel = new JLabel(desc + " (vel. " + size + ")");
        descLabel.setForeground(new Color(160, 200, 255));
        descLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        info.add(descLabel);

        row.add(info, BorderLayout.CENTER);

        JButton buyBtn = new JButton("Koupit");
        buyBtn.setFont(new Font("Arial", Font.BOLD, 12));
        buyBtn.setBackground(new Color(50, 150, 220));
        buyBtn.setForeground(Color.WHITE);
        buyBtn.setFocusPainted(false);
        buyBtn.setPreferredSize(new Dimension(85, 40));
        buyBtn.addActionListener(e -> buyFish(species, price, size));
        row.add(buyBtn, BorderLayout.EAST);

        return row;
    }
    // Buys a fish when the player has enough coins.
    private void buyFish(String species, int price, int size) {
        if (!aquarium.spendCoins(price)) {
            JOptionPane.showMessageDialog(this, "Mas malo coinu.");
            return;
        }

        String name = JOptionPane.showInputDialog(this, "Jmeno pro rybu:", species + aquarium.getFishList().size());
        if (name == null || name.isBlank()) {
            name = species + aquarium.getFishList().size();
        }

        Fish fish = new Fish(name.trim(), species, size);
        aquarium.addFish(fish);

        refreshLabels();
        JOptionPane.showMessageDialog(this, "Koupil jsi rybu: " + name);
    }

    // Buys food and updates the visible stock label.
    private void buyFood(int price, int portions, JLabel foodStockLabel) {
        if (!aquarium.spendCoins(price)) {
            JOptionPane.showMessageDialog(this, "Mas malo coinu.");
            return;
        }

        aquarium.addFood(portions);
        refreshLabels();
        foodStockLabel.setText("Zasoby krmeni: " + aquarium.getFood() + " porci");
    }

    // Refreshes shop labels after a purchase.
    private void refreshLabels() {
        coinsLabel.setText("Coiny: " + aquarium.getCoins());
    }

    // Creates one shop row for food.
    private JPanel makeFoodRow(String name, int price, int portions, String desc, JLabel foodStockLabel) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setBackground(new Color(25, 80, 60));
        row.setMaximumSize(new Dimension(460, 70));

        JPanel info = new JPanel(new GridLayout(2, 1));
        info.setBackground(new Color(25, 80, 60));

        JLabel nameLabel = new JLabel(name + "  -  " + price + " coinu");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 13));
        info.add(nameLabel);

        JLabel descLabel = new JLabel(desc);
        descLabel.setForeground(new Color(160, 220, 160));
        descLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        info.add(descLabel);

        row.add(info, BorderLayout.CENTER);

        JButton buyBtn = new JButton("Koupit");
        buyBtn.setFont(new Font("Arial", Font.BOLD, 12));
        buyBtn.setBackground(new Color(60, 160, 80));
        buyBtn.setForeground(Color.WHITE);
        buyBtn.setFocusPainted(false);
        buyBtn.setPreferredSize(new Dimension(85, 40));
        buyBtn.addActionListener(e -> buyFood(price, portions, foodStockLabel));
        row.add(buyBtn, BorderLayout.EAST);

        return row;
    }

    // Creates a section title label.
    private JLabel makeSectionLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        lbl.setForeground(new Color(180, 220, 255));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lbl;
    }
    // Returns to the previous window.
    private void goBack() {
        dispose();
        previousWindow.setVisible(true);
        if (previousWindow instanceof mainMenuWindow) {
            ((mainMenuWindow) previousWindow).refreshCoins();
        }
    }
}