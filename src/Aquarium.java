import java.util.ArrayList;
import java.util.List;

public class Aquarium {

    private List<Fish> fishList;
    private int coins;
    private int food;

    // Sets the starting fish list, coins and food.
    public Aquarium() {
        fishList = new ArrayList<>();
        coins    = 200;
        food     = 3;
    }

    // Adds a new fish to the aquarium.
    public void addFish(Fish fish)    { fishList.add(fish); }
    public void removeFish(Fish fish) { fishList.remove(fish); }
    public List<Fish> getFishList()   { return fishList; }

    public int  getCoins()              { return coins; }
    public void addCoins(int amount)    { coins += amount; }

    // Tries to spend coins and returns false when there are not enough.
    public boolean spendCoins(int amount) {
        if (coins >= amount) {
            coins -= amount;
            return true;
        }
        return false;
    }

    // Checks if the aquarium has enough coins.
    public boolean canAfford(int amount) {
        return coins >= amount; }

    public int  getFood()           { return food; }
    public void addFood(int amount) { food += amount; }

    // Uses one food portion if available.
    public boolean useFood() {
        if (food > 0) {
            food--;
            return true;
        }
        return false;
    }
}
