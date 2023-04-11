package org.example.card.ActionCard;

import org.example.card.CardSuit;
import org.example.game.Game;
import org.example.player.Player;

public class QueenActionCard extends ActionCard {
    private final static ActionCardType actionCardType = ActionCardType.QUEEN;

    public QueenActionCard(CardSuit suit) {
        super(actionCardType.getValue(), suit);
    }

    @Override
    public void doAction(Game game) {
        Player nextPlayer = game.getPlayersManager().getNextPlayer();
        nextPlayer.addCardsToHand(game.getCardDeck().drawNCards(2));
    }
}
