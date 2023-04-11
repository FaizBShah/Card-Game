package org.example.card.ActionCard;

import org.example.card.CardSuit;

public class QueenActionCard extends ActionCard {
    private final static ActionCardType actionCardType = ActionCardType.QUEEN;

    public QueenActionCard(CardSuit suit) {
        super(actionCardType.getValue(), suit);
    }

    @Override
    public void doAction() {
        // Do action for Queen Card
    }
}
