import java.util.List;

public class Controller {
    private Player player;
    private Map gameMap;




    public Controller() {
        player = new Player();
        gameMap = new Map();
    }
    public void run() {

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

    public String checkPlayerHealth() {
        return player.checkHealth();
    }

    public boolean getPlayerHasWeapon() {
        boolean hasWeapon = false;
        if (player.getCurrentWeapon() != null) {
            hasWeapon = true;
        }
        return hasWeapon;
    }

    public boolean playerCurrentRoomHasNorth() {
        boolean hasNorth = false;
        if (player.getCurrentRoomNorth() != null) {
            hasNorth = true;
        }
        return hasNorth;
    }
    public boolean movePlayer(String direction) {
       return player.move(direction);

    }

    public void makeConnections(Player player) {
        gameMap.makeConnections(player);
    }

    public String checkPlayerStepCounter() {
        return player.checkStepCounter();
    }

    public Room getPlayerCurrentRoom() {
        return player.getCurrentRoom();
    }

    public Room getWinningRoom() {
        return gameMap.room5;
    }

    public Eat eat(String input) {
        return player.eat(input);
    }

    public String dropItem(String name) {
        return player.dropItem(name);
    }

    public String getCurrentRoomName() {
        return player.getCurrentRoomName();
    }


    public Room dig() {
       return player.dig();
    }

    public Item takeItem(String input) {
            return player.takeItem(input);
    }

    public String attack(String input) {
        return player.attack(input);
    }

    public String equipWeapon(String input) {
        return player.equipWeapon(input);
    }


    }


