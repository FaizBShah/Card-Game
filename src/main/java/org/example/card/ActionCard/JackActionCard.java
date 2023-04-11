package org.example.card.ActionCard;

import org.example.card.CardSuit;

public class JackActionCard extends ActionCard {
    private final static ActionCardType actionCardType = ActionCardType.JACK;

    public JackActionCard(CardSuit suit) {
        super(actionCardType.getValue(), suit);
    }

    @Override
    public void doAction() {
        // Do action for Jack Card
    }
}
