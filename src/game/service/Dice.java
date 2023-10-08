package game.service;


/**
 * @author hatka_bobrikov
 * @created 08.10.2023 20:56
 */
public class Dice {

    public int roll() {
        return (int)(Math.random() * 6) + 1;
    }

    public boolean attacking(int count) {
        for (int i = 0; i < count; i++) {
            int rollNum = roll();
            if(rollNum == 6 || rollNum == 5) {
                return true;
            }
        }
        return false;
    }

    public int roll(int min, int max) {
        return (int)(Math.random() * (max + 1 - min) + min);
    }
}
