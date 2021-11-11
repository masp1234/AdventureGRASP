import java.util.ArrayList;

public class Room {
    private Room north = null;
    private Room south = null;
    private Room west = null;
    private Room east = null;
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

    public String getName() {
        return this.name;
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
        Item item;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(itemName)
                    ^ items.get(i).toString().equalsIgnoreCase(itemName)) {
                item = items.get(i);
                return item;
            }
        }
        return null;
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }
    public Enemy findEnemy(String enemyName) {
        Enemy enemy;
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getName().equalsIgnoreCase(enemyName)
                    ^ enemies.get(i).toString().equalsIgnoreCase(enemyName)) {
                enemy = enemies.get(i);
                return enemy;
            }
        }
        return null;
    }
}

