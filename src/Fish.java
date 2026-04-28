
public class Fish {


    private String name;
    private String species;
    private int size;
    private int hunger;

    private double x;
    private double y;
    private int directionX;
    private int directionY;



    public Fish(String name, String species, int size, double startX, double startY) {
        this.name    = name;
        this.species = species;
        this.size    = size;
        this.x       = startX;
        this.y       = startY;
        this.hunger  = 0;

        this.directionX = 1;
        this.directionY = 1;
    }


    public void move(int aquariumWidth, int aquariumHeight) {
    }




    public String getName()      {
        return name; }
    public String getSpecies()   {
        return species; }
    public int    getSize()      {
        return size; }
    public int    getHunger()    {
        return hunger; }
    public double getX()         {
        return x; }
    public double getY()         {
        return y; }
    public int    getDirectionX(){
        return directionX; }

    @Override
    public String toString() {
        return name + " (" + species + ") | Hlad: " + hunger + "%";
    }
}