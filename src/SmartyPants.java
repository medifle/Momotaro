import java.util.List;
import java.util.ArrayList;

public class SmartyPants extends Player {


    // iterate through the world to store all locations in masterKnowledge.
    protected ArrayList<Location> knowAllKnowledge() {
        ArrayList<Location> masterKnowledge = new ArrayList<Location>();
        for (int i = 0; i < world.getLocations().size(); i++) {
            masterKnowledge.add(world.getLocations().get(i));
        }
        return masterKnowledge;
    };


    public SmartyPants(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb, ArrayList<Location> knowledge) {
        super(w, name, location, peaches, health, rgb, knowledge);
        this.knowledge= knowAllKnowledge();
    }

    @Override
    public void play() {
        if (health < 10) {
            getHelp();
            return;
        }

        //if smartypants is not the only player in a given location, interact with them.
        List<Player> players = location.getPlayers();
        if (players.contains(this) && players.size() != 1) {
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).name != "SmartyPants") {
                    this.interact(players.get(i));
                }
            }
        }
    }



    @Override
    public void interact(Player p) {
        int count = 1;
        if (p.name == "PeachHunter") {
            if (p.peaches.size() >= 7) {
                // transfer peaches
                while (count < p.peaches.size()) {
                    this.peaches.add(p.getPeach());
                    count += 1;
                }


                ArrayList<Location> smartyKnowledge = this.getKnowledge();
                ArrayList<Location> otherKnowledge = p.getKnowledge();

                //iterate knowledge to find PeachGrove.
                //if found location doesn't exist in p's knowledge, add & update knowledge.
                for (int i = 0; i < smartyKnowledge.size(); i++) {
                    String smartyDescription = smartyKnowledge.get(i).description;
                    if (smartyDescription == "PeachGrove") {
                        if (!(otherKnowledge.contains(smartyDescription))) {
                            otherKnowledge.add(smartyKnowledge.get(i));
                            p.setKnowledge(otherKnowledge);
                            break;
                        }
                    }
                }
            }


        } else if (p.name == "PitFinder") {
            if (p.peaches.size() >= 6) {
                // transfer peaches
                while (count < p.peaches.size()) {
                    this.peaches.add(p.getPeach());
                    count += 1;
                }

                ArrayList<Location> smartyKnowledge = this.getKnowledge();
                ArrayList<Location> otherKnowledge = p.getKnowledge();

                //iterate knowledge to find PeachPit or BearsDen
                //if found location doesn't exist in p's knowledge, add & update knowledge.
                for (int i = 0; i < smartyKnowledge.size(); i++) {
                    String smartyDescription = smartyKnowledge.get(i).description;
                    if (smartyDescription == "PeachPit" || smartyDescription == "BearsDen") {
                        if (!(otherKnowledge.contains(smartyDescription))) {
                            otherKnowledge.add(smartyKnowledge.get(i));
                            p.setKnowledge(otherKnowledge);
                            break;
                        }
                    }
                }
            }

        } else if (p.name == "PeachThief") {
            while (count < p.peaches.size()) {
                this.peaches.add(p.getPeach());
                count += 1;
            }

        }

    }

}