# PC5 - Desarrollo de Software

Alumno: De la Cruz Valdiviezo, Pedro Luis David

El juego Tower Defense es un videojuego de consola donde el jugador debe defender su base de
oleadas de enemigos colocando torres en lugares estratégicos del mapa. El proyecto incluirá el uso de mocks, stubs y fakes para pruebas unitarias y de integración utilizando Mockito y pruebas de mutación.

Entrada:

- Comandos del usuario para colocar torres, iniciar oleadas, etc.
- Datos iniciales del mapa y configuración de juego.

Salida:

- Estado del juego después de cada comando.
- Puntuación y estado de salud de la base.

# Código en java para el juego

Clase principal TowerDefenseGame

```java
package org.example;
import java.nio.file.WatchEvent;
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

    public void placeTower(Tower tower, int x, int y){ // inserta una torre
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
}
```

Clase Tower:

```java
package org.example;

public class Tower {

    private char symbol; //simbolo de la torre

    public Tower(char symbol){
        this.symbol = symbol; // asignamos el simbolo de la torre
    }

    public char getSymbol() {
        return symbol; // debolvemos el simbolo de la torre
    }
}
```

Clase Map:

```java
package org.example;

public class Map {

    private char[][] grid; // matriz bidimencional que representa el mapa

    public Map(){ // constructor Map
        grid = new char[5][5]; // Se crea un mapa de 5x5
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                grid[i][j] = ' '; // '0' char vacio en cada celda
            }
        }
    }

    public void placeTower(Tower tower, int x, int y) {
        grid[x][y] = tower.getSymbol(); // colocamos la torre en el mapa
    }

    @Override
    public String toString(){ // podemos imprimir el map con esto
        StringBuilder sb = new StringBuilder();
        for(char[] row : grid){
            for(char cell : row){
                sb.append("[").append(cell).append("]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

```

Clase Wave:

```java
package org.example;

public class Wave {

    public void start() {// iniciamos la oleada
        System.out.println("Oleada iniciada!");
    }
}
```

Clase Player:

```java
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
```

Este juego es igual a la PC4 por lo que no vamos a entrar en detalles en el codigo

## Teoría de conceptos clave

❓¿Qué es Docker?

✅ Docker es una plataforma de software que permite la creación, prueba e implementación de aplicaciones en contenedores. Los contenedores son ligeros y contienen todo lo necesario para ejecutar una aplicación, lo que asegura que esta funcionará de la misma forma independientemente del entorno en el que se ejecute.

❓¿Qué es el Dockerfile?

✅ Dockerfile es un archivo de texto simple que contiene las instrucciones para construir una imagen Docker. Se puede considerar como una especie de script que automatiza los comandos que de otro modo tendrías que ejecutar manualmente para construir una imagen.

❓¿Qué es docker exec?

✅ Docker exec es un comando que permite ejecutar comandos específicos dentro de un contenedor en ejecución. Por ejemplo, podemos usar docker exec para abrir una shell interactiva dentro de un contenedor, lo que nos permite explorar el sistema de archivos del contenedor y ejecutar comandos directamente. Por ejemplo:

```java
docker exec -it tower-defense-container /bin/bash
```

❓¿Qué son las redes en Docker?

✅ Las redes en Docker permiten que los contenedores se comuniquen entre sí y con otros sistemas fuera de Docker. Se pueden utilizar para aislar los contenedores y mejorar la seguridad de la aplicación.

❓¿Para que sirven las redes de Docker?

✅ Las redes de Docker sirven para establecer una comunicación entre los contenedores, para que puedan intercambiar información y funcionar como una única entidad. A su vez, también permiten controlar el tráfico de red y mejorar la seguridad al aislar los contenedores entre sí.

Se pueden hacer con:

```java
docker network create game-network
```

Y los contenedores pueden unirse a esta red mediante la opción--network al ejecutardocker run

❓¿Qué son los volúmenes en Docker?

✅ Los volúmenes en Docker son como carpetas especiales que persisten los datos incluso cuando los contenedores se detienen o eliminan.

❓¿Para que sirven los volúmenes en Docker?

✅ Los volúmenes almacenan datos que son importantes para la aplicación, como archivos de configuración o datos de la base de datos.

Se hace con:

```java
docker volume create game-data
```

Y se puede montar en un contenedor con la opción-v:

```java
docker run -it --name tower-defense-container --network game-network -v game-data:/app/data tower-
defense-game
```

❓¿Qué es Docker Compose?

✅ Docker Compose es una herramienta que permite definir y administrar aplicaciones de múltiples contenedores en Docker. 

❓¿Para que sirve Docker Compose?

✅ Se utilizan archivos de configuración YAML para declarar los servicios, redes y volúmenes necesarios para su aplicación. Con un solo comando, puede crear e iniciar todos los servicios desde su configuración.

Por ejemplo, un archivo docker-compose.yml para nuestro juego:

```java
version: '3'
services:
  game:
    image: tower-defense-game
    networks:
      - game-network
    volumes:
      - game-data:/app/data

networks:
  game-network:
    driver: bridge

volumes:
  game-data:
    driver: local
```

Para iniciar los servicios definidos, se usa el comando:

```java
docker-compose up -d
```

❓¿Qué es Docker Swarm?

✅ Docker Swarm es una herramienta de orquestación de contenedores nativa de Docker. Permite a los usuarios administrar un clúster de servidores Docker, conocidos como un enjambre, y coordinar tareas entre ellos.

❓¿Para qué sirve Docker Swarm?

✅ Docker Swarm se utiliza para gestionar y orquestar contenedores a gran escala. Permite el despliegue, escalado y gestión de servicios y tareas a través de múltiples nodos Docker.

❓¿Qué son los kubernetes?

✅ Kubernetes es una plataforma de orquestación de contenedores de código abierto diseñada para automatizar la implementación, escalado y operación de aplicaciones en contenedores. Kubernetes permite gestionar clústeres de máquinas virtuales o físicas de manera eficiente.

❓¿Qué son los Pods?

✅ Un Pod es la unidad de despliegue mas pequeña en kubernetes. Un Pod representa un solo proceso dentro de un clúster y puede contener uno o más contenedores que comparten almacenamiento y red.

❓¿Qué es un Deployment?

✅ Un Deployment asegura que una cantidad especificada de réplicas de una aplicación estén corriendo en cualquier momento. Ejemplo dedeployment.yaml:

```java
apiVersion: apps/v1
kind: Deployment
metadata:
  name: tower-defense-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: tower-defense-game
  template:
    metadata:
      labels:
        app: tower-defense-game
    spec:
      containers:
      - name: tower-defense-game
        image: tower-defense-game
        ports:
        - containerPort: 8080
```

❓¿Qué es un service?

✅ Un Service en Kubernetes es un objeto que expone una aplicación ejecutándose en un conjunto de Pods como una red de servicio confiable. Los Services permiten que tu aplicación sea accesible dentro de la red de Kubernetes, o exponerla al internet.

```java
apiVersion: v1
kind: Service
metadata:
  name: tower-defense-service
spec:
  selector:
    app: tower-defense-game
  ports:
  - protocol: TCP
    port: 80
    targetPort: 8080
  type: LoadBalancer
```

# Ejercicio 1: Configuración y uso de docker

❓Describe los principios fundamentales de los contenedores de Docker arquitectura interna

✅ La arquitectura es cliente-servidor, docker es el deamon que se ejecuta en el host del sistema operativo. El clliente se comunica con el servidor para ejecutar comandos. Los contenedores de Docker se ejecutan como procesos aislados en el espacio del usuario en el sistema operativo host. 

❓Explica como docker maneja la seguridad y aislamiento de contenedores

✅ Docker utiliza varias características del kernel de Linux para proporcionar aislamiento y seguridad a los contenedores. Estas incluyen cgroups, que limita los recursos del sistema que un contenedor puede usar, y namespaces, que aíslan a los contenedores entre sí y del sistema operativo host. 

❓Compara y contrasta Docker con soluciones de virtualización tradicionales, como VMware y
VirtualBox. Discute las ventajas y desventajas de cada enfoque.

✅ Docker usa el sistema operativo que se encuentra en la misma maquina, virtualbox mas bien usa un sistema operativo virtual por lo cual es mas ligero. La ventaja de un VirtualBox es de que se encuentra completamente aislado del sistema operativo por lo cual es mucho mas seguro.

❓Escribimos el Dockerfile del juego:

 

```java
FROM openjdk:17
WORKDIR /app
COPY src/main/java/ /app
RUN javac org/example/*.java
CMD ["java", "org.example.TowerDefenseGame"]
```

Explicación:

Primero, se especifica la imagen base `FROM openjdk:17`. Luego se establece el directorio de trabajo en el contenedor con `WORKDIR /app`. Posteriormente, se copian los archivos de código fuente del host al contenedor con `COPY src/main/java/ /app`. La instrucción `RUN javac org/example/*.java` compila los archivos de código fuente. Finalmente, `CMD ["java", "org.example.TowerDefenseGame"]` especifica el comando que se ejecutará cuando se inicie el contenedor.

## Configuración de Docker en Intellj idea

Después de asegurarnos de tener el plugin de docker instalado en nuestro IDE, configuramos el Docker Daemon con una conexión local:

![Untitled](PC5%20-%20Desarrollo%20de%20Software%20e9c9c838f65e423ab6a6a1715f50842a/Untitled.png)

## Creamos una configuración de ejecución para Docker

En la configuración de Run le damos a Dockerfile:

![Untitled](PC5%20-%20Desarrollo%20de%20Software%20e9c9c838f65e423ab6a6a1715f50842a/Untitled%201.png)

Llenamos los campos:

![Untitled](PC5%20-%20Desarrollo%20de%20Software%20e9c9c838f65e423ab6a6a1715f50842a/Untitled%202.png)

Ahora tenemos contruida la imagen de docker:

![Untitled](PC5%20-%20Desarrollo%20de%20Software%20e9c9c838f65e423ab6a6a1715f50842a/Untitled%203.png)

Le damos click para construir el contenedor e iniciar la aplicación:

![Untitled](PC5%20-%20Desarrollo%20de%20Software%20e9c9c838f65e423ab6a6a1715f50842a/Untitled%204.png)

# Ejercicio 2: Redes y volúmenes en Docker

Creamos una red personalizada:

```java
docker network create game-network
```

Salida:

![Untitled](PC5%20-%20Desarrollo%20de%20Software%20e9c9c838f65e423ab6a6a1715f50842a/Untitled%205.png)

Ejecutamos el contenedor en la red:

![Untitled](PC5%20-%20Desarrollo%20de%20Software%20e9c9c838f65e423ab6a6a1715f50842a/Untitled%206.png)

❓¿Qué son los volúmenes en Docker?

✅ Los volúmenes en Docker son como carpetas especiales que persisten los datos incluso cuando los contenedores se detienen o eliminan.

❓¿Para que sirven los volúmenes en Docker?

✅ Los volúmenes almacenan datos que son importantes para la aplicación, como archivos de configuración o datos de la base de datos.

❓Discute los diferentes tipos de redes (bridge, host, overlay) y cuándo es apropiado usar cada una

✅ Docker utiliza la tecnología de red de puente ('bridge') para crear una red privada interna en el host, en la cual los contenedores se conectan. Esta red permite que los contenedores se comuniquen entre sí, y es útil para aplicaciones multiservicio en una misma máquina host. La red 'host' permite que los contenedores utilicen directamente la red del host, sin aislamiento. Esta puede ser útil para los contenedores que necesitan un alto rendimiento de red. La red 'overlay' permite conectar múltiples máquinas Docker en una misma red, lo que es útil para aplicaciones distribuidas que se ejecutan en múltiples host.

❓Describe los mecanismos de persistencia de datos en Docker, incluyendo volúmenes y bind
mounts. Explica las diferencias entre ellos y las mejores prácticas para su uso

✅Un volumen permite persistir datos generados por el contenedor en una área del host, independiente del ciclo de vida del contenedor. Los bind mounts, cualquier carpeta o archivo en el host, son útiles durante el desarrollo para acceder a archivos de configuración y bases de datos en el host. 

Creamos y montamos un volumen:

```java
docker volume create game-data
```

Salida:

![Untitled](PC5%20-%20Desarrollo%20de%20Software%20e9c9c838f65e423ab6a6a1715f50842a/Untitled%207.png)

Luego ejecutamos el comando:

```java
docker run -it --name tower-defense-container --network game-network -v game-data:/app/data tower-defense-game
```

![Untitled](PC5%20-%20Desarrollo%20de%20Software%20e9c9c838f65e423ab6a6a1715f50842a/Untitled%208.png)

## Implementación con Docker Compose

❓¿Qué es Docker Compose?

✅ Docker Compose es una herramienta que permite definir y administrar aplicaciones de múltiples contenedores en Docker. 

❓¿Para que sirve Docker Compose?

✅ Se utilizan archivos de configuración YAML para declarar los servicios, redes y volúmenes necesarios para su aplicación. Con un solo comando, puede crear e iniciar todos los servicios desde su configuración.

Crear un archivo docker-compose.yml

```java
version: '3'
services:
  game:
    image: tower-defense-game
    networks:
      - game-network
    volumes:
      - game-data:/app/data

networks:
  game-network:
    driver: bridge

volumes:
  game-data:
    driver: local
```

Explicación:

Decimos que la version de configuracion es la 3 `version: '3'`. Luego, se definen los servicios que componen la aplicación con `services:`. En este caso, solo hay un servicio: `game`. Para cada servicio, se especifica la imagen Docker a utilizar con `image: tower-defense-game`, las redes a las que se debe conectar con `networks: - game-network` creada previamente, y los volúmenes que debe montar con `volumes: - game-data:/app/data` tambien creada previamente. Después, se definen las redes con `networks:` y los volúmenes con `volumes:`. Para cada red y volumen, se especifica el controlador a utilizar con `driver: bridge` y `driver: loca`. Finalmente, se inician los servicios con el comando `docker-compose up -d`, lo que crea e inicia los contenedores definidos en el archivo docker-compose.yml en modo detached (o en segundo plano).

Iniciar los servicios:

```java
docker-compose up -d
```

Salida:

![Untitled](PC5%20-%20Desarrollo%20de%20Software%20e9c9c838f65e423ab6a6a1715f50842a/Untitled%209.png)

## Ejercicio 3: Orquestación con Kubernetes

❓Describe la arquitectura de Kubernetes y sus componentes principales, incluyendo el API
server, etcd, scheduler, y kubelet. Explica cómo estos componentes interactúan para
gestionar un clúster de Kubernetes

✅ Kubernetes se basa en una arquitectura maestro-esclavo donde hay un orquestador maestro similarmente a big-data. El servidor API, que es el punto central de la arquitectura, procesa las operaciones de los usuarios. etcd es la base de datos clave-valor que almacena toda la información del clúster. El kubelet es un agente que se ejecuta en cada nodo y se encarga de asegurar que los contenedores estén ejecutándose en un Pod.

❓Discute las estrategias de escalabilidad y alta disponibilidad en Kubernetes. Explica cómo
Kubernetes maneja la recuperación de fallos y la gestión de réplicas.

✅Como habiamos dicho en la pregunta anterior que kubernetes tiene una arquitectura similar a la big-data tambien tienen estrategias similares en la escabilidad y alta disponibilidad. Si un pod falla, Kubernetes puede crear automáticamente uno nuevo para reemplazarlo, garantizando así la disponibilidad y la recuperación de fallos. Las réplicas aseguran que siempre haya un número específico de pods idénticos en funcionamiento.

❓Escribe un archivo deployment.yaml para la aplicación Tower Defense. Asegúrate de definir
los recursos necesarios (CPU, memoria) y las políticas de escalabilidad.

deployment.yaml

```java
apiVersion: apps/v1
kind: Deployment
metadata:
  name: tower-defense-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: tower-defense-game
  template:
    metadata:
      labels:
        app: tower-defense-game
    spec:
      containers:
        - name: tower-defense-game
          image: tower-defense-game
          ports:
            - containerPort: 8080
```

Explicación:

Primero, se especifica la versión de la API de Kubernetes con `apiVersion: apps/v1`. Luego, se indica que el recurso que se está definiendo es un Deployment con `kind: Deployment`. A continuación, se proporciona metadatos sobre el recurso, incluyendo su nombre, con `metadata: name: tower-defense-deployment`. En la sección `spec`, se define que solo se debe ejecutar una réplica del pod con `replicas: 1`, y se indica qué pods forman parte de este Deployment con `selector: matchLabels: app: tower-defense-game`. Luego, en la sección `template`, se define cómo debe ser cada pod. Esto incluye metadatos y especificaciones para los contenedores que se ejecutarán en el pod. En este caso, el contenedor se basa en la imagen `tower-defense-game`, y expone el puerto 8080 con `ports: - containerPort: 8080`.

service.yml

```java
apiVersion: v1
kind: Service
metadata:
  name: tower-defense-service
spec:
  selector:
    app: tower-defense-game
  ports:
  - protocol: TCP
    port: 80
    targetPort: 8080
  type: LoadBalancer
```

Explicación:

La instrucción `apiVersion: v1` especifica la versión de la API a utilizar. `kind: Service` indica que estamos creando un servicio. `metadata: name: tower-defense-service` nombra el servicio. `spec: selector: app: tower-defense-game` coincide con los pods que tienen la etiqueta `app: tower-defense-game`. `ports:` define los puertos a ser utilizados, y `type: LoadBalancer` indica que Kubernetes debe utilizar un balanceador de carga para distribuir el tráfico de red a los pods.

### Aplicar los archivos de configuración en Kubernetes

```java
kubectl apply -f deployment.yml
kubectl apply -f service.yml
```

Explicación:

Los comandos `kubectl apply -f deployment.yml` y `kubectl apply -f service.yml` se utilizan para aplicar las configuraciones de despliegue y servicio en el clúster de Kubernetes. Esto creará los pods y servicios necesarios para ejecutar nuestra aplicación. Después de ejecutar estos comandos, Kubernetes comenzará a desplegar nuestra aplicación basándose en las configuraciones especificadas en los archivos de despliegue y servicio.

Salida:

![Untitled](PC5%20-%20Desarrollo%20de%20Software%20e9c9c838f65e423ab6a6a1715f50842a/Untitled%2010.png)

### Verificar el estado del despliegue

```java
kubectl get pods
kubectl get services
```

Explicación:

La instrucción `kubectl get pods` muestra todos los pods que están ejecutándose en el clúster de Kubernetes. `kubectl get services` muestra todos los servicios que están ejecutándose en el clúster de Kubernetes. Los servicios permiten el acceso a los pods desde fuera del clúster.

![Untitled](PC5%20-%20Desarrollo%20de%20Software%20e9c9c838f65e423ab6a6a1715f50842a/Untitled%2011.png)

# Ejercicio 4: Pruebas unitarias y de integración con Mockito

❓¿Que son los mocks?

✅ Los mocks son objetos que simulan el comportamiento de objetos reales de manera controlada. Se utilizan unitarias y de integración

❓¿Que son los stubs?

✅ Su propósito principal es evitar que las pruebas dependan de la implementación real de esos métodos.

❓¿Que son los fakes?

✅ Los fakes son implementaciones simplificadas de clases o interfaces. A diferencia de los mocks y stubs, los fakes tienen una lógica de negocio, aunque esta suele ser mucho más simple que la implementación real.

Añadimos Mockito a nuestro build.gradle:

```java
plugins {
    id 'java'
}

group = 'org.example'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter API for unit testing with JUnit 5
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.8.2'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.2'

    // For backward compatibility with JUnit 4 tests
    testImplementation 'junit:junit:4.13.2'
    testImplementation 'org.junit.vintage:junit-vintage-engine:5.8.2'
    testImplementation 'org.junit.jupiter:junit-jupiter-params:5.8.2'

    testImplementation 'org.assertj:assertj-core:3.23.1'
    testImplementation platform('org.junit:junit-bom:5.10.0')
    testImplementation 'org.junit.jupiter:junit-jupiter'

    implementation platform('org.assertj:assertj-bom:3.25.3')
    implementation 'org.assertj:assertj-core'

    implementation platform('com.google.code.gson:gson:2.10.1')
    implementation 'com.google.code.gson:gson'

    implementation platform('com.fasterxml.jackson:jackson-bom:2.17.0')
    implementation 'com.fasterxml.jackson.core:jackson-databind'

    implementation platform('org.junit:junit-bom:5.11.0-M1')
    implementation 'org.junit.jupiter:junit-jupiter'
    implementation 'org.junit.platform:junit-platform-launcher'
    implementation 'org.junit.vintage:junit-vintage-engine'

    implementation platform('org.mockito:mockito-bom:5.11.0')
    implementation 'org.mockito:mockito-core'
    implementation 'org.mockito:mockito-junit-jupiter'

    implementation platform('com.squareup.retrofit2:retrofit-bom:2.11.0')
    implementation 'com.squareup.retrofit2:retrofit'
    implementation 'com.squareup.retrofit2:converter-gson'

    // https://mvnrepository.com/artifact/com.sun.mail/jakarta.mail
    implementation group: 'com.sun.mail', name: 'jakarta.mail', version: '2.0.1'
}

test {
    useJUnitPlatform()
}
```

Creamos la prueba de mockito para TowerDefenseGame:

```java
package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class TowerDefenseGameTest {
    @Mock
    private Map mockMap;
    
    @Mock
    private Player mockPlayer;
    
    @InjectMocks
    private TowerDefenseGame game;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testPlaceTower() {
        Tower mockTower = mock(Tower.class);
        game.placeTower(mockTower, 2, 2);
        verify(mockMap).placeTower(mockTower, 2, 2);
    }
}
```

Explicación:

Primero, declaramos los objetos `Map` y `Player` que serán simulados con la anotación `@Mock`. Luego, declaramos el objeto `TowerDefenseGame` que será probado con la anotación `@InjectMocks`, ya que este objeto será inyectado con los mocks que hemos creado. En el método `setUp()`, inicializamos los mocks con `MockitoAnnotations.openMocks(this)`. En el método `testPlaceTower()`, creamos un mock de `Tower` y luego probamos el método `placeTower()` de la clase `TowerDefenseGame`. Finalmente, verificamos que el método `placeTower()` del mock `Map` fue llamado con `verify(mockMap).placeTower(mockTower, 2, 2)`.

Al correr el test:

![Untitled](PC5%20-%20Desarrollo%20de%20Software%20e9c9c838f65e423ab6a6a1715f50842a/Untitled%2012.png)

Lamentablemente no podemos juntar mockito con pitest, la salida al ejecutar el comando ./gradlew pitest es el siguiente:

![Untitled](PC5%20-%20Desarrollo%20de%20Software%20e9c9c838f65e423ab6a6a1715f50842a/e69c013a-4f21-4924-9e3d-0e4dec5d8b40.png)

## Ejercicio 6: Diseño por contrato (Design by Contract)

❓¿Qué es el diseño por contrato y cuales son las diferencias entre las precondiciones, postcondiciones e invariantes?

✅ El software debe tener especificaciones precisas y verificables para los componentes del software, es decir, las clases y los métodos. Estas especificaciones se expresan como "contratos" que describen lo que un método debe hacer (precondiciones), lo que garantiza que hará (postcondiciones), y qué condiciones se mantendrán constantes (invariantes).

❓Describe cómo el diseño por contrato puede mejorar la robustez y mantenibilidad del
código

✅El diseño por contrato puede mejorar la robustez del código al definir explícitamente lo que se espera de cada método, permitiendo que el sistema detecte cuando esas expectativas no se cumplen.

```java
package org.example;

public class Tower {

    private char symbol; //simbolo de la torre

    /**
     *
     * @param symbol
     * pre-condiciones: symbol no puede ser vacío
     * post-condiciones: solo se admiten "B" y "T" (por poner un ejemplo)
     */
    public Tower(char symbol){
        if (symbol == 'B' || symbol == 'T') {
            this.symbol = symbol; // asignamos el simbolo de la torre
        } else {
            throw new IllegalArgumentException("El simbolo de la torre no es valido");
        }
    }

    public char getSymbol() {
        return symbol; // debolvemos el simbolo de la torre
    }
}
```

Explicación:

En este ejemplo, diseñamos la clase `Tower` con un contrato. Definimos una precondición que el símbolo no puede estar vacío y una postcondición que sólo se admiten los símbolos "B" y "T". Si el símbolo no cumple estas condiciones, se lanza una excepción. Esto garantiza que los objetos `Tower` siempre se creen con un símbolo válido, mejorando la robustez del código.

Creamos los test para este contrato:

```java
package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TowerTest {

    @Test
    void testCreateTowerWithB() {
        Tower t = new Tower('B');
        assertEquals(t.getSymbol(),'B');
    }

    @Test
    void testCreateTowerWithT(){
        Tower t = new Tower('T');
        assertEquals(t.getSymbol(), 'T');
    }

    @Test
    void testCreateTowerWithInvalidParamether(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Tower('X'));
        assertEquals("El simbolo de la torre no es valido", exception.getMessage());
    }
}
```

Explicación:

En las pruebas, verificamos que el contrato se cumpla correctamente. En `testCreateTowerWithB` y `testCreateTowerWithT`, creamos una torre con los símbolos permitidos y verificamos que se asignen correctamente. En `testCreateTowerWithInvalidParamether`, intentamos crear una torre con un símbolo inválido y verificamos que se lance la excepción esperada.

Al ejecutar las pruebas:

![Untitled](PC5%20-%20Desarrollo%20de%20Software%20e9c9c838f65e423ab6a6a1715f50842a/Untitled%2013.png)