public abstract class Weapon extends Item {
    private int damage;


    public Weapon(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
    public boolean usesLeft() {
        return true;
    }
}
