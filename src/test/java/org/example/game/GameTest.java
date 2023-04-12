package org.example.game;

import org.example.game.Game;
import org.example.player.PlayersManager;
import org.example.player.Player;
import org.example.card.CardDeck.CardDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void shouldBeCreatedProperly() {
        assertTrue(game.getPlayersManager() instanceof PlayersManager);
        assertTrue(game.getCardDeck() instanceof CardDeck);
        assertFalse(game.isGameActive());
        assertFalse(game.isActionCardPlayedLastTurn());
    }

    @Test
    public void shouldSettersWorkFine() {
        game.setIsGameActive(true);
        assertTrue(game.isGameActive());

        game.setActionCardPlayedLastTurn(true);
        assertTrue(game.isActionCardPlayedLastTurn());
    }

    @Test
    public void shouldAddPlayersProperly() {
        game.addPlayer(new Player("Faiz"));
        assertEquals(1, game.getPlayersManager().getNumberOfPlayers());
    }

    @Test
    public void shouldNotAddPlayerOnceGameHasStarted() {
        game.setIsGameActive(true);

        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            game.addPlayer(new Player("Faiz"));
        });

        assertEquals("Cannot add players once the game has started", exception.getMessage());
    }

    @Test
    public void shouldGameStartProperly() {
        game.addPlayer(new Player("Faiz"));
        game.addPlayer(new Player("Kanisht"));

        game.start();

        for (Player player: game.getPlayersManager().getPlayers()) {
            assertEquals(5, player.getHand().size());
        }

        assertTrue(game.isGameActive());
    }

    @Test
    public void shouldGameNotStartIfLessPeople() {
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            game.start();
        });

        assertEquals("Game needs at least 2 players to start", exception.getMessage());
    }

    public void shouldGameNotEndIfGameIsActive() {
        game.setIsGameActive(true);

        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            game.getGameResult();
        });

        assertEquals("Game is not over yet", exception.getMessage());
    }

}
