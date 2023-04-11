package org.example.card.ActionCard;

import org.example.card.CardSuit;

public class KingActionCard extends ActionCard {
    private final static ActionCardType actionCardType = ActionCardType.KING;

    public KingActionCard(CardSuit suit) {
        super(actionCardType.getValue(), suit);
    }

    @Override
    public void doAction() {
        // Do action for King Card
    }
}
