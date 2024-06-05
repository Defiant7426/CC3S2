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
