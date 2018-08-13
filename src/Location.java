import java.util.List;

public class Location {
    protected Position position;

    protected String description = "Nothing special about this location.";

    protected List<Player> peopleAtLocation = null;
    protected List<Peach> peachesAtLocation = null;

    public Location(Position p, String description, List<Player> people, List<Peach> peaches) {
        this.position = p;
        this.description = description;
        this.peopleAtLocation = people;
        this.peachesAtLocation = peaches;
    }

    /**
     * getter for position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * getter for description
     */
    public String getDescription() {
        return description;
    }

    /**
     * getter for players
     */
    public List<Player> getPlayers() {
        return peopleAtLocation;
    }

    /**
     * getter for a Peach
     */
    public Peach getPeach() {
        return peachesAtLocation.remove(0);
    }

    /**
     * check number pf peaches in location
     */
    public int numberOfPeaches() {
        return peachesAtLocation == null ? 0 : peachesAtLocation.size();
    }

    public void showPeaches() {
        System.out.println("peaches left at " + this + ": " + peachesAtLocation);
    }


    /**
     * adding a peach to the location
     */
    public void addPeach(Peach p) {
        peachesAtLocation.add(p);
    }

    /**
     * Allows the location to do something to a player when entering the location
     */
    public void enter(Player p) {
        enter(p, true);
    }

    /**
     * Allows the location to do something to a player when entering the location
     *
     * @param p   the player to enter the location
     * @param msg if true, print message
     */
    public void enter(Player p, boolean msg) {
        p.setLocation(this);
        peopleAtLocation.add(p);
        if (msg) {
            System.out.println(p.getName() + " just entered location " + position);
        }
    }

    /**
     * Removes a player from a room
     *
     * @param p the player to exit from the location
     */
    public void exit(Player p) {
        exit(p, true);
    }


    /**
     * Removes a player from a room
     *
     * @param p   the player to exit from the location
     * @param msg if true, print message
     */
    public void exit(Player p, boolean msg) {
        peopleAtLocation.remove(p);
        if (msg) {
            System.out.println(p.getName() + " just left location " + position);
        }
    }

    /* ONLY for Home subclass */
    public void callForHelp(Player p, Location targetLocation, World world) {
    }

    @Override
    public String toString() {
        return description + position.toString();
    }


}