import java.util.Set;
import java.util.List;

public class PeachHunter extends Player {

    public PeachHunter(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb, Set<Location> knowledge) {
        super(w, name, location, peaches, health, rgb, knowledge);
    }

    @Override
    public void play() {
        super.play();
        // TODO: Call move method

        // Remember or forget PeachGrove location
        updatePeachGrove();
    }


    protected void updatePeachGrove() {
        if (location instanceof PeachGrove) {
            if (location.numberOfPeaches() > 0) {
                if (!knowledge.contains(location)) {
                    addPeachGrove();
                }
            } else {
                removePeachGrove();
            }
        }
    }

    protected boolean addPeachGrove() {
        boolean result = knowledge.add(location);
        System.out.println(getName() + ": " + location + " detected, added to my grove knowledge: " + result);
        return result;
    }

    protected boolean removePeachGrove() {
        boolean result = knowledge.remove(location);
        System.out.println(getName() + ": " + location + " runs out of peaches, removed from my grove knowledge: " + !result);
        return result;
    }
}
