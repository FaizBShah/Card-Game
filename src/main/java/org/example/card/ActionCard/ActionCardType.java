package org.example.card.ActionCard;

public enum ActionCardType {
    ACE(1),
    JACK(11),
    QUEEN(12),
    KING(13);

    private final Integer value;

    private ActionCardType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
