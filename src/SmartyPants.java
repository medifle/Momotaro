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


                ArrayList<Location> smartyKnowledge = this.getKnowledge();
                ArrayList<Location> otherKnowledge = p.getKnowledge();

                //iterate knowledge to find PeachGrove
                for (int i = 0; i < smartyKnowledge.size(); i++) {
                    if (smartyKnowledge.get(i).description == "PeachGrove") {
                        if (!(otherKnowledge.contains(smartyKnowledge.get(i)))) {
                            otherKnowledge.add(smartyKnowledge.get(i));
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

                //iterate knowledge to find PeachGrove
                for (int i = 0; i < smartyKnowledge.size(); i++) {
                    if (smartyKnowledge.get(i).description == "PeachPit") {
                        if (!(otherKnowledge.contains(smartyKnowledge.get(i)))) {
                            otherKnowledge.add(smartyKnowledge.get(i));
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