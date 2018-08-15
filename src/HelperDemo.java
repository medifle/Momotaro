import java.util.ArrayList;

public class HelperDemo {
    public static void main(String[] args) {
        System.out.println("");
        System.out.println(" _   _        _                       ______                         ");
        System.out.println("| | | |      | |                      |  _  \\                        ");
        System.out.println("| |_| |  ___ | | _ __    ___  _ __    | | | |  ___  _ __ ___    ___  ");
        System.out.println("|  _  | / _ \\| || '_ \\  / _ \\| '__|   | | | | / _ \\| '_ ` _ \\  / _ \\ ");
        System.out.println("| | | ||  __/| || |_) ||  __/| |      | |/ / |  __/| | | | | || (_) |");
        System.out.println("\\_| |_/ \\___||_|| .__/  \\___||_|      |___/   \\___||_| |_| |_| \\___/ ");
        System.out.println("                | |                                                  ");
        System.out.println("                |_|                                                  ");
        System.out.println("");
        System.out.println("");

        System.out.println("====================================== World 01 Initializing ======================================");
//        System.out.println("");

        World w = new World(5, 5);
        PeachHunter peachHunter = new PeachHunter(w, "PeachHunter", w.getHome(), new ArrayList<Peach>(), 100, RGB.BLUE, new ArrayList<>());

        w.genGrove(1, 1, 0, 3, 6, true);
        w.genGrove(4, 3, 5, 5, 2, false);

        // Test: peachHunter calls for help
        peachHunter.move(Directions.RIGHT);
        peachHunter.move(Directions.RIGHT);
        peachHunter.move(Directions.RIGHT);
        peachHunter.move(Directions.DOWN);
        peachHunter.move(Directions.DOWN);
        peachHunter.move(Directions.DOWN);
        peachHunter.move(Directions.DOWN);
        peachHunter.location.showPeaches();
        peachHunter.pickPeach();
        peachHunter.pickPeach();
        peachHunter.pickPeach();
        peachHunter.location.showPeaches();
        peachHunter.setHealth(11);

        for (int i = 0; i < 6; i++) {
            w.getHome().addPeach(new Peach(10));
        }
        System.out.println("Home's peaches: " + w.getHome().numberOfPeaches());


        System.out.println("====================================== " + peachHunter + "'s TURN" + " ======================================");
        peachHunter.play();
        System.out.println("");

        Player helper = ((Home) w.getHome()).selectHelper();
        try {
            System.out.println("====================================== " + helper + "'s TURN" + " ======================================");
            helper.play();
            System.out.println("");
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Helper does not exist.");
        }

        for (int i = 0; i < 3; i++) {
            System.out.println("====================================== " + peachHunter + "'s TURN" + " ======================================");
            peachHunter.play();
            System.out.println("");
        }


        // Helper should go towards home from now
        for (int i = 0; i < 5; i++) {
            System.out.println("====================================== " + helper + "'s TURN" + " ======================================");
            helper.play();
            System.out.println("");
        }

        System.out.println("===================================== " + "Game Statistics" + " =====================================");
        System.out.println("Helper's location: " + helper.getLocation());
        helper.showPeaches();
        System.out.println("Home's players: " + w.getHome().getPlayers());
        System.out.println("World's players: " + w.getPlayers());
        System.out.println("Home's peaches: " + w.getHome().numberOfPeaches());

        System.out.println("");
        System.out.println("=========== Helper Demo End ===========");

    }
}