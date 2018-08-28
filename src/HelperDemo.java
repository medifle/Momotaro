import java.util.ArrayList;
import java.util.Iterator;

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

        for (int n = 0; n < 10; n++) {
            for (int i = 0; i < w.getPlayers().size(); i++) {
                System.out.println("====================================== " + w.getPlayers().get(i) + "'s TURN" + " ======================================");
                System.out.println("");
                w.getPlayers().get(i).play();
                System.out.println("");
            }
        }

//        System.out.println("====================================== " + peachHunter + "'s TURN" + " ======================================");
//        peachHunter.play();
//        System.out.println("");
//
//        Player helper1 = ((Home) w.getHome()).selectHelper();
//        try {
//            System.out.println("====================================== " + helper1 + "'s TURN" + " ======================================");
//            helper1.play();
//            System.out.println("");
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            System.out.println("Helper does not exist.");
//        }
//
//        for (int i = 0; i < 3; i++) {
//            System.out.println("====================================== " + peachHunter + "'s TURN" + " ======================================");
//            peachHunter.play();
//            System.out.println("");
//        }
//
//        Player helper2 = ((Home) w.getHome()).selectHelper();
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println("====================================== " + helper1 + "'s TURN" + " ======================================");
//            helper1.play();
//            System.out.println("");
//            System.out.println("====================================== " + helper2 + "'s TURN" + " ======================================");
//            helper2.play();
//            System.out.println("");
//            System.out.println("====================================== " + peachHunter + "'s TURN" + " ======================================");
//            peachHunter.play();
//            System.out.println("");
//        }

        System.out.println("===================================== " + "Game Statistics" + " =====================================");
        System.out.println("Home's players: " + w.getHome().getPlayers());
        System.out.println("World's players: " + w.getPlayers());
        System.out.println("Home's peaches: " + w.getHome().numberOfPeaches());

        System.out.println("");
        System.out.println("=========== Helper Demo End ===========");

    }
}