import java.util.*;

public class SmartyPants extends Player {


    protected HashMap<Integer, HashSet<Location>> smartyKnowledge;

    // HashMap of HashSets of locations

    protected HashMap<Integer, HashSet<Location>> knowAllKnowledge() {
        HashMap<Integer, HashSet<Location>> master = new HashMap<>();
        HashSet<Location> groves = new HashSet<>();
        HashSet<Location> pits = new HashSet<>();
        HashSet<Location> dens = new HashSet<>();
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

        master.put(1, groves);
        master.put(2, pits);
        master.put(3, dens);

        return master;
    };



    public SmartyPants(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb, HashSet<Location> knowledge ) {
        super(w, name, location, peaches, health, rgb, knowledge);

        smartyKnowledge= knowAllKnowledge();
    }




    //get the correct hashset based on need.
    public HashSet<Location> getHash(String location) {
        if (location == "PeachGrove") {
            return knowAllKnowledge().get(1);
        } else if (location == "PeachPit") {
            return knowAllKnowledge().get(2);
        } else if (location == "BearsDen") {
            return knowAllKnowledge().get(3);
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
                if (players.get(i).name != "SmartyPants") {
                    this.interact(players.get(i));
                }
            }
        }else{
            moveSmarty();
        }


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
        move(directions.get(rnd));
    }




    @Override
    public void interact(Player p) {
        int count = 0;
        if (p.name == "PeachHunter") {
            if (p.peaches.size() >= 7) {
                // transfer peaches
                while (count < 7) {
                    this.peaches.add(p.getPeach());
                    count += 1;
                }



                HashSet<Location> smartyKnowledge = this.getHash("PeachGrove");
                Set<Location> otherKnowledge = p.getKnowledge();

                //iterate peachgrove HashSet and exchange info.
                for (Location location: smartyKnowledge) {
                    if (!(otherKnowledge.contains(location))) {
                        otherKnowledge.add(location);
                        p.setKnowledge(otherKnowledge);
                        System.out.println("PeachGrove location exchanged with PeachHunter!");
                        break;
                    }
                }
            }else{
                System.out.println("No transaction made! PeachHunter did not have enough peaches");
            }


        } else if (p.name == "PitFinder") {
            if (p.peaches.size() >= 6) {
                // transfer peaches
                while (count < 6) {
                    this.peaches.add(p.getPeach());
                    count += 1;
                }


                HashSet<Location> smartyKnowledge1 = this.getHash("PeachPit");
                HashSet<Location> smartyKnowledge2 = this.getHash("BearsDen");
                Set<Location> otherKnowledge = p.getKnowledge();

                //iterate PeachPit HashSet and exchange info.
                for (Location location: smartyKnowledge1) {
                    if (!(otherKnowledge.contains(location))) {
                        otherKnowledge.add(location);
                        p.setKnowledge(otherKnowledge);
                        break;
                    }
                }

                //if all pits are revealed, reveal bearsden
                for (Location location: smartyKnowledge2) {
                    if (!(otherKnowledge.contains(location))) {
                        otherKnowledge.add(location);
                        p.setKnowledge(otherKnowledge);
                        break;
                    }
                }
            }else{
                System.out.println("No transaction made! PitFinder did not have enough peaches");
            }

        } else if (p.name == "PeachThief") {
            while (count + 1 < p.peaches.size()) {
                this.peaches.add(p.getPeach());
                count += 1;
            }

        }

    }

}