import java.util.ArrayList;
import java.util.HashSet;

public class PeachesGame {
    public static void main(String[] args) {
        World w = new World();
        Player p = new Player(w, "cat", w.getHome(), new ArrayList<Peach>(), 50, RGB.YELLOW, new ArrayList<>());
        Player q = new Player(w, "dog", w.getHome(), new ArrayList<Peach>(), 100, RGB.BLUE, new ArrayList<>());
        w.addPlayer(p).addPlayer(q);

        System.out.println("Home : " + w.getHome());
        System.out.println("  Players at Home : " + w.getHome().getPlayers());
        System.out.println("Location of all players in world");
        for (Player pp : w.getPlayers()) {
            System.out.println(pp.getLocation());
            System.out.println(pp.getLocation().getPlayers());
        }

        System.out.println("Move some players in world");
        p.move(Directions.DOWN);
        q.move(Directions.RIGHT);
        System.out.println("Location of all players in world");

        for (Player pp : w.getPlayers()) {
            System.out.println(pp.getLocation());
            System.out.println(pp.getLocation().getPlayers());
        }


        // what the game might look like...
        while (w.getHome().numberOfPeaches() <= 100) {
            // iterate over all locations in the world
            for (Location location : w.getLocations()) {
                // iterate over all players in the current location
                for (Player player : location.getPlayers()) {
                    System.out.println(player.getName() + ", " + player.getLocation());
                    player.play();
                }
            }

        }

    }
}