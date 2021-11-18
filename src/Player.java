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


    public void attack(Enemy enemy) {
        enemy.getHit(getCurrentWeapon().getDamage());

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

    public void takeItem(Item item) {
        if (item != null) {
            inventory.add(item);
            getCurrentRoomItems().remove(item);
        }

    }

    public void equipWeapon(Weapon weapon) {
        if (weapon != null) {
            if (currentWeapon != null) {
                inventory.add(currentWeapon);
            }
            setCurrentWeapon(weapon);
            getCurrentRoomItems().remove(weapon);
            inventory.remove(weapon);
        }
    }

    public void dropItem(Item item) {
        if (item != null) {
            getCurrentRoomItems().add(item);
            inventory.remove(item);
        }

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

    public Room move(String direction) {
        Room destination = null;
        if (direction.equalsIgnoreCase("go north")) {
            setCurrentRoom(currentRoom.getNorth());
            destination = currentRoom.getNorth();
        }
        else if (direction.equalsIgnoreCase("go west")) {
            setCurrentRoom(currentRoom.getWest());
            destination = currentRoom.getWest();
        }
        else if (direction.equalsIgnoreCase("go south")) {
            setCurrentRoom(currentRoom.getSouth());
            destination = currentRoom.getSouth();
        }
        else if (direction.equalsIgnoreCase("go east")) {
            setCurrentRoom(currentRoom.getEast());
            destination = currentRoom.getEast();
        }
        incrementStepCounter();
        return destination;
    }
}

