import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AquariumWindow extends JFrame {

    private Aquarium aquarium;
    private JFrame previousWindow;

    private JPanel fishListContent;
    private JLabel coinsLabel;
    private JLabel foodLabel;
    private JLabel statusLabel;


    public AquariumWindow(Aquarium aquarium, JFrame previousWindow) {
        this.aquarium       = aquarium;
        this.previousWindow = previousWindow;

        setTitle("Akvarium");
        setSize(920, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Aquariumpanel aquariumPanel = new Aquariumpanel();
        add(aquariumPanel, BorderLayout.CENTER);

        add(buildRightPanel(), BorderLayout.EAST);
        add(buildBottomBar(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel buildRightPanel() {
        JPanel outer = new JPanel(new BorderLayout());
        outer.setPreferredSize(new Dimension(200, 0));
        outer.setBackground(new Color(10, 50, 90));

        JLabel title = new JLabel("Moje ryby", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 15));
        title.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        outer.add(title, BorderLayout.NORTH);

        fishListContent = new JPanel();
        fishListContent.setLayout(new BoxLayout(fishListContent, BoxLayout.Y_AXIS));
        fishListContent.setBackground(new Color(10, 50, 90));

        JScrollPane scroll = new JScrollPane(fishListContent);
        scroll.setBackground(new Color(10, 50, 90));
        scroll.getViewport().setBackground(new Color(10, 50, 90));
        scroll.setBorder(BorderFactory.createEmptyBorder());
        outer.add(scroll, BorderLayout.CENTER);

        refreshFishList();
        return outer;
    }

    public void refreshFishList() {
        fishListContent.removeAll();
        List<Fish> fish = aquarium.getFishList();

        if (fish.isEmpty()) {
            JLabel empty = new JLabel("<html><center>Zadne ryby.<br>Navstiv obchod!</center></html>",
                    SwingConstants.CENTER);
            empty.setForeground(new Color(150, 180, 210));
            empty.setFont(new Font("Arial", Font.ITALIC, 12));
            empty.setAlignmentX(Component.CENTER_ALIGNMENT);
            fishListContent.add(Box.createVerticalStrut(20));
            fishListContent.add(empty);
        } else {
            for (Fish f : fish) {
                fishListContent.add(Box.createVerticalStrut(6));
            }
        }

    }


    private JPanel buildBottomBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 6));
        bar.setBackground(new Color(5, 30, 65));

        JButton btnBack = new JButton("Zpet do menu");
        btnBack.addActionListener(e -> goBack());
        btnBack.setForeground(new Color(150, 210, 255));
        btnBack.setBackground(new Color(15, 50, 100));
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        btnBack.setFont(new Font("Arial", Font.BOLD, 20));
        bar.add(btnBack);

        coinsLabel = new JLabel();
        coinsLabel.setForeground(new Color(255, 215, 0));
        coinsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bar.add(coinsLabel);

        foodLabel = new JLabel();
        foodLabel.setForeground(new Color(100, 220, 100));
        foodLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bar.add(foodLabel);

        statusLabel = new JLabel("Ryby jsou spokojene");
        statusLabel.setForeground(new Color(160, 200, 255));
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        bar.add(statusLabel);

        updateStats();
        return bar;
    }

    private void feedFish(Fish fish) {
        if (aquarium.getFood() == 0) {
            setStatus("Nemas krmeni! Kup ho v obchode.");
            return;
        }
        if (fish.getHunger() == 0) {
            setStatus(fish.getName() + " je uz syty.");
            return;
        }
        aquarium.useFood();
        fish.feed(40);
        updateStats();
        refreshFishList();
        setStatus(fish.getName() + " byl nakrmen!");
    }

    private void updateStats() {
        coinsLabel.setText("Coiny: " + aquarium.getCoins());
        foodLabel.setText("  Krmeni: " + aquarium.getFood() + " porci");
    }

    private void setStatus(String msg) {
        statusLabel.setText(msg);
    }



    private void goBack() {
        dispose();
        previousWindow.setVisible(true);
        if (previousWindow instanceof mainMenuWindow) {
            ((mainMenuWindow) previousWindow).refreshCoins();
        }
    }
}
