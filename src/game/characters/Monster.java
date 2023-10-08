package game.characters;

import java.util.Scanner;

/**
 * @author hatka_bobrikov
 * @created 08.10.2023 19:34
 */
public class Monster extends Creature {

    public Monster(Scanner in) {
        super(in);
    }

    @Override
    public void getInfo() {
        System.out.println("\n=== Информация о монстре ===");
        super.getInfo();
    }
}
