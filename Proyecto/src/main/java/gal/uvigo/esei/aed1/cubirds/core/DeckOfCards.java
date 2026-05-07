package gal.uvigo.esei.aed1.cubirds.core;

import es.uvigo.esei.aed1.tads.list.LinkedList;
import es.uvigo.esei.aed1.tads.list.List;

public class DeckOfCards {

    private final List<Card> deckOfCards;

    // Constructor de la baraja de cartas
    public DeckOfCards() {
        this.deckOfCards = new LinkedList<>();

        List<Card> allCards = new LinkedList<>();

        for (int i = 0; i < Card.values().length; i++) {
            allCards.addLast(Card.values()[i]);
        }

        // Barajamos las cartas
        while (allCards.size() > 0) {
            int randomCard = (int) (Math.random() * allCards.size());
            deckOfCards.addLast(allCards.remove(randomCard));
        }
    }

    public Card takeFirstCard() {
        return deckOfCards.removeFirst();
    }

    public boolean isEmpty() {
        return deckOfCards.isEmpty();
    }

    public int size() {
        return deckOfCards.size();
    }

    public void addLast(Card card) {
        deckOfCards.addLast(card);
    }

    public void shuffle() {
        List<Card> shuffled = new LinkedList<>();

        while (!deckOfCards.isEmpty()) {
            int randomCard = (int) (Math.random() * deckOfCards.size());
            shuffled.addLast(deckOfCards.remove(randomCard));
        }

        while (!shuffled.isEmpty()) {
            deckOfCards.addLast(shuffled.removeFirst());
        }
    }
}