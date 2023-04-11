package org.example.service;

import org.example.card.Card;
import org.example.game.Game;

public class PrintService {
    public static void getCurrentGameStatus(Game game) {
        System.out.println(game.getPlayersManager().getCurrentActivePlayer().getName() + " has the following cards in their hand");

        for (Card card: game.getPlayersManager().getCurrentActivePlayer().getHand()) {
            String cardValue = switch (card.getValue()) {
                case 1 -> "Ace";
                case 11 -> "Jack";
                case 12 -> "Queen";
                case 13 -> "King";
                default -> card.getValue().toString();
            };

            System.out.print(cardValue + " " + card.getSuit() + "       ");
        }

        System.out.println();

        if (game.getCardDeck().isDeckEmpty()) {
            throw new IllegalStateException("Deck is already empty");
        }

        Card card = game.getCardDeck().getTopCard();

        String cardValue = switch (card.getValue()) {
            case 1 -> "Ace";
            case 11 -> "Jack";
            case 12 -> "Queen";
            case 13 -> "King";
            default -> card.getValue().toString();
        };

        System.out.println("The current top card in the deck is: " + cardValue + " " + card.getSuit());
    }
}
