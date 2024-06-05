# PC3-DesarrolloDeSoftware

Alumno: De la Cruz Valdiviezo, Pedro Luis David

# Sprint 2

Clases a desarrollar/modificar:
Celda:

1. Atributos: tipo de celda (T, X, .).
2. Métodos: constructor, obtener tipo de celda.

```java
package com.example;

/*
Celda:

1. Atributos: tipo de celda (T, X, .).
2. Métodos: constructor, obtener tipo de celda.
*/

public class Celda {

    private char tipo;

    public Celda(char tipo) {
        this.tipo = tipo;
    }

    public char obtenerTipoDeCelda() {
        return tipo;
    }
}
```

Explicacion:

Para lograr esto, modificamos la clase Jugador para añadir lógica en el método verificarColisiones(). Cuando el jugador se mueve a una nueva celda, este método se llama para comprobar si la celda contiene un tesoro o una trampa. Si es un tesoro, el jugador recoge el tesoro y se aumenta su puntuación. Si es una trampa, el jugador pierde una vida. Además, se añade un mensaje de impresión para notificar al usuario de estos eventos. En la clase Juego, se añade un método para imprimir el puntaje y las vidas restantes después de cada movimiento.

Clase Jugador (modificada):

```java
package com.example;

public class Jugador {

    private int[] posicionActual;
    private int puntaje;
    private int vidas;

    public Jugador(int vidas) {
        this.vidas = vidas;
        this.puntaje = 0;
    }

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
        }
    }

    private void mover(int[] ints, Laberinto laberinto) {
        if (ints[0] >= 0 && ints[0] < laberinto.getTamano() && ints[1] >= 0 && ints[1] < laberinto.getTamano()){
            laberinto.borrarCasilla(posicionActual);
            this.posicionActual = ints;
            verificarColisiones(laberinto);
            laberinto.setCasilla(ints);
        }
    }

    public void actualizarPosicion(int[] nuevaPosicion, Laberinto laberinto) {
        this.posicionActual = nuevaPosicion;
        laberinto.setCasilla(nuevaPosicion);
        verificarColisiones(laberinto);
    }

    public void verificarColisiones(Laberinto laberinto) {
        Celda celda = laberinto.obtenerCelda(posicionActual);
        if (celda.obtenerTipoDeCelda() == 'T') {
            System.out.println("Has recogido un tesoro!");
            puntaje++;
        } else if (celda.obtenerTipoDeCelda() == 'X') {
            System.out.println("Caiste en una trampa!");
            vidas--;
        }
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getVidas() {
        return vidas;
    }
}
```

Clase Juego (modificada):

```java
package com.example;

public class Juego {

    private Laberinto laberinto;
    private Jugador jugador;

    public Juego(Laberinto laberinto, Jugador jugador) {
        this.laberinto = laberinto;
        this.jugador = jugador;
    }

    public void iniciarJuego() {
        laberinto.inicializarLaberinto();
        jugador.actualizarPosicion(new int[]{(int) (Math.random() * laberinto.getTamano()),
                (int) (Math.random() * laberinto.getTamano())}, laberinto);
    }

    public void procesarComandos(String comando) {
        jugador.moverJugador(comando, laberinto);
        actualizarEstadoDelJuego();
    }

    public void verificarEstadoDelJuego() {
        if (jugador.getPuntaje() == laberinto.getTamano()) {
            System.out.println("Has ganado!");
        } else if (jugador.getVidas() == 0) {
            System.out.println("Has perdido!");
        }
    }

    public void actualizarEstadoDelJuego() {
        laberinto.mostrarLaberinto();
        System.out.println("Puntaje: " + jugador.getPuntaje());
        System.out.println("Vidas: " + jugador.getVidas());
    }

    public Laberinto getLaberinto() {
        return laberinto;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
```

Laberinto (modificado):

1. Métodos adicionales: colocar tesoros y trampas, actualizar celda.
Jugador (modificado):
2. Métodos adicionales: recoger tesoro, verificar trampa.
Juego (modificado):
• Métodos adicionales: actualizar estado del juego, verificar victoria o derrota.

```java
package com.example;

import java.util.Random;

/*
Laberinto (modificado):

1. Métodos adicionales: colocar tesoros y trampas, actualizar celda.
Jugador (modificado):
2. Métodos adicionales: recoger tesoro, verificar trampa.
Juego (modificado):
• Métodos adicionales: actualizar estado del juego, verificar victoria o derrota.
*/

public class Laberinto {

    private Celda[][] matriz;
    private int tamano;
    private Random rand = new Random();

    public Laberinto(int tamano) {
        this.tamano = tamano;
        this.matriz = new Celda[tamano][tamano];
    }

    public void inicializarLaberinto() {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                this.matriz[i][j] = new Celda('.');
            }
        }
        colocarTesorosYTrampas();
    }

    private void colocarTesorosYTrampas() {
        for (int i = 0; i < tamano; i++) {
            int x = rand.nextInt(tamano);
            int y = rand.nextInt(tamano);
            matriz[x][y] = new Celda('T');
        }

        for (int i = 0; i < tamano; i++) {
            int x = rand.nextInt(tamano);
            int y = rand.nextInt(tamano);
            matriz[x][y] = new Celda('X');
        }
    }

    public void mostrarLaberinto() {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                System.out.print(matriz[i][j].obtenerTipoDeCelda() + " ");
            }
            System.out.println();
        }
    }

    public void setCasilla(int[] casilla){
        this.matriz[casilla[0]][casilla[1]] = new Celda('P');
    }

    public void borrarCasilla(int[] casilla) {
        this.matriz[casilla[0]][casilla[1]] = new Celda('.');
    }

    public Celda obtenerCelda(int[] casilla) {
        return this.matriz[casilla[0]][casilla[1]];
    }

    public int getTamano() {
        return tamano;
    }
}

```

Explicación:

La clase Laberinto ha sido modificada para trabajar con el nuevo tipo de dato Celda. Ahora, la matriz se compone de Celdas en lugar de enteros. El método inicializarLaberinto() ha sido modificado para llenar la matriz con Celdas de tipo '.' (espacio vacío). También se ha añadido un nuevo método, colocarTesorosYTrampas(), que se llama al final de inicializarLaberinto(). Este método coloca un número aleatorio de tesoros (T) y trampas (X) en el laberinto al azar.

El método mostrarLaberinto() se ha modificado para imprimir el tipo de cada Celda en la matriz. Los métodos setCasilla() y borrarCasilla() se han modificado para trabajar con Celdas en lugar de enteros. También se ha añadido un nuevo método, obtenerCelda(), que devuelve la Celda en una posición dada.

Salidas:

1. Imprimir mensajes al recoger un tesoro o caer en una trampa.
2. Mostrar puntaje y vidas restantes.

```java
package com.example;

public class Salidas {
    public static void main(String[] args) {
        Juego juego = new Juego(new Laberinto(5), new Jugador(5));
        juego.iniciarJuego();
        juego.getLaberinto().mostrarLaberinto();
        System.out.println("vidas: " + juego.getJugador().getVidas());
        System.out.println("puntaje: " + juego.getJugador().getPuntaje());
        System.out.println("\nMovemos hacia la derecha\n");
        juego.getJugador().moverJugador("derecha", juego.getLaberinto());
        juego.getLaberinto().mostrarLaberinto();
        System.out.println("vidas: " + juego.getJugador().getVidas());
        System.out.println("puntaje: " + juego.getJugador().getPuntaje());
        System.out.println("\nMovemos hacia abajo\n");
        juego.getJugador().moverJugador("abajo", juego.getLaberinto());
        juego.getLaberinto().mostrarLaberinto();
        System.out.println("vidas: " + juego.getJugador().getVidas());
        System.out.println("puntaje: " + juego.getJugador().getPuntaje());
        System.out.println("\nMovemos hacia izquierda\n");
        juego.getJugador().moverJugador("izquierda", juego.getLaberinto());
        juego.getLaberinto().mostrarLaberinto();
        System.out.println("vidas: " + juego.getJugador().getVidas());
        System.out.println("puntaje: " + juego.getJugador().getPuntaje());
        System.out.println("\nMovemos hacia derecha\n");
        juego.getJugador().moverJugador("derecha", juego.getLaberinto());
        juego.getLaberinto().mostrarLaberinto();
        System.out.println("vidas: " + juego.getJugador().getVidas());
        System.out.println("puntaje: " + juego.getJugador().getPuntaje());
    }
}

```

Explicación:

Añadimos la impresión de vidas y puntaje en cada movimiento

![Untitled](PC3-DesarrolloDeSoftware%20eea379d6578748f3b07912706ca32a57/23152fb7-e464-40f0-ad03-017e95d6257d.png)

![Untitled](PC3-DesarrolloDeSoftware%20eea379d6578748f3b07912706ca32a57/a09115fb-2f23-4a99-b2c6-bc374d35a3fb.png)

![Untitled](PC3-DesarrolloDeSoftware%20eea379d6578748f3b07912706ca32a57/Untitled.png)

![Untitled](PC3-DesarrolloDeSoftware%20eea379d6578748f3b07912706ca32a57/Untitled%201.png)

![Untitled](PC3-DesarrolloDeSoftware%20eea379d6578748f3b07912706ca32a57/Untitled%202.png)