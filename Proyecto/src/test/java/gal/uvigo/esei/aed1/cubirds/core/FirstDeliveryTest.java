package gal.uvigo.esei.aed1.cubirds.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import es.uvigo.esei.aed1.tads.list.List;

class FirstDeliveryTest {

    @Test
    void deckContainsAllDefinedCards() {
        DeckOfCards deck = new DeckOfCards();
        assertEquals(Card.values().length, deck.size());
    }

    @Test
    void tableStartsWithFourRowsAndThreeDistinctSpeciesPerRow() throws Exception {
        DeckOfCards deck = new DeckOfCards();
        Table table = new Table();
        table.inicializarMesa(deck);

        Field filasField = Table.class.getDeclaredField("filas");
        filasField.setAccessible(true);

        @SuppressWarnings("unchecked")
        List<Card>[] filas = (List<Card>[]) filasField.get(table);

        assertEquals(4, filas.length);

        for (List<Card> fila : filas) {
            assertEquals(3, fila.size());

            Set<TypeBird> species = new HashSet<>();
            for (int i = 0; i < fila.size(); i++) {
                species.add(fila.get(i).getTypeBird());
            }

            assertEquals(3, species.size());
        }
    }

    @Test
    void playerHandIsGroupedBySpecies() {
        Player player = new Player("Ana");
        player.addCardToHand(Card.TUCAN_1);
        player.addCardToHand(Card.TUCAN_2);
        player.addCardToHand(Card.PATO_1);

        assertEquals(3, player.getHandSize());
        assertEquals(2, player.getHandCountForSpecies(TypeBird.TUCAN));
        assertEquals(1, player.getHandCountForSpecies(TypeBird.PATO));
        assertFalse(player.hasNoCards());

        List<Card> tucanes = player.takeCardsOfSpecies(TypeBird.TUCAN);
        assertNotNull(tucanes);
        assertEquals(2, tucanes.size());
        assertEquals(1, player.getHandSize());
    }
}
