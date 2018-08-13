import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class BearsDen extends Location {

    //create a list of people who gave peaches to the bear.
    List<Player> peopleGavePeaches;

    public BearsDen(Position p, String description, List<Player> people, List<Peach> peaches, List<Player> peopleGavePeaches) {
        super(p, description, people, peaches);
        this.peopleGavePeaches = peopleGavePeaches;
    }

    public void bear(Player p) {

        // 2) player gives 2 peaches
        //check if the person is in the list of people who gave peaches previously
        if (!(peopleGavePeaches.contains(p))) {
            //if person doesn't have two peaches, then automatically deduct 25pts.
            if (p.peaches.size() < 2) {
                p.setHealth(p.getHealth() - 25);
            } else {
                int count = 0;
                while (count < 2) {
                    this.addPeach(p.getPeach());
                    count += 1;
                }
            }
            //remember who gave peaches
            peopleGavePeaches.add(p);
        }

    }


    //Call bear method upon player entering.
    @Override
    public void enter(Player p) {
        enter(p, true);
        bear(p);
    }

    /*
    // Test: BearsDen
    public static void main(String[] args) {
        World w = new World();
        SmartyPants smartyPants = new SmartyPants(w, "SmartyPants", w.getHome(), new ArrayList<Peach>(), 50, RGB.YELLOW, new HashSet<>());
        PeachHunter peachHunter = new PeachHunter(w, "PeachHunter", w.getHome(), new ArrayList<Peach>(), 100, RGB.YELLOW, new HashSet<>());
        w.addPlayer(smartyPants).addPlayer(peachHunter);


        BearsDen testDen = new BearsDen(new Position(1, 1), "This is a bearsDen",
                                        new ArrayList<Player>(), new ArrayList<Peach>(), new ArrayList<Player>());

        w.locations[1][1] = testDen;
        w.move(smartyPants, Directions.DOWN);
        w.move(smartyPants, Directions.RIGHT);
        w.move(peachHunter, Directions.DOWN);
        w.move(peachHunter, Directions.RIGHT);

        for (Player pp : w.getPlayers()) {
            System.out.println(pp.getLocation());
            System.out.println(pp.getLocation().getPlayers());
            System.out.println(pp.getHealth());
        }
    }
    */

} 