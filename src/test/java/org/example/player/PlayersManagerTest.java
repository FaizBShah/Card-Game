package org.example.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayersManagerTest {

    PlayersManager playersManager;

    public List<Player> addPlayers() {
        List<Player> players = new ArrayList<>();

        players.add(new Player("Faiz"));
        players.add(new Player("Kanisht"));

        for (Player player: players) {
            playersManager.addPlayer(player);
        }

        return players;
    }

    @BeforeEach
    public void setUp() {
        playersManager = new PlayersManager();
    }

    @Test
    public void shouldBeInitializedProperly() {
        assertTrue(playersManager.getPlayers() instanceof ArrayList<Player>);
        assertEquals(0, playersManager.getPlayers().size());
        assertEquals(1, playersManager.getPlayDirection());
    }

    @Test
    public void shouldGettersBeWorkingProperly() {
        List<Player> players = addPlayers();

        assertEquals(players.get(0), playersManager.getPlayers().get(0));
        assertEquals(players.get(1), playersManager.getPlayers().get(1));
        assertEquals(2, playersManager.getNumberOfPlayers());
        assertEquals(players.get(0), playersManager.getCurrentActivePlayer());
        assertEquals(players.get(1), playersManager.getNextPlayer());
        assertEquals(1, playersManager.getPlayDirection());
    }

    @Test
    public void shouldSettersBeWorkingProperly() {
        List<Player> players = addPlayers();

        playersManager.setCurrentActivePlayer(1);

        assertEquals(players.get(1), playersManager.getCurrentActivePlayer());
    }

    @Test
    public void shouldNextWork() {
        List<Player> players = addPlayers();

        playersManager.next();

        assertEquals(players.get(1), playersManager.getCurrentActivePlayer());
    }

    @Test
    public void shouldAddPlayers() {
        addPlayers();

        Player newPlayer = new Player("Saif");

        playersManager.addPlayer(newPlayer);

        assertEquals(3, playersManager.getNumberOfPlayers());
        assertEquals(newPlayer, playersManager.getPlayers().get(2));
    }

    @Test
    public void shouldAddFirstPlayerToBeCurrentActive() {
        Player player = new Player("Faiz");
        playersManager.addPlayer(player);
        assertEquals(player, playersManager.getCurrentActivePlayer());
    }

    @Test
    public void shouldThrowErrorOnMorePlayers() {
        addPlayers();
        addPlayers();

        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            playersManager.addPlayer(new Player("Karan"));
        });

        assertEquals("Cannot have more than 4 players in the game", exception.getMessage());
    }

    @Test
    public void shouldPlayDirectionBeChanged() {
        playersManager.changePlayDirection();
        assertEquals(-1, playersManager.getPlayDirection());
    }
}