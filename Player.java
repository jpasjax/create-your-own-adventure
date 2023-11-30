import java.util.Random;


public class Player {
    // Other properties...
    int health;
    boolean key;

    public Player() {
        // Other initialization...
        health = 100; // Start with full health
    }

    public void increaseHealth(int amount) {
        health += amount;
    }

    public void decreaseHealth(int amount) {
        health -= amount;
        if (health <= 0) {
            System.out.println("You died! But your adventure doesn't have to end here. Try again! \n\n");
            System.exit(0);
            health = 0;

        }
    }

    public void attack(Enemy enemy) {
        Random random = new Random();
        int randomDamage = random.nextInt(10 + 1);
        enemy.health -= randomDamage; // Or some other amount

    }
    // Other methods...
}
