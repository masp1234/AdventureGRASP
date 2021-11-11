import java.util.*;

public abstract class Item {
    String name;
    String prefix;
    String suffix;

    public String getName() {
        return this.name;
    }

    public String getFullName() {
        return prefix + " " + name + " " + suffix;
    }

    public String toString() {
        String string;
        if (prefix == null && suffix == null) {
            string = name;
        } else if (prefix == null) {
            string = name + suffix;
        } else if (suffix == null) {
            string = prefix + " " + name;
        } else {
            string = prefix + " " + name + " " + suffix;
        }
        return string;

    }
}
