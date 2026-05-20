
package gal.uvigo.esei.aed1.cubirds.iu;

import gal.uvigo.esei.aed1.cubirds.core.Game;

/**
 * CLASE Main — Punto de entrada del programa
 * 
 * ¿QUÉ HACE?
 * - main() es el método que se ejecuta cuando corres el programa
 * - Crea UN objeto IU (para comunicación con el usuario)
 * - Crea UN objeto Game (que controla todo el juego)
 * - Llama a game.play() para empezar la partida
 * 
 * FLUJO:
 * 1. java Main
 * 2. main() se ejecuta
 * 3. Crea IU
 * 4. Crea Game
 * 5. game.play() → el juego comienza
 * 
 * NOTAS:
 * - No hay nada más que hacer aquí
 * - Todo el juego está en Game.play()
 */
public class Main {

  public static void main(String[] args) {
    // Crear la interfaz de usuario (para leer/escribir en consola)
    IU iu = new IU();

    // Crear el juego, pasándole la IU
    Game cubirds = new Game(iu);

    // ¡Empezar el juego!
    cubirds.play();
  }

}
