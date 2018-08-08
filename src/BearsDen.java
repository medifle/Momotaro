import java.util.List;
import java.util.Scanner;

public class BearsDen extends Location {

    //create a list of people who gave peaches to the bear.
    List<Player> peopleGavePeaches;
    Player[] peopleInDen;

    public BearsDen(Position p, String description, List<Player> people, List<Peach> peaches, List<Player> peopleGavePeaches) {
        super(p, description, people, peaches);
        this.peopleGavePeaches = peopleGavePeaches;


        //convert Arraylist to Array
        Player[] peopleInDen = this.getPlayers().toArray(new Player[this.getPlayers().size()]);

        //iterate through array to
        for (int i = 0; i < peopleInDen.length; i++) {
            Player person = peopleInDen[i];
            // 1) enter people in the room
            this.enter(person);

            // 2) player gives 2 or more peaches


            //check if the person is in the list of people who gave peaches previously
            if (!(peopleGavePeaches.contains(person))) {
                //if person doesn't have two peaches, then automatically deduct 25pts.
                if (person.peaches.size() < 2) {
                    person.setHealth(person.getHealth() - 25);
                } else {

                    //ask for user input for the # of peaches to give. If less than 2, deduct.
                    Scanner sc = new Scanner(System.in);
                    int givePeach = sc.nextInt();
                    int count = 0;

                    if (givePeach < 2) {
                        person.setHealth(person.getHealth() - 25);
                    } else {
                        while (count < givePeach) {
                            this.addPeach(person.getPeach());
                            count += 1;
                        }
                    }
                    //remember who gave peaches
                    peopleGavePeaches.add(person);

                }
            }

            // last) exit people from the room
            this.exit(person);
        }

    }


} 