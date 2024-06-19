package org.example;

public class BossEnemyFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy() {
        return new BossEnemy();
    }
}

