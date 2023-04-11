package org.example.card;

import org.example.card.ActionCard.AceActionCard;
import org.example.card.ActionCard.JackActionCard;
import org.example.card.ActionCard.KingActionCard;
import org.example.card.ActionCard.QueenActionCard;

public class CardFactory {
    public static Card create(Integer value, CardSuit suit) {
        if (value < 1 || value > 13) {
            throw new IllegalStateException("Trying to assign invalid value for the card");
        }

        return switch (value) {
            case 1 -> new AceActionCard(suit);
            case 11 -> new JackActionCard(suit);
            case 12 -> new QueenActionCard(suit);
            case 13 -> new KingActionCard(suit);
            default -> new Card(value, suit);
        };
    }
}
