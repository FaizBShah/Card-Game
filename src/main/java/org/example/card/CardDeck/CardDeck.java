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

        // Create a list of all the 52 cards
        for (int value = 1; value <= 13; value++) {
            for (CardSuit suit: CardSuit.values()) {
                cards.add(CardFactory.create(value, suit));
            }
        }

        // Shuffle the cards
        Collections.shuffle(cards);

        // Push the shuffled cards into deck
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
