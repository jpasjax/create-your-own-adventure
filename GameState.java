/*
GameState.java
For use in the Final project for COSC 236.
Based on starter code first developed by Prof. Dastyni Loksa

This is the class to hold the state of the running game and allows easy
passing of important information to methods that require data from the
state of the game.

This starter code is designed for the verbs to be stored in the commandSystem.

*/

import java.util.ArrayList;
import java.util.List;

// No import statement needed

public class GameState {
    Location currentLocation;
    CommandSystem commandSystem;
    List<Item> items;
    List<Item> playerInventory;
    List<NPC> npcs = new ArrayList<>();
    Player player;

    public static int DISPLAY_WIDTH = 100;

    /*
     * GameState Constructor
     * 
     * Ideally, your game will be fully loaded and ready to play once this
     * constructor has finished running.
     * 
     * How things have been done here are just a rudementry setup to link the other
     * classes and have the
     * bare bones example of the command system. This is not a great way to
     * initilize your project.
     * 
     * You should do better!
     */

     
    public GameState() {
        commandSystem = new CommandSystem(this);
        
        // Create first (starting) location
        playerInventory = new ArrayList<>();
        player = new Player();
        // (and store it in currentLocation so I can always referece where the player is
        // easily)
        
        // NPC'S THAT WILL BE ADDED IN THE GAME
        npcs.add(new NPC("Bob", "A friendly NPC.", "Hello, player!"));

        // LOCATIONS IN THE GAME //

        // STARTING LOCATION - Room
        currentLocation = new Location();
        currentLocation.name = "Room";
        currentLocation.description = "Welcome to Hogwarts School of Wizardry, young wizard. You are in your first year room. There is a hallway you walk to in the north of your room";
        currentLocation.lookaround = "You are in your dorm room. There is a hallway you walk to in the north of your room";
        commandSystem.addNoun("north");

        

        // THE NEXT LOCATION AFTER YOU GO NORTH- Great Hall
        Location GreatHall = new Location();
        GreatHall.name = "Great Hall";
        GreatHall.description = "You walk down the stairs and you are now in the Great Hall. Hogwarts main gathering place. There is a door to the south. But it's locked? How can I open the door?";
        GreatHall.lookaround = "You are in the Great Hall. There is a door to the south. But it's locked? How can open the door?, There is a mysterious key on one of the tables. I wonder what it's for?";

        currentLocation.setAdjacentLocation(Direction.NORTH, GreatHall); 
        commandSystem.addNoun("Great Hall");
        // Set the next location to the north of the current location
        // currentLocation.nextLocation =  GreatHall;

        // THE NEXT LOCATION AFTER YOU GO EAST - Library
        Location Library = new Location();
        Library.name = "Library";
        Library.description = "You are now in the Library. There are books everywhere.";
        Library.lookaround = "You see books and a quiet study area.";
        commandSystem.addNoun("Library");

        currentLocation.setAdjacentLocation(Direction.EAST, Library); // Set the next location to the east of the current location

        // THE NEXT LOCATION AFTER YOU GO WEST - Potion Class
        Location PotionClass = new Location();
        PotionClass.name = "Potion Class";
        PotionClass.description = "You are now in the Potion Class. There are potions brewing.";
        PotionClass.lookaround = "You see potions brewing and a blackboard with potion recipes.";
        commandSystem.addNoun("Potion Class");

        currentLocation.setAdjacentLocation(Direction.WEST, PotionClass); // Set the next location to the west of the current location


    // ITEMS //

        items = new ArrayList<>();
        Item key = new Item();
        key.name = "Key";
        key.description = "A shiny golden key.";
        items.add(key);
        GreatHall.itemsHere.add(key);

        commandSystem.addNoun(key.name);

        
    }

    // METHODS //

    public void openDoor() {
        if (currentLocation.hasDoor && !currentLocation.isDoorOpen) {
            currentLocation.isDoorOpen = true;
            System.out.println("You open the door.");
            currentLocation = currentLocation.nextLocation; // Move to the next location
            System.out.println(currentLocation.description);
        } else {
            System.out.println("There's no door to open.");
        }
    }
    public void pickupItem(String itemName) {
        Item itemToPickup = null;
        for (Item item : currentLocation.itemsHere) {
            if (item.name.equalsIgnoreCase(itemName)) {
                itemToPickup = item;
                break;
            }
        }
    
        if (itemToPickup != null) {
            currentLocation.itemsHere.remove(itemToPickup);
            playerInventory.add(itemToPickup);
            System.out.println("You picked up the " + itemToPickup.name + ".");
        } else {
            System.out.println("There's no " + itemName + " to pick up.");
        }
    }
    public void displayInventory() {
        if (playerInventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Your inventory:");
            for (Item item : playerInventory) {
                System.out.println("- " + item.name);
            }
        }
    }
    public void useItem(String itemName) {
    Item itemToUse = null;
    for (Item item : playerInventory) {
        if (item.name.equalsIgnoreCase(itemName)) {
            itemToUse = item;
            break;
        }
    }

    if (itemToUse != null) {
        if (itemToUse.name.equalsIgnoreCase("Key") && currentLocation.hasDoor && !currentLocation.isDoorOpen) {
            currentLocation.isDoorOpen = true;
            System.out.println("You use the key to open the door.");
            currentLocation = currentLocation.nextLocation; // Move to the next location
            System.out.println(currentLocation.description); // Print the description of the new location
        } else {
            System.out.println("You can't use the " + itemToUse.name + " here.");
        }
    } else {
        System.out.println("You don't have a " + itemName + ".");
    }
}
    public void dropItem(String itemName) {
        Item itemToDrop = null;
        for (Item item : playerInventory) {
            if (item.name.equalsIgnoreCase(itemName)) {
                itemToDrop = item;
                break;
            }
        }
    
        if (itemToDrop != null) {
            playerInventory.remove(itemToDrop);
            currentLocation.itemsHere.add(itemToDrop);
            System.out.println("You dropped the " + itemToDrop.name + ".");
        } else {
            System.out.println("You don't have a " + itemName + ".");
        }
    }
    public void move(Direction direction) {
        Location nextLocation = currentLocation.getAdjacentLocation(direction);
        if (nextLocation != null) {
            currentLocation = nextLocation;
            System.out.println("You move to the " + currentLocation.name + ".");
            System.out.println(currentLocation.description);
        } else {
            System.out.println("There's nothing in that direction.");
        }
    }
    public NPC findNPC(String name) {
        for (NPC npc : npcs) {
            if (npc.name.equalsIgnoreCase(name)) {
                return npc;
            }
        }
        return null;
    }
}
    
    // other methods...
