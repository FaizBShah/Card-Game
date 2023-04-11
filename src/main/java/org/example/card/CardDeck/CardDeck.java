package org.example.card.CardDeck;

import org.example.card.Card;
import org.example.card.CardFactory;
import org.example.card.CardSuit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardDeck {
    private final Stack<Card> deck;

    public CardDeck() {
        this.deck = new Stack<>();
        List<Card> cards = new ArrayList<>(52);

        for (int value = 1; value <= 13; value++) {
            for (CardSuit suit: CardSuit.values()) {
                cards.add(CardFactory.create(value, suit));
            }
        }

        Collections.shuffle(cards);

        for (Card card: cards) {
            this.deck.push(card);
        }
    }

    public Card getTopCard() {
        if (isDeckEmpty()) {
            throw new IllegalStateException("Deck is already empty");
        }

        return deck.peek();
    }

    public void printTopCard() {
        if (isDeckEmpty()) {
            throw new IllegalStateException("Deck is already empty");
        }

        Card card = getTopCard();

        String cardValue = switch (card.getValue()) {
            case 1 -> "Ace";
            case 11 -> "Jack";
            case 12 -> "Queen";
            case 13 -> "King";
            default -> card.getValue().toString();
        };

        System.out.println("The current top card in the deck is: " + cardValue + " " + card.getSuit());
    }

    public void addCard(Card card) {
        deck.push(card);
    }

    public List<Card> drawNCards(int n) {
        if (n > deck.size()) {
            throw new IllegalStateException("Trying to draw more cards than is present in the deck");
        }

        List<Card> drawnCards = new ArrayList<>();

        while (n > 0) {
            drawnCards.add(deck.pop());
            n--;
        }

        return drawnCards;
    }

    public boolean isDeckEmpty() {
        return deck.isEmpty();
    }
}
