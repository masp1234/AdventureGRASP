import java.util.Scanner;

public class UserInterface {
    private Controller controller;
    private Scanner scanner;

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
    public void choice() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Exiting game...");
            exitGame();
        } else if (input.equalsIgnoreCase("help")) {
            help();
        } else if (input.equalsIgnoreCase("look")) {
            look();
        } else if (input.equalsIgnoreCase("inventory")
                || input.equalsIgnoreCase("inv")) {
            printPlayerInventory();
        } else if (input.equalsIgnoreCase("health")) {
            System.out.println(controller.checkPlayerHealth());
        } else if (input.contains("eat ")) {
            if (player.getCurrentRoom().findItem(input.substring(4)) != null
                    && player.getCurrentRoom().findItem(input.substring(4)) instanceof Consumable) {
                player.eatConsumable((Food) player.getCurrentRoom().findItem(input.substring(4)));
                player.getCurrentRoom().getItems().remove(player.getCurrentRoom().findItem(input.substring(4)));
                System.out.println("You consumed " + input.substring(4));

            } else if (player.findItem(input.substring(4)) != null
                    && player.findItem(input.substring(4)) instanceof Consumable) {
                System.out.println(player.getHealth());
                player.eatConsumable((Consumable) player.findItem((input.substring(4))));
                player.getInventory().remove(player.findItem(input.substring(4)));
                System.out.println("You ate " + input.substring(4));
                System.out.println(player.getHealth());
            } else if (player.getCurrentRoom().findItem(input.substring(4)) != null
                    && player.getCurrentRoom().findItem(input.substring(4)) instanceof Consumable != true
                    || player.findItem(input.substring(4)) != null &&
                    player.findItem(input.substring(4)) instanceof Consumable != true) {
                System.out.println("You can't eat " + input.substring(4) + ".");
            } else {
                System.out.println("You don't have a " + input.substring(4) + " in your inventory.");
            }

        } else if (input.equalsIgnoreCase("dig")
                && player.findItem("shovel") != null
                && player.getCurrentRoom() == gameMap.room7) {
            player.setCurrentRoom(gameMap.secretRoom);
            System.out.println("You have entered the " + player.getCurrentRoom());
            getCurrentRoomDescription();
            // Bruger trim() så den ikke crasher
        } else if (input.contains("drop")) {
            if (player.findItem(input.substring(4).trim()) != null) {
                player.dropItem(player.findItem(input.substring(4).trim()));
                System.out.println("You dropped a " + input.substring(4).trim());
            } else {
                System.out.println("There is no such thing as a " + input.substring(5) + " in your inventory.");
            }
            // Vigtigt med mellemrum efter "take", da den ellers crasher. Har gjort det på en anden måde oppe i "drop".
        } else if (input.contains("take ")) {
            if (player.getCurrentRoom().findItem(input.substring(5)) != null) {
                Item currentItem = (player.getCurrentRoom().findItem(input.substring(5)));
                player.takeItem(currentItem);
                System.out.println("You picked up a " + currentItem);
            } else {
                System.out.println("There is no such thing as a " + input.substring(5) + " in the room.");
            }
        } else if (input.contains("attack ")) {
            if (controller.getPlayerHasWeapon()) {
                System.out.println("You don't have a weapon equipped");
            } else if (!player.getCurrentWeapon().usesLeft()) {
                System.out.println("Your " +  controller.getPlayerCurrentWeapon() + " has no ammunition left, " +
                        "you will have to use something else");
            } else if (player.getCurrentRoom().getEnemies().size() < 1
                    && player.getCurrentRoom().findEnemy(input.substring(7).trim()) == null) {
                System.out.println("You attack the air in confusion");
            } else if (player.getCurrentRoom().findEnemy(input.substring(7)) != null
                    && player.getCurrentWeapon() != null) {

                Enemy currentEnemy = player.getCurrentRoom().findEnemy(input.substring(7));
                player.attack(currentEnemy);
                System.out.println("You hit the " + currentEnemy + " for "
                        + player.getCurrentWeapon().getDamage());
                if (currentEnemy.getHealth() < 1) {
                    Weapon droppedWeapon = currentEnemy.getCurrentWeapon();
                    player.getCurrentRoomItems().add(droppedWeapon);
                    player.getCurrentRoom().getEnemies().remove(currentEnemy);
                    System.out.println("The " + input.substring(7) + " has died!");
                } else if (currentEnemy.getHealth() > 0) {
                    currentEnemy.attack(player);
                    System.out.println("The " + currentEnemy + " hit you for "
                            + currentEnemy.getCurrentWeapon().getDamage());
                }
            }
        } else if (input.contains("equip ")) {
            if (player.getCurrentRoom().findItem(input.substring(6)) != null
                    && player.getCurrentRoom().findItem(input.substring(6)) instanceof Weapon) {
                Weapon weapon = (Weapon) player.getCurrentRoom().findItem(input.substring(6));
                player.equipWeapon(weapon);
                System.out.println("You picked up and equipped the " + weapon);
            } else if (player.findItem(input.substring(6)) != null
                    && player.findItem(input.substring(6)) instanceof Weapon) {
                Weapon weapon = (Weapon) player.findItem(input.substring(6));
                player.equipWeapon(weapon);
                System.out.println("You have equipped " + weapon + " from your inventory");
            } else if (player.findItem(input.substring(6)) != null
                    && !(player.findItem(input.substring(6)) instanceof Weapon)) {
                System.out.println("You can't equip that");
            } else if (player.getCurrentWeapon() != null && player.getCurrentWeapon().getName().equalsIgnoreCase(input.substring(6))) {
                Weapon weapon = player.getCurrentWeapon();
                System.out.println("You already have the " + weapon + " equipped");
            } else if (player.getCurrentRoom().findItem(input.substring(6)) != null
                    && !(player.getCurrentRoom().findItem(input.substring(6)) instanceof Weapon)) {
                System.out.println("The " + input.substring(6) + " is not very effective to use as a weapon");
            } else {
                System.out.println("There is no such thing as a " + input.substring(6).trim() + " to equip");
            }
        } else if (!input.equalsIgnoreCase("look") && !input.equalsIgnoreCase("exit")
                && !input.equalsIgnoreCase("help") && !input.equalsIgnoreCase("go east")
                && !input.equalsIgnoreCase("go north") &&
                !input.equalsIgnoreCase("go west") && !input.equalsIgnoreCase("go south")) {
            System.out.println("Sorry i don't understand the input.. try again!");
        } else {
            String direction = input;
                if (input.equalsIgnoreCase("go north") && controller.playerCurrentRoomHasNorth()) {
                    Room destination = controller.movePlayer(direction);
                    System.out.println("Going north!");
                    System.out.println("You have entered the " + destination);
                    getCurrentRoomDescription();


                } else if (input.equalsIgnoreCase("go south") && player.getCurrentRoom().getSouth() != null) {
                    player.setCurrentRoom(player.getCurrentRoom().getSouth());
                    System.out.println("Going south!");
                    System.out.println("You have entered the " + player.getCurrentRoom());
                    getCurrentRoomDescription();


                } else if (input.equalsIgnoreCase("go west") && player.getCurrentRoom().getWest() != null) {
                    player.setCurrentRoom(player.getCurrentRoom().getWest());
                    System.out.println("Going west!");
                    System.out.println("You have entered the " + player.getCurrentRoom());
                    getCurrentRoomDescription();


                } else if (input.equalsIgnoreCase("go east") && player.getCurrentRoom().getEast() != null) {
                    player.setCurrentRoom(player.getCurrentRoom().getEast());
                    System.out.println("Going east!");
                    System.out.println("You have entered the " + player.getCurrentRoom());
                    getCurrentRoomDescription();

                } else {
                    if (!input.equalsIgnoreCase("exit"))
                        System.out.println("Can't go that way");
                }
            }
            controller.movePlayer(input);

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