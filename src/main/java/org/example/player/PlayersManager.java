package org.example.player;

import java.util.ArrayList;
import java.util.List;

public class PlayersManager {
    private final List<Player> players;
    private Integer currentActivePlayer;
    private Integer playDirection;

    PlayersManager() {
        players = new ArrayList<>();
        playDirection = 1;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentActivePlayer() {
        return players.get(currentActivePlayer);
    }

    private void setCurrentActivePlayer(Integer currentActivePlayer) {
        this.currentActivePlayer = currentActivePlayer;
    }

    private Integer getPlayDirection() {
        return playDirection;
    }

    private void changePlayDirection() {
        playDirection *= -1;
    }

    public void addPlayer(Player player) {
        if (players.size() == 4) {
            throw new IllegalStateException("Cannot have more than 4 players in the game");
        }

        if (players.isEmpty()) {
            setCurrentActivePlayer(0);
        }

        players.add(player);
    }

    public void next() {
        setCurrentActivePlayer((players.size() + currentActivePlayer + playDirection) % players.size());
    }
}
