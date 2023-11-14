import java.util.ArrayList;

public class Location {
    // State of the location object
    String name;
    String description;
    String lookaround;

    // Use ArrayList instead of arrays
    ArrayList<Item> itemsHere; // to hold all of the items in this location.
    ArrayList<Location> exits; // to hold all of the locations that you can get to from this location.

    public Location() {
        itemsHere = new ArrayList<>();
        exits = new ArrayList<>();
    }

    public Item getItem(String objectName) {
        // Find the item in itemsHere and return it.
        for (Item item : itemsHere) {
            if (item.name.equals(objectName)) {
                return item;
            }
        }
        return null; // Return null if the item is not found.
    }

    public void addExit(Location exit) {
        // Add a location to the exits list.
        exits.add(exit);
    }


}


