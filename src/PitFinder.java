import java.util.HashSet;
import java.util.List;

public class PitFinder extends Player {
    protected HashSet<Location> pitKnowledge; // locations of pit

    public PitFinder(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb) {
        super(w, name, location, peaches, health, rgb);
        this.pitKnowledge = new HashSet<>();
    }

    @Override
    public void play() {
        super.play();
        // TODO: Call move method

        if (location instanceof PeachPit) {
            addPit();
        }
    }

    // Move method
    // Move randomly until he finds a pit


    // addPit
    public boolean addPit() {
        boolean result = pitKnowledge.add(location);
        System.out.println("PeachPit detected, added to pit knowledge" + result);
        return result;
    }
}
