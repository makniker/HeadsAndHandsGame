import java.util.Random;

public class Hero extends Entity {

    private static final int MAX_HEAL = 4;
    private int healCount = MAX_HEAL;
    public Hero(String name, int attack, int defence, int maxHealth, int minDamage, int maxDamage) {
        super(name, attack, defence, maxHealth, minDamage, maxDamage);
    }

    public boolean heal() {
        if (healCount-- != 0) {
            int maxHeal = (int) (0.3 * getMaxHealth());
            int healAmount = new Random().nextInt(maxHeal) + 1;
            setHealth(getHealth() + healAmount);
            return true;
        }
        return false;
    }

    public void printStats() {
        System.out.println(isAlive() ? "You, brave " + name + ", have " + getHealth() + " health points left" : "This dungeon has swallowed you, no one will find your bones");
    }
}
