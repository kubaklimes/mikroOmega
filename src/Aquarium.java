import java.util.ArrayList;
import java.util.List;


public class Aquarium {

    private List<Fish> fishList;
    private int coins;

    public Aquarium() {
        fishList = new ArrayList<>();
        coins    = 500;
    }

    public void addFish(Fish fish)    {
        fishList.add(fish); }
    public void removeFish(Fish fish) {
        fishList.remove(fish); }
    public List<Fish> getFishList()   {
        return fishList; }

    public int  getCoins()              {
        return coins; }
    public void addCoins(int amount)    {
        coins += amount; }

}