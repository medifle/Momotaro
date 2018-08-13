import java.util.List;
import java.util.Set;

public class PitFinder extends Player {

    public PitFinder(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb, Set<Location> knowledge) {
        super(w, name, location, peaches, health, rgb, knowledge);
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
        boolean result = knowledge.add(location);
        System.out.println("PeachPit detected, added to pit knowledge" + result);
        return result;
    }
}
