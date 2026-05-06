package gal.uvigo.esei.aed1.cubirds.core;

import es.uvigo.esei.aed1.tads.list.LinkedList;
import es.uvigo.esei.aed1.tads.list.List;

public class Player {
    private String name;
    private List<List<Card>> hand; // Lista de grupos, cada grupo contiene cartas de la misma especie.

    // Constructor
    public Player(String name) {
        this.name = name;
        this.hand = new LinkedList<>();
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
        boolean found = false;

        for (int i = 0; i < hand.size() && !found; i++) {
            List<Card> group = hand.get(i);
            if (group.get(0).getTypeBird() == species) {
                toReturn = group;
                hand.remove(i);
                found = true;
            }
        }

        return toReturn;
    }

    public boolean hasNoCards() {
        return hand.isEmpty();
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

        // Otra manera de los fors:
        /*
         * int index = 0;
         * for (Pair par : hand) {
         * int handSize = hand.get(index).getValue().size();
         * for (int i=0; i<handSize; i++) {
         * Card card = hand.get(index).getValue().get(i); //me da una carta
         * sb.append("\n").append(card.getTypeBird()).append(": Bandada pequeña - ").
         * append(card.getSmallFlock())
         * .append("; Bandada grande - ").append(card.getLargeFlock());
         * }
         * index++;
         * }
         */
    }

}

// Métodos que fueron usados en algun punto:

/*
 * public void generateHand() {
 * 
 * orderCards(getHand());
 * }
 * 
 * private void orderCards(List<Card> hand) { // creamos grupos y metemos las
 * cartas en ellos
 * 
 * // List<Pair(typeBird, List<Card>)>
 * //
 * 
 * List<Card> groupFLAMENCO = new LinkedList<>();
 * List<Card> groupTUCAN = new LinkedList<>();
 * List<Card> groupPATO = new LinkedList<>();
 * List<Card> groupURRACA = new LinkedList<>();
 * List<Card> groupPETIRROJO = new LinkedList<>();
 * List<Card> groupLECHUZA = new LinkedList<>();
 * List<Card> groupCURRUCA = new LinkedList<>();
 * List<Card> groupGUACAMAYO = new LinkedList<>();
 * 
 * int initialCardCounter = this.hand.size();
 * 
 * for (int i = 0; i < initialCardCounter; i++) {
 * switch (this.hand.get(i).getTypeBird()) {
 * case FLAMENCO:
 * groupFLAMENCO.addLast(this.hand.get(i));
 * break;
 * case TUCAN:
 * groupTUCAN.addLast(this.hand.get(i));
 * break;
 * case PATO:
 * groupPATO.addLast(this.hand.get(i));
 * break;
 * case URRACA:
 * groupURRACA.addLast(this.hand.get(i));
 * break;
 * case PETIRROJO:
 * groupPETIRROJO.addLast(this.hand.get(i));
 * break;
 * case LECHUZA:
 * groupLECHUZA.addLast(this.hand.get(i));
 * break;
 * case CURRUCA:
 * groupCURRUCA.addLast(this.hand.get(i));
 * break;
 * case GUACAMAYO:
 * groupGUACAMAYO.addLast(this.hand.get(i));
 * break;
 * }
 * }
 * 
 * this.hand.clear(); // eliminar lo que había en la mano porque tiene el orden
 * raro
 * 
 * for (Card card : groupFLAMENCO)
 * this.hand.addLast(card);
 * for (Card card : groupTUCAN)
 * this.hand.addLast(card);
 * for (Card card : groupCURRUCA)
 * this.hand.addLast(card);
 * for (Card card : groupPATO)
 * this.hand.addLast(card);
 * for (Card card : groupURRACA)
 * this.hand.addLast(card);
 * for (Card card : groupPETIRROJO)
 * this.hand.addLast(card);
 * for (Card card : groupLECHUZA)
 * this.hand.addLast(card);
 * for (Card card : groupGUACAMAYO)
 * this.hand.addLast(card);
 * }
 * 
 * 
 */
