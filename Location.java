import java.util.ArrayList;

/**
 * Represents a location in the game.
 */
public class Location {
    String name;
    String description;
    String lookaround;
    ArrayList<Item> itemsHere;
    ArrayList<Location> exits;
    boolean hasDoor;
    boolean isDoorOpen;

    public Location() {
        itemsHere = new ArrayList<>();
        exits = new ArrayList<>();
    }

    public Location(String name, String description, String lookaround) {
        this();
        this.name = name;
        this.description = description;
        this.lookaround = lookaround;
    }

    public Item getItem(String objectName) throws Exception {
        for (Item item : itemsHere) {
            if (item.name.equals(objectName)) {
                return item;
            }
        }
        throw new Exception("Item " + objectName + " not found.");
    }

    public void removeItem(Item item) {
        itemsHere.remove(item);
    }

    public void addExit(Location exit) {
        exits.add(exit);
    }

    public void removeExit(Location exit) {
        exits.remove(exit);
    }

    // Add getters and setters here
}

