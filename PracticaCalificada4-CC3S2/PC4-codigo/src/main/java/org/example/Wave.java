package org.example;

import java.util.ArrayList;
import java.util.List;

public class Wave {
    private List<EnemyFactory> enemies;
    private int waveNumber;

    public Wave(int waveNumber) {
        this.waveNumber = waveNumber;
        this.enemies = generateEnemies(waveNumber);
    }

    private List<EnemyFactory> generateEnemies(int waveNumber) {
        List<EnemyFactory> enemies = new ArrayList<>();
        for (int i = 0; i < waveNumber * 5; i++) { // más enemigos cada oleada
            enemies.add(new BasicEnemyFactory());
        }
        if (waveNumber % 5 == 0) { // jefe cada 5 oleadas
            enemies.add(new BossEnemyFactory());
        }
        return enemies;
    }


}
