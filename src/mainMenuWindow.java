import javax.swing.*;
import java.awt.*;


public class mainMenuWindow extends JFrame {

    private Aquarium aquarium;

    public mainMenuWindow(Aquarium aquarium) {
        this.aquarium = aquarium;


        setTitle("Virtuální Akvárium");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(20, 80, 140));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));


        JLabel title = new JLabel("Virtuální Akvárium");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        panel.add(title);
        panel.add(Box.createVerticalStrut(30));


        JButton btnAquarium = createButton("Spustit akvárium");
        btnAquarium.addActionListener(e -> openAquarium());
        panel.add(btnAquarium);
        panel.add(Box.createVerticalStrut(10));


        JButton btnShop = createButton("Otevřít obchod");
        btnShop.addActionListener(e -> openShop());
        panel.add(btnShop);
        panel.add(Box.createVerticalStrut(10));


        JButton btnExit = createButton("Ukončit");
        btnExit.addActionListener(e -> System.exit(0));
        panel.add(btnExit);

        add(panel);
        setVisible(true);
    }


    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 20));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(220, 40));
        btn.setBackground(new Color(70, 160, 220));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
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