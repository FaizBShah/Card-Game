package org.example.card;

import org.example.card.ActionCard.ActionCard;

public class Card {
    private final Integer value;
    private final CardSuit suit;

    public Card(Integer value, CardSuit suit) {
        this.value = value;
        this.suit = suit;
    }

    public Integer getValue() {
        return value;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public boolean isActionCard() {
        return this instanceof ActionCard;
    }
}
