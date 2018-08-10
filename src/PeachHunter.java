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

        if (location instanceof PeachGrove) {
            if (location.peachesAtLocation.size() > 0) {
                addPeachGrove();
            } else {
                removePeachGrove();
            }
        }
    }

    protected boolean addPeach() {
        // TODO: how many peaches would he add one time?
        if (location instanceof PeachGrove && location.peachesAtLocation.size() > 0) {
            boolean result = peaches.add(location.getPeach());
            System.out.println("PeachHunter added a peach from " + location + ": " + result);
            return result;
        }
        return false;
    }

    protected boolean addPeachGrove() {
        boolean result = groveKnowledge.add(location);
        System.out.println("PeachGrove detected, added to grove knowledge: " + result);
        return result;
    }

    protected boolean removePeachGrove() {
        boolean result = groveKnowledge.remove(location);
        System.out.println("PeachGrove runs out of peaches, removed from grove knowledge" + result);
        return result;
    }
}
