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
    protected HashSet<Location> knowledge;
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
    public Player(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb, HashSet<Location> knowledge) {
        this.world = w;
        this.name = name;
        this.location = location;
        location.getPlayers().add(this); // Make sure player can be born in Home
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
    public HashSet<Location> getKnowledge() {
        return knowledge;
    }

    /**
     * Setter for a player's knowledge
     */
    public void setKnowledge(HashSet<Location> knowledge) {
        this.knowledge =  knowledge;
    }
    /**
     * This is the logic of the player.
<<<<<<< HEAD
     * It defines what they should do when given a chance to do somerthing
     *
     *
     * overridden play method should at least call move
=======
     * It defines what they should do when given a chance to do something
>>>>>>> f87e25bdde21fb440a9872bc9760a9d92de8e12b
     */
    public void play() {
        if (health < 10) {
            getHelp();
            return;
        }



    }

    /**
     * Move a player one step to the direction
     *
<<<<<<< HEAD
     * @param newLocation is the new location that the player will be moved to
     * @return true if the move was successful and false otherwise (e.g. when trying to move from one
     * location to another that are not connected)
=======
     * @param direction the direction the player moves
     * @return true if the move was successful and false otherwise
>>>>>>> f87e25bdde21fb440a9872bc9760a9d92de8e12b
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
        world.getHome().callForHelp(this, location, world);
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