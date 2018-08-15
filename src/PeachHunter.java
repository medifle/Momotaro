import java.util.ArrayList;
import java.util.Random;
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

    protected void moveToTarget(Location targetLocation) {
        int target_X = targetLocation.getPosition().getX();
        int target_Y = targetLocation.getPosition().getY();
        int helper_X = location.getPosition().getX();
        int helper_Y = location.getPosition().getY();

        // Get available directions to get to target
        int direction_X = -1;
        int direction_Y = -1;
        if ((target_X - helper_X) < 0) {
            direction_X = Directions.UP;
        } else if ((target_X - helper_X) > 0) {
            direction_X = Directions.DOWN;
        }
        if ((target_Y - helper_Y) < 0) {
            direction_Y = Directions.LEFT;
        } else if ((target_Y - helper_Y) > 0) {
            direction_Y = Directions.RIGHT;
        }

        // Choose randomly from available directions
        ArrayList<Integer> directions = new ArrayList<>();
        if (direction_X != -1) {
            directions.add(direction_X);
            play();
        }
        if (direction_Y != -1) {
            directions.add(direction_Y);
            play();
        }
        // If helper has not reached the target location, move to the target
        if (directions.size() != 0) {
            int rnd = new Random().nextInt(directions.size());
            move(directions.get(rnd));
            play();
        }
    }

    protected void returnHome(){
        if(peaches.size() > 50){
            backHome();
        }
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
