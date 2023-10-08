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

        boolean input = true;
        while(input) {
            try {
                System.out.println("Выберите режим игры: \n - ручной(1)\n - автоматический(2) \nРежим: ");
                int mode = Integer.parseInt(in.nextLine());
                if(mode != 1 || mode != 2) throw new NumberFormatException();
                input = false;
            }
            catch (NumberFormatException e) {
                System.out.println("Непривильный выбор режима!");
            }
        }
        this.init();
    }

    private void init() {
        System.out.println("\n===  Создание игрока ===");
        player = new Player(in);
        player.getInfo();
        System.out.println("\n=== Создание монстра ===");
        monster = new Monster(in);
        monster.getInfo();
        this.start();
    }

    private void start() {
        System.out.println("\n\n=== Начало сражения ===\n\n");
        while(gameIsOn) {

            System.out.println(("\nАтака игрока"));
            if(player.fight(monster)) {
                monster.getInfo();
            }
            if(!monster.isAlive()) {
                System.out.println("=== Игрок победил ===");
                gameIsOn = false;
                break;
            }

            System.out.println("\nАтака монстра");
            if(monster.fight(player)) {
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
