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

