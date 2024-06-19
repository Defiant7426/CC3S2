# PC4-Desarrollo de software

Alumno: De la Cruz valdiviezo, Pedro Luis David

# Descripción del problema

**Nombre del problema**: Tower Defense

**Descripción**: El juego es un juego de defensa de torres donde el jugador debe defender su base de oleadas de enemigos colocando torres en lugares estratégicos del mapa.
Características del problema "Tower Defense"

## Mapa

**Descripción**: El mapa es el escenario donde se desarrolla el juego. Está representado por una matriz (arreglo bidimensional) que define los caminos por los que se moverán los enemigos y las posiciones donde se pueden colocar las torres.

## Matriz del mapa:

- Una celda vacía (' ' o 0) representa un espacio disponible para colocar una torre.
- Una celda de camino ('C' o 1) representa el camino por el que los enemigos se desplazan.
- La base ('B' o 2) representa el objetivo que los enemigos deben alcanzar.

**Inicialización**: El mapa se inicializa con un diseño predefinido que incluye caminos y posiciones
disponibles para las torres.

Mapa inicial (5x5):
[ ][ ][C][ ][ ]
[ ][C][ ][ ][ ]
[C][ ][ ][C][B]
[ ][ ][C][ ][ ]
[ ][ ][ ][ ][ ]

Los enemigos seguirán el camino definido por las 'C' hasta llegar a la base 'B'.

Creamos la clase Celda.java:

```java
package org.example;

public class Celda {
    private boolean esCamino;
    private boolean esBase;
    private boolean esVacio = false;
    private Tower torre;

    public Celda(boolean esCamino, boolean esBase) {
        this.esCamino = esCamino;
        this.esBase = esBase;
        this.torre = null;
        if (!esCamino && !esBase) this.esVacio = true;
    }

    public boolean esCamino() {
        return esCamino;
    }

    public void setEsCamino(boolean esCamino) {
        this.esCamino = esCamino;
    }

    public boolean esBase() {
        return esBase;
    }

    public void setEsBase(boolean esBase) {
        this.esBase = esBase;
    }

    public Tower getTorre() {
        return torre;
    }

    public void setTorre(Tower torre) {
        this.torre = torre;
    }

    public boolean isEsVacio() {
        return esVacio;
    }

    public void setEsVacio(boolean esVacio) {
        this.esVacio = esVacio;
    }
}
```

A partir de esta clase [Celda.java](http://Celda.java) vamos a construir nuestra clase [Mapa.java](http://Mapa.java) de la siguiente manera:

```java
public class Mapa {
    private Celda[][] celdas;

    public Mapa(int filas, int columnas) {
        this.celdas = new Celda[filas][columnas];
        inicializarMapa();
    }

    private void inicializarMapa() {
        // Inicializa el mapa con celdas vacías, caminos y la base
        // según el diseño predefinido
    }

    public void colocarTorre(Tower torre, int fila, int columna) {
        // Coloca una torre en una celda vacía del mapa
    }

    // Otros métodos según las necesidades del juego
}

```

La clase Mapa.java será la responsable de gestionar el mapa del juego. Esto incluye la inicialización del mapa, la colocación de torres y cualquier otra operación relacionada con el mapa.

# Enemigos

**Descripción**: Los enemigos son las entidades que el jugador debe detener. Tienen varias
características que determinan su comportamiento y dificultad.

## Tipos de enemigos:

Enemigo básico: Lento, poca vida.
Enemigo rápido: Rápido, menos vida.
Jefe: Lento, mucha vida, mayor recompensa.

## Características:

Velocidad: Determina cuántas celdas se mueve el enemigo por turno.
Vida: Cantidad de daño que puede recibir antes de ser derrotado.
Recompensa: Puntos que el jugador gana al derrotar al enemigo.

Ejemplo dado por el profesor:

```java
public class Enemy {
private int speed; // número de celdas por turno
private int health;
private int reward;
// Constructores, getters y setters
}
public class BasicEnemy extends Enemy {
public BasicEnemy() {
super(1, 100, 10); // velocidad, vida, recompensa
}
}
```

Lo vamos a separar en dos archivos de la siguiente manera, creamos el archivo Enemy.java:

```java
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
```

Ahora creamos el archivo BasicEnemy que hereda de Enemy:

```java
package org.example;

public class BasicEnemy extends Enemy {
    public BasicEnemy() {
        super(1, 100, 10); // velocidad, vida, recompensa
    }
}
```

Seguidamente crearemos los demas tipos de enemigos:

Crearemos los archivos FastEnemy.java y BossEnemy.java para definir los otros dos tipos de enemigos:

```java
package org.example;

public class FastEnemy extends Enemy {
    public FastEnemy() {
        super(2, 50, 15); // velocidad, vida, recompensa
    }
}
```

```java
package org.example;

public class BossEnemy extends Enemy {
    public BossEnemy() {
        super(1, 200, 30); // velocidad, vida, recompensa
    }
}
```

# Torres

**Descripción**: Las torres son las defensas que el jugador coloca en el mapa para detener a los
enemigos. Tienen diferentes características y habilidades.

## Tipos de torres:

Cañón: Alto daño, corto alcance.
Láser: Daño moderado, largo alcance.
Flecha: Daño bajo, alcance moderado, alta velocidad de disparo.

## Características:

Daño: Cantidad de daño que inflige a los enemigos.
Alcance: Número de celdas a las que puede atacar desde su posición.
Velocidad de disparo: Frecuencia con la que puede atacar.

Codigo dado por el profesor:

```java
public class Tower {
private int damage;
private int range;
private int fireRate; // turnos entre disparos// Constructores, getters y setters
}
public class CannonTower extends Tower {
public CannonTower() {
super(50, 2, 3); // daño, alcance, velocidad de disparo
}
}
```

De la misma manera que con los enemigos, crearemos dos archivos para las torres. Primero, el archivo Tower.java que contendrá la definición de la clase base:

```java
package org.example;

public class Tower {
    private int damage;
    private int range;
    private int fireRate; // turnos entre disparos

    public Tower(int damage, int range, int fireRate) {
        this.damage = damage;
        this.range = range;
        this.fireRate = fireRate;
    }

    // getters y setters
}

```

Luego, el archivo CannonTower.java para definir una torre específica que herede de Tower:

```java
package org.example;

public class CannonTower extends Tower {
    public CannonTower() {
        super(50, 2, 3); // daño, alcance, velocidad de disparo
    }
}
```

# Sistema de oleadas

**Descripción**: Los enemigos llegan en oleadas que aumentan en dificultad a medida que avanza el
juego. Cada oleada puede tener más enemigos o enemigos más fuertes.

## Configuración de oleadas:

**Número de enemigos**: Cantidad de enemigos por oleada.
**Tipo de enemigos**: Variedad de enemigos que aparecen en la oleada.

Código dado por el profesor:

```java
public class Wave {
private List<Enemy> enemies;
private int waveNumber;
public Wave(int waveNumber) {
this.waveNumber = waveNumber;
this.enemies = generateEnemies(waveNumber);
}
private List<Enemy> generateEnemies(int waveNumber) {
List<Enemy> enemies = new ArrayList<>();
for (int i = 0; i < waveNumber * 5; i++) { // más enemigos cada oleada
enemies.add(new BasicEnemy());
}
if (waveNumber % 5 == 0) { // jefe cada 5 oleadas
enemies.add(new BossEnemy());
}
return enemies;
}
// Métodos para manejar el progreso de la oleada
}
```

Para implementar este sistema de oleadas, crearemos un archivo Wave.java:

```java
package org.example;

import java.util.ArrayList;
import java.util.List;

public class Wave {
    private List<Enemy> enemies;
    private int waveNumber;

    public Wave(int waveNumber) {
        this.waveNumber = waveNumber;
        this.enemies = generateEnemies(waveNumber);
    }

    private List<Enemy> generateEnemies(int waveNumber) {
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < waveNumber * 5; i++) { // más enemigos cada oleada
            enemies.add(new BasicEnemy());
        }
        if (waveNumber % 5 == 0) { // jefe cada 5 oleadas
            enemies.add(new BossEnemy());
        }
        return enemies;
    }

    // Métodos para manejar el progreso de la oleada
}
```

# Sistema de puntuación

**Descripción**: El jugador gana puntos por derrotar enemigos y pierde puntos si los enemigos alcanzan la base.

**Puntos por enemigo derrotado:** Cada enemigo tiene una recompensa que se suma a la puntuación del jugador.

**Pérdida de puntos**: Si un enemigo alcanza la base, el jugador pierde puntos.

Código dado por el profesor:

```java
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
return baseHealth;}
// Métodos adicionales según las necesidades del juego
}
```

Para implementar este sistema de puntuación, creamos un archivo Player.java:

```java
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
```

# Flujo del juego

1. Inicialización:
o Se inicializa el mapa, el jugador y la primera oleada de enemigos.
2. Colocación de torres:
o El jugador coloca torres en el mapa.
3. Inicio de oleadas:
o El jugador inicia una oleada y los enemigos comienzan a moverse hacia la base.
4. Ataque de torres:
o Las torres atacan a los enemigos dentro de su alcance.
5. Actualización del estado:
o Se actualiza el estado del juego: se verifican las vidas de los enemigos, se calcula la
puntuación y la salud de la base.
6. Finalización de oleada:
o Al finalizar una oleada, el jugador puede colocar nuevas torres o mejorar las
existentes antes de iniciar la siguiente oleada.
7. Fin del juego:
o El juego termina si la salud de la base llega a cero.

Sabiendo todo esto vamos a crear Celda.java

```java
package org.example;

public class Celda {
    private boolean esCamino;
    private boolean esBase;
    private boolean esVacio = false;
    private Tower torre;

    public Celda(boolean esCamino, boolean esBase) {
        this.esCamino = esCamino;
        this.esBase = esBase;
        this.torre = null;
        if (!esCamino && !esBase) this.esVacio = true;
    }

    public boolean esCamino() {
        return esCamino;
    }

    public void setEsCamino(boolean esCamino) {
        this.esVacio = false;
        this.esCamino = esCamino;
    }

    public boolean esBase() {
        return esBase;
    }

    public void setEsBase(boolean esBase) {
        this.esVacio = false;
        this.esBase = esBase;
    }

    public Tower getTorre() {
        return torre;
    }

    public void setTorre(Tower torre) {
        this.torre = torre;
    }

    public boolean esVacio() {
        return esVacio;
    }

    public void setEsVacio(boolean esVacio) {
        this.esVacio = esVacio;
    }
}

```

y ahora creamos el Mapa.java:

```java
package org.example;

import java.util.ArrayList;

public class Mapa {
    private Celda[][] mapa;
    private int tamano;

    public Mapa(int tamano) {
        this.tamano = tamano;
        generateMapa();
    }

    public void generateMapa(){
        mapa = new Celda[this.tamano][this.tamano];
        for(int i = 0; i<tamano; i++){
            for(int j=0; j<tamano; j++){
                this.mapa[i][j] = new Celda(false, false);
            }
        }
    }

    public void insertarTorre(Tower torre, int x, int y){
        getCelda(x,y).setTorre(torre);
    }

    public void insertarCamino(int x, int y){
        getCelda(x, y).setEsCamino(true);
    }

    public void insertarBase(int x, int y){
        getCelda(x, y).setEsBase(true);
    }

    public Tower getTorre(int x, int y){
        return getCelda(x, y).getTorre();
    }

    private Celda getCelda(int x, int y){
        return this.mapa[x][y];
    }

    public void imprimirMapa(){
        for(int i = 0; i<tamano; i++){
            for(int j=0; j<tamano; j++){
                if(getCelda(i,j).EsVacio()) System.out.print(" ");
                else if(getCelda(i,j).esCamino()) System.out.print("C");
                else if(getCelda(i,j).esBase()) System.out.print("B");
            }
        }
        System.out.println();
    }
}
```

Explicación:

Primero, creamos la clase 'Celda.java', que representa cada celda en nuestro mapa de juego. Cada celda puede ser un camino, una base, o un espacio vacío donde se puede colocar una torre. Luego, construimos la clase 'Mapa.java', que utiliza la clase 'Celda' para crear el mapa completo del juego. Este mapa puede contener varias torres, y cada torre se coloca en una celda específica. Adicionalmente, el mapa tiene un método para insertar un camino y la base, y un método para obtener la torre de una celda específica.

## Estructura del problema

**Clases principales:**

Game: Clase principal que maneja la lógica del juego.
Map: Representa el mapa del juego.
Enemy: Clase base para todos los enemigos.
Tower: Clase base para todas las torres.
Wave: Maneja las oleadas de enemigos.
Player: Representa al jugador y sus estadísticas.

**Interfaces y clases de Mocking:**

EnemyFactory: Interfaz para la creación de enemigos (concretas: BasicEnemyFactory,
BossEnemyFactory).
TowerFactory: Interfaz para la creación de torres (concretas: CannonTowerFactory,
LaserTowerFactory).

**Entrada y salida**

Entrada:

Comandos del usuario para colocar torres, iniciar oleadas, etc.
Datos iniciales del mapa y configuración de juego.

Salida:

Estado del juego después de cada comando.
Puntuación y estado de salud de la base.

## Ejemplo de entrada y salida

PLACE_TOWER Cannon 3 4
START_WAVE
Salida:
Torre Cannon colocada en (3, 4)
Oleada 1 iniciada
Enemigos en camino...
Estado del juego:
Mapa:
[ ][ ][ ][ ][ ]
[ ][C][ ][ ][ ]
[ ][ ][ ][E][B]
Puntuación: 100
Vida de la base: 90

Bien empecemos

Primero crearemos la interfaz EnemyFactory:

```java
package org.example;

public interface EnemyFactory {
    Enemy createEnemy();
}
```

Luego, crearemos la interfaz TowerFactory:

```java
package org.example;

public interface TowerFactory {
    Tower createTower();
}

```

Luego crearemos las clases concretas que implementan estas interfaces. Comenzaremos con BasicEnemyFactory y BossEnemyFactory:

```java
package org.example;

public class BasicEnemyFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy() {
        return new BasicEnemy();
    }
}
```

```java
package org.example;

public class BossEnemyFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy() {
        return new BossEnemy();
    }
}

```

A continuación, crearemos las clases CannonTowerFactory y LaserTowerFactory:

```java
package org.example;

public class CannonTowerFactory implements TowerFactory {
    @Override
    public Tower createTower() {
        return new CannonTower();
    }
}

```

```java
package org.example;

public class LaserTowerFactory implements TowerFactory {
    @Override
    public Tower createTower() {
        return new LaserTower();
    }
}
```

Luego creamos la clase LaserTower:

```java
package org.example;

public class LaserTower extends Tower{
    public LaserTower() {
        super(100, 4, 4);
    }
}
```

Listo, ahora vamos a crear la clase [Game.java](http://Game.java) que es la clase principal que maneja la lógica del juego:

```java
package org.example;

public class Game {
    private Player player;
    private Mapa mapa;
    private Wave wave;
    private TowerFactory towerFactory;
    private EnemyFactory enemyFactory;

    public Game() {
        this.player = new Player();
        this.mapa = new Mapa(5);
        this.towerFactory = new CannonTowerFactory();
        this.enemyFactory = new BasicEnemyFactory();
        this.wave = new Wave(1);
    }

    public void placeTower(int x, int y) {
        Tower tower = towerFactory.createTower();
        mapa.insertarTorre(tower, x, y);
    }

    public void startWave() {
        Wave wave = new Wave(player.getScore());
        wave.start();
    }

    // Otros métodos de control del juego
}
```

Esta clase inicializa el jugador, el mapa y las fábricas de torres y enemigos. También maneja la colocación de las torres y el inicio de las oleadas de enemigos.

Reescribimos [Wave.java](http://Wave.java) para implementar las interfaces:

```java
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

    // Métodos para manejar el progreso de la oleada
}
```

Creamos el metodo [main.java](http://main.java) para correr el juego:

```java
package org.example;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.insertarCaminoPredeterminado();
        game.insertarObjetivoPredeterminado();
        System.out.println("Mapa inicial:");
        game.mostrarMapa();
    }

}
```

Salida:

![Untitled](PC4-Desarrollo%20de%20software%208c67f291d4b94610bba1510cbf37dc30/Untitled.png)