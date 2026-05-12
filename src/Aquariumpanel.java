import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Aquariumpanel extends JPanel {
    private Aquarium aquarium;

    public Aquariumpanel(Aquarium aquarium) {
        this.aquarium = aquarium;
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
        if (aquarium == null) return;

        List<Fish> fish = aquarium.getFishList();
        for (int i = 0; i < fish.size(); i++) {
            Fish f = fish.get(i);
            drawSimpleFish(g2, f, i);
        }
    }

    private void drawSimpleFish(Graphics2D g2, Fish fish, int index) {
        int tankW = Math.max(300, getWidth() - 40);
        int x = 20 + (index * 130) % (tankW - 100);
        int y = 70 + ((index * 80) % Math.max(120, getHeight() - 180));

        int bodyW = Math.max(32, fish.getSize());
        int bodyH = Math.max(18, fish.getSize() / 10);

        Color bodyColor = fish.isCriticallyHungry() ? new Color(220, 120, 120) : new Color(250, 180, 70);
        g2.setColor(bodyColor);
        g2.fillOval(x, y, bodyW, bodyH);

        Polygon tail = new Polygon();
        tail.addPoint(x, y + bodyH / 2);
        tail.addPoint(x - 14, y + 3);
        tail.addPoint(x - 14, y + bodyH - 3);
        g2.fillPolygon(tail);

        g2.setColor(Color.WHITE);
        g2.fillOval(x + bodyW - 10, y + 4, 5, 5);
        g2.setColor(Color.BLACK);
        g2.fillOval(x + bodyW - 8, y + 6, 2, 2);

        g2.setColor(new Color(230, 240, 255));
        g2.setFont(new Font("Arial", Font.PLAIN, 11));
        g2.drawString(fish.getName(), x - 4, y - 4);
    }
}
