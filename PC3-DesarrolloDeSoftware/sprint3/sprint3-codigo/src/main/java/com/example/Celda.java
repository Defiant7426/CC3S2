package com.example;

/*
Celda:

1. Atributos: tipo de celda (T, X, .).
2. Métodos: constructor, obtener tipo de celda.
*/

public class Celda {

    private char tipo;

    public Celda(char tipo) {
        this.tipo = tipo;
    }

    public char obtenerTipoDeCelda() {
        return tipo;
    }
}