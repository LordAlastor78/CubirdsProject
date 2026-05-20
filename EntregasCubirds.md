PROYECTO DE ALGORITMOS Y ESTRUCTURAS DE DATOS I
Curso 2025-2026
PRIMERA ENTREGA
Una vez leído el enunciado del proyecto disponible en Moovi, sección Proyecto, puedes
comenzar el desarrollo del mismo.
Descarga desde Moovi, sección Proyecto, la estructura del proyecto en la que están
identificadas las clases implicadas en el diseño de la aplicación. Además, puedes
descargarte el diagrama de clases en el que se visualizan las clases del proyecto y las
relaciones entre ellas.
Durante el desarrollo del proyecto se debe reflexionar e implementar cada clase
identificando los atributos y métodos correspondientes. Se debe tener especial cuidado en
controlar la visibilidad de los atributos (evitando, por ejemplo, que una clase haga uso de
la estructura de datos propia de otra clase) y de los métodos. Además, se deben seleccionar
y utilizar los algoritmos y las estructuras de datos más adecuadas en cada caso.
La primera entrega del proyecto está relacionada con el inicio del juego, y será necesario
implementar las siguientes funcionalidades:
• Crear la baraja de pájaros con 110 cartas, las cuales están definidas en el
enumerado Card disponible en el paquete core del proyecto. Como se puede
observar, las cartas constan de un tipo de especie (Curruca de caña, Flamenco,
Petirrojo, Tucán, Pato, Urraca, Lechuza, Guacamayo, definidos en el enumerado
TypeBird) y dos números (el primero indica el número de cartas necesarias para
una bandada pequeña y el segundo para una bandada grande).
• Preguntar cuántos jugadores/as van a jugar (entre 2 y 5) y, a continuación, crear
los/as jugadores/as. Cada jugador/a se identifica por un nombre que debe ser
conocido en el momento de su creación.
• Crear la mesa del juego, inicialmente vacía.
• Una vez que ya se dispone de la baraja de cartas, de los/as jugadores/as y de la
mesa, la siguiente tarea consiste en barajar, repartir 8 cartas a cada jugador/a y
colocar 3 cartas en cada una de las 4 filas de la mesa.
• No puede haber pájaros de la misma especie en la misma fila. En tal caso, se
siguen sacando cartas de la baraja hasta conseguir 3 especies distintas. Las cartas
no utilizadas se devuelven de nuevo al final de la baraja.
• Las cartas de la mano de cada jugador deben estar agrupadas por especie.
• Hecho el reparto, se debe mostrar por pantalla las cartas colocadas en la mesa.
Además, se mostrará el nombre de cada jugador/a y las cartas que le han tocado
para jugar.
Una vez finalizado el proyecto, uno de los miembros del grupo debe subirlo a Moovi,
sección Proyecto / Primera Entrega. Sólo se admitirán proyectos que compilen y que se
ejecuten según lo solicitado en la entrega.
FECHA TOPE DE ENTREGA
Martes, 7 de abril de 2026 a las 23:00 h. No se admitirán entregas posteriores a esta fecha.
REVISIÓN
Tutoría grupal obligatoria para todos los miembros del grupo. La fecha debe ser
previamente acordada con el/la docente y la tutoría se realizará entre el 8 y el 10 de abril.


PROYECTO DE ALGORITMOS Y ESTRUCTURAS DE DATOS I
Curso 2025-2026
SEGUNDA ENTREGA
Una vez realizados en el proyecto los cambios sugeridos por el/la docente en la tutoría
grupal obligatoria, puedes continuar con el desarrollo del mismo.
La segunda entrega va a ampliar las funcionalidades de la primera entrega del proyecto.
En cada ronda, cada jugador por turnos puede realizar alguna de estas acciones:
• Jugar cartas (acción obligatoria)
◦ El jugador escoge qué especie de pájaro de su mano quiere colocar y baja
todos los pájaros de esa especie, situándolos a la derecha o a la izquierda de
una de las 4 filas que hay en la mesa.
◦ Si el jugador baja una especie de pájaro que ya se encuentra en la fila, se lleva
a su mano todas las cartas situadas entre los pájaros de esa especie y los que
acaba de colocar (son las cartas rodeadas).
◦ Puede suceder que al bajar las cartas a la mesa no se rodee ningún pájaro.
• Una vez realizada la acción anterior se muestra por pantalla la mano del jugador
activo.
• El proceso se repite hasta que uno de los/as jugadores/as se queda sin cartas en su
mano.
• Se mostrará un mensaje indicando quién es el/la jugador/a que se ha quedado sin
cartas.
Una vez finalizado el proyecto, uno de los miembros del grupo debe subirlo a Moovi,
sección Proyecto / Segunda Entrega. Sólo se admitirán proyectos que compilen y que se
ejecuten según lo solicitado en la entrega.
FECHA TOPE DE ENTREGA
Martes, 28 de abril de 2026 a las 23:00 h. No se admitirán entregas posteriores a esta
fecha.
REVISIÓN
Tutoría grupal obligatoria para todos los miembros del grupo. La fecha debe ser
previamente acordada con el/la docente y la tutoría se realizará entre el 29 de abril y el 4
de mayo.

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

