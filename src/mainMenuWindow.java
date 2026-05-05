import javax.swing.*;
import java.awt.*;

public class mainMenuWindow extends JFrame {

    private Aquarium aquarium;
    private JLabel coinsLabel;
    private JLabel fishCountLabel;

    public mainMenuWindow(Aquarium aquarium) {
        this.aquarium = aquarium;

        setTitle("Virtuálni Akvarium");
        setSize(420, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(10, 40, 90));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        JLabel title = new JLabel("Virtualni Akvarium");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(6));

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBackground(new Color(20, 60, 120));
        statsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 100, 180)),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        statsPanel.setMaximumSize(new Dimension(120, 50));
        statsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        coinsLabel = new JLabel("Coiny: " + aquarium.getCoins());
        coinsLabel.setForeground(new Color(255, 215, 0));
        coinsLabel.setFont(new Font("Arial", Font.BOLD, 15));
        coinsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(coinsLabel);

        fishCountLabel = new JLabel("Ryby: " + aquarium.getFishList().size());
        fishCountLabel.setForeground(new Color(150, 210, 255));
        fishCountLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        fishCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(fishCountLabel);

        panel.add(statsPanel);
        panel.add(Box.createVerticalStrut(25));

        JButton btnAquarium = createButton("Spustit akvarium");
        btnAquarium.addActionListener(e -> openAquarium());
        panel.add(btnAquarium);
        panel.add(Box.createVerticalStrut(10));

        JButton btnShop = createButton("Otevrit obchod");
        btnShop.addActionListener(e -> openShop());
        panel.add(btnShop);
        panel.add(Box.createVerticalStrut(10));

        JButton btnExit = createButton("Ukoncit");
        btnExit.setBackground(new Color(140, 40, 40));
        btnExit.addActionListener(e -> System.exit(0));
        panel.add(btnExit);

        panel.add(Box.createVerticalStrut(20));

        JLabel tip = new JLabel("<html><center><i>Tip: Otevri akvarium a vydelavej pasivni coiny!</i></center></html>");
        tip.setForeground(new Color(120, 170, 220));
        tip.setFont(new Font("Arial", Font.PLAIN, 11));
        tip.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(tip);

        add(panel);
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 17));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(250, 42));
        btn.setBackground(new Color(50, 130, 210));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }

    public void refreshCoins() {
        coinsLabel.setText("Coiny: " + aquarium.getCoins());
        fishCountLabel.setText("Ryby: " + aquarium.getFishList().size());
    }

    private void openAquarium() {
        setVisible(false);
        new AquariumWindow(aquarium, this);
    }

    private void openShop() {
        setVisible(false);
        new ShopWindow(aquarium, this);
    }
}
