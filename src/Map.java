import java.util.ArrayList;
import java.util.Random;

public class Map {

    Room room1 = new Room("cave entrance", "There is a glooming light in the corner, must be from" +
            " where you came in.. you¨ll have to take either the way " +
            "to the right or straight down to explore further..");
    Room room2 = new Room("chance room", "here, only the lucky will persevere.");
    Room room3 = new Room("room of the neglected", "The room is filled with a smell of old feet" +
            " and bad quality soap, better get out quick, yuck..");
    Room room4 = new Room("room of distractions", "Someone filled this room with puppies, look at " +
            "them go.. don't you just want to\nstay here forever?");
    Room room5 = new Room("ROOM OF WINNINGS!");
    Room room6 = new Room("hall of bling bling");
    Room room7 = new Room("underwater terrarium", "MLMLLLLL BLBLLLLBLBL mrglmrglmrglmrgl\nWhile getting" +
            " your ears destroyed by weirds sounds, you notice something resembling a cross drawn on the ground.");
    Room room8 = new Room("DontGoUp place", "Welcome to this room traveler, if u go up north " +
            "here, i will kill\na puppy from the room of " +
            "distractions.. i PROMISE I WILL.. so don't do it! ");
    Room room9 = new Room("you-are-probably-lost area");
    Room secretRoom = new Room("Pit of despair", "There is no way out");


    public void makeConnections(Player player){
        room5.setDescription("You did it " + player + ".. when no one believed in you" +
                ", not even yourself, so how'd u get out? like\n" +
                "really please tell me, i need to know for realz man.. congratz");

        room6.setDescription("The room is full of gold and glitters," +
                " but everytime your eyes gaze upon the treasures\n" +
                "your butt starts to itch.. better keep moving before " +
                "you make too much of a mess in your behind mr. " + player + "!");

        room9.setDescription("ey " + player + ", you are probably lost, like so many of us... " +
                "better get moving.");
        room1.setEast(room2);
        room1.setSouth(room4);
        room2.setEast(room3);
        room3.setSouth(room6);
        room4.setSouth(room7);
        room5.setSouth(room8);
        room6.setSouth(room9);
        room7.setEast(room8);
        room8.setEast(room9);
        player.setCurrentRoom(room1);
        room4.getItems().add(new Thing("shovel"));
        room1.getItems().add(new Food());
        room1.getItems().add(new Food("edible lamp", 200));
        room1.getItems().add(new MeleeWeapon("greataxe", 30));
        room2.getItems().add(new Food(-200, "a suspicious looking",
                "banana", "that looks completely safe"));
        room1.getItems().add(new RangedWeapon(35, 5));
        room6.getItems().add(new RangedWeapon("one-hit wonder", 100, 1));
        room3.getItems().add(new Food("green liquid",-20));
        room1.getEnemies().add(new Enemy("gargoyle", 70, new MeleeWeapon("hammer",40 )));
        room7.getEnemies().add(new Enemy(80, new MeleeWeapon(30)));
        room3.getEnemies().add(new Enemy(60, new MeleeWeapon(40)));
        room6.getEnemies().add(new Enemy(20, new MeleeWeapon(50)));

    }

    public void addItems(){
        Random random = new Random();
        int f = random.nextInt(9)+1;
        int g = random.nextInt(4)+1;
        int m = 0;
        do {
            switch (f) {
                case 2:
                    room2.getItems().add(new Thing());
                    room2.getItems().add(new Food());
                    m++;
                case 3:
                    room3.getItems().add(new Thing());
                    room3.getItems().add(new Food());
                    m++;
                case 4:
                    room4.getItems().add(new Thing());
                    room4.getItems().add(new Food());
                    m++;
                case 5:
                    room5.getItems().add(new Thing());
                    m++;
                case 6:
                    room6.getItems().add(new Thing());
                    room6.getItems().add(new Food());
                    m++;
                case 7:
                    room7.getItems().add(new Thing());
                    m++;
                case 8:
                    room8.getItems().add(new Thing());
                    m++;
                case 9:
                    room9.getItems().add(new Thing());
                    m++;
            }
        }
        while (m < g);

    }

}