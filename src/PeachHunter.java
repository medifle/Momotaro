import java.util.List;

public class PeachHunter extends Player {
    protected int peachCapacity;
    protected List<Peach> peachesAtLocation = null;

    public PeachHunter(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb, List<Location> knowledge) {
        super(w, name, location, peaches, health, rgb, knowledge);
        this.peachCapacity = 100;
    }

    @Override
    public void play() {
        super.play();
        // TODO: Call move method
        if (knowledge.size() > 0) {
//            moveToTarget(knowledge.);
        }

        // Remember or forget PeachGrove location
        updatePeachGrove();
    }


    // adding peaches to the current location
    public void addPeach(Peach p) {
        peachesAtLocation.add(p);
    }

    // getter for peaches
    public Peach getPeach() {
        return peachesAtLocation.remove(0);
    }

    // once peach hunter has 50 or more peaches return home and deposit peaches
    protected void returnHome(Player p) {
        while (p.numberOfPeaches() > 0) {
            if (p instanceof PeachHunter && p.getHealth() > 0 && p.numberOfPeaches() > 0) {
                if (peaches.size() > 50) {
                    addPeach(p.getPeach());
                }
                System.out.println(p.getName() + " deposited all the peaches");
            }
        }
    }

    // if a hunter's health is below 50 then it can only carry 25 peaches
    protected void carryPeaches(Player p) {
        int count = 0;
        for (Player player : location.getPlayers()) {
            if (health < 50) {
                while (this.numberOfPeaches() < 0) {
                    player.receivePeach(this);
                    count += 1;
                }
                if (count == 25) {
                    player.receivePeach(this);
                    break;
                }
            }
        }
    }


    // remembering where peach groves are
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
