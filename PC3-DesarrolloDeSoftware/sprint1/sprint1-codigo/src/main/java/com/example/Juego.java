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
                (int) (Math.random() * laberinto.getTamano())}, laberinto);
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



