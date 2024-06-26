package org.example;

public class Player {

    private int score; // el score del jugador
    private int baseHealth; // la vida base

    public Player(){
        this.score = 0; // iniciamos con score 0
        this.baseHealth = 100; // iniciamos con vida 100
    }

    public int getScore() { // obtenemos el score actual
        return score;
    }

    public int getBaseHealth() { // obtenemos la vida base actual
        return baseHealth;
    }
}
