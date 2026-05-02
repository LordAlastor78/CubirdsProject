Tercera Entrega Cubirds [AEDI - TADs]

[FASE 0] → Preparación del entorno y correcciones previas
[FASE 1] → Creación de la clase DiscardedCards con TADs
[FASE 2] → Actualización de Player: contador de especies (zona de juego)
[FASE 3] → Actualización de Game: atributo DiscardedCards + lógica de descartes
[FASE 4] → Lógica de rellenado de filas (mínimo 2 especies por fila)
[FASE 5] → Acción: descartar especie a zona de juego con validación de bandada
[FASE 6] → Condiciones de victoria: 7 especies en colección
[FASE 7] → Condición especial: jugador sin cartas → redistribución masiva
[FASE 8] → Condición de fin por agotamiento de baraja + descartes
[FASE 9] → Integración, pruebas y entrega
```

---

fase 0
correciones varias

 Pasos:

1. Verificar estructura de paquetes
   - Confirmar que los TADs están en: `src/main/java/es/uvigo/esei/aed1/tads/...`
   - Confirmar que tus clases core están en: `src/main/java/gal/uvigo/esei/aed1/cubirds/core/`
   - Ejecutar: `mvn clean compile` → debe compilar sin errores de paquete

2. Eliminar imports prohibidos
   - En todas tus clases (`Player`, `Table`, `Game`, `DeckOfCards`):
     - ❌ Eliminar: `import java.util.*;`
     -  Usar solo: `import es.uvigo.esei.aed1.tads.list.*;`

3. Corregir DeckOfCards: de static a instancia
   - El atributo `deckOfCards` debe ser de instancia, NO static
   - Todos los métodos (`takeFirstCard`, `addLast`, etc.) deben ser de instancia
   - Justificación: el enunciado pide "no usar static si solo vas a crear una instancia"

4. Actualizar Player.orderCards()
   - Cambiar de método con parámetro `orderCards(List<Card> hand)` a `orderCardsBySpecies()` sin parámetros
   - Implementar con array indexado por `TypeBird.ordinal()` para evitar código hardcoded

5. Actualizar Table.inicializarMesa()
   - Cambiar firma a `inicializarMesa(DeckOfCards deck)` recibiendo el mazo como parámetro
   - Usar método auxiliar `contieneTipoEnFila()` para lógica genérica


[                                       ]




---

fase 1 → Creación de la clase DiscardedCards con TADs

 Implementar el montón de descartes que almacena cartas descartadas y permite reincorporarlas al mazo.


src/main/java/gal/uvigo/esei/aed1/cubirds/core/DiscardedCards.java



1. Declarar la clase y atributos
   - Atributo privado: `private List<Card> discardedPile;`
   - Usar el TAD: `es.uvigo.esei.aed1.tads.list.LinkedList<Card>`

2 Constructor
   - Inicializar `discardedPile = new LinkedList<>();`

3. Métodos públicos esenciales
   - `public void addCard(Card card)`: añade una carta al montón
   - `public void addCards(List<Card> cards)`: añade múltiples cartas (iterar con TAD)
   - `public boolean isEmpty()`: verifica si el montón está vacío
   - `public int size()`: retorna número de cartas descartadas
   - `public List<Card> getAllCards()`: retorna una copia o referencia controlada del montón
   - `public void clear()`: vacía el montón (usada al reincorporar al mazo)

4 Método clave: reincorporar al mazo
   - `public void returnToDeck(DeckOfCards deck)`: 
     - Iterar sobre `discardedPile` y añadir cada carta al `deck` usando `deck.addLast(card)`
     - Llamar a `clear()` después de transferir

5.Método toString() para debug
   - Retornar representación legible del montón (ej: `"Descartes: [FLAMENCO, TUCÁN, ...]"`)



comprobar que al crear instancia de `DiscardedCards`, añadir 3 cartas, verificar `size() == 3`, luego `returnToDeck()` y verificar que el mazo las recibió.

---

fase 2 → Actualización de Player: contador de especies (zona de juego)

 Implementar la "zona de juego" de cada jugador como un contador por especie, para determinar la victoria (7 especies diferentes).

 Pasos:

1. Añadir atributo en Player
   - `private int[] speciesCounter;` → array de 8 enteros, indexado por `TypeBird.ordinal()`
   - Alternativa con TAD: `private List<Integer> speciesCounter;` (menos eficiente, pero válido)

2. Inicializar en constructor
   - `speciesCounter = new int[TypeBird.values().length];` → todos a 0 por defecto

3. Método para incrementar contador
   - `public void incrementSpeciesCounter(TypeBird species)`:
     - Validar que `species != null`
     - `speciesCounter[species.ordinal()]++;`

4. Método para consultar contador de una especie
   - `public int getSpeciesCount(TypeBird species)`: retorna `speciesCounter[species.ordinal()]`

5. Método para contar especies diferentes con contador > 0
   - `public int countDifferentSpeciesInCollection()`:
     - Iterar sobre `speciesCounter`, contar cuántos valores son `> 0`
     - Este método será clave para verificar victoria (7 especies)

6 Actualizar toString()
   - Mostrar zona de juego: `"Zona: Flamenco(2), Tucán(1), ..."`

 Nota importante:
- La "colección" (`collection`) del enunciado se refiere a las cartas capturadas de la mesa (entrega 2)
- La "zona de juego" (`speciesCounter`) es NUEVA: representa especies completadas para ganar
- No confundir: `collection` = cartas físicas recogidas; `speciesCounter` = progreso hacia victoria

comprobar que al crear instancia de `Player`, incrementar Flamenco 3 veces, verificar `getSpeciesCount(FLAMENCO) == 3` y `countDifferentSpeciesInCollection() == 1`.



[                                       ]






---

fase 3 → Actualización de Game: atributo DiscardedCards + lógica de descartes

Objetivo: Integrar el montón de descartes en el flujo principal del juego.



1. Añadir atributo en Game
   - `private DiscardedCards discardedCards;`

2. Inicializar en constructor
   - `this.discardedCards = new DiscardedCards();`

3. Actualizar método executePlayerTurn() (de la entrega 2)
   - Después de jugar cartas en mesa y capturar rodeadas:
     - Si se capturaron cartas, añadirlas a la mano del jugador (ya implementado)
     - NUEVO: Preguntar al jugador si desea descartar una especie a su zona de juego

4. Implementar flujo de descarte a zona de juego
   - Pedir especie a descartar (de las disponibles en mano)
   - Contar cuántas cartas de esa especie tiene el jugador en mano
   - Obtener el valor de `smallFlock` de esa especie desde el enum `Card` (cualquier carta de esa especie sirve)
   - Si `cartasEnMano >= smallFlock`:
     - Eliminar TODAS las cartas de esa especie de la mano del jugador
     - Añadir esas cartas al `discardedCards` montón
     - Incrementar el contador de esa especie en la zona de juego del jugador: `player.incrementSpeciesCounter(species)`
     - Mostrar mensaje de éxito
   - Si no cumple:
     - Mostrar mensaje: "No puedes bajar [especie]: necesitas al menos [smallFlock] cartas y tienes [X]"

5. Actualizar IU para estas interacciones
   - Añadir métodos en `IU.java` si es necesario: `readSpeciesOption()`, `confirmAction()`

 Check de salida: En una partida de prueba, jugar turno, descartar Flamenco (si tienes >=2 cartas), verificar que: 
- Las cartas desaparecen de la mano
- Aparecen en `discardedCards`
- El contador de Flamenco del jugador incrementa en 1






[                                       ]








---

fase 4 → Lógica de rellenado de filas (mínimo 2 especies por fila)

Objetivo: Después de capturar cartas de una fila, asegurar que queden al menos 2 especies diferentes en esa fila.

 Contexto:
- Esto ocurre DESPUÉS de que un jugador juega cartas y posiblemente captura cartas rodeadas
- Si tras la captura, todas las cartas restantes en esa fila son de la MISMA especie → hay que rellenar

 Pasos:

1. Crear método auxiliar en Table
   - `private boolean filaTieneUnaSolaEspecie(int rowIndex)`:
     - Si la fila está vacía → retornar `false` (no aplica)
     - Obtener la especie de la primera carta
     - Iterar sobre el resto: si alguna carta tiene especie diferente → retornar `false`
     - Si todas son iguales → retornar `true`

2. Crear método para rellenar fila hasta obtener especie diferente
   - `public void rellenarFilaConEspecieDiferente(int rowIndex, DeckOfCards deck, DiscardedCards discarded)`:
     - Mientras `filaTieneUnaSolaEspecie(rowIndex)`:
       - Si `!deck.isEmpty()`: sacar carta con `deck.takeLastCard()`
       - Si `deck.isEmpty() && !discarded.isEmpty()`:
         - Llamar a `discarded.returnToDeck(deck)` para reincorporar descartes
         - Barajar el mazo: implementar `deck.shuffle()` (algoritmo Fisher-Yates con TAD)
         - Sacar carta con `deck.takeLastCard()`
       - Si `deck.isEmpty() && discarded.isEmpty()`: romper bucle (no hay más cartas)
       - Si la carta sacada tiene especie DIFERENTE a las de la fila → añadirla con `filas[rowIndex].addLast(card)` y salir del bucle
       - Si tiene la MISMA especie → devolver al final del mazo con `deck.addLast(card)` y continuar

3. Integrar en Table.playCardsOnRow()
   - Después de colocar cartas y capturar rodeadas:
     - Llamar a `rellenarFilaConEspecieDiferente(rowIndex, deck, discardedCards)`
     - Nota: necesitarás pasar `deck` y `discardedCards` como parámetros a `playCardsOnRow()`

4. Implementar deck.shuffle() en DeckOfCards
   - Usar algoritmo Fisher-Yates con el TAD `List`:
     ```
     for i from size-1 down to 1:
         j = random index in [0, i]
         swap elements at i and j using get/set del TAD
     ```

 Consideraciones:
- El barajado debe usar `Math.random()` y los métodos `get()` y `set()` del TAD `List`
- Asegurar que `rellenarFilaConEspecieDiferente` no entra en bucle infinito si no hay cartas disponibles

 Check de salida: En una fila con [Flamenco, Flamenco], tras jugar y capturar, si queda [Flamenco], el sistema debe sacar cartas del mazo hasta encontrar una no-Flamenco y añadirla.





[                                       ]










---

fase 5 → Acción: descartar especie a zona de juego con validación de bandada

Objetivo: Implementar completamente la mecánica de "bajar bandada" a la zona de juego.

 Pasos detallados:

1. Obtener smallFlock de una especie
   - Crear método helper en `Card` o `TypeBird`:
     - `public static int getSmallFlockRequirement(TypeBird species)`:
       - Iterar sobre `Card.values()` hasta encontrar primera carta de esa especie
       - Retornar `card.getSmallFlock()`
       - Nota: todas las cartas de la misma especie tienen el mismo `smallFlock`

2. Validar antes de descartar
   - En `Game.executePlayerTurn()`, antes de permitir descarte:
     ```
     int cartasEnMano = player.countCardsOfType(species);
     int requisito = Card.getSmallFlockRequirement(species);
     if (cartasEnMano >= requisito) {
         // permitir descarte
     } else {
         // mostrar error
     }
     ```

3. Implementar player.countCardsOfType(TypeBird)
   - Iterar sobre `hand` con iterator del TAD, contar coincidencias

4. Eliminar cartas de la mano tras descarte exitoso
   - Usar `player.removeCardsBySpecies(species)` implementado con iterator del TAD para evitar `ConcurrentModificationException`

5. Añadir cartas descartadas al montón
   - `discardedCards.addCards(cartasEliminadas)`

6. Incrementar contador de especie
   - `player.incrementSpeciesCounter(species)`

7. Feedback al usuario
   - Mostrar: " Has completado una bandada de [especie]. +1 en tu zona de juego."

comprobar que un jugador con 3 Flamencos en mano (requisito: 2) → descarta → mano pierde 3 Flamencos, descartes ganan 3, contador de Flamenco del jugador incrementa.








[                                       ]









fase 6 → Condiciones de victoria: 7 especies en colección

Objetivo: Detectar cuando un jugador ha completado 7 especies diferentes en su zona de juego.

 Pasos:

1. Método de verificación en Player
   - `public boolean hasWon()`:
     - Retornar `countDifferentSpeciesInCollection() >= 7`

2. Integrar en bucle principal de Game
   - Después de cada turno de jugador:
     ```
     if (currentPlayer.hasWon()) {
         iu.displayMessage("🏆 ¡" + currentPlayer.getName() + " HA GANADO!");
         iu.displayMessage("Ha completado 7 especies de pájaros en su zona de juego.");
         gameFinished = true;
         break;
     }
     ```

3. Mostrar resumen final
   - Para cada jugador, mostrar: nombre + número de especies completadas + tamaño de colección
   - Destacar al ganador

 Nota:
- La victoria por 7 especies tiene PRIORIDAD sobre otras condiciones de fin
- Verificar esta condición ANTES de verificar "jugador sin cartas"

 Check de salida: En partida de prueba, forzar que un jugador complete 7 especies → mensaje de victoria aparece inmediatamente, juego termina.



[                                       ]






---

fase 7 → Condición especial: jugador sin cartas → redistribución masiva

Objetivo: Implementar la regla: si un jugador se queda sin cartas en mano, los demás descartan todo, se baraja y se reparten 8 nuevas.

 Pasos:

1. Detectar jugador sin cartas
   - Después de verificar victoria por 7 especies:
     ```
     if (currentPlayer.hasNoCards()) {
         // Activar redistribución
     }
     ```

2. Recoger cartas de los otros jugadores
   - Para cada jugador `p` en `players`:
     - Si `p != currentPlayer`:
       - `discardedCards.addCards(p.getHand())` → añadir todas sus cartas al montón
       - `p.clearHand()` → implementar método que vacíe `hand` usando `hand.clear()` del TAD

3. Reincorporar descartes al mazo y barajar
   - `discardedCards.returnToDeck(deck)`
   - `deck.shuffle()`

4. Repartir 8 cartas nuevas a cada jugador
   - Para cada jugador `p` en `players`:
     - Para `i = 0 to 7`:
       - Si `!deck.isEmpty()`: `p.addCardToHand(deck.takeFirstCard())`
     - `p.orderCardsBySpecies()`

5. Verificar si fue posible repartir completamente
   - Si algún jugador recibió < 8 cartas → activar condición de fin por agotamiento (FASE 8)
   - Si todos recibieron 8 → continuar juego, pasar al siguiente jugador

6. Feedback al usuario
   - Mostrar: "🔄 [jugador] se quedó sin cartas. Todos descartan. Barajando y repartiendo nuevas manos..."

 Implementar Player.clearHand()
```java
public void clearHand() {
    this.hand.clear();  // clear() existe en el TAD List
}
```

 Check de salida: Jugador A juega sus últimas cartas → Jugadores B y C pierden sus manos, descartes se reincorporan al mazo, todos reciben 8 cartas nuevas, juego continúa.




[                                       ]








fase 8 → Condición de fin por agotamiento de baraja + descartes

Objetivo: Si no hay suficientes cartas para repartir 8 a cada jugador, finalizar y declarar ganador por colección.

 Pasos:

1. Detectar fallo en reparto
   - Durante el bucle de reparto de la FASE 7:
     ```
     boolean repartoCompleto = true;
     for (Player p : players) {
         for (int i = 0; i < 8; i++) {
             if (deck.isEmpty()) {
                 repartoCompleto = false;
                 break;
             }
             p.addCardToHand(deck.takeFirstCard());
         }
         if (!repartoCompleto) break;
     }
     ```

2. Si reparto incompleto → declarar ganador
   - `Player winner = determineWinnerByCollection();`
   - Implementar `determineWinnerByCollection()`:
     - Iterar sobre `players`, encontrar quien tiene `collection.size()` máximo
     - En caso de empate, retornar el primero encontrado (según enunciado: "ganaría cualquier jugador de los vencedores")

3. Mostrar mensaje final
   ```
   iu.displayMessage(" No fue posible repartir 8 cartas a cada jugador.");
   iu.displayMessage(" Ganador por colección: " + winner.getName());
   iu.displayMessage("Cartas en colección: " + winner.getCollection().size());
   gameFinished = true;
   ```

4. Método Player.getCollectionSize()
   - Si no existe, añadir: `public int getCollectionSize() { return collection.size(); }`

 Prioridad de condiciones de fin (orden de verificación en bucle):
1.  Jugador tiene 7 especies en zona de juego → victoria inmediata
2.  Jugador se queda sin cartas → redistribución (si es posible) o fin por agotamiento
3.  No hay cartas suficientes para redistribuir → fin por colección

 Check de salida: Agotar manualmente el mazo + descartes, forzar redistribución → mensaje de fin por agotamiento, ganador declarado por mayor colección.







[                                       ]








---
COMPROBACIÓN FINAL E INTEGRACIÓN

Objetivo: Asegurar que todo funciona junto y preparar la entrega.

 Pasos:

1. Pruebas unitarias manuales
   - [ ] Crear partida con 2 jugadores, verificar flujo completo de un turno
   - [ ] Probar descarte de especie con requisito cumplido y no cumplido
   - [ ] Probar captura de cartas rodeadas + rellenado de fila
   - [ ] Probar victoria por 7 especies
   - [ ] Probar redistribución por jugador sin cartas
   - [ ] Probar fin por agotamiento de cartas

2. Limpieza de código
   - Eliminar código de debug (print statements temporales)
   - Eliminar imports no usados (`mvn compile` avisa de ellos)
   - Verificar que no hay `System.out.println` fuera de `IU.java`
   - Verificar que no hay `Scanner` fuera de `IU.java`

3. Documentación mínima
   - Añadir Javadoc a métodos públicos nuevos:
     - `DiscardedCards.addCard()`, `returnToDeck()`
     - `Player.incrementSpeciesCounter()`, `hasWon()`
     - `Table.rellenarFilaConEspecieDiferente()`
   - Comentar lógica compleja (ej: algoritmo de rellenado de fila)

4. Compilación final
   ```bash
   mvn clean compile
   # Debe finalizar con: BUILD SUCCESS
   ```

5. Prueba de ejecución completa
   ```bash
   main.java
   # Jugar partida completa hasta fin natural
   # Verificar que no hay excepciones ni comportamientos inesperados
   ```


 Checklist de entrega: marcamos con [X] cada punto completado.
```
[ ] DiscardedCards.java creado con métodos requeridos
[ ] Game tiene atributo discardedCards y lo usa en lógica
[ ] Player tiene speciesCounter[] y métodos asociados
[ ] Lógica de rellenado de filas implementada en Table
[ ] Acción de descartar a zona de juego con validación de bandada
[ ] Victoria por 7 especies detectada y mostrada
[ ] Redistribución por jugador sin cartas implementada
[ ] Fin por agotamiento de cartas implementado
[ ] Solo se usan TADs de AEDI (no java.util.*)
[ ] No hay System.out.println ni Scanner fuera de IU
[ ] Proyecto compila con mvn clean compile
[ ] Proyecto ejecuta sin errores con mvn exec:java
```

---

## 🧭 Diagrama de flujo del bucle principal (resumen)

```
while (!gameFinished) {
    Player actual = players[currentPlayerIndex];
    
    // 1. Ejecutar turno: jugar cartas + capturar rodeadas
    executePlayerTurn(actual);
    
    // 2. Preguntar: ¿descartar especie a zona de juego?
    if (playerWantsToDiscard) {
        if (handCount >= smallFlockRequirement) {
            discardToPlayZone();  // Actualiza discardedCards + speciesCounter
        } else {
            showErrorMessage();
        }
    }
    
    // 3. Verificar victoria por 7 especies (PRIORIDAD)
    if (actual.hasWon()) {
        declareWinnerBySpecies();
        break;
    }
    
    // 4. Verificar si se quedó sin cartas
    if (actual.hasNoCards()) {
        if (canRedistribute()) {
            redistributeAllHands();  // Descartes → mazo → barajar → repartir
            // Si reparto falló → caer a fase 8
        }
        if (!canRedistribute() || repartoFalló) {
            declareWinnerByCollection();  // Fase 8
            break;
        }
    }
    
    // 5. Pasar al siguiente jugador
    currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
}
```


vale asi más o menos dejamos un roadmap claro y detallado para la tercera entrega, luego no nos liamos con tonterías, lo podemos seguir en caso de que no haya orden claro.