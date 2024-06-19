package org.example;

public class LaserTowerFactory implements TowerFactory {
    @Override
    public Tower createTower() {
        return new LaserTower();
    }
}
