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
