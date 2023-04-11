package org.example.game;

import org.example.card.ActionCard.ActionCard;
import org.example.card.Card;
import org.example.card.CardDeck.CardDeck;
import org.example.player.Player;
import org.example.player.PlayersManager;

import java.util.List;

public class Game {
    private final PlayersManager playersManager;
    private final CardDeck cardDeck;
    private boolean isGameActive;
    private boolean isActionCardPlayedLastTurn;

    public Game() {
        playersManager = new PlayersManager();
        cardDeck = new CardDeck();
        isGameActive = false;
        isActionCardPlayedLastTurn = false;
    }

    public PlayersManager getPlayersManager() {
        return playersManager;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public boolean isGameActive() {
        return isGameActive;
    }

    public boolean isActionCardPlayedLastTurn() {
        return isActionCardPlayedLastTurn;
    }

    private void setActionCardPlayedLastTurn(boolean actionCardPlayedLastTurn) {
        this.isActionCardPlayedLastTurn = actionCardPlayedLastTurn;
    }

    public void start() {
        if (playersManager.getNumberOfPlayers() < 2) {
            throw new IllegalStateException("Game needs at least 2 players to start");
        }

        List<Player> players = playersManager.getPlayers();

        for (Player player: players) {
            player.addCardsToHand(cardDeck.drawNCards(5));
        }

        isGameActive = true;
    }

    public void addPlayer(Player player) {
        playersManager.addPlayer(player);
    }

    public void playTurn() {
        Player currPlayer = playersManager.getCurrentActivePlayer();
        Card card = currPlayer.getMatchingCardFromHand(cardDeck.getTopCard(), !isActionCardPlayedLastTurn());

        if (card == null) {
            currPlayer.addCardsToHand(cardDeck.drawNCards(1));

            if (cardDeck.isDeckEmpty()) {
                isGameActive = false;
            }

            return;
        }

        if (card.isActionCard()) {
            ActionCard actionCard = (ActionCard) card;
            actionCard.doAction(this);
            setActionCardPlayedLastTurn(true);
        } else {
            setActionCardPlayedLastTurn(false);
        }

        if (currPlayer.getHand().isEmpty() || cardDeck.isDeckEmpty()) {
            isGameActive = false;
            return;
        }

        playersManager.next();
    }

    public String getGameResult() {
        if (!playersManager.getCurrentActivePlayer().getHand().isEmpty() && !cardDeck.isDeckEmpty()) {
            throw new IllegalStateException("Game is not over yet");
        }

        if (playersManager.getCurrentActivePlayer().getHand().isEmpty()) {
            return playersManager.getCurrentActivePlayer().getName() + " is the winner of the game";
        } else {
            return "The game is a draw as the deck has become empty";
        }
    }
}
