import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Helper extends Player {
    protected Player target;
    protected Location targetLocation;

    public Helper(Player target, Location targetLocation, World world, List<Peach> peaches) {
        super(world, "Helper", world.getHome(), peaches, 100, RGB.GREEN);
        this.target = target;
        this.targetLocation = targetLocation;
    }

    @Override
    public void play() {
        moveToTarget(target);
        interact(target);
    }

    protected void moveToTarget(Player target) {
        int target_X = target.getLocation().getPosition().getX();
        int target_Y = target.getLocation().getPosition().getY();
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
        }
        if (direction_Y != -1) {
            directions.add(direction_Y);
        }
        // If helper has not reached the target location, move to the target
        if (directions.size() != 0) {
            int rnd = new Random().nextInt(directions.size());
            move(directions.get(rnd));
        }
    }


    @Override
    public void interact(Player p) {
        // Search for target at current location, then give it all peaches
        for (Player player : location.getPlayers()) {
            if (player.equals(target)) {
                int count = 0;
                while (this.peaches.size() > 0) {
                    player.receivePeach(this);
                    count += 1;
                }
                System.out.println(p.getName() + " received " + count + " peaches from " + getName());
            }
        }
    }

}
