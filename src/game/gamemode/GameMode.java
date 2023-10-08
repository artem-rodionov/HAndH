package game.gamemode;

import game.characters.Monster;
import game.characters.Player;

import java.util.Scanner;

/**
 * @author hatka_bobrikov
 * @created 08.10.2023 19:53
 */
public class GameMode {
    private Player player;
    private Monster monster;
    private boolean gameIsOn = true;

    Scanner in = new Scanner(System.in);

    public GameMode() {
        System.out.println("===    Запуск игры   ===");
        int mode = 0;
        boolean input = true;
        while (input) {
            try {
                System.out.print("Выберите режим игры: \n - ручной(1)\n - автоматический(2) \nРежим: ");
                mode = Integer.parseInt(in.nextLine());
                if (mode != 1 && mode != 2) throw new NumberFormatException();
                input = false;
            } catch (NumberFormatException e) {
                System.out.println("Неправильный выбор режима!");
            }
        }
        if (mode == 1) {
            this.init();
        } else {
            this.auto_init();
        }
    }

    private void init() {
        System.out.println("\n===  Создание игрока ===");
        player = new Player(in);
        player.getInfo();
        System.out.println("\n=== Создание монстра ===");
        monster = new Monster(in);
        monster.getInfo();
        this.start(1);
    }

    private void auto_init() {
        System.out.println("\n===  Создание игрока ===");
        player = new Player();
        player.getInfo();
        System.out.println("\n=== Создание монстра ===");
        monster = new Monster();
        monster.getInfo();
        this.start(2);
    }

    private void start(int mode) {
        System.out.println("\n\n=== Начало сражения ===\n\n");
        while (gameIsOn) {
            if (mode == 1 && player.getHealCount() > 0) {
                String answer = null;
                boolean input = true;
                while (input) {
                    try {
                        System.out.println("\nИспользовать лечение ? [Да/Нет]");
                        answer = in.nextLine();
                        if (!answer.equals("Да") && !answer.equals("Нет")) throw new NumberFormatException();
                        input = false;
                    } catch (NumberFormatException e) {
                        System.out.println("Неправильный вариант ответа!");
                    }
                }
                if (answer.equals("Да")) {
                    player.healing();
                    System.out.println("\nТекущее здоровье: " + player.getHealth() + "\nОсталось лечений: " + player.getHealCount());
                }
            }
            else if(player.getHealCount() > 0){
                if (player.getHealth() < player.getMaxHealth()*0.5) {
                    player.healing();
                    System.out.println("\nИспользуется лечение\nТекущее здоровье: " + player.getHealth() + "\nОсталось лечений: " + player.getHealCount());
                }
            }
            System.out.println(("\nАтака игрока"));
            if (player.fight(monster)) {
                monster.getInfo();
            }
            if (!monster.isAlive()) {
                System.out.println("=== Игрок победил ===");
                gameIsOn = false;
                break;
            }

            System.out.println("\nАтака монстра");
            if (monster.fight(player)) {
                player.getInfo();
            }
            if (!player.isAlive()) {
                System.out.println("=== Игрок проиграл ===");
                System.out.println("\uD83D\uDC80");
                gameIsOn = false;
                break;
            }
        }
    }
}
