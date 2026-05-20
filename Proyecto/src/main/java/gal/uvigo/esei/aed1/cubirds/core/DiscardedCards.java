package gal.uvigo.esei.aed1.cubirds.core;

import es.uvigo.esei.aed1.tads.list.LinkedList;
import es.uvigo.esei.aed1.tads.list.List;

/**
 * CLASE DiscardedCards — El montón de cartas descartadas
 * 
 * ¿QUÉ ES?
 * - Un contenedor que acumula cartas descartadas durante el juego
 * - Las cartas van aquí cuando un jugador "baja" una especie a su colección
 * 
 * ¿CUÁNDO SE DESCARTA?
 * - Un jugador tiene, por ejemplo, 3 FLAMENCO en la mano
 * - Elige bajar FLAMENCO a su colección
 * - Las 3 cartas de FLAMENCO se envían aquí (al montón de descartes)
 * 
 * ¿QUÉ PASA DESPUÉS?
 * - Si la baraja se queda sin cartas y alguien se queda sin cartas en la mano:
 * → Se llama a DiscardedCards.moveAllToDeck()
 * → TODAS las cartas aquí se devuelven a la baraja
 * → Se baraja todo de nuevo
 * 
 * FLUJO TÍPICO:
 * 1. Jugador baja especie → cartas van a DiscardedCards (addCards)
 * 2. Baraja se queda vacía → DiscardedCards devuelve todo a la baraja
 * 3. Se baraja de nuevo → el juego continúa con las cartas recicladas
 */
public class DiscardedCards {

    // ============================================================================
    // ATRIBUTO PRIVADO
    // ============================================================================

    /** La lista que contiene todas las cartas descartadas */
    private final List<Card> discarded;

    // ============================================================================
    // CONSTRUCTOR
    // ============================================================================

    /**
     * Constructor — Crea un montón vacío de descartes.
     */
    public DiscardedCards() {
        this.discarded = new LinkedList<>();
    }

    // ============================================================================
    // MÉTODOS PÚBLICOS
    // ============================================================================

    /**
     * Añade UNA carta al montón de descartes.
     * 
     * ¿QUIÉN LA USA?
     * - Game.handleCollectionChoice() → cuando un jugador baja una especie a su
     * colección
     * (aunque normalmente se llama addCards en lugar de addCard)
     * 
     * @param card La carta a descartar
     */
    public void addCard(Card card) {
        discarded.addLast(card);
    }

    /**
     * Añade VARIAS cartas al montón de descartes.
     * 
     * ¿QUIÉN LA USA?
     * - Game.handleCollectionChoice() → cuando un jugador baja una especie
     * - Game.handleEmptyHand() → cuando todos menos el jugador activo se descartan
     * 
     * EJEMPLO:
     * Si un jugador tiene 3 FLAMENCO y baja FLAMENCO a su colección,
     * se llama a addCards(lista_de_3_flamenco).
     * 
     * @param cards Una lista de cartas a descartar
     */
    public void addCards(List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            discarded.addLast(cards.get(i));
        }
    }

    /**
     * Comprueba si el montón de descartes está vacío.
     * 
     * ¿QUIÉN LA USA?
     * - Table.ensureRowHasTwoSpecies() → para saber si hay descartes disponibles
     * cuando la baraja está vacía
     * - Game.handleEmptyHand() → para verificar si hay descartes
     * 
     * @return true si no hay cartas descartadas, false si hay al menos 1
     */
    public boolean isEmpty() {
        return discarded.isEmpty();
    }

    /**
     * Devuelve cuántas cartas hay en el montón de descartes.
     * 
     * ¿QUIÉN LA USA?
     * - (Usado principalmente para depuración o estadísticas)
     * 
     * @return Número de cartas descartadas
     */
    public int size() {
        return discarded.size();
    }

    /**
     * Mueve TODAS las cartas del montón de descartes a la baraja y las baraja.
     * 
     * ¿CUÁNDO SE LLAMA?
     * - Cuando la baraja está vacía Y un jugador se queda sin cartas en la mano
     * - Se llama en Game.handleEmptyHand()
     * 
     * PROCESO:
     * 1. Quita TODAS las cartas de discarded y las añade a la baraja
     * 2. Llama a deck.shuffle() para mezclar todo
     * 
     * RESULTADO:
     * - discarded queda vacío
     * - deck tiene todas las cartas (nuevamente disponibles)
     * - Las cartas están en orden aleatorio
     * 
     * @param deck La baraja a la que enviar las cartas
     */
    public void moveAllToDeck(DeckOfCards deck) {
        // Mientras haya cartas en discarded
        while (!discarded.isEmpty()) {
            // Saca la primera carta de discarded y la añade al final de deck
            deck.addLast(discarded.removeFirst());
        }
        // Baraja la baraja (ahora contiene todas las cartas)
        deck.shuffle();
    }
}
