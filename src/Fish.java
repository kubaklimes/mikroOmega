public class Fish {

    private String name;
    private String species;
    private int size;
    private int hunger;

    public Fish(String name, String species, int size) {
        this.name    = name;
        this.species = species;
        this.size    = size;
        this.hunger  = 0;
    }

    public void increaseHunger(int amount) {
        hunger = Math.min(100, hunger + amount);
    }

    public boolean feed(int foodValue) {
        if (hunger == 0) return false;
        hunger = Math.max(0, hunger - foodValue);
        return true;
    }

    public boolean isCriticallyHungry() {
        return hunger >= 80;
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
