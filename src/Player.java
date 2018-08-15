import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
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
    protected List<Location> knowledge; // locations remembered by player
    protected Location lastLocation; // last location just left
    protected int helpCoolDown; // help cooldown set to 3 by default
    protected int speed; // speed set to 1 by default

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
    public Player(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb, List<Location> knowledge) {
        this.world = w;
        this.name = name;
        this.location = location;
        location.getPlayers().add(this); // Make sure player can be born in Home
        w.addPlayer(this); // add Player to World
        this.peaches = peaches;
        this.health = health;
        this.colour = rgb;
        this.knowledge = knowledge;
        this.helpCoolDown = 0;
        this.speed = 1;
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
    public List<Location> getKnowledge() {
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
        System.out.println(this + "'s peaches: " + this.peaches);
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
        // CoolDown for getHelp() is 2
        if (health < 10) {
            if (helpCoolDown == 0) {
                System.out.println("callForHelp cooldown refreshed");
                getHelp();
                helpCoolDown = 2;
            } else {
                System.out.println("callForHelp cooling, " + helpCoolDown + " turns left");
                helpCoolDown -= 1;
            }
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
                System.out.println("\uD83D\uDE00" + this + " restore " + peachToEat.ripeness + " health");
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
        lastLocation = location;
        return world.move(this, direction);
    }


    /**
     * Move to the target location
     *
     * @param targetLocation the target location the player wanna move to
     */
    protected void moveToTarget(Location targetLocation) {
        moveToTarget(targetLocation, 1);
    }

    protected void moveToTarget(Location targetLocation, int speed) {
        for (int i = 0; i < speed; i++) {
            moveToTarget(targetLocation, null);
        }
    }

    // TODO: recursive method to take a detour to avoid Grove or Pit or Den

    /**
     * Move to the target location
     *
     * @param target the player who called for help
     */
    protected void moveToTarget(Player target) {
        moveToTarget(target, 1);
    }

    protected void moveToTarget(Player target, int speed) {
        for (int i = 0; i < speed; i++) {
            moveToTarget(target.getLocation(), speed);
        }
    }


    // Avoid certain places like Grove, Pit, Den (junior smart level)
    protected void moveToTarget(Location targetLocation, List<Location> dangers) {
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

        // Add available directions for randomly choosing
        ArrayList<Object> directions = new ArrayList<>();
        if (direction_X != -1) {
            directions.add(direction_X);
        }
        if (direction_Y != -1) {
            directions.add(direction_Y);
        }

        // If player has not reached the target location, move to the target
        if (directions.size() != 0) {
            // if available directions contains a danger place, remove the direction
            if (dangers != null) {
                avoidDangers(directions, dangers);
            }

            // Randomly choose a direction
            int rnd = new Random().nextInt(directions.size());
            move((int) directions.get(rnd));
        }
    }

    /**
     * Go back Home
     */
    protected void backHome() {
        moveToTarget(world.getHome());
    }

    protected void backHome(int speed) {
        moveToTarget(world.getHome(), speed);
    }

    protected void backHome(List<Location> dangers) {
        moveToTarget(world.getHome(), dangers);
    }


    protected void avoidDangers(List<Object> directions, List<Location> dangers) {
        if (directions.size() > 1) {
            // Remove randomly
            World.shuffleItem(directions);
            // Use iterator to avoid ConcurrentModificationException(remove item in a foreach loop)
            for (Iterator<Object> iterator = directions.iterator(); iterator.hasNext(); ) {
                Object dir = iterator.next();
                Location newLocation = world.getNewLocation(location, (int) dir);
                if (directions.size() > 1) {
                    for (Location danger : dangers) {
                        // Remove direction to a danger
                        // But at least one direction is kept
                        if (danger == newLocation) {
                            iterator.remove();
                            System.out.println(this + ": Decided not to enter a dangerous place: " + danger);
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }
    }

    protected void moveRandom() {
        moveRandom(null);
    }

    /**
     * Moves randomly(junior smart level)
     */
    protected void moveRandom(List<Location> dangers) {
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

        // If player has at least two available directions
        if (directions.size() > 1) {
            // remove the direction to the lastLocation
            for (Object dir : directions) {
                Location newLocation = world.getNewLocation(location, (int) dir);
                if (newLocation == lastLocation) {
                    directions.remove(dir);
//                    System.out.println(this + ": does not want to go back even in wandering");
                    break;
                }
            }
        }

        // Avoid dangers
        if (dangers != null) {
            avoidDangers(directions, dangers);
        }


        int rnd = new Random().nextInt(directions.size());
        // Cast back to int
        move((int) directions.get(rnd));
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
    public void setKnowledge(List<Location> knowledge) {
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


    protected boolean pickPeach() {
        return pickPeach(true);
    }

    // Pick a peach from location
    protected boolean pickPeach(boolean msg) {
        if (location.numberOfPeaches() > 0) {
            boolean result = peaches.add(location.getPeach());
            if (msg) {
                System.out.println(this + " picked a peach from " + location + ": " + result);
            }
            return result;
        } else {
            if (msg) {
                System.out.println(this.location + ": Not enough peaches!");
            }
            return false;
        }
    }


    // Pick peaches from location
    protected void pickPeaches(int num) {
        if (location.numberOfPeaches() > 0) {
            int oriNum = num;
            while (num > 0) {
                boolean result = pickPeach(false);
                if (!result) {
                    break;
                }
                num -= 1;
            }
            int count = (oriNum - num);
            if (count > 1) {
                System.out.println(this + " picked " + count + " peaches from " + location);
            } else {
                System.out.println(this + " picked " + count + " peach from " + location);
            }
        }
    }

//    protected void pickPeaches(int num, List<Location> locations) {
//        for (Location loc : locations) {
//            if (location.getClass().equals(loc.getClass())) {
//
//            }
//        }
//    }


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