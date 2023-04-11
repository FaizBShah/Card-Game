package org.example.player;

import org.example.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addCardsToHand(List<Card> cards) {
        hand.addAll(cards);
    }

    public void removeCardFromHand(Card card) {
        if (!hand.remove(card)) {
            throw new IllegalStateException("The card does not belong to " + name + "'s hand");
        }
    }

    // To check whether any card from the hand has a similar rank or suit to the given card
    // Also checks whether the matching card from hand can be an action card or not
    public Card getMatchingCardFromHand(Card card, boolean canFetchActionCard) {
        for (Card handCard: hand) {
            if (!canFetchActionCard && card.isActionCard()) {
                continue;
            }

            if (handCard.getValue().intValue() == card.getValue().intValue() || handCard.getSuit() == card.getSuit()) {
                return handCard;
            }
        }

        return null;
    }
}
