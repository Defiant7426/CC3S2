package org.example;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.insertarCaminoPredeterminado();
        game.insertarObjetivoPredeterminado();
        System.out.println("Mapa inicial:");
        game.mostrarMapa();
        System.out.println("Insertar una torre:");
        game.insertarTorrePredeterminada();
        System.out.println();
        game.mostrarMapa();
    }
}
