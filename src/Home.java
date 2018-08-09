import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public final class Home extends Location {
    protected HashSet<Location> pitLog; // locations of pit

    // Constructs a Home with empty players and peaches
    public Home() {
        this(new ArrayList<Player>(), new ArrayList<Peach>());
    }

    // Constructs a Home with a list of players and peaches
    public Home(List<Player> people, List<Peach> peaches) {
        super(new Position(0, 0), "Home", people, peaches);
        this.pitLog = new HashSet<>();
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

    // When PitFinder enter home, report the pit location to Home
    @Override
    public void enter(Player p) {
        super.enter(p);
        // When PeachHunter enter home, deposit all peaches
        if (p instanceof PeachHunter) {
            for (Peach peach : p.peaches) {
                addPeach(peach);
            }
        }

        // When PitFinder enter home, report pit location
        if (p instanceof PitFinder) {
            for (Location pit : ((PitFinder) p).pitKnowledge) {
                if (!pitLog.contains(pit)) {
                    pitLog.add(pit);
                    System.out.println(p.getName() + " just reported pit location " + pit.position);
                }
            }

        }
    }

    // TODO: keeps track of how many peaches each player brings back.
    // know peopleAtLocation
    // may use HashMap


    // Test Home
    public static void main(String[] args) {
        World w = new World();
        PitFinder pitFinder = new PitFinder(w, "PitFinder", w.getHome(), new ArrayList<Peach>(), 50, RGB.YELLOW);
        PeachHunter peachHunter = new PeachHunter(w, "PeachHunter", w.getHome(), new ArrayList<Peach>(), 100, RGB.BLUE);
        w.addPlayer(pitFinder).addPlayer(peachHunter);

        PeachPit testPit = new PeachPit(new Position(1, 1), new ArrayList<Player>(), new ArrayList<Peach>());
        for (int i = 0; i < 10; i++) {
            testPit.addPeach(new Peach(5));
        }
        w.locations[1][1] = testPit;
        w.move(pitFinder, Directions.DOWN);
        w.move(pitFinder, Directions.RIGHT);
        pitFinder.addPit();
        w.move(pitFinder, Directions.UP);
        w.move(pitFinder, Directions.LEFT);
        System.out.println(((Home) w.getHome()).pitLog);

        PeachGrove testGrove = new PeachGrove(new Position(0, 1), new ArrayList<Player>(), new ArrayList<Peach>());
        w.locations[0][1] = testGrove;
        w.move(peachHunter, Directions.RIGHT);
        for (int i = 0; i < 10; i++) {
            testGrove.addPeach(new Peach(8));
        }
        peachHunter.addPeach();
        peachHunter.addPeach();
        w.move(peachHunter, Directions.LEFT);
        System.out.println(((Home) w.getHome()).peachesAtLocation);
    }
}
