import java.util.ArrayList;

public class PeachesGame {
    public static void main(String[] args) {

        System.out.println("______  _____   ___   _____  _   _  _____  _____      ___  ______  _   _  _____  _   _  _____  _   _ ______  _____ ");
        System.out.println("| ___ \\|  ___| / _ \\ /  __ \\| | | ||  ___|/  ___|    / _ \\ |  _  \\| | | ||  ___|| \\ | ||_   _|| | | || ___ \\|  ___|");
        System.out.println("| |_/ /| |__  / /_\\ \\| /  \\/| |_| || |__  \\ `--.    / /_\\ \\| | | || | | || |__  |  \\| |  | |  | | | || |_/ /| |__  ");
        System.out.println("|  __/ |  __| |  _  || |    |  _  ||  __|  `--. \\   |  _  || | | || | | ||  __| | . ` |  | |  | | | ||    / |  __| ");
        System.out.println("| |    | |___ | | | || \\__/\\| | | || |___ /\\__/ /   | | | || |/ / \\ \\_/ /| |___ | |\\  |  | |  | |_| || |\\ \\ | |___ ");
        System.out.println("\\_|    \\____/ \\_| |_/ \\____/\\_| |_/\\____/ \\____/    \\_| |_/|___/   \\___/ \\____/ \\_| \\_/  \\_/   \\___/ \\_| \\_|\\____/ ");
        System.out.println("                                                                                                                   ");


        World w = new World(4, 4);
        PeachHunter peachHunter = new PeachHunter(w, "PeachHunter", w.getHome(), new ArrayList<Peach>(), 100, RGB.BLUE, new ArrayList<>());
//        PitFinder pitFinder = new PitFinder(w, "PitFinder", w.getHome(), new ArrayList<Peach>(), 50, RGB.YELLOW, new ArrayList<>());


//        PeachPit testPit = new PeachPit(new Position(1, 1), new ArrayList<Player>(), new ArrayList<Peach>());
//        for (int i = 0; i < 10; i++) {
//            testPit.addPeach(new Peach(5));
//        }

        w.genGrove(3, 2, 180, 10, 9, true);
        w.genGrove(1, 2, 180, 3, 6, true);
        w.genGrove(2, 1, 180, 3, 7, true);
        w.genGrove(1, 0, 30, 30, 5, true);


        // Game Start
        while (w.getHome().numberOfPeaches() <= 400) {
            for (Player player : w.getPlayers()) {
                System.out.println("====================================== " + player + "'s TURN" + " ======================================");
                System.out.println("");
                player.play();
                System.out.println("");
            }
        }

        System.out.println("");
        System.out.println("▒█▀▄▀█ ▀█▀ ▒█▀▀▀█ ▒█▀▀▀█ ▀█▀ ▒█▀▀▀█ ▒█▄░▒█ 　 ▒█▀▀█ ▒█▀▀▀█ ▒█▀▄▀█ ▒█▀▀█ ▒█░░░ ▒█▀▀▀ ▀▀█▀▀ ▒█▀▀▀ ");
        System.out.println("▒█▒█▒█ ▒█░ ░▀▀▀▄▄ ░▀▀▀▄▄ ▒█░ ▒█░░▒█ ▒█▒█▒█ 　 ▒█░░░ ▒█░░▒█ ▒█▒█▒█ ▒█▄▄█ ▒█░░░ ▒█▀▀▀ ░▒█░░ ▒█▀▀▀  ");
        System.out.println("▒█░░▒█ ▄█▄ ▒█▄▄▄█ ▒█▄▄▄█ ▄█▄ ▒█▄▄▄█ ▒█░░▀█ 　 ▒█▄▄█ ▒█▄▄▄█ ▒█░░▒█ ▒█░░░ ▒█▄▄█ ▒█▄▄▄ ░▒█░░ ▒█▄▄▄   ");






// TODO: If we add players(like a Helper), how to avoid ConcurrentModificationException?

//        for (Location location : w.getLocations()) {
////            List<Player> playersCopy = new ArrayList<>(location.getPlayers());
//            List<Player> playersCopy = new ArrayList<>();
//            playersCopy.addAll(location.getPlayers());
//            // iterate over all players in the current location
//            for (Player player : playersCopy) {
//                System.out.println(player + ", " + player.getLocation());
////                player.play();
//                player.move(Directions.DOWN);
//                player.move(Directions.RIGHT);
//            }
//        }

    }
}