import java.util.ArrayList;
import java.util.List;

public class Player {
    private int stepCounter;
    private final int MAX_STEPS = 30;
    private Room currentRoom;
    private String playerName;
    private int health = 100;
    private ArrayList<Item> inventory = new ArrayList<>();
    private Weapon currentWeapon;

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (getHealth() > 100) {
            this.health = 100;
        }
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public int inventorySize() {
        return inventory.size();
    }

    public String getCurrentRoomDescription() {
        return currentRoom.getDescription();
    }

    public ArrayList<Item> getCurrentRoomItems() {
        return getCurrentRoom().getItems();
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public String toString() {
        return this.playerName;
    }


    public int getStepCounter() {
        return this.stepCounter;
    }

    public void incrementStepCounter() {
        stepCounter++;
    }

    public int getMAX_STEPS() {
        return this.MAX_STEPS;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setCurrentWeapon(Weapon weapon) {
        this.currentWeapon = weapon;
    }


    public String attack(String input) {
        String outcome = null;
        if (currentWeapon == null) {
            outcome = "You don't have a weapon equipped";
        } else if (currentWeapon.usesLeft()) {
            outcome = "Your " + currentWeapon + " has no ammunition left, " +
                    "you will have to use something else";
        } else if (currentRoom.getEnemies().size() < 1) {
            outcome = "You attack the air in confusion";
        } else if (currentRoom.findEnemy(input) != null) {
            Enemy currentEnemy = currentRoom.findEnemy(input);
            int damageDone = currentEnemy.getHit(currentWeapon.getDamage());
            outcome = "You hit the " + currentEnemy + " for " + damageDone + ".";
            if (currentEnemy.getHealth() < 1) {
                Weapon droppedWeapon = currentEnemy.getCurrentWeapon();
                currentRoom.addItem(droppedWeapon);
                currentRoom.removeEnemy(currentEnemy);
                outcome.concat("\nThe " + currentEnemy + " has died!");
            } else if (currentEnemy.getHealth() > 0) {
                // TODO Find en måde at fixe den høje kobling
                getHit(currentEnemy.getCurrentWeapon().getDamage());
                outcome.concat("The " + currentEnemy + " hit you for "
                        + currentEnemy.getCurrentWeapon().getDamage());
            }
        }
        return outcome;
    }

    public void getHit(int damage) {
        setHealth(getHealth() - damage);
    }

    public Item findItem(String itemName) {
        Item item = null;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equalsIgnoreCase(itemName)
                    || inventory.get(i).toString().equalsIgnoreCase(itemName)) {
                item = inventory.get(i);
                return item;
            }
        }
        return item;
    }

    public Item takeItem(String input) {
        Item foundItem = null;
        if (currentRoom.findItem(input) != null) {
            foundItem = currentRoom.findItem(input);
            inventory.add(foundItem);
            currentRoom.removeItem(currentRoom.findItem(input));
        }
        return foundItem;
    }

    public String equipWeapon(String input) {
        String outcome;
        if (currentRoom.findItem(input) != null && currentRoom.findItem(input) instanceof Weapon) {
            if (currentWeapon != null) {
                inventory.add(currentWeapon);
            }
            Weapon weapon = (Weapon) currentRoom.findItem(input);
            setCurrentWeapon(weapon);
            currentRoom.removeItem(weapon);
            outcome = "You picked up and equipped the " + weapon;
        } else if (findItem(input) != null && findItem(input) instanceof Weapon) {
            if (currentWeapon != null) {
                inventory.add(currentWeapon);
            }
            Weapon weapon = (Weapon) findItem(input);
            setCurrentWeapon(weapon);
            inventory.remove(weapon);
            outcome = "You have equipped " + weapon + " from your inventory";
        } else if (findItem(input) != null && !(findItem(input) instanceof Weapon)) {
            outcome = "You can't equip that";
        } else if (currentWeapon != null && currentWeapon.getName().equalsIgnoreCase(input)) {
            outcome = "You already have the " + currentWeapon + " equipped";
        } else if (currentRoom.findItem(input) != null
                && !(currentRoom.findItem(input) instanceof Weapon)) {
            outcome = "The " + input + " is not very effective to use as a weapon";
        } else {
            outcome = "There is no such thing as a " + input.trim() + " to equip";
        }
        return outcome;
    }

    public String dropItem(String itemName) {
        String message;
        if (findItem(itemName) != null) {
            currentRoom.addItem(findItem(itemName));
            inventory.remove(findItem(itemName));
            message = "You dropped a " + itemName + ".";

        } else {
            message = "There is no such thing as a " + itemName + " in your inventory.";
        }

        return message;
    }

    public void eatConsumable(Consumable consumable) {
        setHealth(getHealth() + consumable.getHealthValue());
    }

    public String checkHealth() {
        String status = "";
        if (getHealth() > 74) {
            status = "You are in great health and ready to fight!";
        } else if (getHealth() <= 74 && getHealth() > 50) {
            status = "You are slightly wounded but can still fight.";
        } else if (getHealth() <= 50 && getHealth() > 25) {
            status = "You are in poor shape and should probably eat something";
        } else if (getHealth() <= 25) {
            status = "You are severely wounded and need to avoid fighting if you can";
        }
        return status;
    }

    public String checkStepCounter() {
        String message = null;
        if (getStepCounter() == 10 || getStepCounter() == 15 || getStepCounter() == 25) {
            message = "You have walked " + getStepCounter() + " steps and are getting exhausted. " +
                    "\nYou have " + (getMAX_STEPS() - getStepCounter()) + " steps left.";
        }
        return message;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public int getCurrentWeaponDamage() {
        return currentWeapon.getDamage();
    }

    public int getCurrentRoomItemsSize() {
        return currentRoom.getItemsSize();
    }

    public int getCurrentRoomEnemiesSize() {
        return currentRoom.getEnemiesSize();
    }

    public Iterable<Enemy> getCurrentRoomEnemies() {
        return currentRoom.getEnemies();
    }

    public Room getCurrentRoomNorth() {
        return currentRoom.getNorth();
    }
// FIXME måske lave move om så Room har checkNorth, checkSouth osv, som returnerer en boolean, og så goNorth osv i player
    public boolean move(String direction) {
        boolean hasMoved = false;
        if (direction.equalsIgnoreCase("go north") && currentRoom.getNorth() != null) {
            setCurrentRoom(currentRoom.getNorth());
            hasMoved = true;
            incrementStepCounter();
        } else if (direction.equalsIgnoreCase("go west") && currentRoom.getWest() != null) {
            setCurrentRoom(currentRoom.getWest());
            hasMoved = true;
            incrementStepCounter();
        } else if (direction.equalsIgnoreCase("go south") && currentRoom.getSouth() != null) {
            setCurrentRoom(currentRoom.getSouth());
            hasMoved = true;
            incrementStepCounter();
        } else if (direction.equalsIgnoreCase("go east") && currentRoom.getEast() != null) {
            setCurrentRoom(currentRoom.getEast());
            hasMoved = true;
            incrementStepCounter();
        }
        return hasMoved;
    }

    public Eat eat(String input) {
        Eat outcome;
        if (currentRoom.findItem(input) != null
                && currentRoom.findItem(input) instanceof Consumable) {
            Consumable foundConsumable = (Food) currentRoom.findItem(input);
            eatConsumable(foundConsumable);
            currentRoom.removeItem(foundConsumable);
            outcome = Eat.EATEN;

        } else if (findItem(input) != null
                && findItem(input) instanceof Consumable) {
            eatConsumable((Consumable) findItem((input)));
            inventory.remove(findItem(input));
            outcome = Eat.EATEN;

        } else if (currentRoom.findItem(input) != null
                && currentRoom.findItem(input) instanceof Consumable != true
                || findItem(input) != null &&
                findItem(input) instanceof Consumable != true) {
            outcome = Eat.NOT_EDIBLE;
        } else {
            outcome = Eat.NOT_FOUND;
        }
        return outcome;
    }

    public String getCurrentRoomName() {
        return currentRoom.getName();
    }

    public Room dig() {
        Room location = null;
        if (findItem("shovel") != null && currentRoom.getSecretRoom() != null) {
            setCurrentRoom(currentRoom.getSecretRoom());
            location = currentRoom;
        }
        return location;
    }
}

