# PC3-Desarrollo de software

# Sprint 1

Alumno: De la Cruz Valdiviezo, Pedro Luis David

Vamos a crear un juego en java que se llama “Aventura en el Laberinto”, vamos a tener un tamaño de tablero de 10x10 con diferentes elementos:
• P: Posición del jugador.
• T: Tesoro.
• X: Trampa.
• .: Espacio vacío.

Instrucciones del juego

1. Inicio del juego: El juego inicia con el jugador en una posición aleatoria en el laberinto.
2. Movimientos: El jugador puede moverse en cuatro direcciones: norte (N), sur (S), este (E) y
oeste (O).
3. Recolectar tesoro: Al moverse sobre una celda con un tesoro (T), el jugador lo recoge y su
puntaje aumenta.
4. Evitar trampas: Si el jugador se mueve sobre una celda con una trampa (X), pierde una vida.
5. Fin del juego: El juego termina cuando el jugador recoge todos los tesoros o pierde todas sus
vidas.

Clases a a desarrollar:
Laberinto:

1. Atributos: matriz de celdas, tamaño del laberinto.
2. Métodos: inicializar laberinto, mostrar laberinto.

Clase Laberinto:

```java
package com.example;

/*
Laberinto:

1. Atributos: matriz de celdas, tamaño del laberinto.
2. Métodos: inicializar laberinto, mostrar laberinto.
* */

public class Laberinto {

    private int[][] matriz;
    private int tamano;

    public Laberinto(int tamano) {
        this.tamano = tamano;
    }

    public void inicializarLaberinto() {
        this.matriz = new int[tamano][tamano];
    }

    public void mostrarLaberinto() {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getTamano() {
        return tamano;
    }
}

```

Explicación:

En esta clase Laberinto tenemos un constructor Laberinto que va a recibir el tamaño como argumento, vamos a crear un metodo inicializarLaberinto donde vamos a crear la matriz con el tamaño especificado, finalmente el metodo mostrarLaberinto que va a imprimir todas las celdas de la matriz

Jugador:

1. Atributos: posición actual, puntaje, vidas.
2. Métodos: mover jugador, actualizar posición, verificar colisiones.

Clase Jugador:

```java
package com.example;

/*
Jugador:

1. Atributos: posición actual, puntaje, vidas.
2. Métodos: mover jugador, actualizar posición, verificar colisiones.
* */

public class Jugador {

    private int[] posicionActual;
    private int puntaje;
    private int vidas;

    public Jugador(int vidas) {
        this.vidas = vidas;
        this.puntaje = 0;
    }

    public void moverJugador(int[] nuevaPosicion) {
        this.posicionActual = nuevaPosicion;
    }

    public void actualizarPosicion(int[] nuevaPosicion) {
        this.posicionActual = nuevaPosicion;
    }

    public void verificarColisiones() {
    }
}
```

Explicación:

En la clase Jugador, tenemos un constructor que acepta las vidas como argumentos. Tenemos un método para mover al jugador a una nueva posición, actualizar la posición actual y verificar colisiones que lo implementaremos mas adelante.

Juego:

1. Atributos: instancia de laberinto, instancia de jugador.
2. Métodos: iniciar juego, procesar comandos, verificar estado del juego.

Clase Juego:

```java
package com.example;

/*
Juego:

1. Atributos: instancia de laberinto, instancia de jugador.
2. Métodos: iniciar juego, procesar comandos, verificar estado del juego.

Clase Juego:
* */

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
                (int) (Math.random() * laberinto.getTamano())});
    }

    public void procesarComandos() {
    }

    public void verificarEstadoDelJuego() {
    }

    public Laberinto getLaberinto() {
        return laberinto;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
```

Explicación:

En la clase Juego, tenemos un constructor que acepta la instancia de Laberinto y Jugador como argumentos. Tenemos métodos para iniciar el juego, que inicializa el laberinto y inicializa la posición inicial del jugador a una posición aleatoria del tablero. También tenemos métodos para procesar comandos y verificar el estado del juego, que implementaremos más adelante. Añadimos dos metodos get para Laberinto y Jugador

Salidas:

1. Mostrar laberinto inicial.
2. Permitir al jugador moverse e imprimir la nueva posición.

Para lograr esto debemos de hacer algunos cambios a nuestro codigo escrito anteriormente, aqui se encuentran dichos cambios:

Clase Jugador

```java
package com.example;

/*
Jugador:

1. Atributos: posición actual, puntaje, vidas.
2. Métodos: mover jugador, actualizar posición, verificar colisiones.
* */

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
            laberinto.setCasilla(ints);
        }
    }

    public void actualizarPosicion(int[] nuevaPosicion, Laberinto laberinto) {
        this.posicionActual = nuevaPosicion;
        laberinto.setCasilla(nuevaPosicion);
    }

    public void verificarColisiones() {
    }
}
```

explicacion:

En la clase Jugador, hemos modificado el método moverJugador para que acepte una cadena que representa la dirección del movimiento y una instancia de la clase Laberinto. Dependiendo de la dirección de movimiento, calculamos la nueva posición deseada y la pasamos al método mover() junto con la instancia del laberinto. El método mover() verifica si la nueva posición está dentro de los límites del laberinto. Si es así, actualiza la posición actual del jugador y llama a los métodos borrarCasilla() y setCasilla() en la instancia del laberinto para actualizar el laberinto.

Clase Laberinto:

```java
package com.example;

/*
Laberinto:

1. Atributos: matriz de celdas, tamaño del laberinto.
2. Métodos: inicializar laberinto, mostrar laberinto.
* */

public class Laberinto {

    private int[][] matriz;
    private int tamano;

    public Laberinto(int tamano) {
        this.tamano = tamano;
    }

    public void inicializarLaberinto() {
        this.matriz = new int[tamano][tamano];
    }

    public void mostrarLaberinto() {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setCasilla(int[] casilla){
        this.matriz[casilla[0]][casilla[1]] = 1;
    }

    public int getTamano() {
        return tamano;
    }

    public void borrarCasilla(int[] casilla) {
        this.matriz[casilla[0]][casilla[1]] = 0;
    }
}
```

Explicacion:

En la clase Laberinto, hemos añadido dos nuevos métodos: setCasilla() y borrarCasilla(). Estos métodos toman una posición de la casilla como argumento y actualizan la matriz del laberinto en esa posición. setCasilla() establece el valor de la matriz en la posición dada a 1, lo que representa la posición actual del jugador. borrarCasilla() establece el valor de la matriz en la posición dada a 0, lo que representa una casilla vacía.

Ahora si implementamos la clase Salidas:

```java
package com.example;

public class Salidas {
    public static void main(String[] args) {
        Juego juego = new Juego(new Laberinto(5), new Jugador(5));
        juego.iniciarJuego();
        juego.getLaberinto().mostrarLaberinto();
        System.out.println("\nMovemos hacia la derecha\n");
        juego.getJugador().moverJugador("derecha", juego.getLaberinto());
        juego.getLaberinto().mostrarLaberinto();
        System.out.println("\nMovemos hacia abajo\n");
        juego.getJugador().moverJugador("abajo", juego.getLaberinto());
        juego.getLaberinto().mostrarLaberinto();
        System.out.println("\nMovemos hacia izquierda\n");
        juego.getJugador().moverJugador("izquierda", juego.getLaberinto());
        juego.getLaberinto().mostrarLaberinto();
        System.out.println("\nMovemos hacia derecha\n");
        juego.getJugador().moverJugador("derecha", juego.getLaberinto());
        juego.getLaberinto().mostrarLaberinto();
    }
}
```

Explicacion:

Hemos creado una clase Salidas que contiene un método main(). En este método, creamos una nueva instancia del juego con un laberinto de tamaño 5 y un jugador con 5 vidas. Luego iniciamos el juego e imprimimos el laberinto inicial. Después de eso, movemos al jugador en varias direcciones e imprimimos el laberinto después de cada movimiento. Como puede ver en las imágenes, la posición del jugador, representada por el número 1, se actualiza correctamente al mover al jugador.+

![Untitled](PC3-Desarrollo%20de%20software%207e0c23a012a54e028cb26d5ad5560cff/Untitled.png)

![Untitled](PC3-Desarrollo%20de%20software%207e0c23a012a54e028cb26d5ad5560cff/Untitled%201.png)

![Untitled](PC3-Desarrollo%20de%20software%207e0c23a012a54e028cb26d5ad5560cff/Untitled%202.png)