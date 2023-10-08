package game.gamemode;

import java.util.Random;

/**
 * @author hatka_bobrikov
 * @created 08.10.2023 20:56
 */
public class Dice {
    Random r = new Random();

    public int roll() {
        return r.nextInt(6);
    }
}
