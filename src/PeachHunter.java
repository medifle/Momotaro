import java.util.List;

public class PeachHunter extends Player {
    protected int peachCapacity;
    protected boolean isPeachEnough;

    public PeachHunter(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb, List<Location> knowledge) {
        super(w, name, location, peaches, health, rgb, knowledge);
        this.peachCapacity = 100;
    }

    @Override
    public void play() {
        // For health
        super.play();
        // Update PeachGrove
        updatePeachGrove();
        // PeachHunter can pick peaches anywhere except Home
        if (location != world.getHome()) {
            // Pick maximum 20 peaches in one turn
            pickPeaches(20);
        }
        // Update PeachGrove again
        updatePeachGrove();
        // Check if peaches are enough
        checkPeaches();
        if (isPeachEnough) {
            System.out.println(this + ": Going back Home...");
            // Go back home trying to avoid dangers including PeachGroves
            backHome(knowledge);
        } else if (knowledge.size() > 0) {
            // If it knows a PeachGrove, move to the latest updated PeachGrove
            moveToTarget(knowledge.get(knowledge.size() - 1));
        } else {
            // Searching grove trying to avoid dangers
            moveRandom(knowledge);
        }
    }


    protected void checkPeaches() {
        if (!isPeachEnough && numberOfPeaches() >= 50) {
            isPeachEnough = true;
            System.out.println(this + ": Got " + numberOfPeaches() + " peaches!");
        }
        if (isPeachEnough && numberOfPeaches() == 0) {
            isPeachEnough = false;
        }
    }


    /**
     * Updates the Grove info
     */
    protected void updatePeachGrove() {
        if (location instanceof PeachGrove) {
            if (location.numberOfPeaches() > 0) {
                if (!knowledge.contains(location)) {
                    addPeachGrove();
                    // Check if location is updated already
                } else if (location != knowledge.get(knowledge.size() - 1)) {
                    // Update nearest PeachGrove
                    knowledge.remove(location);
                    knowledge.add(location);
                    System.out.println(this + ": Updated nearest " + location);
                }
            } else {
                if (knowledge.contains(location)) {
                    removePeachGrove();
                }
            }
        }
    }


    /**
     * Adds a peach grove to knowledge
     *
     * @return
     */
    protected void addPeachGrove() {
        boolean result = knowledge.add(location);
        System.out.println(getName() + ": " + location + " detected, added to my grove knowledge");
//        System.out.println(getName() + ": " + location + " detected, added to my grove knowledge: " + result);
    }


    /**
     * Removes the peach grove from knowledge
     *
     * @return
     */
    protected void removePeachGrove() {
        boolean result = knowledge.remove(location);
        System.out.println(getName() + ": " + location + " runs out of peaches, removed from my grove knowledge");
//        System.out.println(getName() + ": " + location + " runs out of peaches, removed from my grove knowledge: " + result);
    }
}
