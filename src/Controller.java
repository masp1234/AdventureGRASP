import java.util.List;

public class Controller {
    private Player player;
    private Map gameMap;

    public Controller() {
        player = new Player();
        gameMap = new Map();
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayerName(String name) {
        player.setPlayerName(name);
    }

    public int getPlayerInventorySize() {
        return player.inventorySize();
    }

    public List<Item> getPlayerInventory() {
        return player.getInventory();
    }

    public Weapon getPlayerCurrentWeapon() {
       return player.getCurrentWeapon();
    }

    public int getPlayerCurrentWeaponDamage() {
        return player.getCurrentWeaponDamage();
    }

    public int getPlayerCurrentRoomItemsSize() {
        return player.getCurrentRoomItemsSize();
    }

    public int getPlayerCurrentRoomEnemiesSize() {
        return player.getCurrentRoomEnemiesSize();
    }

    public String getPlayerCurrentRoomDescription() {
        return player.getCurrentRoomDescription();
    }

    public List<Item> getPlayerCurrentRoomItemList() {
        return player.getCurrentRoomItems();
    }

    public Iterable<Enemy> getPLayerCurrentRoomEnemiesList() {
        return player.getCurrentRoomEnemies();
    }
}
