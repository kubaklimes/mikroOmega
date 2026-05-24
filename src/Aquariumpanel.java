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
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

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
            drawSimpleFish(g2, fish.get(i), i);
        }
    }

    private void drawSimpleFish(Graphics2D g2, Fish fish, int index) {
        long t = System.currentTimeMillis();

        int availableW = Math.max(220, getWidth() - 120);
        int baseX = 40 + (index * 120) % availableW;
        int swimOffset = (int) (Math.sin((t / 380.0) + index) * 20);
        int x = baseX + swimOffset;

        int y = 80 + ((index * 75) % Math.max(120, getHeight() - 200));
        y += (int) (Math.cos((t / 520.0) + index) * 6);

        int bodyW = Math.max(40, fish.getSize());
        int bodyH = Math.max(20, fish.getSize() / 2);

        Color bodyColor = fish.isCriticallyHungry() ? new Color(220, 120, 120) : new Color(250, 180, 70);
        g2.setColor(bodyColor);
        g2.fillOval(x, y, bodyW, bodyH);

        int tailSwing = (int) (Math.sin((t / 180.0) + index) * 6);
        Polygon tail = new Polygon();
        tail.addPoint(x, y + bodyH / 2);
        tail.addPoint(x - 16, y + 4 + tailSwing);
        tail.addPoint(x - 16, y + bodyH - 4 + tailSwing);
        g2.fillPolygon(tail);

        g2.setColor(new Color(255, 220, 140, 180));
        g2.fillOval(x + bodyW / 3, y + 3, bodyW / 3, bodyH / 3);

        g2.setColor(Color.WHITE);
        g2.fillOval(x + bodyW - 14, y + 5, 7, 7);
        g2.setColor(Color.BLACK);
        g2.fillOval(x + bodyW - 11, y + 8, 3, 3);

        g2.setColor(new Color(230, 240, 255));
        g2.setFont(new Font("Arial", Font.PLAIN, 11));
        g2.drawString(fish.getName(), x - 4, y - 4);
    }
}
