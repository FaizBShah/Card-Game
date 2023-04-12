package org.example.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    Card normalCard;
    Card actionCard;

    @BeforeEach
    void setUp() {
        normalCard = CardFactory.create(2, CardSuit.SPADE);
        actionCard = CardFactory.create(1, CardSuit.HEART);
    }

    @Test
    void shouldGetValueWorkFine() {
        assertEquals(2, normalCard.getValue());
        assertEquals(1, actionCard.getValue());
    }

    @Test
    void shouldGetSuitWorkFine() {
        assertEquals(CardSuit.SPADE, normalCard.getSuit());
        assertEquals(CardSuit.HEART, actionCard.getSuit());
    }

    @Test
    void shouldIsActionCardWorkFine() {
        assertFalse(normalCard.isActionCard());
        assertTrue(actionCard.isActionCard());
    }
}