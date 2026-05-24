public class Fish {

    private String name;
    private String species;
    private int size;
    private int hunger;
    private int maxHunger = 100;

    public Fish(String name, String species, int size) {
        this.name    = name;
        this.species = species;
        this.size    = size;
        this.hunger  = 0;
    }

    public void increaseHunger(int amount) {
            hunger = Math.min(maxHunger, hunger + amount);
    }

    public void feed(int amount) {
        hunger = Math.max(0, hunger - amount);
    }

    public boolean isCriticallyHungry() {
        return hunger >= 80;
    }
    public void update() {
        increaseHunger(1);
    }
    public void grow() {
        size = Math.min(110, size + 2);
    }
    public int getCoinValuePerTick() {
        return Math.max(1, size / 20);
    }

    public String getName()    { return name; }
    public String getSpecies() { return species; }
    public int    getSize()    { return size; }
    public int    getHunger()  { return hunger; }

    @Override
    public String toString() {
        return name + " (" + species + ") | Hlad: " + hunger + "%";
    }
}
