/*
GameState.java
For use in the Final project for COSC 236.
Based on starter code first developed by Prof. Dastyni Loksa

This is the class to hold the state of the running game and allows easy
passing of important information to methods that require data from the
state of the game.

This starter code is designed for the verbs to be stored in the commandSystem.

*/

public class GameState {
    Location currentLocation;
    CommandSystem commandSystem;

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
        
        // (and store it in currentLocation so I can always referece where the player is
        // easily)
        currentLocation = new Location();
        currentLocation.name = "Room";
        currentLocation.description = "Welcome to Hogwarts School of Wizardry, young wizard. You are in a room. There is a door to the north.";
        currentLocation.lookaround = "You are in a room. There is a door to the north.";
        commandSystem.addNoun("Room");

        Location livingRoom = new Location();
        livingRoom.name = "Living Room";
        livingRoom.description = "You find yourself in a cozy living room with a fireplace.";

        // Add the new location to the command system
        commandSystem.addNoun("Living Room");
        

        // Create a demo item.
        Item mat = new Item(); // There is an error here. Remember how variable scope works and fix this.
        mat.name = "Mat";
        mat.description = "There is a welcome mat here. This is a special mat.";

        Item key = new Item();
        key.name = "Key";
        key.description = "A shiny golden key.";


        // Add item to list of nouns so our command system knows it exists.
        commandSystem.addNoun(mat.name);
        commandSystem.addNoun(key.name);


        /*
         * Once the commandSystem knows about the item, we need to code what happens
         * with each of the commands that can happen with the item.
         * See CommandSystem line 96 for what happens if you currently "look mat"
         */
    }
}
