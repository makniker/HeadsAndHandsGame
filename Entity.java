import java.util.Random;

public abstract class Entity {
    private final static Random random = new Random();
    protected final String name;
    private final int attack;
    private final int defence;
    private int health;
    private final int maxHealth;
    private final int[] damage;

    public Entity(String name, int attack, int defence, int maxHealth, int minDamage, int maxDamage) {
        if (attack < 0 || attack > 30 || defence < 0 || defence > 30 || minDamage >= maxDamage) {
            throw new IllegalArgumentException("Bad stats for Entity!");
        }
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        damage = new int[maxDamage - minDamage + 1];
        for (int i = 0; i < damage.length; i++ ) {
            damage[i] = minDamage++;
        }
    }

    public int getDefence() {
        return defence;
    }
    public int getAttack() {
        return attack;
    }

    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Bad damage value!");
        }
        health = Math.min(health - damage, 0);
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setHealth(int newHealth) {
        if (newHealth <= 0) {
            throw new IllegalArgumentException("Bad health value!");
        }
        health = Math.min(newHealth, maxHealth);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void hit(Entity enemy) {
        if (!enemy.isAlive()) {
            return;
        }
        int attackMod = this.attack - enemy.getDefence();
        for (int i = 0; i < attackMod; i++) {
            int diceResult = random.nextInt(1, 7);
            if (diceResult == 5 || diceResult == 6) {
                enemy.takeDamage(damage[random.nextInt(damage.length)]);
                break;
            }
        }
    }
}
