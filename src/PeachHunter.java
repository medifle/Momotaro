import java.util.HashSet;
import java.util.List;

public class PeachHunter extends Player {
    protected HashSet<Location> groveKnowledge; // locations of grove

    public PeachHunter(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb) {
        super(w, name, location, peaches, health, rgb);
        this.groveKnowledge = new HashSet<>();
    }

    @Override
    public void play() {
        super.play();
        // TODO: Call move method

        // Remember or forget PeachGrove location
        if (location instanceof PeachGrove) {
            if (location.numberOfPeaches() > 0) {
                addPeachGrove();
            } else {
                removePeachGrove();
            }
        }
    }


    protected boolean addPeachGrove() {
        boolean result = groveKnowledge.add(location);
        System.out.println(getName() + ": " + location + " detected, added to my grove knowledge: " + result);
        return result;
    }

    protected boolean removePeachGrove() {
        boolean result = groveKnowledge.remove(location);
        System.out.println(getName() + ": " + location + " runs out of peaches, removed from my grove knowledge" + result);
        return result;
    }
}
