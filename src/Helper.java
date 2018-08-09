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
        // TODO: move one step
        interact(target);
    }

    @Override
    public void interact(Player p) {

    }

}
