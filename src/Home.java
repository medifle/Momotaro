import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public final class Home extends Location {
    protected ArrayList<Location> homeLog; // locations of places (pit, grove, den)

    // Constructs a Home with empty players and peaches
    public Home() {
        this(new ArrayList<Player>(), new ArrayList<Peach>());
    }

    // Constructs a Home with a list of players and peaches
    public Home(List<Player> people, List<Peach> peaches) {
        super(new Position(0, 0), "Home", people, peaches);
    }


    @Override
    public void callForHelp(Player p, Location targetLocation, World world) {
        // Get peaches from Home
        int peachNum = 4;
        ArrayList<Peach> peaches = new ArrayList<>();
        for (int i = 0; i < peachNum; i++) {
            peaches.add(getPeach());
        }
        // Create a Helper and give peaches to the player in distress
        Helper helper = new Helper(p, targetLocation, world, peaches);
    }

    // keeps track of how many peaches each player brings back.
    // know peopleAtLocation
    // may use HashMap
}
