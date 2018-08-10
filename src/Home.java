import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public final class Home extends Location {
    protected HashSet<Location> pitLog; // locations of pit
    protected HashMap<Player, Integer> peachMap;

    // Constructs a Home with empty players and peaches
    public Home() {
        this(new ArrayList<Player>(), new ArrayList<Peach>());
    }

    // Constructs a Home with a list of players and peaches
    public Home(List<Player> people, List<Peach> peaches) {
        super(new Position(0, 0), "Home", people, peaches);
        this.pitLog = new HashSet<>();
        this.peachMap = new HashMap<>();
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

    @Override
    public void enter(Player p) {
        super.enter(p);
        // When PeachHunter enters home, deposits all peaches
        if (p instanceof PeachHunter) {
            while (p.peaches.size() > 0) {
                addPeach(p.getPeach());
                // Keeps track of how many peaches each player brings back
                peachMap.merge(p, 1, (x, y) -> x + y);
            }
            System.out.println(p.getName() + " deposited all the peaches");
        }

        // When PitFinder enters home, reports pit location
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
        PeachHunter peachHunter_js = new PeachHunter(w, "PeachHunter_js", w.getHome(), new ArrayList<Peach>(), 100, RGB.BLUE);
        w.addPlayer(pitFinder).addPlayer(peachHunter).addPlayer(peachHunter_js);

//        PeachPit testPit = new PeachPit(new Position(1, 1), new ArrayList<Player>(), new ArrayList<Peach>());
//        for (int i = 0; i < 10; i++) {
//            testPit.addPeach(new Peach(5));
//        }
//        w.locations[1][1] = testPit;
//        w.move(pitFinder, Directions.DOWN);
//        w.move(pitFinder, Directions.RIGHT);
//        pitFinder.addPit();
//        w.move(pitFinder, Directions.UP);
//        w.move(pitFinder, Directions.LEFT);
//        System.out.println(((Home) w.getHome()).pitLog);
//
        PeachGrove testGrove = new PeachGrove(new Position(0, 1), new ArrayList<Player>(), new ArrayList<Peach>());
        w.locations[0][1] = testGrove;
        for (int i = 0; i < 10; i++) {
            testGrove.addPeach(new Peach(8));
        }

        // peachHunter start moving
        w.move(peachHunter, Directions.RIGHT);
        peachHunter.addPeach();
        peachHunter.addPeach();
        System.out.println("peaches left at peachGrove: " + peachHunter.location.peachesAtLocation);
        w.move(peachHunter, Directions.LEFT);
        System.out.println("peaches at Home: " + ((Home) w.getHome()).peachesAtLocation);
        System.out.println("peaches in peachHunter: " + peachHunter.peaches);
        System.out.println("peachMap : " + ((Home) w.getHome()).peachMap);

        // peachHunter_js start moving
        w.move(peachHunter_js, Directions.RIGHT);
        peachHunter_js.addPeach();
        peachHunter_js.addPeach();
        peachHunter_js.addPeach();
        System.out.println("peaches left at peachGrove: " + peachHunter_js.location.peachesAtLocation);
        w.move(peachHunter_js, Directions.LEFT);
        System.out.println("peaches at Home: " + ((Home) w.getHome()).peachesAtLocation);
        System.out.println("peaches in peachHunter_js: " + peachHunter_js.peaches);
        System.out.println("peachMap : " + ((Home) w.getHome()).peachMap);

    }
}
