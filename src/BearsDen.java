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
        if (!(peopleGavePeaches.contains(p)) && !(p instanceof Helper)) {
            //if person doesn't have two peaches, then automatically deduct 25pts.
            if (p.peaches.size() < 2) {
                System.out.println("Player did not give 2 peaches. The bear will attack!");
                p.setHealth(p.getHealth() - 25);
            } else {
                int count = 0;
                while (count < 2) {
                    this.addPeach(p.getPeach());
                    count += 1;
                    //remember who gave peaches
                    peopleGavePeaches.add(p);
                }
                System.out.println(p.toString() + " gave bear 2 peaches. Player is safe from now on");
                System.out.println();
            }
        } else {
            System.out.println("Player gave bear 2 peaches before. The bear will leave player alone");
        }


    }


    //Call bear method upon player entering.
    @Override
    public void enter(Player p) {
        enter(p, true);
        bear(p);
    }
} 