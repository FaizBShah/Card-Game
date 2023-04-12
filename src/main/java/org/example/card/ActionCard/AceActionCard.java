package org.example.card.ActionCard;

import org.example.card.CardSuit;
import org.example.game.Game;

public class AceActionCard extends ActionCard {
    private final static ActionCardType actionCardType = ActionCardType.ACE;

    public AceActionCard(CardSuit suit) {
        super(actionCardType.getValue(), suit);
    }

    @Override
    public void doAction(Game game) {
        // If card is Ace, skip the next player's turn
        game.getPlayersManager().next();
    }
}
