import java.util.Scanner;

public class UserInterface {
    private Controller controller;

    public UserInterface(Controller controller) {
        this.controller = controller;

    }

    public void run() {
        System.out.println("Welcome young traveler, to the cave of the unforeseen.. If you dare enter, " +
                "sign the waiver of \"prolly ok\"\n");
        System.out.print("""
                |The waiver of prolly ok                                            |
                |bla bla, something about having being trapped forever jada jada..  |
                |bla bla.. giving away your soul to the kingdom of mages jada jada..|
                |SIGN HERE:\s """);
        Scanner scanner = new Scanner(System.in);
        controller.setPlayerName(scanner.nextLine());

        gameMap.room5.setDescription("You did it " + player + ".. when no one believed in you" +
                ", not even yourself, so how'd u get out? like\n" +
                "really please tell me, i need to know for realz man.. congratz");

        gameMap.room6.setDescription("The room is full of gold and glitters," +
                " but everytime your eyes gaze upon the treasures\n" +
                "your butt starts to itch.. better keep moving before " +
                "you make too much of a mess in your behind mr. " + player + "!");

        gameMap.room9.setDescription("ey " + player + ", you are probably lost, like so many of us... " +
                "better get moving.");

        System.out.println("\nMuahaha, you now belong to the cave.. Find your way out and your soul shall be saved.." +
                "\nI will grant you.. hmm.." +
                "100 steps in my cave before exhaustion gets the best of you. - Waiiit..\"looking at the waiver\".. " +
                "\nyour name is " + controller.getPlayer() + "?!.. for such " +
                "a puny name i shall only provide you with 30 steps.. best of luck, you'll need it!");

        System.out.println("""
                 - Luckily your journey has been blessed by Merlin the great wizard and he wants you to succeed
                he has bestowed upon you the gift of choice, and therefore you have the following choices:\s""");
        options();
        System.out.println("Best make haste, " + controller.getPlayer() + ", you don't have much time!");
    }

    private void options() {
        System.out.println("""
                ________________________
                Directions
                Go north: "go north"
                Go south: "go south"
                Go west: "go west"
                Go east: "go east"
                ________________________
                Interactions
                Take item: "take" followed by item
                Equip a weapon: "Equip" followed by weapon name
                Drop item/weapon: "drop" followed by its name
                See current inventory: "Inventory"
                Attack enemy: "attack" followed by enemy name
                See your current condition: "health"
                ________________________    
                Look around: "Look"
                ________________________
                Summon help: "Help"
                ________________________
                Exit the game: "Exit"
                """);
    }
    void look() {
        System.out.println("You're looking around in the room...");
        getCurrentRoomDescription();
    }



    void help() {
        System.out.println("\n\n( ಠ ͜ʖ ಠ ) Hello outcast it is I  --  Merlin, the great wizard. " +
                "You've asked for advice " + "on your journey -- and so, i shall provide!." +
                "\n\n( ಠ ͜ʖ ಠ )⊃══⛧⌒｡ ~ALAKAZAM~");
        options();
        System.out.println("Make your choice to proceed, " + controller.getPlayer() + "!");
    }

    public void printPlayerInventory() {
        int itemNumber = 1;
        if (controller.getPlayerInventorySize() != 0) {
            for (Item item : controller.getPlayerInventory()) {
                System.out.println(itemNumber + ". " + item);
                itemNumber++;
            }
            if (controller.getPlayerCurrentWeapon() != null) {
                System.out.println("\nCurrently equipped weapon:\n" + controller.getPlayerCurrentWeapon()
                        + "\nDamage: " + controller.getPlayerCurrentWeaponDamage());
            }
        } else {
            System.out.println("There is nothing in your inventory.");
        }
    }

    public void getCurrentRoomDescription() {
        int amountOfEnemies = controller.getPlayerCurrentRoomEnemiesSize();
        int amountOfItems = controller.getPlayerCurrentRoomItemsSize();
        String roomDescription = controller.getPlayerCurrentRoomDescription();
        Iterable<Enemy> enemyList = controller.getPLayerCurrentRoomEnemiesList();
        Iterable<Item> itemList = controller.getPlayerCurrentRoomItemList();

        if (amountOfItems < 1 && amountOfEnemies < 1) {
            System.out.println(roomDescription);
            System.out.println("There's nothing of interest in the room");
        } else if (amountOfItems > 0 && amountOfEnemies < 1) {
            System.out.print(roomDescription + "\nLooking around the room you see ");
            int counter = 0;
            for (Item item : itemList) {
                if (amountOfItems == 1)
                    System.out.println(item.getFullName() + ".");
                else if (amountOfItems == counter + 1) {
                    System.out.println("and " + item.getFullName() + ".");
                } else {
                    System.out.print(item.getFullName() + ", ");
                }
                counter++;
            }
        } else if (amountOfEnemies > 0 && amountOfItems < 1) {
            System.out.println(roomDescription);
            for (Enemy enemy : enemyList) {
                System.out.println("You see a " + enemy.getFullName());
            }
        } else if (amountOfItems > 0 && amountOfEnemies > 0) {
            System.out.print(roomDescription + "\nLooking around the room you see ");
            int counter = 0;
            for (Item item : itemList) {
                if (amountOfItems == 1)
                    System.out.println(item.getFullName() + ".");
                else if (amountOfItems == counter + 1) {
                    System.out.println("and " + item.getFullName() + ".");
                } else {
                    System.out.print(item.getFullName() + ", ");
                }
                counter++;
            }
            for (Enemy enemy : enemyList) {
                System.out.println("You also see a " + enemy.getFullName());
            }

        }


    }
}
