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


    // Test: BearsDen
    public static void main(String[] args) {
        World w = new World();

        //add some players to the world
        SmartyPants smartyPants = new SmartyPants(w, "SmartyPants", w.getHome(), new ArrayList<Peach>(), 150, RGB.YELLOW, new ArrayList<>() {
        });
        PeachHunter peachHunter = new PeachHunter(w, "PeachHunter", w.getHome(), new ArrayList<Peach>(), 100, RGB.YELLOW, new ArrayList<>());
        PitFinder pitFinder = new PitFinder(w, "PitFinder", w.getHome(), new ArrayList<Peach>(), 200, RGB.BLUE, new ArrayList<>());
        w.addPlayer(smartyPants).addPlayer(peachHunter).addPlayer(pitFinder);


        //add some locations and peaches to the world
        PeachPit testPit = new PeachPit(new Position (0,1), new ArrayList<Player>(), new ArrayList<Peach>());
        for (int i = 0; i < 10; i++) {
            testPit.addPeach(new Peach(10));
        }

        PeachGrove testGrove = new PeachGrove(new Position (1,0), new ArrayList<Player>(), new ArrayList<Peach>());
        for (int i = 0; i < 10; i++) {
            testGrove.addPeach(new Peach(10));
        }


        BearsDen testDen = new BearsDen(new Position(1, 1), "This is a bearsDen",
                                        new ArrayList<Player>(), new ArrayList<Peach>(), new ArrayList<Player>());

        BearsDen testDen2 = new BearsDen(new Position(1, 1), "This is a bearsDen",
                new ArrayList<Player>(), new ArrayList<Peach>(), new ArrayList<Player>());

        for (int i = 0; i < 30; i++) {
            testDen.addPeach(new Peach(30));
        }

        //smartyPants will be positioned in some location
        //peachHunter and pitFinder will first move to the Den, take the damage
        w.locations[1][1] = testDen;
        w.move(smartyPants, Directions.DOWN);
        w.move(peachHunter, Directions.DOWN);
        w.move(peachHunter, Directions.RIGHT);
        w.move(pitFinder, Directions.DOWN);
        w.move(pitFinder, Directions.RIGHT);


        //peachHunter and pitFinder will pick up some peaches
        int count = 0;
        while (count < 10) {
            peachHunter.pickPeach();
            pitFinder.pickPeach();
            count += 1;
        }

        //peachHunter and pitFinder will meet smartyPants
        w.move(peachHunter, Directions.LEFT);
        w.move(pitFinder, Directions.LEFT);

        for (Player pp : w.getPlayers()) {
            System.out.println("current location: " + pp.getLocation() + "current Players: " + pp.getLocation().getPlayers());
            System.out.println(pp.getName() + " with " +pp.getHealth() + " health and " + pp.numberOfPeaches() + " peaches");
        }

        //smartyPants will interact
        smartyPants.play();

        //peachHunter and pitFinder will move back to the BearsDen, but not take damage this time,
        //because they have more than 2 peaches that they can give the bear.
        w.move(peachHunter, Directions.RIGHT);
        w.move(pitFinder, Directions.RIGHT);

        //demonstrates what happens when a player doesn't have enough peaches to interact with SmartyPants
        w.move(pitFinder, Directions.LEFT);
        smartyPants.play();
    }


} 