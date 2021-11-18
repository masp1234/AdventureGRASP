import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




    private String input;

    public Boolean getGameIsRunning() {
        return this.gameIsRunning;
    }

    public void setGameIsRunning(Boolean gameIsRunning) {
        this.gameIsRunning = gameIsRunning;
    }


    void exitGame() {
        setGameIsRunning(false);
    }

    public void run() {
        gameMap.makeConnections();
        gameMap.addItems();
        player.setCurrentRoom(gameMap.room1);





    public void choice() {
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();

        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Exiting game");
            exitGame();
        } else if (input.equalsIgnoreCase("help")) {
            System.out.print("\nSummoning a helping hand...");
            help();
        } else if (input.equalsIgnoreCase("look")) {
            look();
        } else if (input.equalsIgnoreCase("inventory")
                || input.equalsIgnoreCase("inv")) {
            printInventory();
        } else if (input.equalsIgnoreCase("health")) {
            System.out.println(player.checkHealth());
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
            if (player.getCurrentWeapon() == null) {
                System.out.println("You don't have a weapon equipped");
            } else if (!player.getCurrentWeapon().usesLeft()) {
                System.out.println("Your " + player.getCurrentWeapon() + " has no ammunition left, " +
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
            move(input);
            checkStepCounter();
        }
    }

    public void move(String direction) {

        if (direction.equalsIgnoreCase("go north") && player.getCurrentRoom().getNorth() != null) {
            player.setCurrentRoom(player.getCurrentRoom().getNorth());
            System.out.println("Going north!");
            System.out.println("You have entered the " + player.getCurrentRoom());
            getCurrentRoomDescription();
            player.incrementStepCounter();

        } else if (direction.equalsIgnoreCase("go south") && player.getCurrentRoom().getSouth() != null) {
            player.setCurrentRoom(player.getCurrentRoom().getSouth());
            System.out.println("Going south!");
            System.out.println("You have entered the " + player.getCurrentRoom());
            getCurrentRoomDescription();
            player.incrementStepCounter();


        } else if (direction.equalsIgnoreCase("go west") && player.getCurrentRoom().getWest() != null) {
            player.setCurrentRoom(player.getCurrentRoom().getWest());
            System.out.println("Going west!");
            System.out.println("You have entered the " + player.getCurrentRoom());
            getCurrentRoomDescription();
            player.incrementStepCounter();


        } else if (direction.equalsIgnoreCase("go east") && player.getCurrentRoom().getEast() != null) {
            player.setCurrentRoom(player.getCurrentRoom().getEast());
            System.out.println("Going east!");
            System.out.println("You have entered the " + player.getCurrentRoom());
            getCurrentRoomDescription();
            player.incrementStepCounter();

        } else {
            if (!direction.equalsIgnoreCase("exit"))
                System.out.println("Can't go that way");
        }
    }
