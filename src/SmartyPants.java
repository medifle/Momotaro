import java.util.List;
import java.util.ArrayList;

public class SmartyPants extends Player {


    public SmartyPants(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb, ArrayList<Location> knowledge) {
        super(w, name, location, peaches, health, rgb, knowledge);


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


                Location[] SmartyKnowledge = ArrayList.toArray(new Location[this.getKnowledge().size]);
                Location[] otherKnowledge = ArrayList.toArray(new Location[p.getKnowledge().size]);
                //iterate knowledge to find PeachGrove
                for (int i = 0; i < SmartyKnowledge.length; i++) {
                    if (SmartyKnowledge[i].description == "PeachGrove") {
                        if (!(otherKnowledge.contains(SmartyKnowledge[i]))) {
                            otherKnowledge.add(SmartyKnowledge[i]);
                        }
                    }
                }
            }


        } else if (p.name == "PitFinder") {

        } else if (p.name == "PeachThief") {
            while (count < p.peaches.size()) {
                this.peaches.add(p.getPeach());
                count += 1;
            }

        }
    }
}