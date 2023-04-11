package org.example.card.ActionCard;

import org.example.card.Card;
import org.example.card.CardSuit;

public abstract class ActionCard extends Card {

    public ActionCard(Integer value, CardSuit suit) {
        super(value, suit);
    }

    public abstract void doAction();
}
