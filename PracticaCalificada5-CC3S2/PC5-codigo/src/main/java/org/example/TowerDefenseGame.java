package org.example;
import java.util.*;

public class TowerDefenseGame {
    private Map map; // mapa del juego
    private Player player; // El jugador
    private List<Wave> waves; // Mapa de oleadas

    public TowerDefenseGame(){
        this.map = new Map(); // Se crea el mapa del juego
        this.player = new Player(); // Se crea el jugador
        this.waves = new ArrayList<>(); //  Se crea las oleadas
    }

    public void placeTower(Tower tower, int x, int y){ // desconocido
        map.placeTower(tower, x, y);
    }

    public void startWave(){
        Wave wave = new Wave(); // se sea una oleada
        waves.add(wave); // se le agrega a la lista de oleadas
        wave.start(); // empieza
    }

    public void gameState(){ // se imprime el estado del juego
        System.out.println(map); // se imprime el mapa
        System.out.println("Puntuación: " + player.getScore()); // se imprime la puntuación
        System.out.println("Vida de la base: " + player.getBaseHealth()); // se imprime la vida de la base
    }

    public static void main(String[] args) {

        System.out.println("\nBienvenidos a Tower Defense Game\n");

        TowerDefenseGame tdg = new TowerDefenseGame();
        Tower t1 = new Tower('B');
        Tower t2 = new Tower('B');
        Tower t3 = new Tower('B');
        tdg.placeTower(t1 ,0 ,0);
        tdg.placeTower(t2, 1, 1);
        tdg.placeTower(t3, 2, 2);
        tdg.startWave();
        tdg.gameState();
    }
}