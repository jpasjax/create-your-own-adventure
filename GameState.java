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
    Location GreatHall;
    Location SouthofDoor;
    Location Library;
    Location RestrictedSection;
    Location PotionClass;
    Location FirstBossFight;
    CommandSystem commandSystem;
    List<Item> items;
    List<Item> playerInventory;
    public List<NPC> npcs = new ArrayList<>();
    Player player;
    List<Enemy> enemies;

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
        items = new ArrayList<>();
        playerInventory = new ArrayList<>();
        player = new Player();
        // (and store it in currentLocation so I can always referece where the player is
        // easily)

        // NPC'S THAT WILL BE ADDED IN THE GAME
        NPC Joseph = new NPC("Joseph", "Your friend is here",
                "Joseph: Well hello, I havent seen you here before. I am Joseph. I am a first year here at Hogwarts. I am in Gryffindor. I think we can be really good friends.");
        npcs.add(Joseph);
        commandSystem.addNoun("Joseph");

        NPC wizard = new NPC("Wizard", "A wise old man with a long beard and a pointy hat.",
                "Wizard: I'm not sure what is through that door, but I have a bad feeling about it. Please be careful.");
        npcs.add(wizard);
        commandSystem.addNoun("wizard");

        // ENEMIES THAT WILL BE ADDED IN THE GAME
        Enemy dementor = new Enemy("Dementor", 100, 20);

        // LOCATIONS IN THE GAME //

        // STARTING LOCATION - Room
        currentLocation = new Location();
        currentLocation.name = "Room";
        currentLocation.description = "Welcome to Hogwarts School of Wizardry, young wizard. You are in your first year room. There is a hallway you walk to in the north of your room";
        currentLocation.lookaround = "You are in your dorm room. There is a hallway you walk to in the north of your room. \nYour friend Joseph is here. Talk to him to learn more about the game.";
        commandSystem.addNoun("north");

        // THE NEXT LOCATION AFTER YOU GO NORTH- Great Hall
        Location GreatHall = new Location();
        GreatHall.name = "Great Hall";
        GreatHall.description = "You walk down the Grand Staircase and you are now in the Great Hall. Hogwarts main gathering place. There is a door to the south But it's locked. \nThere is also another flight of stairs to the east.";
        GreatHall.lookaround = "You are in the Great Hall. There is a door to the south, but there is a lock on it. There is also another flight of stairs to the east.";
        String[] nouns = { "south", "east" };
        for (String noun : nouns) {
            commandSystem.addNoun(noun);

            currentLocation.setAdjacentLocation(Direction.NORTH, GreatHall); // If user types "north" from Room, they
                                                                             // will go to GreatHall

        }

        // currentLocation.nextLocation = GreatHall;
        Location SouthofDoor = new Location();
        SouthofDoor.name = "South of Door";
        SouthofDoor.description = "There is a door here, but its locked. I wonder if there is a key somewhere. \nIf I go back north, I will be in the Great Hall.";
        SouthofDoor.lookaround = "There is a key on the ground. I wonder if it will open the door.\n There is a friendly wizard here. I wonder what he has to say.";
        commandSystem.addNoun("key");
        commandSystem.addNoun("door");
        commandSystem.addNoun("wizard");
        SouthofDoor.locked = true;
        SouthofDoor.hasDoor = true;
        SouthofDoor.isDoorOpen = false;

        Location FirstBossFight = new Location();
        FirstBossFight.name = "First Boss Fight";
        FirstBossFight.description = "Checkpoint: \nYou feel a cold prescence.......... \nThe door slams behind you. You see a dementor. You must fight it in order to continue. \n ";
        FirstBossFight.lookaround = "You see a dementor. You must fight it in order to continue. \nThe Dementor has 100 health. ";
        FirstBossFight.enemies = new ArrayList<>();
        FirstBossFight.enemies.add(dementor);
        commandSystem.addNoun("Dementor");
        commandSystem.addNoun("wand");

        // THE NEXT LOCATION AFTER YOU GO EAST - Library
        Location Library = new Location();
        Library.name = "Hogwarts Library";
        Library.description = "You are now in the Hogwarts Library. There are books and a quiet study area. But there is a mysterious door in here. Maybe the library has a secret room. \nYou can also head to you first class if you go South \nYou can also go back to the Great Hall by going west.";
        Library.lookaround = "You see books and a quiet study area. But there is a mysterious door to the west. There is a key on the library desk.";
        Library.hasDoor = true;
        Library.locked = true;
        Library.isDoorOpen = false;
        commandSystem.addNoun("key");

        Location RestrictedSection = new Location();
        RestrictedSection.name = "Restricted Section";
        RestrictedSection.description = "idk what to put here";
        RestrictedSection.lookaround = "idk what to put here";

        // Set the next location to the east of the current location

        // THE NEXT LOCATION AFTER YOU GO WEST - Potion Class
        Location PotionClass = new Location();
        PotionClass.name = "Potion Class";
        PotionClass.description = "You are now in the Potion Class. There are potions brewing.";
        PotionClass.lookaround = "You see potions brewing and a blackboard with potion recipes. There is a healing potion on the desk. \nYou can go back to the Library by going north.";
        commandSystem.addNoun("Potion Class");

        // LOCATION CONNECTIONS //

        // Great Hall connections
        GreatHall.setAdjacentLocation(Direction.EAST, Library); // If user types "east" from GreatHall, they will go to
                                                                // Library
        GreatHall.setAdjacentLocation(Direction.SOUTH, SouthofDoor); // If user types "south" from GreatHall, they will
                                                                     // go to SouthofDoor

        // South of Door connections
        SouthofDoor.setAdjacentLocation(Direction.NORTH, GreatHall); // If user types "north" from SouthofDoor, they
                                                                     // will go to GreatHall
        SouthofDoor.nextLocation = FirstBossFight; // If user unlocks and opens the door, they will go to FirstBossFight
                                                   // and they cannot go back to SouthofDoor

        // Library connections
        Library.setAdjacentLocation(Direction.WEST, GreatHall); // If user types "west" from Library, they will go to
                                                                // GreatHall
        Library.setAdjacentLocation(Direction.SOUTH, PotionClass); // If user types "south" from Library, they will go
                                                                   // to PotionClass
        Library.nextLocation = RestrictedSection; // If user unlocks and opens the door, they will go to
                                                  // RestrictedSection.

        // Potion Class connections
        PotionClass.setAdjacentLocation(Direction.NORTH, Library); // If user types "north" from PotionClass, they will
                                                                   // go to Library

        // Restricted Section connections
        RestrictedSection.setAdjacentLocation(Direction.EAST, Library); // If user types "east" from RestrictedSection,
                                                                        // they will go to Library

        // ITEMS //

        Item wand = new Item();
        wand.name = "Wand";
        wand.description = "A magical wand. It is made of wood and has a phoenix feather core. It is 11 inches long. \n It is the most important item in the game. You can use it to cast spells and fight enemies.";
        items.add(wand);
        playerInventory.add(wand);
        commandSystem.addNoun("wand");

        Item invisibilityCloak = new Item();
        invisibilityCloak.name = "Invisibility Cloak";
        invisibilityCloak.description = "An invisibility cloak. It is made of a special material that makes you invisible when you wear it. \n It is the second most important item in the game. You can use it to sneak past enemies.";
        items.add(invisibilityCloak);
        commandSystem.addNoun("invisibility cloak");

        Item potion = new Item();
        potion.name = "Potion";
        potion.description = "A potion. It is a magical liquid that can be used to heal you. \n It is the third most important item in the game. You can use it to heal yourself.";
        items.add(potion);
        commandSystem.addNoun("potion");

        Item book = new Item();
        book.name = "Book";
        book.description = "A book. It is a magical book that can be used to learn spells. \n It is the fourth most important item in the game. You can use it to learn spells.";
        items.add(book);
        commandSystem.addNoun("book");

        Item broomstick = new Item();
        broomstick.name = "Broomstick";
        broomstick.description = "A broomstick. It is a magical broomstick that can be used to fly. \n It is the fifth most important item in the game. You can use it to fly.";
        items.add(broomstick);
        commandSystem.addNoun("broomstick");

        Item swordItem = new Item();
        swordItem.name = "Sword";
        swordItem.description = "A sword. It is a magical sword that can be used to fight enemies. \n It is the sixth most important item in the game. You can use it to fight enemies.";
        items.add(swordItem);
        commandSystem.addNoun("sword");

        items = new ArrayList<>();
        Item key = new Item();
        key.name = "Key";
        key.description = "A shiny golden key.";
        items.add(key);
        commandSystem.addNoun("key");

        // ITEMS IN LOCATIONS //
        SouthofDoor.itemsHere.add(key); // Add the key to the SouthofDoor location
        Library.itemsHere.add(key); // Add the key to the Library location
        PotionClass.itemsHere.add(potion); // Add the key to the PotionClass location

    }

    // METHODS //
    public boolean hasKey() {
        for (Item item : playerInventory) {
            if (item.name.equalsIgnoreCase("Key")) {
                return true;
            }
        }
        return false;
    }

    public void openDoor() {

        if (!currentLocation.hasDoor) {
            System.out.println("There is no door to open.");
        } else if (currentLocation.locked) {
            if (hasKey()) {
                currentLocation.locked = false;
                System.out.println("You unlocked the door with the key.");
            } else {
                System.out.println("The door is locked. You need a key to open it.");
            }
        } else {
            System.out.println("You opened the door.");
            currentLocation = currentLocation.nextLocation;
            System.out.println(currentLocation.description);
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

        if (itemToUse == null) {
            System.out.println("You can't use the " + itemName + " here.");
            return;
        }

        switch (itemToUse.name.toLowerCase()) {
            case "key":
                if (currentLocation.hasDoor && !currentLocation.isDoorOpen && currentLocation.locked) {
                    currentLocation.isDoorOpen = true;
                    currentLocation.locked = false;
                    System.out.println("You use the key, the door is unlocked, now try to open it.");
                    playerInventory.remove(itemToUse);

                } else {
                    System.out.println("You can't use the " + itemToUse.name + " here.");
                }
                break;
            case "wand":
                if (currentLocation.enemies != null) {
                    for (Enemy enemy : currentLocation.enemies) {
                        enemy.health -= 25;
                        System.out.println("You used your wand and did 25 damage to the " + enemy.name + ".");
                        System.out.println("The " + enemy.name + " has " + enemy.health + " health left.");
                        player.decreaseHealth(5);
                        if (enemy.health <= 0) {
                            System.out.println("You killed the " + enemy.name + ".");
                            currentLocation.enemies.remove(enemy);
                        }
                    }
                } else {
                    System.out.println("You can't use the " + itemToUse.name + " here.");
                }
                break;
            case "potion":
                player.increaseHealth(25);
                System.out.println("You used the potion and increased your health by 25.");
                System.out.println("Your health is now " + player.health + ".");
                playerInventory.remove(itemToUse);
                break;
            
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

    public Enemy findEnemy(String name) {
        enemies = new ArrayList<>();
        for (Enemy enemy : currentLocation.enemies) {
            if (enemy.name.equalsIgnoreCase(name)) {
                return enemy;
            }
        }
        return null;
    }
}

// other methods...
