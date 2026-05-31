import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Aquariumpanel extends JPanel {
    private Aquarium aquarium;

    // Connects the drawing panel to the shared aquarium data.
    public Aquariumpanel(Aquarium aquarium) {
        this.aquarium = aquarium;
        setBackground(new Color(10, 80, 160));
    }

    // Draws the aquarium background and all fish.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gradient = new GradientPaint(0, 0,
                new Color(5, 60, 140), 0, getHeight(), new Color(2, 30, 80));
        g2.setPaint(gradient);
        g2.fillRect(0, 0, getWidth(), getHeight());

        int panelWidth = Math.max(1, getWidth());
        int panelHeight = Math.max(1, getHeight());
        g2.setColor(new Color(255, 255, 255, 20));
        for (int i = 0; i < 8; i++) {
            int bx = (i * 97 + 30) % panelWidth;
            int by = (i * 71 + 50) % panelHeight;
            int br = 8 + (i % 3) * 6;
            g2.fillOval(bx, by, br, br);
        }
        if (aquarium == null) return;

        List<Fish> fish = aquarium.getFishList();
        for (int i = 0; i < fish.size(); i++) {
            drawSimpleFish(g2, fish.get(i), i);
        }
    }

    // Draws one fish at its current position.
    // Zdroje : https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#sin-double-
    private void drawSimpleFish(Graphics2D g2, Fish fish, int index) {
        long t = System.currentTimeMillis();

        int x = fish.getX();
        int y = fish.getY();
        int bodyW = fish.getBodyWidth();
        int bodyH = fish.getBodyHeight();
        int tailSwing = (int) (Math.sin((t / 180.0) + index) * 6);
        boolean facingRight = fish.isFacingRight();

        Color bodyColor = fish.isCriticallyHungry() ? new Color(220, 120, 120) : fish.getBodyColor();
        g2.setColor(bodyColor);
        g2.fillOval(x, y, bodyW, bodyH);

        Polygon tail = new Polygon();
        if (facingRight) {
            tail.addPoint(x, y + bodyH / 2);
            tail.addPoint(x - 16, y + 4 + tailSwing);
            tail.addPoint(x - 16, y + bodyH - 4 + tailSwing);
        } else {
            tail.addPoint(x + bodyW, y + bodyH / 2);
            tail.addPoint(x + bodyW + 16, y + 4 + tailSwing);
            tail.addPoint(x + bodyW + 16, y + bodyH - 4 + tailSwing);
        }
        g2.fillPolygon(tail);

        g2.setColor(new Color(255, 255, 255, 80));
        g2.fillOval(x + bodyW / 3, y + 3, bodyW / 3, bodyH / 3);

        int eyeX = facingRight ? x + bodyW - 14 : x + 7;
        g2.setColor(Color.WHITE);
        g2.fillOval(eyeX, y + 5, 7, 7);
        g2.setColor(Color.BLACK);
        g2.fillOval(eyeX + 3, y + 8, 3, 3);

        g2.setColor(new Color(230, 240, 255));
        g2.setFont(new Font("Arial", Font.PLAIN, 11));
        g2.drawString(fish.getName(), x - 4, y - 4);
    }
}