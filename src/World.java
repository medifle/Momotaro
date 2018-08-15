import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class World {
    protected Location[][] locations;
    protected Location home;
    protected List<Player> players; // all players in the world

    public World() {
        this(3, 3);
    }

    public World(int rows, int cols) {
        locations = new Location[rows][cols];
        for (int r = 0; r < rows; r += 1) {
            for (int c = 0; c < cols; c += 1) {
                locations[r][c] = new EmptyLocation(new Position(r, c));
            }
        }
        home = new Home();
        locations[0][0] = home;
        players = new ArrayList<Player>();
    }


    // Shuffle a List of items
    protected static <T> void shuffleItem(List<T> Items) {
        int length = Items.size();
        for (int i = 0; i < length; i += 1) {
            // Swap with element
            Random rnd = new Random();
            int j = rnd.nextInt(length);
            T swap = Items.get(i);
            Items.set(i, Items.get(j));
            Items.set(j, swap);
        }
    }

    protected void genGrove(int x, int y, int numGood, int numBad, int ripeness, boolean isShuffle) {
        PeachGrove Grove = new PeachGrove(new Position(x, y), new ArrayList<Player>(), new ArrayList<Peach>());
        locations[x][y] = Grove;
        for (int i = 0; i < numBad; i++) {
            Grove.addPeach(new Peach(ripeness, true));
        }
        for (int i = 0; i < numGood; i++) {
            Grove.addPeach(new Peach(ripeness));
        }
        if (isShuffle) {
            World.shuffleItem(Grove.peachesAtLocation);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Location[][] getWorld() {
        return locations;
    }

    public Location getHome() {
        return home;
    }

    public List<Location> getLocations() {
        List<Location> list = new ArrayList<Location>(locations.length * locations[0].length);
        for (Location[] array : locations) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }

    /* keep a list of all players in the world. Each time a helper is created be
     * sure to add them to this list
     */
    public World addPlayer(Player p) {
        players.add(p);
        return this;
    }


    protected Location getNewLocation(Location oldLoc, int direction) {
        int x = oldLoc.getPosition().getX();
        int y = oldLoc.getPosition().getY();
        Location newLocation = null;
        switch (direction) {
            case Directions.UP:
                try {
                    newLocation = locations[x - 1][y];
                } catch (ArrayIndexOutOfBoundsException e) {
                    return oldLoc;
                }
                break;
            case Directions.DOWN:
                try {
                    newLocation = locations[x + 1][y];
                } catch (ArrayIndexOutOfBoundsException e) {
                    return oldLoc;
                }
                break;
            case Directions.LEFT:
                try {
                    newLocation = locations[x][y - 1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    return oldLoc;
                }
                break;
            case Directions.RIGHT:
                try {
                    newLocation = locations[x][y + 1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    return oldLoc;
                }
                break;
            default:
                break;
        }

        return newLocation;
    }

    public boolean move(Player p, int direction) {
        Location loc = p.getLocation(); // player's current location
        Location newLocation = getNewLocation(loc, direction);
        if (loc == newLocation) {
            return false;
        }
        loc.exit(p, false);
        newLocation.enter(p);
        return true;
    }
}