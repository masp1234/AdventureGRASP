import java.util.Scanner;

public class UserInterface {
    private Controller controller;
    private Scanner scanner;
    private boolean gameIsRunning = true;

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
        scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        controller.setPlayerName(playerName);
        controller.makeConnections(controller.getPlayer());


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

        boolean gameIsRunning = true;
        while (gameIsRunning) {
            choice();
        }
    }

    public void choice() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Exiting game...");
            gameIsRunning = false;
        } else if (input.equalsIgnoreCase("help")) {
            help();
        } else if (input.equalsIgnoreCase("look")) {
            look();
        } else if (input.equalsIgnoreCase("inventory")
                || input.equalsIgnoreCase("inv")) {
            printPlayerInventory();
        } else if (input.equalsIgnoreCase("health")) {
            PlayerHealthStatus status = controller.checkPlayerHealth();
            if (status == PlayerHealthStatus.VERY_HIGH) {
                System.out.println("You are in great health and ready to fight!");
            } else if (status == PlayerHealthStatus.HIGH) {
                System.out.println("You are slightly wounded but can still fight.");
            } else if (status == PlayerHealthStatus.LOW) {
                System.out.println("You are in poor shape and should probably eat something.");
            } else if (status == PlayerHealthStatus.VERY_LOW) {
                System.out.println("You are severely wounded and need to avoid fighting if you can.");
            }

        } else if (input.contains("eat ")) {
            Eat outcome = controller.eat(input.substring(4));
            if (outcome == Eat.EATEN) {
                // TODO Måske lave en findItemInInventory metode og så skrive det returnede item ud.
                // TODO måske lave en ekstra mulighed i Eat, der kunne hedde EATEN_FROM_INVENTORY og EATEN_FROM_ROOM
                System.out.println("You consumed the " + input.substring(4));
            } else if (outcome == Eat.NOT_EDIBLE) {
                // TODO Måske lave en findItemInInventory metode og så skrive det returnede item ud.
                System.out.println("You can't eat " + input.substring(4));
            } else if (outcome == Eat.NOT_FOUND) {
                System.out.println("There is no such thing as a " + input.substring(4) +
                        " in your inventory, nor in the room.");
            }
        } else if (input.equalsIgnoreCase("dig")) {
            Room newLocation = controller.dig();
            if (newLocation != null) {
                System.out.println("You have entered the " + newLocation + ".");
                getCurrentRoomDescription();
            } else {
                System.out.println("You can't dig here");
            }
            // Bruger trim() så den ikke crasher
        } else if (input.contains("drop ")) {
            System.out.println(controller.dropItem(input.substring(5)));
            // Vigtigt med mellemrum efter "take", da den ellers crasher. Har gjort det på en anden måde oppe i "drop".
        } else if (input.contains("take ")) {
            if (controller.takeItem(input.substring(5)) != null) {
                Item foundItem = controller.takeItem(input.substring(5));
                System.out.println("You picked up a " + foundItem);
            } else {
                System.out.println("There is no such thing as a " + input.substring(5) + " in the room.");
            }
            // TODO måske en enum i stedet, ATTACK_AIR, ATTACK_ENEMY, ATTACK_TARGET, CANT_ATTACK, NO_WEAPON
        } else if (input.contains("attack ")) {
            System.out.println(controller.attack(input.substring(7)));
        } else if (input.contains("equip ")) {
            System.out.println(controller.equipWeapon(input.substring(6)));

        } else if (input.equalsIgnoreCase("go north") || input.equalsIgnoreCase("go south") ||
                input.equalsIgnoreCase("go west") || input.equalsIgnoreCase("go east")) {
            boolean hasMoved = controller.movePlayer(input);
            if (hasMoved && controller.getPlayerCurrentRoom() != controller.getWinningRoom()) {
                System.out.println("You have entered the " + controller.getCurrentRoomName());
                getCurrentRoomDescription();
                System.out.println(controller.checkPlayerStepCounter());
            } else {
                System.out.println("Can't go that way.");
            }
        } else {
            System.out.println("Sorry i don't understand the input.. try again!");
        }
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
        System.out.print("\nSummoning a helping hand...");
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
