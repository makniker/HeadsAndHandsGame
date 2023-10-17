import java.util.ArrayList;
import java.util.Random;

public class Level {
    private final static int MAX_MONSTERS_COUNT = 10;
    private final static Random random = new Random();
    private final ArrayList<Monster> monstersInLevel;

    public Level() {
        int numOfMonster = random.nextInt(1, MAX_MONSTERS_COUNT);
        monstersInLevel = new ArrayList<>(numOfMonster);
        for (int i = 0; i < numOfMonster; i++) {
            int minDamage = random.nextInt(1,20);
            monstersInLevel.add(new Monster("Skeleton", random.nextInt(1, 30), random.nextInt(1, 30), random.nextInt(1, 100), minDamage, minDamage + random.nextInt(1, 10)));
        }
    }

    public void stepIntoRandomFight(Hero hero) {
        if (monstersInLevel.isEmpty() || !hero.isAlive()) {
            return;
        }
        int randomIndex = random.nextInt(monstersInLevel.size());
        Monster currentEnemy = monstersInLevel.remove(randomIndex);
        if (hero.getAttack() - currentEnemy.getDefence() <= 0) {
            System.out.println("The last thing you hear before you die is the terrible roar of a monster.\nThis fight was too hard for you");
            return;
        }
        while (currentEnemy.isAlive() && hero.isAlive()) {
            hero.hit(currentEnemy);
            currentEnemy.hit(hero);
        }
        System.out.println(hero.isAlive() ? "You won a battle" : "The last thing you hear before you die is the terrible roar of a monster.");
    }

    public boolean isLevelFinished() {
        return monstersInLevel.isEmpty();
    }
    public void finish() {
        monstersInLevel.clear();
    }

}
