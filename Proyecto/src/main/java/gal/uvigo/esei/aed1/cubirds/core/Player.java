package gal.uvigo.esei.aed1.cubirds.core;

import es.uvigo.esei.aed1.tads.list.LinkedList;
import es.uvigo.esei.aed1.tads.list.List;

public class Player {
    private String name;
    private List<List<Card>> hand; // Lista de grupos, cada grupo contiene cartas de la misma especie.
    private int[] speciesCounters; // Contador de especies en la zona de juego.

    // Constructor
    public Player(String name) {
        this.name = name;
        this.hand = new LinkedList<>();
        this.speciesCounters = new int[TypeBird.values().length];
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public int getHandSize() {
        int total = 0;
        for (int i = 0; i < hand.size(); i++) {
            total += hand.get(i).size();
        }
        return total;
    }

    public int getCollectedSpeciesCount() {
        int total = 0;
        for (int i = 0; i < speciesCounters.length; i++) {
            if (speciesCounters[i] > 0) {
                total += 1;
            }
        }
        return total;
    }

    public int getCollectionSize() {
        int total = 0;
        for (int i = 0; i < speciesCounters.length; i++) {
            total += speciesCounters[i];
        }
        return total;
    }

    public void incrementSpeciesCounter(TypeBird species) {
        int index = species.ordinal();
        speciesCounters[index] = speciesCounters[index] + 1;
    }

    public List<TypeBird> getPlayableSpecies() {
        List<TypeBird> species = new LinkedList<>();
        for (int i = 0; i < hand.size(); i++) {
            List<Card> group = hand.get(i);
            if (!group.isEmpty()) {
                species.addLast(group.get(0).getTypeBird());
            }
        }
        return species;
    }

    public int getHandCountForSpecies(TypeBird species) {
        int index = findGroupIndex(species);
        if (index == -1) {
            return 0;
        }
        return hand.get(index).size();
    }

    public int getSmallFlockForSpecies(TypeBird species) {
        int index = findGroupIndex(species);
        if (index == -1) {
            return 0;
        }
        return hand.get(index).get(0).getSmallFlock();
    }

    // Añadir carta a la mano
    public void addCardToHand(Card card) {
        boolean added = false;

        for (int i = 0; i < hand.size() && !added; i++) {
            List<Card> group = hand.get(i);
            if (group.get(0).getTypeBird() == card.getTypeBird()) {
                group.addLast(card);
                added = true;
            }
        }

        if (!added) {
            List<Card> newGroup = new LinkedList<>();
            newGroup.addLast(card);
            hand.addLast(newGroup);
        }
    }

    public void addCardsToHand(List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            addCardToHand(cards.get(i));
        }
    }

    public List<Card> takeCardsOfSpecies(TypeBird species) {
        List<Card> toReturn = new LinkedList<>();
        int index = findGroupIndex(species);

        if (index != -1) {
            toReturn = hand.get(index);
            hand.remove(index);
        }

        return toReturn;
    }

    public List<Card> takeAllCards() {
        List<Card> allCards = new LinkedList<>();

        for (int i = 0; i < hand.size(); i++) {
            List<Card> group = hand.get(i);
            for (int j = 0; j < group.size(); j++) {
                allCards.addLast(group.get(j));
            }
        }

        hand.clear();

        return allCards;
    }

    public boolean hasNoCards() {
        return hand.isEmpty();
    }

    private int findGroupIndex(TypeBird species) {
        int index = -1;
        for (int i = 0; i < hand.size() && index == -1; i++) {
            List<Card> group = hand.get(i);
            if (group.get(0).getTypeBird() == species) {
                index = i;
            }
        }
        return index;
    }

    // Método toString
    public String toString() {

        StringBuilder sb = new StringBuilder();
        if (this.hasNoCards()) {
            return "\nBaraja de " + this.getName() + ": Sin cartas!";
        } else {
            sb.append("\nBaraja de ").append(this.getName()).append(": ");

            for (int i = 0; i < hand.size(); i++) { // Por cada tipo de carta...
                List<Card> group = hand.get(i);
                for (int j = 0; j < group.size(); j++) {
                    Card card = group.get(j); // Me da una carta
                    sb.append("\n").append(card.toString());
                }
            }
        }

        return sb.toString();
    }

}
