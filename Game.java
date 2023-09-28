import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {


    private Hero chooseClassForHero() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your hero class:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Rogue");
        while (true) {
            try {
                int heroChoice = scanner.nextInt();
                switch (heroChoice) {
                    case 1 -> {
                        return new Hero("Warrior", 20, 20, 120, 10, 30);
                    }
                    case 2 -> {
                        return new Hero("Mage", 20, 20, 100, 5, 50);
                    }
                    case 3 -> {
                        return new Hero("Rogue", 25, 15, 100, 10, 14);
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Type a number!");
            }
        }
    }
    public void start() {
        Hero hero = chooseClassForHero();
        System.out.println("You made your choice...\nDark dungeon beckons you with its treasures...");
        Level level = new Level();
        Scanner scanner = new Scanner(System.in);
        while (hero.isAlive() && !level.isLevelFinished()) {
            try {
                hero.printStats();
                System.out.println("Choose your action");
                System.out.println("1. Bravely step into next room");
                System.out.println("2. Ask Gods for help");
                System.out.println("3. Leave this place");
                int action = scanner.nextInt();
                switch (action) {
                    case 1 -> level.stepIntoRandomFight(hero);
                    case 2 -> {
                        if (hero.heal()) {
                            System.out.println("You were healed with ancient magic");
                        } else {
                            System.out.println("Nothing happens...");
                        }
                    }
                    case 3 -> {
                        System.out.println("You finished your journey in this Dungeon.");
                        level.finish();
                    }
                    default -> System.out.println("Enter right command, please");
                }
            } catch (Exception e) {
                System.out.println("Enter right command, please");
            }
        }
    }
}
