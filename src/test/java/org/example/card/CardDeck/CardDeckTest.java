package org.example.card.CardDeck;

import org.example.card.Card;
import org.example.card.CardSuit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CardDeckTest {

    private CardDeck cardDeck;

    @BeforeEach
    public void setUp() {
        cardDeck = new CardDeck();
    }

    @Test
    public void shouldBeCreatedProperly() {
        HashMap<String, Card> cardMap = new HashMap<>();

        while (!cardDeck.isDeckEmpty()) {
            Card card = cardDeck.drawNCards(1).get(0);
            cardMap.put(card.getValue().toString() + card.getSuit().toString(), card);
        }

        assertEquals(52, cardMap.size());

        for (int i = 1; i <= 13; i++) {
            for (CardSuit suit: CardSuit.values()) {
                Card card = cardMap.get(i + suit.toString());
                assertEquals(i, card.getValue());
                assertEquals(suit, card.getSuit());
            }
        }
    }

    @Test
    public void shouldAddAndRemoveCards() {
        Card card = cardDeck.getTopCard();
        Card removedCard = cardDeck.drawNCards(1).get(0);

        assertEquals(card, removedCard);
        assertNotEquals(removedCard, cardDeck.getTopCard());

        cardDeck.addCard(removedCard);

        assertEquals(removedCard, cardDeck.getTopCard());
    }

    @Test
    public void shouldThrowExceptionOnDrawingInvalidNumberOfCards() {
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            cardDeck.drawNCards(0);
        });

        assertEquals("You cannot draw less than 1 card", exception.getMessage());

        exception = assertThrows(IllegalStateException.class, () -> {
            cardDeck.drawNCards(53);
        });

        assertEquals("Trying to draw more cards than is present in the deck", exception.getMessage());
    }

    @Test
    public void shouldGetTopCardWork() {
        Card card = cardDeck.getTopCard();
        Card drawnCard = cardDeck.drawNCards(1).get(0);

        assertEquals(drawnCard, card);
    }

    @Test
    public void shouldThrowErrorOnCallingGetTopCardWhenDeckIsEmpty() {
        cardDeck.drawNCards(52);

        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            cardDeck.getTopCard();
        });

        assertEquals("Deck is already empty", exception.getMessage());
    }

    @Test
    public void shouldIsDeckEmptyWork() {
        assertFalse(cardDeck.isDeckEmpty());

        cardDeck.drawNCards(52);

        assertTrue(cardDeck.isDeckEmpty());
    }

}