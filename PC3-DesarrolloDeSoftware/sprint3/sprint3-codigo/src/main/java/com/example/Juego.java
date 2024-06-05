package com.example;

import java.util.Random;
import java.util.logging.Logger;

public class Juego {

    private Laberinto laberinto;
    private Jugador jugador;
    private Random rand = new Random();
    private Logger logger = Logger.getLogger(getClass().getName());

    public Juego(Laberinto laberinto, Jugador jugador) {
        this.laberinto = laberinto;
        this.jugador = jugador;
    }

    public void iniciarJuego() {
        laberinto.inicializarLaberinto();
        jugador.actualizarPosicion(new int[]{rand.nextInt(laberinto.getTamano()),
                rand.nextInt(laberinto.getTamano())}, laberinto);
    }

    public void procesarComandos(String comando) {
        jugador.moverJugador(comando, laberinto);
        actualizarEstadoDelJuego();
    }

    public void verificarEstadoDelJuego() {
        if (jugador.getPuntaje() == laberinto.getTamano()) {
            logger.info("Has ganado!");
        } else if (jugador.getVidas() == 0) {
            logger.info("Has perdido!");
        }
    }

    public void actualizarEstadoDelJuego() {
        laberinto.mostrarLaberinto();
        logger.info("Puntaje: " + jugador.getPuntaje());
        logger.info("Vidas: " + jugador.getVidas());
    }

    public Laberinto getLaberinto() {
        return laberinto;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
