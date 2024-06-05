package com.example;

import java.util.logging.Logger;

public class Jugador {

    private int[] posicionActual;
    private int puntaje;
    private int vidas;
    private Logger logger = Logger.getLogger(getClass().getName());

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
            default:
                throw new IllegalArgumentException("Movimiento no permitido");
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
            logger.info("Has recogido un tesoro!");
            puntaje++;
        } else if (celda.obtenerTipoDeCelda() == 'X') {
            logger.info("Caiste en una trampa!");
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
