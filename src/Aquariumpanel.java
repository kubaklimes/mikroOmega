import javax.swing.*;
import java.awt.*;

public class Aquariumpanel extends JPanel {

    public Aquariumpanel() {
        setBackground(new Color(10, 80, 160));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        GradientPaint gradient = new GradientPaint(0, 0,
                new Color(5, 60, 140), 0, getHeight(), new Color(2, 30, 80));
        g2.setPaint(gradient);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(new Color(255, 255, 255, 20));
        for (int i = 0; i < 8; i++) {
            int bx = (i * 97 + 30) % getWidth();
            int by = (i * 71 + 50) % getHeight();
            int br = 8 + (i % 3) * 6;
            g2.fillOval(bx, by, br, br);
        }
    }
}
