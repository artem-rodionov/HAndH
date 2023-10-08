package game.gamemode;

import game.characters.Player;
import java.util.Scanner;

/**
 * @author hatka_bobrikov
 * @created 08.10.2023 19:53
 */
public class GameMode {
    private Player player;
    private int currentLevel;
    private GameTerminalUI gameUI = new GameTerminalUI();

    Scanner in = new Scanner(System.in);

    public GameMode() {
        gameUI.init();
        this.init();
    }

    private void init() {
        player = new Player(in);
        player.getInfo();
    }
}
