import java.util.ArrayList;
import java.util.List;

public class Aquarium {

    private List<Fish> fishList;
    private int coins;
    private int food;

    public Aquarium() {
        fishList = new ArrayList<>();
        coins    = 200;
        food     = 3;
    }

    public void addFish(Fish fish)    { fishList.add(fish); }
    public void removeFish(Fish fish) { fishList.remove(fish); }
    public List<Fish> getFishList()   { return fishList; }

    public int  getCoins()              { return coins; }
    public void addCoins(int amount)    { coins += amount; }

    public boolean spendCoins(int amount) {
        if (coins >= amount) {
            coins -= amount;
            return true;
        }
        return false;
    }

    public boolean canAfford(int amount) {
        return coins >= amount; }

    public int  getFood()           { return food; }
    public void addFood(int amount) { food += amount; }

    public boolean useFood() {
        if (food > 0) {
            food--;
            return true;
        }
        return false;
    }
}
