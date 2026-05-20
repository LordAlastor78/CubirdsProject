package gal.uvigo.esei.aed1.cubirds.core;

import es.uvigo.esei.aed1.tads.list.LinkedList;
import es.uvigo.esei.aed1.tads.list.List;

/**
 * CLASE DeckOfCards — La baraja de cartas del juego
 * 
 * ¿QUÉ ES?
 * - Un contenedor que tiene TODAS las cartas del juego
 * - Las cartas están en un orden aleatorio (barajadas)
 * - Funciona como una cola (FIFO): quitas desde el frente, añades al final
 * 
 * ¿QUÉ CONTIENE?
 * - Todas las constantes del enum Card (Card.FLAMENCO_1, Card.TUCAN_5, etc.)
 * - Aproximadamente 110 cartas en total
 * 
 * ¿CÓMO SE USA?
 * 1. En el constructor de Game, se crea UN objeto DeckOfCards
 * 2. Cuando un jugador necesita cartas, se llama a takeFirstCard()
 * 3. Cuando se devuelven cartas no utilizadas, se llama a addLast()
 * 4. Cuando se acaba la baraja, DiscardedCards.moveAllToDeck() devuelve
 * todos los descartes aquí y se baraja de nuevo
 * 
 * ESTRUCTURA INTERNA:
 * - Usa una LinkedList<Card> privada llamada deckOfCards
 * - LinkedList es una lista enlazada que permite quitar/añadir rápidamente
 * desde el principio y el final
 */
public class DeckOfCards {

    // ============================================================================
    // ATRIBUTO PRIVADO
    // ============================================================================

    /** La lista privada que contiene todas las cartas (barajadas) */
    private final List<Card> deckOfCards;

    // ============================================================================
    // CONSTRUCTOR
    // ============================================================================

    /**
     * Constructor — Crea la baraja con TODAS las cartas y las baraja.
     * 
     * Proceso:
     * 1. Crea una LinkedList vacía
     * 2. Añade TODAS las constantes del enum Card (Card.values())
     * 3. Llama a shuffle() para mezclarlas aleatoriamente
     * 
     * Nota: Card.values() devuelve un array con todas las cartas del enum.
     */
    public DeckOfCards() {
        this.deckOfCards = new LinkedList<>();

        // Añadir TODAS las cartas del enum Card (aproximadamente 110 cartas)
        for (int i = 0; i < Card.values().length; i++) {
            deckOfCards.addLast(Card.values()[i]);
        }

        // Barajar la baraja
        shuffle();
    }

    // ============================================================================
    // MÉTODOS PÚBLICOS
    // ============================================================================

    /**
     * Saca y devuelve la PRIMERA carta de la baraja.
     * 
     * IMPORTANTE: La carta se ELIMINA de la baraja.
     * 
     * ¿QUIÉN LA USA?
     * - Game.repartirCartas() → para darle cartas a cada jugador
     * - Table.inicializarMesa() → para llenar la mesa de 4 filas
     * - Table.ensureRowHasTwoSpecies() → para rellenar una fila si tiene 1 especie
     * 
     * @return La primera carta de la baraja
     * @throws Exception Si la baraja está vacía (en la práctica, Game comprueba
     *                   isEmpty() antes)
     */
    public Card takeFirstCard() {
        return deckOfCards.removeFirst();
    }

    /**
     * Comprueba si la baraja está vacía.
     * 
     * ¿QUIÉN LA USA?
     * - Game.handleEmptyHand() → para decidir si hay suficientes cartas para
     * repartir
     * - Game.canDealCards() → para verificar si hay suficientes cartas
     * - Table.ensureRowHasTwoSpecies() → para dejar de rellenar una fila
     * 
     * @return true si no hay cartas, false si hay al menos 1
     */
    public boolean isEmpty() {
        return deckOfCards.isEmpty();
    }

    /**
     * Devuelve cuántas cartas quedan en la baraja.
     * 
     * ¿QUIÉN LA USA?
     * - Game.canDealCards() → para saber si hay suficientes cartas para repartir
     * 
     * @return Número de cartas en la baraja
     */
    public int size() {
        return deckOfCards.size();
    }

    /**
     * Añade UNA carta al FINAL de la baraja.
     * 
     * ¿CUÁNDO SE USA?
     * - Table.inicializarMesa() → cuando una carta candidata causa duplicado de
     * especie
     * en una fila, se devuelve al final de la baraja
     * - DiscardedCards.moveAllToDeck() → para devolver cartas descartadas
     * (y luego se mezcla todo)
     * 
     * @param card La carta a añadir al final de la baraja
     */
    public void addLast(Card card) {
        deckOfCards.addLast(card);
    }

    // ============================================================================
    // MÉTODO PRIVADO: shuffle()
    // ============================================================================

    /**
     * Baraja la baraja aleatoriamente.
     * 
     * ALGORITMO:
     * 1. Crea una LinkedList vacía llamada "shuffled"
     * 2. Mientras deckOfCards no esté vacía:
     * - Elige UN índice aleatorio entre 0 y size()-1
     * - Saca la carta en ese índice y la añade a "shuffled"
     * 3. Devuelve todas las cartas de "shuffled" a "deckOfCards"
     * 
     * RESULTADO: Las cartas están en orden aleatorio
     * 
     * ¿CUÁNDO SE LLAMA?
     * - En el constructor de DeckOfCards
     * - En DiscardedCards.moveAllToDeck() (para mezclar los descartes devueltos)
     */
    public void shuffle() {
        List<Card> shuffled = new LinkedList<>();

        // Mientras haya cartas en deckOfCards, movemos una aleatoria a shuffled
        while (!deckOfCards.isEmpty()) {
            // Elige un índice aleatorio entre 0 e size()-1
            int randomCard = (int) (Math.random() * deckOfCards.size());

            // Saca la carta en ese índice y la añade a la lista barajada
            shuffled.addLast(deckOfCards.remove(randomCard));
        }

        // Devuelve todas las cartas de shuffled a deckOfCards (ya en orden aleatorio)
        while (!shuffled.isEmpty()) {
            deckOfCards.addLast(shuffled.removeFirst());
        }
    }
}
