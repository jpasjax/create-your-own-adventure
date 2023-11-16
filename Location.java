import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a location in the game.
 */
public class Location {
    String name;
    String description;
    String lookaround;
    ArrayList<Item> itemsHere;
    ArrayList<Location> exits;
    Location nextLocation;
    boolean hasDoor;
    boolean locked;
    boolean key;
    boolean isDoorOpen;
    Map<Direction, Location> adjacentLocations = new HashMap<>();



    public Location() {
        itemsHere = new ArrayList<>();
        exits = new ArrayList<>();
        adjacentLocations = new HashMap<>();
        key = false;
    }

    public void setAdjacentLocation(Direction direction, Location location) {
        adjacentLocations.put(direction, location);
    }

    public Location getAdjacentLocation(Direction direction) {
        return adjacentLocations.get(direction);
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

