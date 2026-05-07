PROYECTO DE ALGORITMOS Y ESTRUCTURAS DE DATOS I
Curso 2025-2026
TERCERA ENTREGA
Una vez realizados en el proyecto los cambios sugeridos por el/la docente en la tutoría
grupal obligatoria, puedes continuar con el desarrollo del mismo.
En la tercera entrega se pide ampliar las funcionalidades de la segunda entrega del
proyecto, para obtener el producto final.
• Crea la clase DiscardedCards con sus atributos y métodos correspondientes. Esta
clase tendrá la funcionalidad de almacenar las cartas descartadas durante el juego
y de devolver a la baraja las cartas almacenadas.
• Añade a la clase Game un atributo de tipo DiscardedCards.
• Añade a la clase Player un atributo que represente el contador de especies (su
zona de juego) y el método incrementar contador de una especie determinada.
En cada ronda, cada jugador por turnos puede realizar alguna de estas acciones:
• Si el jugador ha rodeado cartas de la mesa y se han introducido en su mano
(acciones implementadas en la entrega anterior) se debe comprobar si todas las
cartas restantes en esa fila de la mesa son de la misma especie. En caso afirmativo,
habrá que añadir cartas de la baraja hasta que salga un pájaro de una especie
diferente (es obligatorio que en cada fila existan como mínimo 2 especies). Si la
baraja se queda sin cartas, se incorporarán las almacenadas en el montón de
descartes, se barajarán y se continúa rellenando la fila hasta obtener una carta de
una especie diferente.
• Una vez que se ha mostrado el estado actual de la mano del jugador (acción
implementada en la entrega anterior), se le debe preguntar si desea añadir una
especie en su zona de juego. En caso afirmativo, se le pregunta la especie que
quiere descartar de su mano. Si se cumple la restricción de que el número de cartas
de la especie seleccionada es igual o superior al número indicado por la
bandada pequeña se incrementará en UNO el contador de la especie de esa
bandada (el contador representa la zona de juego de un jugador), se quitan las
cartas de la mano del jugador y se añaden al montón de descartes. Si la especie
seleccionada no cumple la restricción, se indica que no es posible bajar esa especie
a la zona de juego.
• Se comprueba si el jugador activo ha ganado, y por lo tanto finaliza el juego. Se
considera ganador si tiene 7 especies diferentes en su colección de pájaros (zona
de juego). En caso afirmativo, se muestra por pantalla el nombre del ganador,
indicando que ha conseguido 7 especies de pájaros.
• Si no ha ganado, se comprueba si el jugador activo se ha quedado sin cartas en su
mano. En caso afirmativo, los demás jugadores se verán obligados a descartarse
por completo de su mano (se añaden todas las cartas al montón de descartes). Se
vacía el montón de descartes pasando todas las cartas a la baraja, se barajan y se
reparten 8 cartas nuevas a cada jugador. El juego continúa pasando el turno al
siguiente jugador.
• Si no es posible repartir 8 cartas a cada jugador, finaliza el juego y el jugador con
el mayor número de cartas en su colección (zona de juego) gana la partida. Si
hubiera un empate, ganaría cualquier jugador de los vencedores. Se muestra por
pantalla el nombre del ganador, indicando que no ha sido posible realizar el
reparto de cartas.
Una vez finalizado el proyecto, uno de los miembros del grupo debe subirlo a Moovi,
sección Proyecto / Tercera Entrega. Sólo se admitirán proyectos que compilen y que se
ejecuten según lo solicitado en la entrega.
FECHA TOPE DE ENTREGA
Martes, 19 de mayo de 2026 a las 23:00 h. No se admitirán entregas posteriores a esta
fecha.
EVALUACIÓN GRUPAL
Tutoría y evaluación grupal obligatoria para todos los miembros del grupo. La fecha debe
ser previamente acordada con el/la docente y la evaluación se realizará a partir del 20 de
mayo.