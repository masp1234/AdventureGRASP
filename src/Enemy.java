import java.util.ArrayList;
import java.util.Random;

public class Enemy {
    private String name;
    private String prefix;
    private String suffix;
    private int health;
    private Weapon currentWeapon;



    public Enemy(String name, int health, Weapon weapon) {
        this.health = health;
        this.name = name;
        this.prefix = enemyPrefix();
        this.suffix = enemySuffix();
        this.currentWeapon = weapon;

    }

    public Enemy(int health, Weapon weapon) {
        this.name = enemyName();
        this.prefix = enemyPrefix();
        this.suffix = enemySuffix();
        this.health = health;
        this.currentWeapon = weapon;
    }
    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public int getHealth() {
        return this.health;
    }
    public int getHit(int damage) {
        return health = (getHealth() - damage);
    }
    public void attack(Player player) {
        player.getHit(getCurrentWeapon().getDamage());
    }

    public String getName(){
        return this.name;
    }
    public String getFullName() {
        return this.prefix + " " + this.name + " " + this.suffix;
    }

    public String enemyName() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("pig");
        strings.add("gargoyle");
        strings.add("bat");
        strings.add("old man");
        Random rand = new Random();
        int random = rand.nextInt(strings.size());
        return strings.get(random);
    }

    public String enemyPrefix() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("dangrous");
        strings.add("angry");
        strings.add("damn good looking");
        strings.add("worn out");
        strings.add("giddi'up looking");
        Random rand = new Random();
        int random = rand.nextInt(strings.size());
        return strings.get(random);
    }

    public String enemySuffix() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("that looks pissed off");
        strings.add(", i wonder how long he's been here");
        strings.add("that looks at me with killereyes");
        strings.add("i wonder what i can use this for");
        strings.add("what a strange thing to find");
        Random rand = new Random();
        int random = rand.nextInt(strings.size());
        return strings.get(random);
    }
    public String toString() {
        return prefix + " " + name;
    }

}