import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public final class Home extends Location {
    // TODO: peachHunter will update pitLog to its knowledge
    protected ArrayList<Location> pitLog; // locations of pit
    protected HashMap<Player, Integer> peachMap;

    // Constructs a Home with empty players and peaches
    public Home() {
        this(new ArrayList<Player>(), new ArrayList<Peach>());
    }

    // Constructs a Home with a list of players and peaches
    public Home(List<Player> people, List<Peach> peaches) {
        super(new Position(0, 0), "Home", people, peaches);
        this.pitLog = new ArrayList<>();
        this.peachMap = new HashMap<>();
    }


    @Override
    public void callForHelp(Player p, Location targetLocation, World world) {
        System.out.println(p + " (HP:" + p.health + ")" + " at " + targetLocation + " called for help");

        // Create a Helper if it can get at least one peach
        if (numberOfPeaches() > 0) {
            Helper helper = new Helper(p, targetLocation, world, new ArrayList<>());
            System.out.println(this + " : A " + helper + " was born");

            // Helper gets peaches from Home
            int peachNum = 4;
            helper.pickPeaches(peachNum);
            if (this.numberOfPeaches() == 0) {
                System.out.println(this + ": No peaches left");
            }
        } else {
            System.out.println(this + ": No peaches left, Helper is not born");
        }
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
        if (p instanceof PeachHunter && p.getHealth() > 0 && p.numberOfPeaches() > 0) {
            while (p.numberOfPeaches() > 0) {
                addPeach(p.getPeach());
                // Keeps track of how many peaches each player brings back
                peachMap.merge(p, 1, (x, y) -> x + y);
            }
            System.out.println(this + ": " + p.getName() + " deposited all the peaches");
            System.out.println(this + ": Now has " + numberOfPeaches() + " peaches");
            System.out.println(this + " peachMap: " + peachMap);
        }

        // When PitFinder enters home, reports pit location
        if (p instanceof PitFinder && p.getHealth() > 0) {
            for (Location pit : ((PitFinder) p).knowledge) {
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
        PitFinder pitFinder = new PitFinder(w, "PitFinder", w.getHome(), new ArrayList<Peach>(), 50, RGB.YELLOW, new ArrayList<>());
        PeachHunter peachHunter = new PeachHunter(w, "PeachHunter", w.getHome(), new ArrayList<Peach>(), 100, RGB.BLUE, new ArrayList<>());
        PeachHunter peachHunter_js = new PeachHunter(w, "PeachHunter_js", w.getHome(), new ArrayList<Peach>(), 100, RGB.BLUE, new ArrayList<>());

        // TODO: genPit
//        PeachPit testPit = new PeachPit(new Position(1, 1), new ArrayList<Player>(), new ArrayList<Peach>());
//        for (int i = 0; i < 10; i++) {
//            testPit.addPeach(new Peach(5));
//        }

        w.genGrove(0, 1, 0, 3, 6, true);
        w.genGrove(2, 1, 0, 9, 9, false);

//        // Test: pitFinder enters home, report pit location
//        w.addPlayer(pitFinder);
//
//        w.locations[1][1] = testPit;
//        pitFinder.move(Directions.DOWN);
//        pitFinder.move(Directions.RIGHT);
//        pitFinder.addPit();
//        pitFinder.move(Directions.UP);
//        pitFinder.move(Directions.LEFT);
//        System.out.println(((Home) w.getHome()).pitLog);
//        pitFinder.move(Directions.DOWN);
//        pitFinder.move(Directions.UP);
//        System.out.println(((Home) w.getHome()).pitLog);

//        // Test: peachHunter starts moving
//        w.addPlayer(peachHunter);
//        peachHunter.move(Directions.RIGHT);
//        peachHunter.move(Directions.DOWN);
//        peachHunter.move(Directions.DOWN);
//        peachHunter.pickPeach();
//        peachHunter.pickPeach();
//        System.out.println("peaches left at peachGrove: " + peachHunter.location.peachesAtLocation);
//        peachHunter.move(Directions.LEFT);
//        peachHunter.move(Directions.UP);
//        peachHunter.move(Directions.UP);
//        System.out.println("peaches at Home: " + ((Home) w.getHome()).peachesAtLocation);
//        System.out.println("peaches in peachHunter: " + peachHunter.peaches);
//        System.out.println("peachMap : " + ((Home) w.getHome()).peachMap);
//
//        // Test: peachHunter_js starts moving
//        w.addPlayer(peachHunter_js);
//        peachHunter_js.move(Directions.RIGHT);
//        peachHunter_js.move(Directions.DOWN);
//        peachHunter_js.move(Directions.DOWN);
//        peachHunter_js.pickPeach();
//        peachHunter_js.pickPeach();
//        peachHunter_js.pickPeach();
//        System.out.println("peaches left at peachGrove: " + peachHunter_js.location.peachesAtLocation);
//        peachHunter_js.move(Directions.LEFT);
//        peachHunter_js.move(Directions.UP);
//        peachHunter_js.move(Directions.UP);
//        System.out.println("peaches at Home: " + ((Home) w.getHome()).peachesAtLocation);
//        System.out.println("peaches in peachHunter_js: " + peachHunter_js.peaches);
//        System.out.println("peachMap : " + ((Home) w.getHome()).peachMap);

        // Test: peachHunter calls for help
        peachHunter.move(Directions.RIGHT);
        peachHunter.move(Directions.DOWN);
        peachHunter.move(Directions.DOWN);
        peachHunter.location.showPeaches();
        peachHunter.pickPeach();
        peachHunter.pickPeach();
        peachHunter.pickPeach();
        peachHunter.location.showPeaches();
        peachHunter.setHealth(15);

        for (int i = 0; i < 3; i++) {
            w.getHome().addPeach(new Peach(10));
        }

        System.out.println("=========== Test start ===========");
        peachHunter.play();

        Player helper = ((Home) w.getHome()).selectHelper();
        try {
            helper.play();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Helper does not exist.");
        }

        peachHunter.play();
        peachHunter.play();
        peachHunter.play();


        // Helper should go towards home from now
        helper.play();
        helper.play();
        helper.play();
        helper.showPeaches();
        System.out.println(helper.getLocation());
        System.out.println(w.getHome().getPlayers());
        System.out.println(w.getPlayers());


//        // Test: move
//        // outOfBounds check
//        w.addPlayer(peachHunter);
//        peachHunter.move(Directions.RIGHT);
//        System.out.println(peachHunter.location);
//        peachHunter.move(Directions.RIGHT);
//        System.out.println(peachHunter.location);
//        peachHunter.move(Directions.RIGHT);
//        System.out.println(peachHunter.location);

//        // Test: move randomly
//        for (int i = 0; i < 50; i++) {
//            peachHunter.moveRandom();
//        }

//        // Test: move with knowledge of dangerous places
//        w.addPlayer(peachHunter);
//        peachHunter.move(Directions.RIGHT);
//        peachHunter.play();
//        peachHunter.move(Directions.DOWN);
//        peachHunter.move(Directions.DOWN);
//        peachHunter.play();
//        peachHunter.pickPeaches(51);
//        System.out.println(peachHunter.numberOfPeaches());
//        peachHunter.move(Directions.UP);
//
//        for (int i = 0; i < 30; i++) {
//            peachHunter.play();
//        }

    }
}
