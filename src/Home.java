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
        System.out.println(p + " at " + targetLocation + " called for help");
        // Get peaches from Home
        int peachNum = 4;
        ArrayList<Peach> peaches = new ArrayList<>();
        for (int i = 0; i < peachNum; i++) {
            if (this.peachesAtLocation.size() > 0) {
                peaches.add(getPeach());
            } else {
                System.out.println("Home does not have enough peaches!");
                break;
            }
        }
        // Create a Helper if it can get at least one peach
        if (peaches.size() > 0) {
            Helper helper = new Helper(p, targetLocation, world, peaches);
            return;
        }

        System.out.println("Helper can not get a peach from Home, Helper not created");
        return;
    }


    // Select a helper at Home
    protected Player selectHelper() {
        for (Player p : this.getPlayers()) {
            if (p instanceof Helper) {
                return p;
            }

        }
        return null;
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

    // Test: Home
    public static void main(String[] args) {
        World w = new World();
        PitFinder pitFinder = new PitFinder(w, "PitFinder", w.getHome(), new ArrayList<Peach>(), 50, RGB.YELLOW);
        PeachHunter peachHunter = new PeachHunter(w, "PeachHunter", w.getHome(), new ArrayList<Peach>(), 100, RGB.BLUE);
        PeachHunter peachHunter_js = new PeachHunter(w, "PeachHunter_js", w.getHome(), new ArrayList<Peach>(), 100, RGB.BLUE);


//        // Test: pitFinder enters home, report pit location
//        w.addPlayer(pitFinder);
//        PeachPit testPit = new PeachPit(new Position(1, 1), new ArrayList<Player>(), new ArrayList<Peach>());
//        for (int i = 0; i < 10; i++) {
//            testPit.addPeach(new Peach(5));
//        }
//        w.locations[1][1] = testPit;
//        pitFinder.move(Directions.DOWN);
//        pitFinder.move(Directions.RIGHT);
//        pitFinder.addPit();
//        pitFinder.move(Directions.UP);
//        pitFinder.move(Directions.LEFT);
//        System.out.println(((Home) w.getHome()).pitLog);
//
        PeachGrove testGrove = new PeachGrove(new Position(0, 1), new ArrayList<Player>(), new ArrayList<Peach>());
        w.locations[0][1] = testGrove;
        for (int i = 0; i < 10; i++) {
            testGrove.addPeach(new Peach(8));
        }
//
//        // Test: peachHunter starts moving
//        w.addPlayer(peachHunter);
//        peachHunter.move(Directions.RIGHT);
//        peachHunter.pickPeach();
//        peachHunter.pickPeach();
//        System.out.println("peaches left at peachGrove: " + peachHunter.location.peachesAtLocation);
//        peachHunter.move(Directions.LEFT);
//        System.out.println("peaches at Home: " + ((Home) w.getHome()).peachesAtLocation);
//        System.out.println("peaches in peachHunter: " + peachHunter.peaches);
//        System.out.println("peachMap : " + ((Home) w.getHome()).peachMap);
//
//        // Test: peachHunter_js starts moving
//        w.addPlayer(peachHunter_js);
//        peachHunter_js.move(Directions.RIGHT);
//        peachHunter_js.pickPeach();
//        peachHunter_js.pickPeach();
//        peachHunter_js.pickPeach();
//        System.out.println("peaches left at peachGrove: " + peachHunter_js.location.peachesAtLocation);
//        peachHunter_js.move(Directions.LEFT);
//        System.out.println("peaches at Home: " + ((Home) w.getHome()).peachesAtLocation);
//        System.out.println("peaches in peachHunter_js: " + peachHunter_js.peaches);
//        System.out.println("peachMap : " + ((Home) w.getHome()).peachMap);

        // Test: peachHunter calls for help
        peachHunter.move(Directions.RIGHT);
        peachHunter.pickPeach();
        peachHunter.pickPeach();
        peachHunter.pickPeach();
        System.out.println("peaches left at peachGrove: " + peachHunter.location.peachesAtLocation);
        peachHunter.setHealth(9);

        for (int i = 0; i < 3; i++) {
            w.getHome().addPeach(new Peach(10));
        }

        peachHunter.play();

        Player helper = ((Home)w.getHome()).selectHelper();

        try {
            helper.play();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Helper is not created.");
        }
        helper.move(Directions.RIGHT);
        helper.play();
        peachHunter.getHealth();
        peachHunter.checkPeaches();
    }
}
