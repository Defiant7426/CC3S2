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
