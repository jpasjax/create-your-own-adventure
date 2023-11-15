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
        // (and store it in currentLocation so I can always referece where the player is
        // easily)
        currentLocation = new Location();
        currentLocation.name = "Room";
        currentLocation.description = "Welcome to Hogwarts School of Wizardry, young wizard. You are in a room. There is a door to the north.";
        currentLocation.lookaround = "You are in a room. There is a door to the north.";
        currentLocation.hasDoor = true;
        currentLocation.isDoorOpen = false;
        commandSystem.addNoun("Room");

        Location livingRoom = new Location();
        livingRoom.name = "Living Room";
        livingRoom.description = "You find yourself in a cozy living room with a fireplace.";

        // Add the new location to the command system
        commandSystem.addNoun("Living Room");

        items = new ArrayList<>();
        Item key = new Item();
        key.name = "Key";
        key.description = "A shiny golden key.";
        items.add(key);

        commandSystem.addNoun(key.name);
    }

    public void openDoor() {
        if (currentLocation.hasDoor && !currentLocation.isDoorOpen) {
            currentLocation.isDoorOpen = true;
            System.out.println("You open the door.");
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
        System.out.println(currentLocation.description);
    } else {
        System.out.println("You can't move in that direction.");
    }
}
    // other methods...
}