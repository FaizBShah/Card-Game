package org.example.player;

import java.util.ArrayList;
import java.util.List;

public class PlayersManager {
    private final List<Player> players;
    private Integer currentActivePlayer;
    private Integer playDirection;

    public PlayersManager() {
        players = new ArrayList<>();
        playDirection = 1;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Integer getNumberOfPlayers() {
        return players.size();
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

    public void changePlayDirection() {
        playDirection *= -1;
    }

    public void addPlayer(Player player) {
        if (players.size() == 4) {
            throw new IllegalStateException("Cannot have more than 4 players in the game");
        }

        // If this is the first player, set them as the current active player
        if (players.isEmpty()) {
            setCurrentActivePlayer(0);
        }

        players.add(player);
    }

    public PlayersManager next() {
        setCurrentActivePlayer((players.size() + currentActivePlayer + getPlayDirection()) % players.size());
        return this;
    }

    public Player getNextPlayer() {
        return players.get((players.size() + currentActivePlayer + getPlayDirection()) % players.size());
    }
}
