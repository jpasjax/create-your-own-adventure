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
        player.decreaseHealth(damage);
    }
}