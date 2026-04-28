import javax.swing.*;
import java.awt.*;


public class AquariumWindow extends JFrame {

    private Aquarium aquarium;
    private JFrame   previousWindow;

    public AquariumWindow(Aquarium aquarium, JFrame previousWindow) {
        this.aquarium       = aquarium;
        this.previousWindow = previousWindow;

        setTitle("Akvárium");
        setSize(880, 530);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        Aquariumpanel aquariumPanel = new Aquariumpanel();
        add(aquariumPanel, BorderLayout.CENTER);


        JPanel fishListPanel = new JPanel();
        fishListPanel.setBackground(new Color(15, 60, 100));
        fishListPanel.setPreferredSize(new Dimension(180, 0));

        JLabel listTitle = new JLabel("Moje ryby");
        listTitle.setForeground(Color.WHITE);
        listTitle.setFont(new Font("Arial", Font.BOLD, 20));
        fishListPanel.add(listTitle);

        add(fishListPanel, BorderLayout.EAST);

        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomBar.setBackground(new Color(10, 40, 80));

        JButton btnBack = new JButton("← Zpět do menu");
        btnBack.addActionListener(e -> goBack());
        bottomBar.add(btnBack);

        add(bottomBar, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void goBack() {
        dispose();
        previousWindow.setVisible(true);
    }
}