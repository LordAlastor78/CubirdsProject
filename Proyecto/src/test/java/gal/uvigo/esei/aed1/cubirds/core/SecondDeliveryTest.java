package gal.uvigo.esei.aed1.cubirds.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;
import es.uvigo.esei.aed1.tads.list.LinkedList;
import es.uvigo.esei.aed1.tads.list.List;

class SecondDeliveryTest {

    @Test
    void placingCardsCapturesEnclosedCards() throws Exception {
        Table table = new Table();

        Field filasField = Table.class.getDeclaredField("filas");
        filasField.setAccessible(true);

        @SuppressWarnings("unchecked")
        List<Card>[] filas = (List<Card>[]) filasField.get(table);

        List<Card> fila0 = new LinkedList<>();
        fila0.addLast(Card.FLAMENCO_1);
        fila0.addLast(Card.TUCAN_1);
        fila0.addLast(Card.PATO_1);
        filas[0] = fila0;

        List<Card> played = new LinkedList<>();
        played.addLast(Card.TUCAN_2);

        List<Card> captured = table.placeCardsOnRow(played, 0, true);

        assertEquals(1, captured.size());
        assertEquals(TypeBird.FLAMENCO, captured.get(0).getTypeBird());
    }
}
