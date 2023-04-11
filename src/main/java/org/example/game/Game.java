package org.example.game;

import org.example.card.ActionCard.ActionCard;
import org.example.card.Card;
import org.example.card.CardDeck.CardDeck;
import org.example.player.Player;
import org.example.player.PlayersManager;
import org.example.service.PrintService;

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

    public void setIsGameActive(boolean isGameActive) {
        this.isGameActive = isGameActive;
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

        // Add 5 cards from the deck to every player
        for (Player player: players) {
            player.addCardsToHand(cardDeck.drawNCards(5));
        }

        // Start the game
        setIsGameActive(true);
    }

    public void addPlayer(Player player) {
        playersManager.addPlayer(player);
    }

    public void playTurn() {
        // Print the current status of the game before each turn
        PrintService.getCurrentGameStatus(this);

        // Get the current player as well as the card they want to put on the deck
        Player currPlayer = playersManager.getCurrentActivePlayer();
        // If isActionCardPlayedLastTurn is true, then they cannot draw an action card this turn
        Card card = currPlayer.getMatchingCardFromHand(cardDeck.getTopCard(), !isActionCardPlayedLastTurn());

        // If card is null, it means they have no valid card they can play, and hence need to draw a card
        // and add it to their hand
        if (card == null) {
            currPlayer.addCardsToHand(cardDeck.drawNCards(1));

            // If after drawing the card, deck becomes empty, then end the game
            if (cardDeck.isDeckEmpty()) {
                setIsGameActive(false);
            }

            return;
        }

        // If card is not null, then remove the card from hand and add it to deck
        currPlayer.removeCardFromHand(card);
        cardDeck.addCard(card);

        // If card is an action card, then do the corresponding action
        // and also set isActionCardPlayedLastTurn to true, else false
        if (card.isActionCard()) {
            ActionCard actionCard = (ActionCard) card;
            actionCard.doAction(this);
            setActionCardPlayedLastTurn(true);
        } else {
            setActionCardPlayedLastTurn(false);
        }

        // If the player's hand or the deck has become empty, stop the game
        if (currPlayer.getHand().isEmpty() || cardDeck.isDeckEmpty()) {
            setIsGameActive(false);
            return;
        }

        // Change the turn to next player
        playersManager.next();
    }

    public String getGameResult() {
        if (!playersManager.getCurrentActivePlayer().getHand().isEmpty() && !cardDeck.isDeckEmpty()) {
            throw new IllegalStateException("Game is not over yet");
        }

        // If the current player's hand is empty, then they have won the game, else if deck
        // is empty, then it's a draw
        if (playersManager.getCurrentActivePlayer().getHand().isEmpty()) {
            return playersManager.getCurrentActivePlayer().getName() + " is the winner of the game";
        } else {
            return "The game is a draw as the deck has become empty";
        }
    }
}
