package org.example;

public class Player {
    private int score;
    private int baseHealth;

    public Player() {
        this.score = 0;
        this.baseHealth = 100;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void deductBaseHealth(int damage) {
        this.baseHealth -= damage;
    }

    public int getScore() {
        return score;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    // Métodos adicionales según las necesidades del juego

}