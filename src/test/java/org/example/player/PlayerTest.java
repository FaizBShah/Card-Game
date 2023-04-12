package org.example.player;

import org.example.card.Card;
import org.example.card.CardFactory;
import org.example.card.CardSuit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("Faiz");
    }

    @Test
    public void shouldGetNameWorkFine() {
        assertEquals("Faiz", player.getName());
    }

    @Test
    public void shouldInitializeHand() {
        assertTrue(player.getHand() instanceof ArrayList<Card>);
        assertEquals(0, player.getHand().size());
    }

    @Test
    public void shouldAddCardsToHand() {
        List<Card> cards = new ArrayList<>();

        cards.add(CardFactory.create(1, CardSuit.CLUB));
        cards.add(CardFactory.create(2, CardSuit.HEART));

        player.addCardsToHand(cards);

        assertEquals(2, player.getHand().size());
        assertEquals(cards.get(0), player.getHand().get(0));
        assertEquals(cards.get(1), player.getHand().get(1));
    }

    @Test
    public void shouldRemoveCardFromHand() {
        List<Card> cards = new ArrayList<>();

        cards.add(CardFactory.create(1, CardSuit.CLUB));
        cards.add(CardFactory.create(2, CardSuit.HEART));

        player.addCardsToHand(cards);
        player.removeCardFromHand(cards.get(0));

        assertEquals(1, player.getHand().size());
        assertEquals(cards.get(1), player.getHand().get(0));
    }

    @Test
    public void shouldThrowErrorOnInvalidCardRemoval() {
        List<Card> cards = new ArrayList<>();

        cards.add(CardFactory.create(1, CardSuit.CLUB));
        cards.add(CardFactory.create(2, CardSuit.HEART));

        player.addCardsToHand(cards);

        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            player.removeCardFromHand(CardFactory.create(3, CardSuit.DIAMOND));
        });

        assertEquals("The card does not belong to Faiz's hand", exception.getMessage());
    }

    @Test
    public void shouldGetMatchingCardWhenActionCardDrawnIsAllowed() {
        List<Card> cards = new ArrayList<>();

        cards.add(CardFactory.create(1, CardSuit.CLUB));
        cards.add(CardFactory.create(2, CardSuit.HEART));

        player.addCardsToHand(cards);

        Card matchingCard = CardFactory.create(2, CardSuit.DIAMOND);
        Card matchingCard2 = CardFactory.create(3, CardSuit.CLUB);

        assertEquals(cards.get(1), player.getMatchingCardFromHand(matchingCard, true));
        assertEquals(cards.get(0), player.getMatchingCardFromHand(matchingCard2, true));
    }

    @Test
    public void shouldGetMatchingCardWhenActionCardDrawnIsNotAllowed() {
        List<Card> cards = new ArrayList<>();

        cards.add(CardFactory.create(1, CardSuit.CLUB));
        cards.add(CardFactory.create(2, CardSuit.CLUB));

        player.addCardsToHand(cards);

        Card matchingCard = CardFactory.create(3, CardSuit.CLUB);

        assertEquals(cards.get(1), player.getMatchingCardFromHand(matchingCard, false));
    }

    @Test
    public void shouldReturnNullWhenNoMatchingCardInHand() {
        List<Card> cards = new ArrayList<>();

        cards.add(CardFactory.create(1, CardSuit.CLUB));
        cards.add(CardFactory.create(2, CardSuit.HEART));

        player.addCardsToHand(cards);

        Card matchingCard = CardFactory.create(3, CardSuit.DIAMOND);
        Card matchingCard2 = CardFactory.create(4, CardSuit.CLUB);

        assertNull(player.getMatchingCardFromHand(matchingCard, true));
        assertNull(player.getMatchingCardFromHand(matchingCard2, false));
    }
}