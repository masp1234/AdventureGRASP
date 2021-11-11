import java.util.ArrayList;
import java.util.Random;

public class RangedWeapon extends Weapon{

    int ammo;

    public RangedWeapon(String name, int damage, int ammo) {
        super(damage);
        this.name = name;
        this.ammo = ammo;
        this.prefix = nonConsumablePrefix();
        this.suffix = nonConsumableSuffix();


    }
    public RangedWeapon(int damage, int ammo) {
        super(damage);
        this.ammo = ammo;
        this.name = nonConsumableName();
        this.prefix = nonConsumablePrefix();
        this.suffix = nonConsumableSuffix();

    }

    public boolean usesLeft() {
        boolean usesLeft = true;
        if (this.ammo < 1) {
            usesLeft = false;
        }
        ammo--;
        return usesLeft;
    }

    public String nonConsumableName() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("bow");
        strings.add("crossbow");
        strings.add("musket");
        strings.add("pistol");
        Random rand = new Random();
        int random = rand.nextInt(strings.size());
        return strings.get(random);
    }

    public String nonConsumablePrefix() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("dirty old");
        strings.add("shiny");
        strings.add("damn good looking");
        strings.add("worn out");
        strings.add("giddi'up looking");
        Random rand = new Random();
        int random = rand.nextInt(strings.size());
        return strings.get(random);
    }

    public String nonConsumableSuffix() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("with some dust on it");
        strings.add("that has seen better days");
        strings.add("that looks good to me");
        strings.add("i wonder what i can use this for");
        strings.add("what a strange thing to find");
        Random rand = new Random();
        int random = rand.nextInt(strings.size());
        return strings.get(random);
    }
}
