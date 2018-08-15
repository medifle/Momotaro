import java.util.List;
import java.util.ArrayList;

public class Helper extends Player {
    protected Player target;
    protected Location targetLocation;
    protected boolean hasHelped;

    public Helper(Player target, Location targetLocation, World world, List<Peach> peaches) {
        super(world, "Helper", world.getHome(), peaches, 100, RGB.GREEN, new ArrayList<>());
        this.target = target;
        this.targetLocation = targetLocation;
        this.speed = 2; // helper moves 2 steps in one turn
    }

    @Override
    public void play() {
        isDead();
        // If Helper is in low health and still have peaches after eating one
        if (health < 30 && numberOfPeaches() > 1) {
            eatPeach();
        }
        // If Helper has not help target out
        if (!hasHelped) {
            moveToTarget(target, speed);
            interact(target);
            // After help, go back Home
        } else {
            backHome(speed);
        }

    }


    // If found target at current location, give peaches to target
    @Override
    public void interact(Player p) {
        // Search for target at current location, then give it all peaches
        for (Player player : location.getPlayers()) {
            if (player.equals(target)) {
                int count = 0;
                while (this.numberOfPeaches() > 0) {
                    player.receivePeach(this);
                    count += 1;
                }
                System.out.println(p.getName() + " received " + count + " peaches from " + getName());
                hasHelped = true;
            }
        }
    }

}
