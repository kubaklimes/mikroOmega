import java.awt.Color;

public class Fish {

    private static final Color[] COLOR_PALETTE = {
            new Color(250, 180, 70),
            new Color(90, 200, 255),
            new Color(255, 120, 170),
            new Color(120, 230, 130),
            new Color(210, 150, 255),
            new Color(255, 230, 90)
    };

    private String name;
    private String species;
    private int size;
    private int hunger;
    private int maxHunger = 100;
    private int minSize = 12;
    private int hungerTickCounter;
    private int x;
    private int y;
    private int speedX;
    private int speedY;
    private Color bodyColor;

    // Creates a fish with basic stats, color and simple movement values.
    public Fish(String name, String species, int size) {
        this.name    = name;
        this.species = species;
        this.size    = size;
        this.hunger  = 0;
        this.hungerTickCounter = 0;

        int seed = (name + species).hashCode() & 0x7fffffff;
        this.bodyColor = COLOR_PALETTE[seed % COLOR_PALETTE.length];
        this.x = 40 + seed % 280;
        this.y = 70 + (seed / 7) % 220;
        this.speedX = 1 + seed % 3;
        this.speedY = 1 + (seed / 5) % 2;
        if (seed % 2 == 0) this.speedX *= -1;
        if (seed % 3 == 0) this.speedY *= -1;
    }

    // Raises hunger but never above the maximum value.
    public void increaseHunger(int amount) {
        hunger = Math.min(maxHunger, hunger + amount);
    }

    // Lowers hunger after feeding but never below zero.
    public void feed(int amount) {
        hunger = Math.max(0, hunger - amount);
    }

    // Returns true when the fish needs food urgently.
    public boolean isCriticallyHungry() {
        return hunger >= 80;
    }

    // Updates hunger and shrinks the fish when it is hungry for too long.
    public void update() {
        hungerTickCounter++;
        if (hungerTickCounter >= 5) {
            increaseHunger(1);
            shrinkWhenHungry();
            hungerTickCounter = 0;
        }
    }

    // Shrinks the fish faster when hunger is higher than 25%.
    private void shrinkWhenHungry() {
        int shrinkAmount = 0;

        if (hunger > 75) {
            shrinkAmount = 3;
        } else if (hunger > 50) {
            shrinkAmount = 2;
        } else if (hunger > 25) {
            shrinkAmount = 1;
        }

        size = Math.max(minSize, size - shrinkAmount);
    }

    // Moves the fish around the visible aquarium area.
    public void swimWithin(int aquariumWidth, int aquariumHeight) {
        int bodyW = getBodyWidth();
        int bodyH = getBodyHeight();
        int maxX = Math.max(40, aquariumWidth - bodyW - 20);
        int maxY = Math.max(60, aquariumHeight - bodyH - 35);

        x += speedX;
        y += speedY;

        if (x < 20 || x > maxX) {
            speedX *= -1;
            x = Math.max(20, Math.min(x, maxX));
        }
        if (y < 45 || y > maxY) {
            speedY *= -1;
            y = Math.max(45, Math.min(y, maxY));
        }
    }

    // Makes the fish slightly larger after feeding.
    public void grow() {
        size = Math.min(110, size + 2);
    }

    // Calculates passive coins directly from the current fish size.
    public int getCoinValuePerTick() {
        return Math.max(1, size / 20);
    }

    public String getName()    { return name; }
    public String getSpecies() { return species; }
    public int    getSize()    { return size; }
    public int    getHunger()  { return hunger; }
    public int    getX()       { return x; }
    public int    getY()       { return y; }
    public int    getBodyWidth()  { return Math.max(18, size); }
    public int    getBodyHeight() { return Math.max(10, size / 2); }
    public Color  getBodyColor()  { return bodyColor; }
    public boolean isFacingRight() { return speedX >= 0; }

    @Override
    public String toString() {
        return name + " (" + species + ") | Hlad: " + hunger + "%";
    }
}