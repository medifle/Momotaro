import java.util.ArrayList;

public class EmptyLocation extends Location {
    // Constructs an EmptyLocation
    public EmptyLocation(Position p, String description) {
        super(p, description, new ArrayList<Player>(), new ArrayList<Peach>());
    }

    /**
     * getter for a Peach
     */
    public Peach getPeach() {
        return null;
    }


    /**
     * TODO: different from exit()?
     * remove a player from a room
     */
    public void remove(Player p) {
        peopleAtLocation.remove(p);
    }
}
