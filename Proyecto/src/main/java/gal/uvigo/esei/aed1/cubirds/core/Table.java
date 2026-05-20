package gal.uvigo.esei.aed1.cubirds.core;

import es.uvigo.esei.aed1.tads.list.LinkedList;
import es.uvigo.esei.aed1.tads.list.List;

/**
 * CLASE Table — El tablero con 4 filas de cartas
 * 
 * ¿QUÉ ES?
 * - La mesa tiene 4 filas (vallas) boca arriba
 * - Los jugadores juegan sus cartas sobre estas filas
 * - Cuando un jugador rodea cartas, las captura
 * 
 * ESTRUCTURA:
 * - filas es un array de 4 LinkedList<Card>
 * - Cada fila es una lista de cartas
 * 
 * EJEMPLO VISUAL:
 * Mesa:
 * Fila 1: [FLAMENCO] [TUCAN] [PATO]
 * Fila 2: [URRACA] [PETIRROJO] [LECHUZA]
 * Fila 3: [CURRUCA] [GUACAMAYO] [FLAMENCO]
 * Fila 4: [TUCAN] [PATO] [URRACA]
 */
public class Table {

    // ========================================================================
    // ATRIBUTO PRIVADO
    // ========================================================================

    /** Array de 4 filas. Cada fila es una lista de cartas. */
    private List<Card>[] filas;

    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================

    /**
     * Constructor — Crea la mesa con 4 filas vacías.
     */
    public Table() {
        this.filas = new LinkedList[4];
        for (int i = 0; i < 4; i++) {
            this.filas[i] = new LinkedList<>();
        }
    }

    // ========================================================================
    // INICIALIZACIÓN: llenar la mesa al empezar
    // ========================================================================

    /**
     * Coloca 3 cartas en cada fila al INICIO del juego.
     * 
     * IMPORTANTE: Asegura que NO hay cartas del mismo tipo en la misma fila.
     * 
     * PROCESO:
     * 1. Para cada fila (0, 1, 2, 3):
     * - Mientras la fila tenga < 3 cartas:
     * - Saca una carta del mazo
     * - ¿Es del mismo tipo de las que ya hay en la fila?
     * - SÍ: Devuelve la carta al FINAL de la baraja
     * - NO: Añade la carta a la fila
     * 
     * EJEMPLO:
     * Fila vacía: []
     * - Saca FLAMENCO → lo añade: [FLAMENCO]
     * - Saca TUCAN → lo añade: [FLAMENCO, TUCAN]
     * - Saca FLAMENCO (¡duplicado!) → lo devuelve al final del mazo
     * - Saca PATO → lo añade: [FLAMENCO, TUCAN, PATO]
     * 
     * ¿QUIÉN LA USA?
     * - Game (en el constructor) → llama a inicializarMesa(deck)
     * 
     * @param deck La baraja de la que sacar cartas
     */
    public void inicializarMesa(DeckOfCards deck) {
        for (int i = 0; i < 4; i++) {
            while (this.filas[i].size() < 3) {
                Card candidate = deck.takeFirstCard();
                if (!tipoRepetidoEnFila(i, candidate)) {
                    // La carta NO es del mismo tipo, la añadimos
                    this.filas[i].addLast(candidate);
                } else {
                    // La carta ES del mismo tipo, la devolvemos al final de la baraja
                    deck.addLast(candidate);
                }
            }
        }
    }

    /**
     * Comprueba si una carta es del MISMO TIPO que las ya en la fila.
     * 
     * EJEMPLO:
     * Fila: [FLAMENCO, TUCAN]
     * ¿tipoRepetidoEnFila(0, FLAMENCO)? → true (FLAMENCO ya existe)
     * ¿tipoRepetidoEnFila(0, PATO)? → false (PATO no existe)
     * 
     * @param numFila   El índice de la fila (0, 1, 2, o 3)
     * @param candidate La carta a comprobar
     * @return true si el tipo ya existe en la fila, false si no
     */
    private boolean tipoRepetidoEnFila(int numFila, Card candidate) {
        boolean repetido = false;
        for (Card c : this.filas[numFila]) {
            if (c.getTypeBird() == candidate.getTypeBird()) {
                repetido = true;
            }
        }
        return repetido;
    }

    // ========================================================================
    // CONSULTAS
    // ========================================================================

    /**
     * Devuelve el número de filas (4 en esta versión).
     * 
     * @return 4 (número de filas)
     */
    public int getRowCount() {
        return this.filas.length;
    }

    // ========================================================================
    // OPERACIÓN PRINCIPAL: Colocar cartas y capturar
    // ========================================================================

    /**
     * Coloca cartas en una fila Y DEVUELVE las cartas capturadas.
     * 
     * ¿QUÉ PASA?
     * 1. Coloca las cartas a la izquierda o derecha de la fila
     * 2. Busca la primera/última carta del MISMO tipo que está jugando
     * 3. Captura todo lo que esté entre sus cartas y esa carta del mismo tipo
     * 4. Devuelve las cartas capturadas (como List<Card>)
     * 
     * EJEMPLO VISUAL (placeLeft=true):
     * ANTES: [A] [B] [B] [C]
     * Jugador coloca: 2x[B]
     * DESPUÉS: [B] [B] [A] [B] [B] [C]
     * CAPTURADA: [A] ← estaba entre los dos grupos de [B]
     * 
     * OTRO EJEMPLO (placeLeft=false):
     * ANTES: [A] [B] [B] [C]
     * Jugador coloca: 2x[B] a la derecha
     * DESPUÉS: [A] [B] [B] [C] [B] [B]
     * CAPTURADA: [C] ← estaba entre el grupo original de [B] y los nuevos
     * 
     * ¿QUIÉN LA USA?
     * - Game.executePlayerTurn() → cuando el jugador juega sus cartas
     * 
     * @param cardsToPlay Las cartas a colocar (todos del mismo tipo)
     * @param rowIndex    El índice de la fila (0, 1, 2, o 3)
     * @param placeLeft   true si colocar a la izquierda, false si a la derecha
     * @return Las cartas capturadas (o vacío si no captura nada)
     */
    public List<Card> placeCardsOnRow(List<Card> cardsToPlay, int rowIndex, boolean placeLeft) {
        List<Card> capturedCards = new LinkedList<>();

        if (cardsToPlay == null || cardsToPlay.isEmpty()) {
            return capturedCards;
        }

        List<Card> row = filas[rowIndex];
        TypeBird species = cardsToPlay.get(0).getTypeBird(); // Qué tipo estoy jugando
        int oldSize = row.size(); // Tamaño ANTES de colocar, para saber dónde buscar
        int matchIndex = -1; // -1 si no encuentra carta del mismo tipo

        // Busca la PRIMERA o ÚLTIMA carta del mismo tipo que está jugando
        if (placeLeft) {
            // Busca la PRIMERA aparición
            for (int i = 0; i < oldSize && matchIndex == -1; i++) {
                if (row.get(i).getTypeBird() == species) {
                    matchIndex = i;
                }
            }
        } else {
            // Busca la ÚLTIMA aparición
            for (int i = oldSize - 1; i >= 0 && matchIndex == -1; i--) {
                if (row.get(i).getTypeBird() == species) {
                    matchIndex = i;
                }
            }
        }

        // Coloca las cartas a la izquierda o derecha
        if (placeLeft) {
            for (int i = 0; i < cardsToPlay.size(); i++) {
                row.addFirst(cardsToPlay.get(i));
            }
        } else {
            for (int i = 0; i < cardsToPlay.size(); i++) {
                row.addLast(cardsToPlay.get(i));
            }
        }

        // Si encontró una carta del mismo tipo, captura lo que está entre
        if (matchIndex != -1) {
            if (placeLeft && matchIndex > 0) {
                int removeStart = cardsToPlay.size();
                int removeEnd = cardsToPlay.size() + matchIndex - 1;
                for (int i = removeEnd; i >= removeStart; i--) {
                    capturedCards.addFirst(row.remove(i));
                }
            }

            if (!placeLeft && matchIndex < oldSize - 1) {
                int removeStart = matchIndex + 1;
                int removeEnd = oldSize - 1;
                for (int i = removeEnd; i >= removeStart; i--) {
                    capturedCards.addFirst(row.remove(i));
                }
            }
        }

        return capturedCards;
    }

    // ========================================================================
    // RELLENO AUTOMÁTICO: Si una fila queda con 1 especie
    // ========================================================================

    /**
     * Si una fila tiene menos de 2 especies DIFERENTES, rellena con cartas.
     * 
     * ¿CUÁNDO SE LLAMA?
     * - Después de que un jugador coloca cartas y captura otras
     * - Si la fila quedó con una sola especie (ej: [TUCAN, TUCAN])
     * 
     * PROCESO:
     * 1. Mientras la fila tenga < 2 especies:
     * - Intenta sacar una carta del mazo
     * - Si el mazo está vacío y hay descartes → devuelve descartes al mazo
     * - Si no hay nada más, DETIENE el relleno
     * - Añade la carta a la fila
     * 
     * RESULTADO: La fila tiene al menos 2 especies diferentes (o termina con lo que
     * hay)
     * 
     * ¿QUIÉN LA USA?
     * - Game.executePlayerTurn() → después de capturar cartas
     * 
     * @param rowIndex       El índice de la fila a verificar
     * @param deck           La baraja (para sacar cartas)
     * @param discardedCards Los descartes (en caso de que la baraja se vacíe)
     */
    public void ensureRowHasTwoSpecies(int rowIndex, DeckOfCards deck, DiscardedCards discardedCards) {
        List<Card> row = filas[rowIndex];
        boolean canDraw = true;

        while (canDraw && !rowHasAtLeastTwoSpecies(row)) {
            // Si la baraja está vacía pero hay descartes, devuelve los descartes
            if (deck.isEmpty() && !discardedCards.isEmpty()) {
                discardedCards.moveAllToDeck(deck);
            }

            // Si la baraja sigue vacía, no podemos sacar más cartas
            if (deck.isEmpty()) {
                canDraw = false;
            } else {
                // Saca una carta y la añade a la fila
                row.addLast(deck.takeFirstCard());
            }
        }
    }

    /**
     * Comprueba si una fila tiene al MENOS 2 especies DIFERENTES.
     * 
     * EJEMPLO:
     * [TUCAN, TUCAN, TUCAN] → false (solo 1 especie)
     * [TUCAN, PATO] → true (2 especies diferentes)
     * [TUCAN, TUCAN, PATO, PATO] → true (2 especies)
     * 
     * @param row La fila a comprobar
     * @return true si tiene >= 2 especies, false si tiene < 2
     */
    private boolean rowHasAtLeastTwoSpecies(List<Card> row) {
        if (row.size() < 2) {
            return false;
        }

        TypeBird firstSpecies = row.get(0).getTypeBird();
        boolean different = false;

        for (int i = 1; i < row.size() && !different; i++) {
            if (row.get(i).getTypeBird() != firstSpecies) {
                different = true;
            }
        }

        return different;
    }

    // ========================================================================
    // REPRESENTACIÓN: toString()
    // ========================================================================

    /**
     * Devuelve una representación visual de la mesa.
     * 
     * EJEMPLO:
     * Mesa:
     * Fila 1: [FLAMENCO 2/3] [TUCAN 3/4] [PATO 4/6]
     * Fila 2: [URRACA 5/7] [PETIRROJO 6/9] ...
     * ...
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMesa: \n");
        for (int i = 0; i < filas.length; i++) {
            sb.append("Fila ").append(i + 1).append(": ");
            for (int j = 0; j < filas[i].size(); j++) {
                sb.append(filas[i].get(j).toString()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}