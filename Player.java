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
        if (health < 0) {
            health = 0;
        }
    }

    public void attack(Enemy enemy) {
        enemy.health -= 10; // Or some other amount
    }
    // Other methods...
}
