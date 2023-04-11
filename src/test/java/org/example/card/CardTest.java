package org.example.card;

import org.junit.jupiter.api.AfterEach;
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
    void getValue() {
        assertEquals(2, normalCard.getValue());
        assertEquals(1, actionCard.getValue());
    }

    @Test
    void getSuit() {
        assertEquals(CardSuit.SPADE, normalCard.getSuit());
        assertEquals(CardSuit.HEART, actionCard.getSuit());
    }

    @Test
    void isActionCard() {
        assertFalse(normalCard.isActionCard());
        assertTrue(actionCard.isActionCard());
    }
}