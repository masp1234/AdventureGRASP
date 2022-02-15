import java.util.ArrayList;

public class Room {
    private Room north = null;
    private Room south = null;
    private Room west = null;
    private Room east = null;
    // TODO lav secretRoom
    private Room secretRoom = null;
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private String name;
    private String description;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Room(String name) {
        this.name = name;
    }

    public void setSouth(Room room) {
        this.south = room;
        this.south.north = this;
    }

    public void setEast(Room room) {
        this.east = room;
        this.east.west = this;
    }

    public Room getNorth() {
        return north;
    }

    public Room getSouth() {
        return south;
    }

    public Room getWest() {
        return west;
    }

    public Room getEast() {
        return east;
    }
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return name;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public Item findItem(String itemName) {
        Item foundItem = null;
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)
                    ^ item.toString().equalsIgnoreCase(itemName)) {
                foundItem = item;
            }
        }
        return foundItem;
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    public Enemy findEnemy(String enemyName) {
        Enemy foundEnemy = null;
        for (Enemy enemy : enemies) {
            if (enemy.getName().equalsIgnoreCase(enemyName)
                    ^ enemy.toString().equalsIgnoreCase(enemyName)) {
                foundEnemy = enemy;

            }
        }
        return foundEnemy;
    }

    public int getItemsSize() {
        return items.size();
    }

    public int getEnemiesSize() {
        return enemies.size();
    }

    public Room getSecretRoom() {
        return secretRoom;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }
}

