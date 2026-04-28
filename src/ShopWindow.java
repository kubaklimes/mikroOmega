import javax.swing.*;
import java.awt.*;


public class ShopWindow extends JFrame {

    private Aquarium aquarium;
    private JFrame   previousWindow;


    private static final String[] FISH_SPECIES = { "Klaun", "Kapr", "Neon", "Zlatá rybka", "Zralok" };

    public ShopWindow(Aquarium aquarium, JFrame previousWindow) {
        this.aquarium       = aquarium;
        this.previousWindow = previousWindow;

        setTitle("Obchod");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(20, 60, 110));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));


        JLabel title = new JLabel("Obchod s rybami");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(20));


        for (String species : FISH_SPECIES) {
            JButton btn = createFishButton(species);
            panel.add(btn);
            panel.add(Box.createVerticalStrut(8));
        }

        panel.add(Box.createVerticalStrut(15));


        JButton btnBack = new JButton("← Zpět do menu");
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.addActionListener(e -> goBack());
        panel.add(btnBack);

        add(panel);
        setVisible(true);
    }


    private JButton createFishButton(String species) {
        JButton btn = new JButton("Koupit: " + species);
        btn.setFont(new Font("Arial", Font.PLAIN, 14));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(230, 35));
        btn.setBackground(new Color(70, 160, 220));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);

        return btn;
    }

    private void goBack() {
        dispose();
        previousWindow.setVisible(true);
    }
}