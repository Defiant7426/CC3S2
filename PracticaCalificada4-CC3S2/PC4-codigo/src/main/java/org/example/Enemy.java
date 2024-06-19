package org.example;

public class Enemy {
    private int speed; // número de celdas por turno
    private int health;
    private int reward;

    public Enemy(int velocidad, int vida, int recompensa) {
        this.speed = velocidad;
        this.health = vida;
        this.reward = recompensa;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
}
