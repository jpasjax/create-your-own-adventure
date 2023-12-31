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
import java.util.Random;

// No import statement needed

public class GameState {
    public Location currentLocation;
    public Location nextLocation;
    Location GreatHall;
    Location SouthofDoor;
    Location Library;
    Location RestrictedSection;
    Location PotionClass;
    Location FirstBossFight;
    Location outsideHogwarts;
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

        NPC librarian = new NPC("Librarian", "Nice guy",
                "Librarian: Shhhhh. Quiet please. This is a library. I am the librarian here. Feel free to roam around anywhere. Anywhere but the door there, that is restricted to authorized personel only. Dont make me call the headmaster.");
        npcs.add(librarian);
        commandSystem.addNoun("librarian");

        NPC professor = new NPC("Professor", "A professor in the potions class",
                "Professor: Welcome to potions class. \nI am Professor Snape. \nI am the potions teacher. \nI am also the head of Slytherin house. \nI am a very strict teacher. \nI will be teaching you how to make potions. \nPick up the potion on the table there. \nYou can use it to heal yourself.");
        npcs.add(professor);
        commandSystem.addNoun("professor");

        NPC Olivander = new NPC("Olivander", "The wand guy",
                "Olivander: Welcome to my wand shop. I am Olivander. I am the wand maker. Seems like you are new here so I will be gifting you a wand. \n Pick up that Fire wand on the table there.");
        npcs.add(Olivander);
        commandSystem.addNoun("Olivander");

        NPC Goblin = new NPC("Goblin", "Hides in the vault",
                "Goblin: Hahahahahahah. Young wizard you may have put Hogwarts in great danger. By entering the Vault of Fear, you have unleashed beings of great evil. Now it is up to you to undo the damage you have done. \n There is a sword on the ground. Pick that up and be on your way if you want to save Hogwarts. \n But beware. There is a greater danger ahead that you have to worry about.");
        npcs.add(Goblin);
        commandSystem.addNoun("Goblin");

        NPC Bellatrix = new NPC("Bellatrix", "Villian 2",
                "Bellatrix LeStrange: So young wizard, I know you are here to stop me. But you are too late. I will unleash the evil that lies within this vault. I will destroy Hogwarts. I will destroy you.");
        npcs.add(Bellatrix);
        commandSystem.addNoun("Bellatrix");

        // ENEMIES THAT WILL BE ADDED IN THE GAME
        Enemy dementor = new Enemy("Dementor", 100, 20);
        Enemy Aragog = new Enemy("Aragog", 150, 20);
        Enemy boggart = new Enemy("Boggart", 180, 20);
        Enemy BellatrixLestrange = new Enemy("Bellatrix Lestrange", 200, 20);
        Enemy Voldemort = new Enemy("Voldemort", 200, 20);

        // LOCATIONS IN THE GAME //

        // STARTING LOCATION - Room
        currentLocation = new Location();
        currentLocation.name = "Room";
        currentLocation.description = "Welcome to Hogwarts School of Wizardry, young wizard. You are in your first year room. There is a hallway you walk to in the north of your room.";
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
        FirstBossFight.enemies = new ArrayList<>(); // Create an empty list of enemies
        FirstBossFight.enemies.add(dementor); // Add the dementor to the FirstBossFight location
        commandSystem.addNoun("Dementor");
        commandSystem.addNoun("wand");

        // THE NEXT LOCATION AFTER YOU GO EAST - Library
        Location Library = new Location();
        Library.name = "Hogwarts Library";
        Library.description = "You are now in the Hogwarts Library. There are books and a quiet study area. But there is a mysterious door in here but it is locked. Maybe the library has a secret room. \nYou can also head to you first class if you go South \nYou can also go back to the Great Hall by going west.";
        Library.lookaround = "You see books and a quiet study area. But there is a mysterious door here, but it is locked. There is a key on the library desk. Speak to the librarian about the library.";
        Library.hasDoor = true;
        Library.locked = true;
        Library.isDoorOpen = false;
        commandSystem.addNoun("key");

        Location RestrictedSection = new Location();
        RestrictedSection.name = "Restricted Section";
        RestrictedSection.description = "You have made your way to the Restricted Section of the library. To the east, you can back to the library. \nHowever if you go futher south, there is a mysterious entrance to somewhere else.";
        RestrictedSection.lookaround = "Further south, There is a mysterious entrance. A book has fallen off the shelf. Maybe you should pick it up.";

        Location beforeVault = new Location();
        beforeVault.name = "Before Vault";
        beforeVault.description = "You are now in a dark room. There is a secret vault to the south. But a dementor is blocking your path. You must defeat it to continue.  \nYou can go back to the Restricted Section by going north.";
        beforeVault.lookaround = "You are in a dark room. There is a secret vault to the south. But a dementor is blocking your path. You must defeat it to continue. \nYou can go back to the Restricted Section by going north.";
        beforeVault.enemies.add(dementor);

        Location VaultEntrance = new Location();
        VaultEntrance.name = "Vault Entrance";
        VaultEntrance.description = "You are now at the entrance to the mysterious door. You must unlock it to continue. \nOr you can take the stairs to the east.";
        VaultEntrance.lookaround = "You are now in the vault entrance. There is a door to the south.";
        VaultEntrance.hasDoor = true;
        VaultEntrance.locked = true;
        VaultEntrance.isDoorOpen = false;

        // BEGINNING OF ENDING 2 - Vault of Fear

        Location VaultOfFear = new Location();
        VaultOfFear.name = "Vault of Fear";
        VaultOfFear.description = "You are now in the Vault of Fear. One of the cursed vaults. Continue on east in the vault. Speak to the goblin here.";
        VaultOfFear.lookaround = "You are now in the Vault of Fear. One of the cursed vaults. Continue on east in the vault. Speak to the goblin here.";

        Location VaultOfFear2 = new Location();
        VaultOfFear2.name = "Vault of Fear";
        VaultOfFear2.description = "Bellatrix Lestrange: Hahahahahah, young wizard, you have made it this far. But you will not make it any further. You have unleashed the evil that lies within this vault. You have doomed Hogwarts. You are doomed. \n A boggart has appeared. You must defeat it to continue.";
        VaultOfFear2.lookaround = "A boggart has appeared. You must defeat it to continue.";
        VaultOfFear2.enemies.add(boggart);

        Location VaultOfFear3 = new Location();
        VaultOfFear3.name = "Vault of Fear";
        VaultOfFear3.description = "The room has gotten dark. Speak to Bellatrix Lestrange. ";
        VaultOfFear3.lookaround = "The room has gotten dark. Speak to Bellatrix Lestrange. ";

        Location VaultOfFear4 = new Location();
        VaultOfFear4.name = "Vault of Fear";
        VaultOfFear4.description = "This is it. Defeat Bellatrix Lestrange.";
        VaultOfFear4.lookaround = "There is a super potion on the ground that can be of use to you.";
        VaultOfFear4.enemies.add(BellatrixLestrange);


        // Set the next location to the east of the current location

        // THE NEXT LOCATION AFTER YOU GO WEST - Potion Class
        Location PotionClass = new Location();
        PotionClass.name = "Potion Class";
        PotionClass.description = "You are now in the Potion Class. There are potions brewing.";
        PotionClass.lookaround = "You see potions brewing and a blackboard with potion recipes. There is a healing potion on the desk. \nSpeak to Professor Snape. He will teach you more about the potion \nYou can go back to the Library by going north.";

        // THE NEXT LOCATION AFTER YOU BEAT THE DEMETOR - First Boss Fight
        Location outsideHogwarts = new Location();
        outsideHogwarts.name = "Outside Hogwarts";
        outsideHogwarts.description = "You have defeated the dementor, you now walk outside the school. If you go south, you will be in Hogsmeade. \nIf you go north, you will be in a thick Forest.";
        outsideHogwarts.lookaround = "You are outside Hogwarts. To the north, is a thick, dark forest. To the south, is Hogsmeade.";

        // Hogsmeade
        Location Hogsmeade = new Location();
        Hogsmeade.name = "Hogsmeade";
        Hogsmeade.description = "You are now in Hogsmeade Village. There is a shop to the east. \nThere is a forest to the north. \nThere is a road to the south.";
        Hogsmeade.lookaround = "You are in Hogsmeade. There is a wand shop to the east. \nThere is a forest to the north. \nThere is a road to the south.";

        // Wand Shop
        Location WandShop = new Location();
        WandShop.name = "Olivander's Wand Shop";
        WandShop.description = "You are now in the Olivander wand shop. Talk to the Olivander. You can go back to Hogsmeade by going west.";
        WandShop.lookaround = "You are in the wand shop. Talk to the Olivander. You can go back to Hogsmeade by going west.";

        // BEGINING OF ENDING 1 - Forbidden Forest
        Location ForbiddenForest = new Location();
        ForbiddenForest.name = "Forbidden Forest";
        ForbiddenForest.description = "You are now in the Forbidden Forest. There is Aragog (The giant spider) blocking your path, you must fight it to continue. \nIf you go back south, you will be in Hogsmeade.";
        ForbiddenForest.lookaround = "You are in the Forbidden Forest. There is a big spider blocking your path, you must fight it to continue. \nIf you go back south, you will be in Hogsmeade.";
        ForbiddenForest.enemies.add(Aragog);

        Location ForbiddenForest2 = new Location();
        ForbiddenForest2.name = "Forbidden Forest";
        ForbiddenForest2.description = "The path has gotten darker. There is a door in front of you. I wonder where it goes?";
        ForbiddenForest2.lookaround = "The path has gotten darker. There is a door in front of you. I wonder where it goes?";
        ForbiddenForest2.hasDoor = true;

        Location Graveyard = new Location();
        Graveyard.name = "Graveyard";
        Graveyard.description = "Lord Voldemort: At last, You have fallen for my trap young wizard. I know you've seen Harry Potter. So I suggest you tell me where he is. No? Fine I'll get it out of you myself.";
        Graveyard.lookaround = "There is a potion on the ground that will help you with the fight.";
        Graveyard.enemies.add(Voldemort);

        Location End = new Location();
        End.name = "End";
        End.description = "You did it. You have saved Hogwarts. You have won the game. \nThank you for playing. \nCreated by Jackson Smith and Benjamin Gold";
        End.lookaround = "You did it. You have saved Hogwarts. You have won the game. \nThank you for playing. \nCreated by Jackson Smith and Benjamin Gold";





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
        RestrictedSection.setAdjacentLocation(Direction.SOUTH, beforeVault); // If user types "south" from
                                                                             // RestrictedSection, they will go to
                                                                             // beforeVault

        // Before Vault connections
        beforeVault.setAdjacentLocation(Direction.NORTH, RestrictedSection); // If user types "north" from beforeVault,
                                                                             // they will go to RestrictedSection
        beforeVault.nextLocation = VaultEntrance; // If user defeats the dementor, they will go to outsideHogwarts and

        // First Boss Fight connections
        FirstBossFight.nextLocation = outsideHogwarts; // If user defeats the dementor, they will go to outsideHogwarts
                                                       // and they cannot go back to FirstBossFight

        // Outside Hogwarts connections
        outsideHogwarts.setAdjacentLocation(Direction.SOUTH, Hogsmeade); // If user types "north" from outsideHogwarts,
                                                                         // they will go to Hogsmeade
        outsideHogwarts.setAdjacentLocation(Direction.NORTH, ForbiddenForest); // If user types "north" from
                                                                               // outsideHogwarts, they will go to
                                                                               // ForbiddenForest

        // Hogsmeade connections
        Hogsmeade.setAdjacentLocation(Direction.NORTH, outsideHogwarts); // If user types "south" from Hogsmeade, they
                                                                         // will go to outsideHogwarts
        Hogsmeade.setAdjacentLocation(Direction.EAST, WandShop); // If user types "east" from Hogsmeade, they will go to

        // Vault Entrance connections
        VaultEntrance.setAdjacentLocation(Direction.EAST, outsideHogwarts); // If user types "south" from VaultEntrance,
                                                                            // they will go to beforeVault
        VaultEntrance.nextLocation = VaultOfFear; // If user unlocks and opens the door, they will go to VaultOfFear.

        // Forbidden Forest connections
        ForbiddenForest.nextLocation = ForbiddenForest2; // If user defeats Aragog, they will go to ForbiddenForest2 and
                                                          // they cannot go back to ForbiddenForest

        ForbiddenForest2.nextLocation = Graveyard; // If user unlocks and opens the door, they will go to Graveyard.

        // Vault of Fear Connections
        VaultOfFear.setAdjacentLocation(Direction.EAST, VaultOfFear2);

        // Vault of Fear 2
        VaultOfFear2.nextLocation = VaultOfFear3;

        // Vault of Fear 3
        VaultOfFear3.nextLocation = VaultOfFear4;


        // ITEMS //

        Item wand = new Item();
        wand.name = "Wand";
        wand.description = "A magical wand. It is made of wood and has a phoenix feather core. It is 11 inches long. \n It is the most important item in the game. You can use it to cast spells and fight enemies.";
        items.add(wand);
        playerInventory.add(wand);
        commandSystem.addNoun("wand");

        Item superPotion = new Item();
        superPotion.name = "SuperPotion";
        superPotion.description = "A super potion. It is a magical liquid that can be used to heal you. \n It is the fifth most important item in the game. You can use it to heal yourself.";
        items.add(superPotion);

        Item invisibilityCloak = new Item();
        invisibilityCloak.name = "Cloak";
        invisibilityCloak.description = "An invisibility cloak. It is made of a special material that makes you invisible when you wear it. \n It is the second most important item in the game. You can use it to sneak past enemies.";
        items.add(invisibilityCloak);
        commandSystem.addNoun("cloak");

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

        Item swordItem = new Item();
        swordItem.name = "Sword";
        swordItem.description = "A sword. It is a magical sword that can be used to fight enemies. \n It is the sixth most important item in the game. You can use it to fight enemies.";
        items.add(swordItem);
        commandSystem.addNoun("sword");

        Item fireWand = new Item();
        fireWand.name = "Fire Wand";
        fireWand.description = "A Fire wand. It is a magical wand that can be used to cast spells. \n It is the seventh most important item in the game. You can use it to cast spells.";
        items.add(fireWand);
        commandSystem.addNoun("fire");
        commandSystem.addNoun("wand");

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
        VaultEntrance.itemsHere.add(key); // Add the key to the VaultEntrance location
        VaultOfFear.itemsHere.add(swordItem);
        VaultOfFear4.itemsHere.add(superPotion);
        Graveyard.itemsHere.add(superPotion);

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
            System.out.println("The door is locked. You need a key to open it.");
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
                    Random rand = new Random();
                    for (Enemy enemy : currentLocation.enemies) {
                        // 40% chance to miss
                        if (rand.nextInt(100) < 40) {
                            System.out.println("You missed the " + enemy.name + ".");
                            player.decreaseHealth(10);
                        } else {
                            enemy.health -= 25;
                            System.out.println("You used your wand and did 25 damage to the " + enemy.name + ".");
                            System.out.println("The " + enemy.name + " has " + enemy.health + " health left.");
                            player.decreaseHealth(5);
                            if (enemy.health <= 0) {
                                System.out.println("You killed the " + enemy.name + ".");
                                currentLocation.enemies.remove(enemy);
                                if (enemy.name.equalsIgnoreCase("Dementor")) {
                                    Item invisibilityCloak = new Item();
                                    invisibilityCloak.name = "Cloak";
                                    invisibilityCloak.description = "An invisibility cloak. It is made of a special material that makes you invisible when you wear it. You can use it to sneak past enemies. You can only use it once.";
                                    items.add(invisibilityCloak);
                                    commandSystem.addNoun("Cloak");
                                    playerInventory.add(invisibilityCloak);
                                    System.out.println(
                                            "You picked up the invisibility cloak. You can use it to sneak past enemies.");
                                }
                                currentLocation = currentLocation.nextLocation;
                                System.out.println(currentLocation.description);
                                break;
                                    
                            }
                            if (enemy.health <= 0) {
                                System.out.println("You killed the " + enemy.name + ".");
                                currentLocation.enemies.remove(enemy);
                                // Check if the enemy's name is "Voldemort"
                                if (enemy.name.equalsIgnoreCase("Voldemort")) {
                                    System.out.println("Congratulations! You have defeated Voldemort and won the game!");
                                    System.exit(0);
                                }
                                break;
                            }
                            if (enemy.health <= 0) {
                                System.out.println("You killed the " + enemy.name + ".");
                                currentLocation.enemies.remove(enemy);
                                // Check if the enemy's name is "Voldemort"
                                if (enemy.name.equalsIgnoreCase("BellatrixLestrange")) {
                                    System.out.println("Congratulations! You have defeated BellatrixLestrange and won the game!");
                                    System.exit(0);
                                }
                                break;
                            }
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
            case "cloak":
                if (currentLocation.enemies != null) {
                    for (Enemy enemy : currentLocation.enemies) {
                        System.out.println("You used the invisibility cloak and snuck past the " + enemy.name + ".");
                        playerInventory.remove(itemToUse);
                        currentLocation = currentLocation.nextLocation;
                        System.out.println(currentLocation.description);
                        break;
                    }
                } else {
                    System.out.println("You can't use the " + itemToUse.name + " here.");
                }
                break;
            case "firewand":
                if (currentLocation.enemies != null) {
                    Random rand = new Random();
                    for (Enemy enemy : currentLocation.enemies) {
                        // 40% chance to miss
                        if (rand.nextInt(100) < 60) {
                            System.out.println("You missed the " + enemy.name + ".");
                            player.decreaseHealth(15);
                        } else {
                            enemy.health -= 50;
                            System.out.println("You used your Fire wand and did 50 damage to the " + enemy.name + ".");
                            System.out.println("The " + enemy.name + " has " + enemy.health + " health left.");
                            player.decreaseHealth(10);
                            if (enemy.health <= 0) {
                                System.out.println("You killed the " + enemy.name + ".");
                                currentLocation.enemies.remove(enemy);
                                currentLocation = currentLocation.nextLocation;
                                System.out.println(currentLocation.description);
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("You can't use the " + itemToUse.name + " here.");
                }
                break;
            case "sword":
                if (currentLocation.enemies != null) {
                    Random rand = new Random();
                    for (Enemy enemy : currentLocation.enemies) {
                        // 60% chance to miss
                        if (rand.nextInt(100) < 60) {
                            System.out.println("You missed the " + enemy.name + ".");
                            player.decreaseHealth(10);
                        } else {
                            enemy.health -= 45;
                            System.out.println("You used your sword and did 45 damage to the " + enemy.name + ".");
                            System.out.println("The " + enemy.name + " has " + enemy.health + " health left.");
                            player.decreaseHealth(5);
                            if (enemy.health <= 0) {
                                System.out.println("You killed the " + enemy.name + ".");
                                currentLocation.enemies.remove(enemy);
                                currentLocation = currentLocation.nextLocation;
                                System.out.println(currentLocation.description);
                                if (enemy.health <= 0) {
                                    System.out.println("You killed the " + enemy.name + ".");
                                    currentLocation.enemies.remove(enemy);
                                    // Check if the enemy's name is "Voldemort"
                                    if (enemy.name.equalsIgnoreCase("Voldemort")) {
                                        System.out.println("Congratulations! You have defeated Voldemort and won the game!");
                                        System.exit(0);
                                    }
                                    currentLocation = currentLocation.nextLocation;
                                    System.out.println(currentLocation.description);
                                    break;
                                }
                                if (enemy.health <= 0) {
                                    System.out.println("You killed the " + enemy.name + ".");
                                    currentLocation.enemies.remove(enemy);
                                    // Check if the enemy's name is "Voldemort"
                                    if (enemy.name.equalsIgnoreCase("BellatrixLestrange")) {
                                        System.out.println("Congratulations! You have defeated BellatrixLestrange and won the game!");
                                        System.exit(0);
                                    }
                                    break;
                                }
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("You can't use the " + itemToUse.name + " here.");
                }
                break;
                case "superpotion":
                player.increaseHealth(100);
                System.out.println("You used the super potion and increased your health by 100.");
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
