package org.example.card.ActionCard;

import org.example.card.CardSuit;
import org.example.game.Game;
import org.example.player.Player;

public class JackActionCard extends ActionCard {
    private final static ActionCardType actionCardType = ActionCardType.JACK;

    public JackActionCard(CardSuit suit) {
        super(actionCardType.getValue(), suit);
    }

    @Override
    public void doAction(Game game) {
        // If card is Jack, make the next player draw 4 cards in their hand
        Player nextPlayer = game.getPlayersManager().getNextPlayer();
        nextPlayer.addCardsToHand(game.getCardDeck().drawNCards(4));
    }
}
