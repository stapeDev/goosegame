package it.stapedev;

import it.stapedev.data.Player;
import it.stapedev.service.GooseLogics;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        GooseLogics game = new GooseLogics();
        Player player1 = game.addPlayer("Player1");
        Player player2 = game.addPlayer("Player2");
        Player player3 = game.addPlayer("Player3");
        for (int i = 1; i < 100; i++) {
            game.move(player1, game.lauchDice(player1));
            game.move(player2, game.lauchDice(player2));
            game.move(player3, game.lauchDice(player3));
        }
    }
}
