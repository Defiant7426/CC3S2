# PC3-Desarrollo De Software

Alumno: De la Cruz Valdiviezo, Pedro Luis David

# Sprint 3

Vamos a utilizar sonarque para mejorar la calidad de nuestro codigo, cuando lo corremos por primera vez, nos da el siguiente resultado:

![Untitled](PC3-Desarrollo%20De%20Software%20f5598928330c4e41b8528faa166d0bab/Untitled.png)

Y existen 27 evidencias, asi que vamos a atacarlas uno por uno:

## Evidencia 1 y 2:

![Untitled](PC3-Desarrollo%20De%20Software%20f5598928330c4e41b8528faa166d0bab/Untitled%201.png)

Entonces en la clase juego creamos primero el objeto rand:

```java
private Random rand = new Random();
```

y luego lo cambiamos por Math.random():

```java
public void iniciarJuego() {
        laberinto.inicializarLaberinto();
        jugador.actualizarPosicion(new int[]{rand.nextInt(laberinto.getTamano()),
                rand.nextInt(laberinto.getTamano())}, laberinto);
    }
```

## Evidencia 3, …

![Untitled](PC3-Desarrollo%20De%20Software%20f5598928330c4e41b8528faa166d0bab/Untitled%202.png)

Para ello creamos el objeto logger:

```java
private Logger logger = Logger.getLogger(getClass().getName());
```

y reemplazamos toda linea que diga System.out.println por logger.info:

```java
public void actualizarEstadoDelJuego() {
        laberinto.mostrarLaberinto();
        logger.info("Puntaje: " + jugador.getPuntaje());
        logger.info("Vidas: " + jugador.getVidas());
    }
```

Con ello ahora nos quedan 7 evidencias.

## Evidencia 4

![Untitled](PC3-Desarrollo%20De%20Software%20f5598928330c4e41b8528faa166d0bab/Untitled%203.png)

Añadimos el default al switch:

```java
public void moverJugador(String nuevaPosicion, Laberinto laberinto) {
        switch (nuevaPosicion){
            case "arriba":
                mover(new int[]{posicionActual[0] - 1, posicionActual[1]}, laberinto);
                break;
            case "abajo":
                mover(new int[]{posicionActual[0] + 1, posicionActual[1]}, laberinto);
                break;
            case "izquierda":
                mover(new int[]{posicionActual[0], posicionActual[1] - 1}, laberinto);
                break;
            case "derecha":
                mover(new int[]{posicionActual[0], posicionActual[1] + 1}, laberinto);
                break;
            default:
                System.out.println("Movimiento no permitido");
        }
    }
```

o también podemos lanzar una excepción:

```java
public void moverJugador(String nuevaPosicion, Laberinto laberinto) {
        switch (nuevaPosicion){
            case "arriba":
                mover(new int[]{posicionActual[0] - 1, posicionActual[1]}, laberinto);
                break;
            case "abajo":
                mover(new int[]{posicionActual[0] + 1, posicionActual[1]}, laberinto);
                break;
            case "izquierda":
                mover(new int[]{posicionActual[0], posicionActual[1] - 1}, laberinto);
                break;
            case "derecha":
                mover(new int[]{posicionActual[0], posicionActual[1] + 1}, laberinto);
                break;
            default:
                throw new IllegalArgumentException("Movimiento no permitido");
        }
    }
```

## Evidencia 5

![Untitled](PC3-Desarrollo%20De%20Software%20f5598928330c4e41b8528faa166d0bab/Untitled%204.png)

Entonces para que el codigo no sea repetitivo hacemos lo siguiente en la clase Salidas:

```java
package com.example;
import java.util.logging.Logger;

public class Salidas {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Salidas.class.getName());
        Juego juego = new Juego(new Laberinto(5), new Jugador(5));
        juego.iniciarJuego();
        juego.getLaberinto().mostrarLaberinto();
        imprimirInfo(logger,juego);
        logger.info("\nMovemos hacia la derecha\n");
        juego.getJugador().moverJugador("derecha", juego.getLaberinto());
        juego.getLaberinto().mostrarLaberinto();
        imprimirInfo(logger,juego);
        logger.info("\nMovemos hacia abajo\n");
        juego.getJugador().moverJugador("abajo", juego.getLaberinto());
        juego.getLaberinto().mostrarLaberinto();
        imprimirInfo(logger,juego);
        logger.info("\nMovemos hacia izquierda\n");
        juego.getJugador().moverJugador("izquierda", juego.getLaberinto());
        juego.getLaberinto().mostrarLaberinto();
        imprimirInfo(logger,juego);
        logger.info("\nMovemos hacia derecha\n");
        juego.getJugador().moverJugador("derecha", juego.getLaberinto());
        juego.getLaberinto().mostrarLaberinto();
        imprimirInfo(logger,juego);
    }
    public static void imprimirInfo(Logger logger, Juego juego){
        logger.info("vidas: " + juego.getJugador().getVidas());
        logger.info("puntaje: " + juego.getJugador().getPuntaje());
    }
}

```

Creamos un metodo estatico imprimirInfo donde ahi vamos a imprimir vidas y puntaje cuando se requiera

Con esto concluimos las evidencias que encontro Sonar en nuestro codigo, ahora vamos a escribir los test para este codigo con el patron AAA de la siguiente manera:

```java
package com.example;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CeldaTest {

    @Test
    void obtenerTipoEspecificado(){
        // Arrange
        Celda celda = new Celda('T');

        //Act
        char tipo = celda.obtenerTipoDeCelda();

        //Assert
        assertThat(tipo).isEqualTo('T')
                .as("El tipo deberia de ser T");
    }

}
```

Si lo vemos en Jacoco:

![Untitled](PC3-Desarrollo%20De%20Software%20f5598928330c4e41b8528faa166d0bab/Untitled%205.png)

En este caso basta con una prueba para abarcar todo nuestro codigo, pero sin embargo podemos agregar test mas rigurosos