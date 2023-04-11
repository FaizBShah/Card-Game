package org.example.card.ActionCard;

import org.example.card.CardSuit;

public class AceActionCard extends ActionCard {
    private final static ActionCardType actionCardType = ActionCardType.ACE;

    public AceActionCard(CardSuit suit) {
        super(actionCardType.getValue(), suit);
    }

    @Override
    public void doAction() {
        // Do action for Ace Card
    }
}
