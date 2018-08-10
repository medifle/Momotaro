import java.util.List;

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
        System.out.println("Helper start playing...");
        // TODO: move one step

        interact(target);

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
