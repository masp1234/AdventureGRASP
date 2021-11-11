import java.util.ArrayList;
import java.util.Random;

public class Food extends Consumable{

    public Food(int healthValue, String prefix, String name, String suffix) {
        super(healthValue);
        this.name = name;
        this.prefix = prefix;
        this.suffix = suffix;
    }
    public Food(String name, int healthValue) {
        super(healthValue);
        this.name = name;
        this.prefix = consumablePrefix();
        this.suffix = consumableSuffix();

    }
    public Food() {
        this.name = consumableName();
        this.prefix = consumablePrefix();
        this.suffix = consumableSuffix();
    }


    public String consumableName(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("bread");
        strings.add("apple");
        strings.add("banana");
        strings.add("porkchop");
        Random rand = new Random();
        int random = rand.nextInt(strings.size());
        return strings.get(random);
    }
    public String consumablePrefix(){
        ArrayList<String > strings = new ArrayList<>();
        strings.add("a moldy");
        strings.add("a half-rotten");
        strings.add("a damn good looking");
        strings.add("an overcooked");
        strings.add("a dry");
        Random rand = new Random();
        int random = rand.nextInt(strings.size());
        return strings.get(random);
    }
    public String consumableSuffix(){
        ArrayList<String > strings = new ArrayList<>();
        strings.add("with some dust on it");
        strings.add("that has seen better days");
        strings.add("that looks good to me");
        strings.add(". I wonder what i can use this for");
        strings.add(". What a strange thing to find");
        Random rand = new Random();
        int random = rand.nextInt(strings.size());
        return strings.get(random);
    }

}
