/*
CommandSystem.java
For use in the Final project for COSC 236.
Based on starter code first developed by Prof. Dastyni Loksa

This class is the primary logic class for the system. It defines what commands are valid, 
and what happens when those commands are executed.  
*/

import java.util.*;

public class CommandSystem {
    private GameState state;

    // ArrayList to store all of the verbs that the game knows about. (This is a
    // parallel array with the verbDescription arraylist)
    private List<String> verbs = new ArrayList<String>();

    // ArrayList of the descriptions for the verbs the game knows. (This is a
    // parallel array with the verbs arraylist)
    private List<String> verbDescription = new ArrayList<String>();

    // ArratList to store all of the nouns the game knows about.
    private List<String> nouns = new ArrayList<String>();

    /*
     * Constructor should defines all verbs that can be used in the commands and all
     * nouns the user can interact with.
     * 
     * Suggestion: These could all be loaded from a file.
     * 
     * Verb descriptions are stored in a parallel Arraylist with the Verbs and are
     * used when printing out the help menu (using the ? command).
     */
    public CommandSystem(GameState state) {
        this.state = state;
        // Assign verbs and descriptions here
        addVerb("?", "Show this help screen.");
        addVerb("help", "Same as the ? command.");
        addVerb("look",
                "Use the look command by itself to look in your current area. \nYou can also look at a person or object by ntyping look and the name of what you want to look at.\nExample: look book");
        addVerb("l", "Same as the look command.");
        addVerb("north", "Move to the north.");
        addVerb("east", "Move to the east.");
        addVerb("south", "Move to the south.");
        addVerb("west", "Move to the west.");
        addVerb("open", "Open a door in your current area.");
        addVerb("pickup", "Pick up an item in your current area.");
        addVerb("inventory", "Display the items in your inventory.");
        addVerb("use", "Use an item from your inventory.");
        addVerb("drop", "Drop an item from your inventory.");
        addVerb("talk", "Talk to an NPC.");
        addVerb("health", "Check your current health.");
        addVerb("attack", "Attack an enemy.");
        addVerb("quit", "Quit the game."); // NOTE: In the starter code, this is handeled by the client code - not the
                                           // CommandSystem.
    }

    public void processInput(String input) {
        String[] parts = input.split(" ");
        String verb = parts[0];
        String noun = parts.length > 1 ? parts[1] : "";

        if (verb.equalsIgnoreCase("go") && parts.length > 1) {
            verb = noun;
            noun = parts.length > 2 ? parts[2] : "";
        }

        executeVerb(verb, noun);
    }
    
    // When a command is only one Verb this method controls the result.
    public void executeVerb(String verb, String noun) {
        switch (verb) {
            case "l":
            case "look": // will show the description of the current room (stored in the state object)
                gameOutput("You look around.");
                gameOutput(state.currentLocation.lookaround);
                break;
            case "?":
                this.printHelp();
                break;
            case "help":
                this.printHelp();
                break;
            case "open":
                state.openDoor();
                break;
            case "pickup":
                state.pickupItem(noun);
                break;
            case "inventory":
                state.displayInventory();
                break;
            case "use":
                state.useItem(noun);
                break;
            case "drop":
                state.dropItem(noun);
                break;
            case "north":
                state.move(Direction.NORTH);
                break;
            case "east":
                state.move(Direction.EAST);
                break;
            case "south":
                state.move(Direction.SOUTH);
                break;
            case "west":
                state.move(Direction.WEST);
                break;
            case "talk":
                NPC npc = state.findNPC(noun);
                if (npc != null) {
                    npc.talk();
                } else {
                    System.out.println("There's no one by that name here.");
                }
                break;
            case "health":
                System.out.println("Your current health is: " + state.player.health);
                break;
            case "attack":
                Enemy enemy = state.findEnemy(noun);
                if (enemy != null) {
                    state.player.attack(enemy);
                    if (enemy.health > 0) {
                        enemy.attack(state.player);
                    }
                } else {
                    System.out.println("There's no enemy by that name here.");
                }
                break;
            
        }
    }

    

    // When a command is a Verb followed by a noun, this method controls the result.
    public void executeVerbNoun(String verb, String noun) {

        /**
         * Initilize the string that we will use as a response text.
         * This method allows us to create a single string and just print it at the end
         * of the method.
         * You can do it any way you wish.
         */
        String resultString = "";

        switch (verb) { // Deciddes what to do based on each verb
            case "l":
            case "look":
                resultString = lookAt(noun);
        }

        gameOutput(resultString);
    }

    // When a command is a Verb followed by two nouns, this method controls the
    // result.
    public void executeVerbNounNoun(String string, String string2, String string3) {

    }

    // Method to take care of looking at a noun.
    public String lookAt(String noun) {
        // This will be what is returned by the method.
        String resultString = "";

        switch (noun) { // for the given verb, decide what to do based on what noun was entered
            case "mat":
                // get the description from the item you are looking at.
                // resultString += state.mat.description;
                break;

            // You cound design a way to look at any item without having to specify how to
            // deal with each of them.
            // That way you can code special cases for some items, and others would just use
            // default behavior.
            // This is HIGHLY encouraged. (It will save time and headaches!)
            default:
        }
        return resultString;
    }

    /*****************************************************************
     * Helper methods to help system work.
     ******************************************************************/

    /*
     * Prints out the help menu. Goes through all verbs and verbDescriptions
     * printing a list of all commands the user can use.
     */
    public void printHelp() {
        int DISPLAY_WIDTH = GameState.DISPLAY_WIDTH;
        String s1 = "";
        while (s1.length() < DISPLAY_WIDTH)
            s1 += "-";

        String s2 = "";
        while (s2.length() < DISPLAY_WIDTH) {
            if (s2.length() == (DISPLAY_WIDTH / 2 - 10)) {
                s2 += " Commands ";
            } else {
                s2 += " ";
            }
        }

        System.out.println("\n\n" + s1 + "\n" + s2 + "\n" + s1 + "\n");
        for (String v : verbs) {
            // System.out.printp(v + " --> " + verbDescription.get(verbs.indexOf(v)));
            System.out.printf("%-8s  %s", v, formatMenuString(verbDescription.get(verbs.indexOf(v))));
        }
    }

    // Used to format the help menu
    public String formatMenuString(String longString) {
        String result = "";
        Scanner chop = new Scanner(longString);
        int charLength = 0;

        while (chop.hasNext()) {
            String next = chop.next();
            charLength += next.length();
            result += next + " ";
            if (charLength >= (GameState.DISPLAY_WIDTH - 30)) {
                result += "\n          ";
                charLength = 0;
            }
        }
        chop.close();
        return result + "\n\n";
    }

    /**
     * Default game output.
     * This is an alias for the other gameOutput method and defaults to
     * doing both the bracketing and the width formatting.
     **/
    public void gameOutput(String longstring) {
        gameOutput(longstring, true, true);
    }

    public void gameOutput(String longstring, boolean addBrackets, boolean formatWidth) {
        if (addBrackets) {
            longstring = addNounBrackets(longstring);
        }
        if (formatWidth) {
            longstring = formatWidth(longstring);
        }

        System.out.println(longstring);
    }

    // formats a string to DISPLAY_WIDTH character width.
    // Used when getting descriptions from items/locations and printing them to the
    // screen.
    // You can also add [nl] for a newline in a string in a description etc.
    public String formatWidth(String longString) {

        Scanner chop = new Scanner(longString);
        String result = "";
        int charLength = 0;
        boolean addSpace = true;

        while (chop.hasNext()) {

            // Get our next word in the string.
            String next = chop.next();

            // Add the legnth to our charLength.
            charLength += next.length() + 1;

            // Find and replace any special newline characters [nl] with \n.
            if (next.contains("[nl]")) {
                // Find the index after our [nl] characters.
                int secondHalf = next.indexOf("[nl]") + 4;

                // Set charLength to the number of characters after the [nl],
                // because that will be the beginnig of a new line.
                if (secondHalf < next.length()) {
                    charLength = secondHalf;
                } else {
                    charLength = 0;
                    addSpace = false; // Do not add space after if this ended with a newline character.
                }

                // Now actually replace the [nl] with the newline character
                next = next.replace("[nl]", "\n");

            }

            // Add the word to the result.
            result += next;

            // Only add a space if our special case did not happen.
            if (addSpace)
                result += " ";

            // Normally we add a space after a word, prepare for that.
            addSpace = true;

            if (charLength >= GameState.DISPLAY_WIDTH) {
                result += "\n";
                charLength = 0;
            }
        }
        chop.close();
        return result;
    }

    /**
     * Adds brackets around whole words that are included in the `nouns` list,
     * ignoring case, and also deals with any that have punctuation after them.
     *
     * @param longString the string to check for nouns
     * @return the modified string with brackets around the nouns
     */
    public String addNounBrackets(String longString) {
        String[] words = longString.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            String word = words[i].replaceAll("\\p{Punct}+$", "");
            String punct = words[i].substring(word.length());
            for (String noun : nouns) {
                if (word.equalsIgnoreCase(noun)) {
                    words[i] = "[" + word + "]" + punct;
                    break;
                }
            }
        }
        return String.join(" ", words);
    }

    // Adds a noun to the noun list
    // lets the command system know this is something you an interact with.
    public void addNoun(String string) {
        if (!nouns.contains(string.toLowerCase()))
            nouns.add(string.toLowerCase());
    }

    // Adds a verb to the verb list and its description to the parallel description
    // list.
    // This method should be used to register new commands with the command system.
    public void addVerb(String verb, String description) {
        verbs.add(verb.toLowerCase());
        verbDescription.add(description.toLowerCase());
    }

    // Allows the client code to check to see if a verb is in the game.
    public boolean hasVerb(String string) {
        return verbs.contains(string);
    }

    // Allows the client code to check to see if a noun is in the game.
    public boolean hasNoun(String string) {
        return nouns.contains(string);
    }

    

}
