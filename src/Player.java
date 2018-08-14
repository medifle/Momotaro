import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Random;

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
    protected Set<Location> knowledge; // locations remembered by player

    /**
     * Creates a player in the game
     *
     * @param w        is the world that the player lives in
     * @param name     is the name of the player
     * @param location is where in the world the player is
     * @param peaches  is a list of peaches the player starts with
     * @param health   is the health of the player (which may or may not be relevant in your game)
     * @param rgb      is the colour of the player
     */
    public Player(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb, Set<Location> knowledge) {
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
     * Getter for a player's knowledge
     */
    public Set<Location> getKnowledge() {
        return knowledge;
    }


    /**
     * Getter for a player's health
     */
    public int getHealth() {
        return health;
    }

    protected void showHealth() {
        System.out.println(getName() + " (HP:" + health + ")");
    }


    /**
     * Getter for a player's peach
     */
    public Peach getPeach() {
        return numberOfPeaches() == 0 ? null : peaches.remove(0);
    }

    protected int numberOfPeaches() {
        return peaches == null ? 0 : peaches.size();
    }

    protected void showPeaches() {
        System.out.println(this.peaches);
    }

    /**
     * This is the logic of the player.
     * It defines what they should do when given a chance to do something
     */
    public void play() {
        isDead();
        if (health < 30) {
            eatPeach();
        }
        if (health < 10) {
            getHelp();
        }
    }


    protected void isDead() {
        if (health <= 0) {
            location.exit(this, false);
            world.getHome().enter(this, false);
            health = 100;
            System.out.println("\uD83D\uDC80" + this + " is dead, reborn from " + world.getHome());
        }
    }


    /**
     * Eat a peach the player has.
     * Restore health by the amount of ripeness if the peach is not bad,
     * Otherwise lose health by the amount of ripeness
     */
    protected void eatPeach() {
        if (numberOfPeaches() > 0) {
            Peach peachToEat = getPeach();
            System.out.println(this + " ate a peach...");
            if (!peachToEat.bad) {
                health += peachToEat.ripeness;
                System.out.println(this + " restore " + peachToEat.ripeness + " health \uD83D\uDE00");
            } else {
                if (peachToEat.ripeness > health) {
                    System.out.println("\uD83D\uDC94Bad peach! " + this + " lost last " + health + " health");
                    health = 0;
                    isDead();
                } else {
                    System.out.println("\uD83D\uDC94Bad peach! " + this + " lost " + peachToEat.ripeness + " health");
                    health -= peachToEat.ripeness;
                    showHealth();
                }
            }
        }
    }


    /**
     * Move a player one step to the direction
     *
     * @param direction the direction the player moves
     * @return true if the move was successful and false otherwise
     */
    public boolean move(int direction) {
        return world.move(this, direction);
    }


    /**
     * Move to the target location
     *
     * @param targetLocation the target location the player wanna move to
     */
    protected void moveToTarget(Location targetLocation) {
        int target_X = targetLocation.getPosition().getX();
        int target_Y = targetLocation.getPosition().getY();
        int player_X = location.getPosition().getX();
        int player_Y = location.getPosition().getY();

        // Get available directions to get to target
        int direction_X = -1;
        int direction_Y = -1;
        if ((target_X - player_X) < 0) {
            direction_X = Directions.UP;
        } else if ((target_X - player_X) > 0) {
            direction_X = Directions.DOWN;
        }
        if ((target_Y - player_Y) < 0) {
            direction_Y = Directions.LEFT;
        } else if ((target_Y - player_Y) > 0) {
            direction_Y = Directions.RIGHT;
        }

        // Choose randomly from available directions
        ArrayList<Integer> directions = new ArrayList<>();
        if (direction_X != -1) {
            directions.add(direction_X);
        }
        if (direction_Y != -1) {
            directions.add(direction_Y);
        }
        // If player has not reached the target location, move to the target
        if (directions.size() != 0) {
            int rnd = new Random().nextInt(directions.size());
            move(directions.get(rnd));
        }
    }

    /**
     * Move to the target location
     *
     * @param target the player who called for help
     */
    protected void moveToTarget(Player target) {
        moveToTarget(target.getLocation());
    }


    /**
     * Go back Home
     */
    protected void backHome() {
        moveToTarget(world.getHome());
    }


    protected void moveRandom() {
        // Cast to Object type for easier remove
        ArrayList<Object> directions = new ArrayList<>();
        directions.add(Directions.UP);
        directions.add(Directions.RIGHT);
        directions.add(Directions.DOWN);
        directions.add(Directions.LEFT);

        // Avoid moving to walls
        if (location.getPosition().getX() == 0) {
            // call remove​(Object o) method in ArrayList
            directions.remove((Object) Directions.UP);
        }
        if (location.getPosition().getX() == world.locations.length - 1) {
            directions.remove((Object) Directions.DOWN);
        }
        if (location.getPosition().getY() == 0) {
            directions.remove((Object) Directions.LEFT);
        }
        if (location.getPosition().getY() == world.locations[0].length - 1) {
            directions.remove((Object) Directions.RIGHT);
        }
        
        int rnd = new Random().nextInt(directions.size());
        // Cast back to int
        move((int)directions.get(rnd));

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
        int oldHealth = health;
        health = h;
        System.out.println(this + " health changed from " + oldHealth + " to " + health);
    }


    /**
     * Setter for a player's knowledge
     */
    public void setKnowledge(Set<Location> knowledge) {
        this.knowledge = knowledge;
    }


    // Receive a peach from another player
    protected boolean receivePeach(Player p) {
        if (p.numberOfPeaches() > 0) {
            boolean result = peaches.add(p.getPeach());
//            System.out.println(getName() + " received a peach from " + p.getName() + ": " + result);
            return result;
        }
        return false;
    }


    // Pick a peach from location
    protected boolean pickPeach() {
        if (location.numberOfPeaches() > 0) {
            boolean result = peaches.add(location.getPeach());
            System.out.println(this + " picked a peach from " + location + ": " + result);
            return result;
        } else {
            System.out.println(this.location + ": not enough peaches!");
            return false;
        }
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