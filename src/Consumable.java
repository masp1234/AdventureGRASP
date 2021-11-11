public abstract class Consumable extends Item{
    private int healthValue = 30;

    public Consumable(int healthValue) {
        this.healthValue = healthValue;
    }
    public Consumable() {}

    public int getHealthValue() {
        return this.healthValue;
    }
}