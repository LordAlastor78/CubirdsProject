package gal.uvigo.esei.aed1.cubirds.core;

import es.uvigo.esei.aed1.tads.list.LinkedList;
import es.uvigo.esei.aed1.tads.list.List;

public class DiscardedCards {

    private final List<Card> discarded;

    public DiscardedCards() {
        this.discarded = new LinkedList<>();
    }

    public void addCard(Card card) {
        discarded.addLast(card);
    }

    // Llama al método addCard tantas veces como cards.size().
    public void addCards(List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            discarded.addLast(cards.get(i));
        }
    }

    public boolean isEmpty() {
        return discarded.isEmpty();
    }

    public int size() {
        return discarded.size();
    }

    // Mueve todas las cartas descartadas a la baraja y llama al método shuffle().
    public void moveAllToDeck(DeckOfCards deck) {
        while (!discarded.isEmpty()) {
            deck.addLast(discarded.removeFirst());
        }
        deck.shuffle();
    }
}
