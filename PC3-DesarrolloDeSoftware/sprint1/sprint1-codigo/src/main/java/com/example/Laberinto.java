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
