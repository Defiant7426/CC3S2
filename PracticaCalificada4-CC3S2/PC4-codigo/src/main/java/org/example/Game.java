package org.example;

public class Game {
    private Player player;
    private Mapa mapa;
    private Wave wave;
    private TowerFactory towerFactory;
    private EnemyFactory enemyFactory;

    public Game() {
        this.player = new Player(); // inicializa el jugador
        this.mapa = new Mapa(5); // inicializa el mapa en 5x5
        this.towerFactory = new CannonTowerFactory(); // creamos la torre Cannon usando la fabrica
        this.enemyFactory = new BasicEnemyFactory(); // creamos los enemigos basicos usando la fabrica
        this.wave = new Wave(1); // una oleada un enemigo
    }

    public void placeTower(int x, int y) {
        Tower tower = towerFactory.createTower();
        mapa.insertarTorre(tower, x, y);
    }

    public void startWave() {
        Wave wave = new Wave(player.getScore());
        //wave.start();
    }

    public void mostrarMapa(){
        mapa.imprimirMapa();
    }

    public void insertarCaminoPredeterminado(){
        mapa.insertarCamino(0,2);
        mapa.insertarCamino(1,1);
        mapa.insertarCamino(2,0);
        mapa.insertarCamino(2,3);
        mapa.insertarCamino(3,2);
    }

    public void insertarObjetivoPredeterminado() {
        mapa.insertarBase(2,4);
    }

    public void insertarTorrePredeterminada() {
        mapa.insertarTorre(new CannonTower(),1,3);
    }
}
