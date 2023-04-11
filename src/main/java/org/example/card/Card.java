package org.example.card;

import org.example.card.ActionCard.ActionCard;

public class Card {
    private Integer value;
    private CardSuit suit;

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

    public void setSuit(CardSuit suit) {
        this.suit = suit;
    }

    public boolean isActionCard() {
        return this instanceof ActionCard;
    }
}
