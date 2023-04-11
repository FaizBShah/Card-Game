package org.example.card.ActionCard;

import org.example.card.CardSuit;
import org.example.game.Game;

public class KingActionCard extends ActionCard {
    private final static ActionCardType actionCardType = ActionCardType.KING;

    public KingActionCard(CardSuit suit) {
        super(actionCardType.getValue(), suit);
    }

    @Override
    public void doAction(Game game) {
        game.getPlayersManager().changePlayDirection();
        game.getPlayersManager().next();
    }
}
