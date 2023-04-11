package org.example.card;

import org.example.card.ActionCard.AceActionCard;
import org.example.card.ActionCard.JackActionCard;
import org.example.card.ActionCard.KingActionCard;
import org.example.card.ActionCard.QueenActionCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardFactoryTest {

    @Test
    void shouldThrowExceptionWhenInvalidValue() {
        Throwable tooLowException = assertThrows(IllegalStateException.class, () -> {
            CardFactory.create(-1, CardSuit.SPADE);
        });
        Throwable tooHighException = assertThrows(IllegalStateException.class, () -> {
            CardFactory.create(14, CardSuit.SPADE);
        });
        assertEquals("Trying to assign invalid value for the card", tooLowException.getMessage());
        assertEquals("Trying to assign invalid value for the card", tooHighException.getMessage());
    }

    @Test
    void shouldCreateAceActionCard() {
        Card card = CardFactory.create(1, CardSuit.SPADE);
        assertEquals(1, card.getValue());
        assertEquals(CardSuit.SPADE, card.getSuit());
        assertTrue(card.isActionCard());
        assertTrue(card instanceof AceActionCard);
    }

    @Test
    void shouldCreateJackActionCard() {
        Card card = CardFactory.create(11, CardSuit.SPADE);
        assertEquals(11, card.getValue());
        assertEquals(CardSuit.SPADE, card.getSuit());
        assertTrue(card.isActionCard());
        assertTrue(card instanceof JackActionCard);
    }

    @Test
    void shouldCreateQueenActionCard() {
        Card card = CardFactory.create(12, CardSuit.SPADE);
        assertEquals(12, card.getValue());
        assertEquals(CardSuit.SPADE, card.getSuit());
        assertTrue(card.isActionCard());
        assertTrue(card instanceof QueenActionCard);
    }

    @Test
    void shouldCreateKingActionCard() {
        Card card = CardFactory.create(13, CardSuit.SPADE);
        assertEquals(13, card.getValue());
        assertEquals(CardSuit.SPADE, card.getSuit());
        assertTrue(card.isActionCard());
        assertTrue(card instanceof KingActionCard);
    }
}