import java.util.*;

public class SmartyPants extends Player {


    protected List<List<Location>> smartyKnowledge;

    // HashMap of HashSets of locations

    protected List<List<Location>> knowAllKnowledge() {
        List<List<Location>> master = new ArrayList<>();
        List<Location> groves = new ArrayList<>();
        List<Location> pits = new ArrayList<>();
        List<Location> dens = new ArrayList<>();
        //iterate world to get all objects
        for (int i = 0; i < world.getLocations().size(); i++) {
            Location currentLocation = world.getLocations().get(i);
            if (currentLocation instanceof PeachGrove) {
                groves.add(currentLocation);
            } else if (currentLocation instanceof PeachPit) {
                pits.add(currentLocation);
            } else if (currentLocation instanceof BearsDen) {
                dens.add(currentLocation);
            }
        }

        master.add(groves);
        master.add(pits);
        master.add(dens);

        return master;
    };



    public SmartyPants(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb, List<Location> knowledge ) {
        super(w, name, location, peaches, health, rgb, knowledge);

        smartyKnowledge= knowAllKnowledge();
    }




    //get the correct hashset based on need.
    public List<Location> getSubList(String location) {
        if (location == "PeachGrove") {
            return knowAllKnowledge().get(0);
        } else if (location == "PeachPit") {
            return knowAllKnowledge().get(1);
        } else if (location == "BearsDen") {
            return knowAllKnowledge().get(2);
        } else {
            return null;
        }
    }

    @Override
    public void play() {
        if (health < 10) {
            getHelp();
            return;
        }

        //if smartypants is not the only player in a given location, interact with them. Else, move somewhere
        List<Player> players = location.getPlayers();
        if (players.contains(this) && players.size() != 1) {
            for (int i = 0; i < players.size(); i++) {
                if (!(players.get(i) instanceof SmartyPants)) {
                    this.interact(players.get(i));
                }
            }
        }
        //moveSmarty();



    }

    private void moveSmarty(){
        //Store 4 directions in an arrayList, and
        //randomly choose one of four directions

        ArrayList<Integer> directions = new ArrayList<>();
        directions.add(0);
        directions.add(1);
        directions.add(2);
        directions.add(3);

        int rnd = new Random().nextInt(directions.size());
        System.out.println("SmartyPants will now move randomly");
        move(directions.get(rnd));
    }




    @Override
    public void interact(Player p) {
        System.out.println(this.toString() + " will now interact with " + p.toString());
        System.out.println();
        int count = 0;
        if (p instanceof PeachHunter) {
            if (p.peaches.size() >= 7) {
                // transfer peaches
                while (count < 7) {
                    this.peaches.add(p.getPeach());
                    count += 1;
                }

                System.out.println(p.toString() + " now has " + p.numberOfPeaches() + " peaches.");
                System.out.println(this.toString() + " now has " + this.numberOfPeaches() + " peaches.");
                System.out.println();

                List<Location> smartyKnowledge = this.getSubList("PeachGrove");
                List<Location> otherKnowledge = p.getKnowledge();

                if (smartyKnowledge.isEmpty()) {
                    System.out.println("SmartyPants does not know any PeachGroves, but won't give back peaches");
                    System.out.println();
                }

                //iterate peachgrove HashSet and exchange info.
                for (Location location: smartyKnowledge) {
                    if (!(otherKnowledge.contains(location))) {
                        otherKnowledge.add(location);
                        p.setKnowledge(otherKnowledge);
                        System.out.println(location.toString() + " location exchanged with PeachHunter!");
                        System.out.println();
                        break;
                    } else {
                        System.out.println("PeachHunter already knows all the locations that SmartyPants knows");
                        System.out.println("But the greedy SmartyPants will not give back the peaches");
                        System.out.println();
                    }
                }
            }else{
                System.out.println("No transaction made! PeachHunter did not have enough peaches");
                System.out.println();
            }


        } else if (p instanceof PitFinder) {
            if (p.peaches.size() >= 6) {
                // transfer peaches
                while (count < 6) {
                    this.peaches.add(p.getPeach());
                    count += 1;
                }

                System.out.println(p.toString() + " now has " + p.numberOfPeaches() + " peaches.");
                System.out.println(this.toString() + " now has " + this.numberOfPeaches() + " peaches.");
                System.out.println();


                //Merge PeachPit HashSet and BearsDen HashSet
                List<Location> smartyKnowledge = this.getSubList("PeachPit");
                List<Location> smartyKnowledge2 = this.getSubList("BearsDen");
                boolean mergeKnowledge = smartyKnowledge.addAll(smartyKnowledge2);
                List<Location> otherKnowledge = p.getKnowledge();


                if (smartyKnowledge.isEmpty()) {
                    System.out.println("SmartyPants does not know any PeachPit or BearsDen, but won't give back peaches");
                    System.out.println();
                }

                //iterate merged HashSet and exchange info.
                for (Location location: smartyKnowledge) {
                    if (!(otherKnowledge.contains(location))) {
                        otherKnowledge.add(location);
                        p.setKnowledge(otherKnowledge);
                        System.out.println(location.toString() + " location exchanged with PitFinder!");
                        System.out.println();
                        break;
                    } else {
                        System.out.println("PitFinder already knows all the locations that SmartyPants knows");
                        System.out.println("But the greedy SmartyPants will not give back the peaches");
                        System.out.println();
                    }
                }

            }else{
                System.out.println("No transaction made! PitFinder did not have enough peaches");
                System.out.println();
            }

        } else if (p.name == "PeachThief") {
            while (count + 1 < p.peaches.size()) {
                this.peaches.add(p.getPeach());
                count += 1;
            }

        }

    }

}