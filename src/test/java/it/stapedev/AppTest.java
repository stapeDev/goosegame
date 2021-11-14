package it.stapedev;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import it.stapedev.data.Player;
import it.stapedev.service.GooseLogics;

/**
 * Unit test for simple App.
 */
class AppTest {

    @Test
    void addPlayer() {
        Assertions.assertDoesNotThrow(() -> {
            GooseLogics game = new GooseLogics();
            game.addPlayer("Player1");
        });
    }

    @Test
    void addSamePlayer() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            GooseLogics game = new GooseLogics();
            game.addPlayer("Player1");
            game.addPlayer("Player1");
        });
    }

    @Test
    void moveOnlyPlayer1() {
        Assertions.assertDoesNotThrow(() -> {
            GooseLogics game = new GooseLogics();
            Player player1 = game.addPlayer("Player1");
            game.move(player1, game.lauchDice(player1));
        });
    }

    @Test
    void moveTwoPlayers() {
        Assertions.assertDoesNotThrow(() -> {
            GooseLogics game = new GooseLogics();
            Player player1 = game.addPlayer("Player1");
            Player player2 = game.addPlayer("Player2");
            game.move(player1, game.lauchDice(player1));
            game.move(player2, game.lauchDice(player2));
        });
    }

    @Test
    void gameTest5moves() {
        Assertions.assertDoesNotThrow(() -> {
            GooseLogics game = new GooseLogics();
            Player player1 = game.addPlayer("Player1");
            Player player2 = game.addPlayer("Player2");
            for (int i = 1; i < 5; i++) {
                game.move(player1, game.lauchDice(player1));
                game.move(player2, game.lauchDice(player2));
            }
        });
    }

    @Test
    void gameTest100moves() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            GooseLogics game = new GooseLogics();
            Player player1 = game.addPlayer("Player1");
            Player player2 = game.addPlayer("Player2");
            Player player3 = game.addPlayer("Player3");
            for (int i = 1; i < 100; i++) {
                game.move(player1, game.lauchDice(player1));
                game.move(player2, game.lauchDice(player2));
                game.move(player3, game.lauchDice(player3));
            }
        });
    }

    @Test
    void gameTest5movesWith3Players() {
        Assertions.assertDoesNotThrow(() -> {
            GooseLogics game = new GooseLogics();
            Player player1 = game.addPlayer("Player1");
            Player player2 = game.addPlayer("Player2");
            Player player3 = game.addPlayer("Player3");
            for (int i = 1; i < 5; i++) {
                game.move(player1, game.lauchDice(player1));
                game.move(player2, game.lauchDice(player2));
                game.move(player3, game.lauchDice(player3));
            }
        });
    }

    @Test
    void gameTest100movesWith3Players() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            GooseLogics game = new GooseLogics();
            Player player1 = game.addPlayer("Player1");
            Player player2 = game.addPlayer("Player2");
            Player player3 = game.addPlayer("Player3");
            for (int i = 1; i < 100; i++) {
                game.move(player1, game.lauchDice(player1));
                game.move(player2, game.lauchDice(player2));
                game.move(player3, game.lauchDice(player3));
            }
        });
    }

}
