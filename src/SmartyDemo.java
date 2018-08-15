import java.util.ArrayList;

public class SmartyDemo {
    public static void main(String[] args) {

        System.out.println("BEARSDEN & SMARTY PANTS DEMO");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

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


        BearsDen testDen = new BearsDen(new Position(1, 1), "DemoBearsDen1",
                new ArrayList<Player>(), new ArrayList<Peach>(), new ArrayList<Player>());

        BearsDen testDen2 = new BearsDen(new Position(1, 1), "DemoBearsDen2",
                new ArrayList<Player>(), new ArrayList<Peach>(), new ArrayList<Player>());

        for (int i = 0; i < 30; i++) {
            testDen.addPeach(new Peach(30));
        }





        System.out.println("PLAYERS WILL MOVE NOW");
        System.out.println();
        System.out.println();
        System.out.println("PitFinder & PeachHunter WILL MOVE TO BEARSDEN");
        System.out.println();
        System.out.println();
        System.out.println("SMARTYPANTS WILL MOVE TO [1,0]");
        System.out.println();
        System.out.println();
        System.out.println();

        //smartyPants will be positioned in some location
        //peachHunter and pitFinder will first move to the Den, take the damage
        w.locations[1][1] = testDen;
        w.move(smartyPants, Directions.DOWN);
        w.move(peachHunter, Directions.DOWN);
        w.move(peachHunter, Directions.RIGHT);
        w.move(pitFinder, Directions.DOWN);
        w.move(pitFinder, Directions.RIGHT);


        //peachHunter and pitFinder will pick up some peaches

        System.out.println("PLAYERS WILL PICK PEACHES");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        int count = 0;
        while (count < 10) {
            peachHunter.pickPeach();
            pitFinder.pickPeach();
            count += 1;
        }

        //peachHunter and pitFinder will meet smartyPants

        System.out.println("PLAYERS WILL MEET SMARTYPANTS NOW");
        System.out.println();
        System.out.println();
        System.out.println();


        w.move(peachHunter, Directions.LEFT);
        w.move(pitFinder, Directions.LEFT);



        //for (Player pp : w.getPlayers()) {
        //   System.out.println("current location: " + pp.getLocation() + "current Players: " + pp.getLocation().getPlayers());
        //    System.out.println(pp.getName() + " with " +pp.getHealth() + " health and " + pp.numberOfPeaches() + " peaches");
        //}

        //smartyPants will interact
        smartyPants.play();

        //peachHunter and pitFinder will move back to the BearsDen, but not take damage this time,
        //because they have more than 2 peaches that they can give the bear.
        w.move(peachHunter, Directions.RIGHT);
        w.move(pitFinder, Directions.RIGHT);

        //demonstrates what happens when a player doesn't have enough peaches to interact with SmartyPants
        w.move(pitFinder, Directions.LEFT);
        smartyPants.play();


        w.move(pitFinder, Directions.RIGHT);

    }
}
