package gal.uvigo.esei.aed1.cubirds.core;

/**
 * ENUM Card — Todas las cartas del juego
 * 
 * ¿QUÉ ES?
 * - Una enumeración con TODAS las cartas del mazo
 * - Cada constante es una carta diferente (ej: FLAMENCO_1, TUCAN_5, etc.)
 * - Cada carta tiene:
 * → smallFlock: número mínimo de cartas de esta especie para bajar a colección
 * (ej: 2)
 * → largeFlock: número máximo (no se usa en esta versión del juego)
 * → typeBird: la especie de pájaro (FLAMENCO, TUCAN, etc.)
 * 
 * ¿CÓMO SE DISTRIBUYEN?
 * - FLAMENCO: 7 cartas con bandada pequeña=2, grande=3
 * - TUCAN: 10 cartas con bandada pequeña=3, grande=4
 * - PATO: 13 cartas con bandada pequeña=4, grande=6
 * - URRACA: 17 cartas con bandada pequeña=5, grande=7
 * - PETIRROJO: 20 cartas con bandada pequeña=6, grande=9
 * - LECHUZA: 10 cartas con bandada pequeña=3, grande=4
 * - CURRUCA: 20 cartas con bandada pequeña=6, grande=9
 * - GUACAMAYO: 13 cartas con bandada pequeña=4, grande=6
 * TOTAL: ~110 cartas en la baraja
 * 
 * ¿CÓMO SE USA?
 * 1. En DeckOfCards, se crean TODAS las cartas:
 * for (Card c : Card.values()) { baraja.add(c); }
 * 
 * 2. Para saber qué especie es una carta:
 * TypeBird tipo = carta.getTypeBird(); // Devuelve FLAMENCO, TUCAN, etc.
 * 
 * 3. Para saber cuántas cartas se necesitan para bajar a colección:
 * int minimo = carta.getSmallFlock(); // Devuelve 2, 3, 4, etc.
 * 
 * EJEMPLO EN CÓDIGO:
 * Card c = Card.FLAMENCO_1;
 * System.out.println(c.getTypeBird()); // Imprime: FLAMENCO
 * System.out.println(c.getSmallFlock()); // Imprime: 2
 * System.out.println(c); // Imprime: [FLAMENCO 2/3]
 * 
 * ¿CÓMO MODIFICAR?
 * - Si quieres AÑADIR NUEVAS CARTAS DE UN PÁJARO EXISTENTE:
 * 1. Copia una línea de FLAMENCO_1 pero cambia el nombre (FLAMENCO_8)
 * 2. La bandada pequeña/grande debe ser la MISMA para todas del mismo tipo
 * 
 * - Si quieres AÑADIR UN NUEVO PÁJARO COMPLETO:
 * 1. Primero añade a TypeBird.java
 * 2. Luego añade cartas aquí (ej: AGUILA_1, AGUILA_2, ...)
 * 3. Define la bandada pequeña/grande (ej: 8, 12)
 * 4. Player.java se ajustará automáticamente porque usa TypeBird.values()
 */
public enum Card {
  // FLAMENCO: 7 cartas, bandada pequeña=2, bandada grande=3
  FLAMENCO_1(2, 3, TypeBird.FLAMENCO),
  FLAMENCO_2(2, 3, TypeBird.FLAMENCO),
  FLAMENCO_3(2, 3, TypeBird.FLAMENCO),
  FLAMENCO_4(2, 3, TypeBird.FLAMENCO),
  FLAMENCO_5(2, 3, TypeBird.FLAMENCO),
  FLAMENCO_6(2, 3, TypeBird.FLAMENCO),
  FLAMENCO_7(2, 3, TypeBird.FLAMENCO),

  // TUCAN: 10 cartas, bandada pequeña=3, bandada grande=4
  TUCAN_1(3, 4, TypeBird.TUCAN),
  TUCAN_2(3, 4, TypeBird.TUCAN),
  TUCAN_3(3, 4, TypeBird.TUCAN),
  TUCAN_4(3, 4, TypeBird.TUCAN),
  TUCAN_5(3, 4, TypeBird.TUCAN),
  TUCAN_6(3, 4, TypeBird.TUCAN),
  TUCAN_7(3, 4, TypeBird.TUCAN),
  TUCAN_8(3, 4, TypeBird.TUCAN),
  TUCAN_9(3, 4, TypeBird.TUCAN),
  TUCAN_10(3, 4, TypeBird.TUCAN),

  // PATO: 13 cartas, bandada pequeña=4, bandada grande=6
  PATO_1(4, 6, TypeBird.PATO),
  PATO_2(4, 6, TypeBird.PATO),
  PATO_3(4, 6, TypeBird.PATO),
  PATO_4(4, 6, TypeBird.PATO),
  PATO_5(4, 6, TypeBird.PATO),
  PATO_6(4, 6, TypeBird.PATO),
  PATO_7(4, 6, TypeBird.PATO),
  PATO_8(4, 6, TypeBird.PATO),
  PATO_9(4, 6, TypeBird.PATO),
  PATO_10(4, 6, TypeBird.PATO),
  PATO_11(4, 6, TypeBird.PATO),
  PATO_12(4, 6, TypeBird.PATO),
  PATO_13(4, 6, TypeBird.PATO),

  // URRACA: 17 cartas, bandada pequeña=5, bandada grande=7
  URRACA_1(5, 7, TypeBird.URRACA),
  URRACA_2(5, 7, TypeBird.URRACA),
  URRACA_3(5, 7, TypeBird.URRACA),
  URRACA_4(5, 7, TypeBird.URRACA),
  URRACA_5(5, 7, TypeBird.URRACA),
  URRACA_6(5, 7, TypeBird.URRACA),
  URRACA_7(5, 7, TypeBird.URRACA),
  URRACA_8(5, 7, TypeBird.URRACA),
  URRACA_9(5, 7, TypeBird.URRACA),
  URRACA_10(5, 7, TypeBird.URRACA),
  URRACA_11(5, 7, TypeBird.URRACA),
  URRACA_12(5, 7, TypeBird.URRACA),
  URRACA_13(5, 7, TypeBird.URRACA),
  URRACA_14(5, 7, TypeBird.URRACA),
  URRACA_15(5, 7, TypeBird.URRACA),
  URRACA_16(5, 7, TypeBird.URRACA),
  URRACA_17(5, 7, TypeBird.URRACA),

  // PETIRROJO: 20 cartas, bandada pequeña=6, bandada grande=9
  PETIRROJO_1(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_2(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_3(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_4(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_5(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_6(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_7(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_8(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_9(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_10(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_11(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_12(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_13(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_14(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_15(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_16(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_17(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_18(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_19(6, 9, TypeBird.PETIRROJO),
  PETIRROJO_20(6, 9, TypeBird.PETIRROJO),

  // LECHUZA: 10 cartas, bandada pequeña=3, bandada grande=4
  LECHUZA_1(3, 4, TypeBird.LECHUZA),
  LECHUZA_2(3, 4, TypeBird.LECHUZA),
  LECHUZA_3(3, 4, TypeBird.LECHUZA),
  LECHUZA_4(3, 4, TypeBird.LECHUZA),
  LECHUZA_5(3, 4, TypeBird.LECHUZA),
  LECHUZA_6(3, 4, TypeBird.LECHUZA),
  LECHUZA_7(3, 4, TypeBird.LECHUZA),
  LECHUZA_8(3, 4, TypeBird.LECHUZA),
  LECHUZA_9(3, 4, TypeBird.LECHUZA),
  LECHUZA_10(3, 4, TypeBird.LECHUZA),

  // CURRUCA: 20 cartas, bandada pequeña=6, bandada grande=9
  CURRUCA_1(6, 9, TypeBird.CURRUCA),
  CURRUCA_2(6, 9, TypeBird.CURRUCA),
  CURRUCA_3(6, 9, TypeBird.CURRUCA),
  CURRUCA_4(6, 9, TypeBird.CURRUCA),
  CURRUCA_5(6, 9, TypeBird.CURRUCA),
  CURRUCA_6(6, 9, TypeBird.CURRUCA),
  CURRUCA_7(6, 9, TypeBird.CURRUCA),
  CURRUCA_8(6, 9, TypeBird.CURRUCA),
  CURRUCA_9(6, 9, TypeBird.CURRUCA),
  CURRUCA_10(6, 9, TypeBird.CURRUCA),
  CURRUCA_11(6, 9, TypeBird.CURRUCA),
  CURRUCA_12(6, 9, TypeBird.CURRUCA),
  CURRUCA_13(6, 9, TypeBird.CURRUCA),
  CURRUCA_14(6, 9, TypeBird.CURRUCA),
  CURRUCA_15(6, 9, TypeBird.CURRUCA),
  CURRUCA_16(6, 9, TypeBird.CURRUCA),
  CURRUCA_17(6, 9, TypeBird.CURRUCA),
  CURRUCA_18(6, 9, TypeBird.CURRUCA),
  CURRUCA_19(6, 9, TypeBird.CURRUCA),
  CURRUCA_20(6, 9, TypeBird.CURRUCA),

  // GUACAMAYO: 13 cartas, bandada pequeña=4, bandada grande=6
  GUACAMAYO_1(4, 6, TypeBird.GUACAMAYO),
  GUACAMAYO_2(4, 6, TypeBird.GUACAMAYO),
  GUACAMAYO_3(4, 6, TypeBird.GUACAMAYO),
  GUACAMAYO_4(4, 6, TypeBird.GUACAMAYO),
  GUACAMAYO_5(4, 6, TypeBird.GUACAMAYO),
  GUACAMAYO_6(4, 6, TypeBird.GUACAMAYO),
  GUACAMAYO_7(4, 6, TypeBird.GUACAMAYO),
  GUACAMAYO_8(4, 6, TypeBird.GUACAMAYO),
  GUACAMAYO_9(4, 6, TypeBird.GUACAMAYO),
  GUACAMAYO_10(4, 6, TypeBird.GUACAMAYO),
  GUACAMAYO_11(4, 6, TypeBird.GUACAMAYO),
  GUACAMAYO_12(4, 6, TypeBird.GUACAMAYO),
  GUACAMAYO_13(4, 6, TypeBird.GUACAMAYO);

  // ============================================================================
  // ATRIBUTOS PRIVADOS
  // ============================================================================
  private final int smallFlock; // Número mínimo de cartas para bajar (bandada pequeña)
  private final int largeFlock; // Número máximo de cartas (bandada grande, no usado)
  private final TypeBird typeBird; // Qué tipo de pájaro es esta carta

  // ============================================================================
  // CONSTRUCTOR
  // ============================================================================
  /**
   * Constructor de una carta individual.
   * 
   * @param smallFlock El número mínimo de cartas de esta especie que se necesitan
   *                   para "bajar" la especie a la zona de juego (colección)
   * @param largeFlock El número máximo (no se usa en esta versión del juego)
   * @param typeBird   El tipo de pájaro: FLAMENCO, TUCAN, PATO, etc.
   */
  Card(int smallFlock, int largeFlock, TypeBird typeBird) {
    this.smallFlock = smallFlock;
    this.largeFlock = largeFlock;
    this.typeBird = typeBird;
  }

  // ============================================================================
  // MÉTODOS PÚBLICOS (Getters)
  // ============================================================================

  /**
   * Devuelve el número mínimo de cartas (bandada pequeña).
   * 
   * EJEMPLO: Si tienes 2 FLAMENCO_1 en la mano y su smallFlock es 2,
   * puedes bajar FLAMENCO a tu colección.
   */
  public int getSmallFlock() {
    return smallFlock;
  }

  /**
   * Devuelve el número máximo de cartas (bandada grande).
   * Nota: En esta versión del juego no se usa.
   */
  public int getLargeFlock() {
    return largeFlock;
  }

  /**
   * Devuelve el tipo de pájaro de esta carta.
   * 
   * EJEMPLO: Card.FLAMENCO_1.getTypeBird() devuelve TypeBird.FLAMENCO
   */
  public TypeBird getTypeBird() {
    return typeBird;
  }

  // ============================================================================
  // MÉTODO toString() — Representación Visual
  // ============================================================================

  /**
   * Devuelve una representación legible de la carta para mostrar en la consola.
   * 
   * EJEMPLO: Card.FLAMENCO_1.toString() imprime: [FLAMENCO 2/3]
   * Donde:
   * - FLAMENCO = el tipo de pájaro
   * - 2 = bandada pequeña
   * - 3 = bandada grande
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(" [").append(typeBird)
        .append("  ").append(smallFlock)
        .append("/").append(largeFlock)
        .append("] ");
    return sb.toString();
  }
}
