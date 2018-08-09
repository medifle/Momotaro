import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * A Player in the game
 * <p>
 * Each member of your team should implement their own
 * unique Player subtype. Your group should also have a human player.
 */

public class Player {
    protected World world;    // world that player lives in
    protected String name;     // name of player
    protected Location location; // where in the world the player is
    protected List<Peach> peaches;  // peaches
    protected int health;   // health of player
    protected RGB colour;   // colour of player (if graphics is used)
    protected ArrayList<Location> knowledge; // locations of places (pit, grove, den)

    /**
     * Creates a player in the game
     *
     * @param w    is the world that the player lives in
     * @param name     is the name of the player
     * @param location is where in the world the player is
     * @param peaches  is a list of peaches the player starts with
     * @param health   is the health of the player (which may or may not be relevant in your game)
     * @param rgb      is the colour of the player
     */
    public Player(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb, ArrayList<Location> knowledge) {
        this.world = w;
        this.name = name;
        this.location = location;
        location.getPlayers().add(this);
        this.peaches = peaches;
        this.health = health;
        this.colour = rgb;
        this.knowledge = knowledge;
    }

    /**
     * Getter for a player's world
     */
    public World getWorld() {
        return world;
    }

    /**
     * Getter for a player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for a player's location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Getter for a player's peach
     */
    public Peach getPeach() {
        return peaches == null ? null : peaches.remove(0);
    }

    /**
     * Getter for a player's health
     */
    public int getHealth() {
        return health;
    }


    /**
     * Getter for a player's knowledge
     */
    public ArrayList<Location> getKnowledge() {
        return knowledge;
    }

    /**
     * Setter for a player's knowledge
     */
    public void setKnowledge(ArrayList<Location> knowledge) {
        this.knowledge =  knowledge;
    }
    /**
     * This is the logic of the player.
     * It defines what they should do when given a chance to do somerthing
     *
     *
     * overridden play method should at least call move
     */
    public void play() {
        if (health < 10) {
            getHelp();
            return;
        }



    }


    /**
     * Moves a player from one location to a new location
     *
     * @param newLocation is the new location that the player will be moved to
     * @return true if the move was successful and false otherwise (e.g. when trying to move from one
     * location to another that are not connected)
     */
    public boolean move(int direction) {
        // move from current location to new location (if possible)
        world.move(this, direction);
        return false;
    }

    /**
     * sets a player's current location
     *
     * @param location is the Location to set for the player
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Setter for a player's health
     *
     * @param h is the new health of the player
     */
    public void setHealth(int h) {
        this.health = h;
    }


    /**
     * Allows for interaction with this player and another player
     * (presumably called from within the play method)
     *
     * @param p is a player that is interacting with this player
     */
    public void interact(Player p) {
        // allows for some interaction with a player

    }

    /**
     * ask for help when they need it
     */
    public void getHelp() {
        world.getHome().callForHelp(this, location);
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Two players are the same if they have the same name, location and health.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Player) {
            return this.name.equals(((Player) o).name)
                    && this.location.equals(((Player) o).location)
                    && this.health == ((Player) o).health;

        } else {
            return false;
        }
    }


}