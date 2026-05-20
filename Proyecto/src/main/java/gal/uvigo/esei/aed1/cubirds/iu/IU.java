package gal.uvigo.esei.aed1.cubirds.iu;

import java.util.Scanner;

import es.uvigo.esei.aed1.tads.list.List;
import gal.uvigo.esei.aed1.cubirds.core.TypeBird;

/**
 * CLASE IU (Interfaz de Usuario) — Comunicación con el jugador por consola
 * 
 * ¿QUÉ ES?
 * - Maneja TODA la comunicación entre el juego y el jugador
 * - Lee datos del teclado (números, texto)
 * - Escribe mensajes en pantalla
 * - Muestra menús para que el jugador elija
 * 
 * ¿QUIÉN LA USA?
 * - Game.java llama a métodos de IU constantemente
 * - IU devuelve lo que el jugador elige
 * 
 * FLUJO TÍPICO:
 * 1. Game pide "elige una especie"
 * 2. IU muestra un menú con las opciones
 * 3. IU lee la respuesta del jugador
 * 4. IU devuelve la opción elegida a Game
 * 
 * MÉTODOS PRINCIPALES:
 * - readNumber() — Lee un número del teclado
 * - readString() — Lee un texto del teclado
 * - displayMessage() — Imprime un mensaje en pantalla
 * - chooseBirdType() — Menú para elegir especie
 * - chooseRow() — Menú para elegir fila
 * - chooseSide() — Pregunta izquierda o derecha
 * - chooseYesNo() — Pregunta sí o no
 */
public class IU {

    // Scanner es la clase de Java para leer del teclado
    private final Scanner keyboard;

    // ========================================================================
    // CONSTRUCTOR
    // ========================================================================

    /**
     * Constructor — Inicializa el Scanner para leer del teclado
     */
    public IU() {
        keyboard = new Scanner(System.in);
    }

    // ========================================================================
    // MÉTODOS BÁSICOS: Leer y Mostrar
    // ========================================================================

    /**
     * Lee un NÚMERO del teclado y lo devuelve como int.
     * 
     * IMPORTANTE: Valida que sea un número válido. Si el usuario escribe
     * algo que NO es número, pregunta de nuevo.
     * 
     * ¿QUIÉN LA USA?
     * - Todos los métodos de menú (chooseBirdType, chooseRow, chooseSide, etc.)
     * 
     * @param msg El mensaje a mostrar antes de leer (ej: "Elige una opción: ")
     * @return El número que escribió el jugador
     */
    public int readNumber(String msg) {
        boolean repeat;
        int toret = 0;

        do {
            repeat = false;
            System.out.print(msg);
            try {
                toret = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException exc) {
                // Si no es un número válido, vuelve a intentar
                repeat = true;
            }
        } while (repeat);

        return toret;
    }

    /**
     * Lee un TEXTO (String) del teclado y lo devuelve.
     * 
     * ¿QUIÉN LA USA?
     * - Game (para leer el nombre de cada jugador)
     * - Game (para pausas: "presiona tecla para continuar")
     * 
     * @param msg El mensaje a mostrar antes de leer
     * @return El texto que escribió el jugador
     */
    public String readString(String msg) {
        String toret;
        System.out.print(msg);
        toret = keyboard.nextLine();
        return toret;
    }

    /**
     * Imprime un MENSAJE en pantalla (con salto de línea al final).
     * 
     * ¿QUIÉN LA USA?
     * - Game (para mostrar estados, resultados, etc.)
     * 
     * @param msg El mensaje a mostrar
     */
    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    // ========================================================================
    // MÉTODOS DE MENÚ: Elegir opciones
    // ========================================================================

    /**
     * Muestra un MENÚ para elegir UN tipo de pájaro de una lista.
     * 
     * PROCESO:
     * 1. Muestra todas las opciones numeradas (1, 2, 3, ...)
     * 2. Pide al jugador que escriba el número
     * 3. Valida que el número sea válido (entre 1 y cantidad de opciones)
     * 4. Devuelve el TypeBird elegido
     * 
     * EJEMPLO:
     * disponibles = [FLAMENCO, TUCAN, PATO]
     * Muestra:
     * 1. FLAMENCO
     * 2. TUCAN
     * 3. PATO
     * Jugador escribe: 2
     * Devuelve: TypeBird.TUCAN
     * 
     * ¿QUIÉN LA USA?
     * - Game.executePlayerTurn() → para elegir qué especie jugar
     * - Game.handleCollectionChoice() → para elegir qué especie bajar
     * 
     * @param availableTypes Lista de especies disponibles (List<TypeBird>)
     * @return La especie elegida
     */
    public TypeBird chooseBirdType(List<TypeBird> availableTypes) {
        int choice = -1;

        do {
            displayMessage("Escoge un tipo de pájaro válido:");
            for (int i = 0; i < availableTypes.size(); i++) {
                displayMessage((i + 1) + ". " + availableTypes.get(i));
            }
            choice = readNumber("Opción: ");
        } while (choice < 1 || choice > availableTypes.size());

        return availableTypes.get(choice - 1);
    }

    /**
     * Muestra un MENÚ para elegir UNA fila del tablero.
     * 
     * PROCESO:
     * 1. Muestra todas las filas disponibles (Fila 1, Fila 2, Fila 3, Fila 4)
     * 2. Pide al jugador que escriba el número
     * 3. Valida que sea entre 1 y rowCount
     * 4. Devuelve el índice (0, 1, 2, 3...) NO el número mostrado
     * 
     * NOTA: rowCount es 4 en esta versión, pero puede cambiar si modificas
     * Table.java
     * 
     * EJEMPLO:
     * rowCount = 4
     * Muestra:
     * 1. Fila 1
     * 2. Fila 2
     * 3. Fila 3
     * 4. Fila 4
     * Jugador escribe: 3
     * Devuelve: 2 (índice, no 3)
     * 
     * ¿QUIÉN LA USA?
     * - Game.executePlayerTurn() → para elegir en qué fila jugar
     * 
     * @param rowCount Cuántas filas hay (ej: 4)
     * @return El índice de la fila (0-based), NO el número mostrado
     */
    public int chooseRow(int rowCount) {
        int choice = -1;

        do {
            displayMessage("Elige una fila (1-" + rowCount + "):");
            for (int i = 0; i < rowCount; i++) {
                displayMessage((i + 1) + ". Fila " + (i + 1));
            }
            choice = readNumber("Fila: ");
        } while (choice < 1 || choice > rowCount);

        // IMPORTANTE: Devuelve choice - 1 para convertir a índice 0-based
        return choice - 1;
    }

    /**
     * Pregunta al jugador: ¿Izquierda o Derecha?
     * 
     * PROCESO:
     * 1. Pregunta si quiere colocar a la izquierda (1) o derecha (2)
     * 2. Devuelve true si elige izquierda, false si elige derecha
     * 
     * ¿QUIÉN LA USA?
     * - Game.executePlayerTurn() → para saber dónde colocar las cartas
     * 
     * @return true si elige izquierda (1), false si elige derecha (2)
     */
    public boolean chooseSide() {
        int choice = -1;

        do {
            choice = readNumber("¿En qué lado quieres colocar las cartas? (1 izquierda, 2 derecha): ");
        } while (choice < 1 || choice > 2);

        return choice == 1;
    }

    /**
     * Pregunta al jugador: ¿Sí o No?
     * 
     * PROCESO:
     * 1. Muestra el mensaje con la pregunta
     * 2. Lee 1 (sí) o 2 (no)
     * 3. Devuelve true si elige 1, false si elige 2
     * 
     * ¿QUIÉN LA USA?
     * - Game.handleCollectionChoice() → para preguntar si quiere bajar una especie
     * 
     * @param message La pregunta a hacer (ej: "¿Deseas bajar una especie?")
     * @return true si elige sí (1), false si elige no (2)
     */
    public boolean chooseYesNo(String message) {
        int choice = -1;

        do {
            choice = readNumber(message + " (1 si, 2 no): ");
        } while (choice < 1 || choice > 2);

        return choice == 1;
    }

}
