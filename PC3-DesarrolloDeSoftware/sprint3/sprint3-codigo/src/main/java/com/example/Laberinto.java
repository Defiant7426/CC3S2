package com.example;

import java.util.Random;
import java.util.logging.Logger;

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
    private Logger logger = Logger.getLogger(getClass().getName());

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
                logger.info(matriz[i][j].obtenerTipoDeCelda() + " ");
            }
            logger.info("\n");
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
