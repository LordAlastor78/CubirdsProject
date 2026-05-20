package gal.uvigo.esei.aed1.cubirds.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ThirdDeliveryTest {

    @Test
    void discardedCardsReturnToDeck() {
        DeckOfCards deck = new DeckOfCards();
        DiscardedCards discarded = new DiscardedCards();

        int initialDeckSize = deck.size();

        Card c1 = deck.takeFirstCard();
        Card c2 = deck.takeFirstCard();

        discarded.addCard(c1);
        discarded.addCard(c2);

        assertEquals(initialDeckSize - 2, deck.size());
        assertEquals(2, discarded.size());

        discarded.moveAllToDeck(deck);

        assertEquals(initialDeckSize, deck.size());
        assertTrue(discarded.isEmpty());
    }

    @Test
    void playerCollectionCountersWork() {
        Player player = new Player("Luis");

        player.incrementSpeciesCounter(TypeBird.TUCAN);
        player.incrementSpeciesCounter(TypeBird.TUCAN);
        player.incrementSpeciesCounter(TypeBird.FLAMENCO);

        assertEquals(3, player.getCollectionSize());
        assertEquals(2, player.getCollectedSpeciesCount());
    }
}
