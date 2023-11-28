import java.util.Random;


public class Enemy {
    String name;
    int health;
    int damage;

    public Enemy(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public void attack(Player player) {
        Random random = new Random();
        int randomDamage = random.nextInt(damage + 1);
        player.decreaseHealth(randomDamage);
    }
}