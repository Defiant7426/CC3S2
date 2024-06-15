# Actividad Instalación Docker

Para instalar Docker Engine seguimos los siguientes comandos:

```bash
# Add Docker's official GPG key:
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

# Add the repository to Apt sources:
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update
```

Luego para instalar la ultima version de Docker:

```bash
 sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```

Para verificar que tenemos instalado docker podemos ejecutar el siguiente comando:

```bash
docker version
```

Salida:

![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled.png)

Luego para instalar Docker Desktop descargamos el siguiente archivo:

![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%201.png)

Para ver si puedes ejecutar contenedores, ingresa el siguiente comando en la ventana de
Terminal y presiona Enter:

```bash
sudo apt-get install ./docker-desktop-<version>-<arch>.deb
```

Para verificar que Docker se instalo correctamente podemos ejecutar los sigueintes comandos:

```bash
docker container run hello-world
```

Salida:

![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%202.png)

Prueba con otra imagen de prueba divertida que normalmente se usa para verificar la
instalación de Docker. Ejecuta el siguiente comando:

```bash
docker container run rancher/cowsay Hello
```

Salida:

![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%203.png)

Luego de crear el grupo podemos ejecutarl los comandos de docker sin la necesidad de usar sudo:

![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%204.png)

# Habilitar Kubernetes en Docker Desktop

¿Qué es Kubernetes?
Kubernetes es una plataforma potente que automatiza la implementación, el escalado y la gestión de aplicaciones en contenedores. Ya sea que seas un desarrollador, un ingeniero de DevOps o un administrador de sistemas, Kubernetes ofrece las herramientas y abstracciones necesarias para administrar tus contenedores y aplicaciones de manera escalable y eficiente.

Simplemente nos dirigimos a setting → Enable Kubernets → Apply & restart

![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%205.png)

![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%206.png)

# Probando minikube y kubectl

Para instalar minikube escribimos los siguientes comandos:

```bash
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
```

Salida:

![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/8c1c79a6-eb35-4f55-8584-c7ab38f2c943.png)

![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%207.png)

```bash
sudo install minikube-linux-amd64 /usr/local/bin/minikube && rm minikube-linux-amd64
```

Luego ejecutamos el comando para inicializar minikube:

```bash
minikube start
```

Salida:

![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%208.png)

![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%209.png)

Ahora seguimos los siguientes pasos:

1. escribimos el siguiente comando:
    
    ```bash
    kubectl config get-contexts
    ```
    
    Salida:
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2010.png)
    
2. Ahora veamos los nodos que tengo en mi cluster con este comando:
    
    ```bash
    kubectl get nodes
    ```
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2011.png)
    
3. Creamos un archivo con nombre  nginx.yaml y le ponemos el siguiente contenido:
    
    ```yaml
    apiVersion: v1
    kind: Pod
    metadata:
      name: nginx
      labels:
        app.kubernetes.io/name: proxy
    spec:
      containers:
      - name: nginx
        image: nginx:stable
        ports:
        - containerPort: 80
          name: http-web-svc
    ```
    
4. creamos un pod que ejecute Nginx con el siguiente comando
    
    ```bash
    kubectl apply -f nginx.yaml
    ```
    
    Salida:
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2012.png)
    
5. podemos verificar si el pod se está ejecutando con kubect:
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2013.png)
    
6. Para acceder al servidor Nginx, necesitamos exponer la aplicación que se ejecuta en el pod con el siguiente comando:
    
    ```yaml
    kubectl expose pod nginx --type=NodePort --port=80
    ```
    
    Salida:
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2014.png)
    
7. Podemos usar kubectl para enumerar todos los servicios definidos en el clúster:
    
    ```bash
    kubectl get services
    ```
    
    Salida:
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2015.png)
    
8. Ahora, puedes usar minikube para crear un túnel hacia el clúster y abrir un navegador con la URL correcta para acceder al servidor web Nginx. Utilizamos el siguiente comando:
    
    ```bash
    minikube service nginx
    ```
    
    Salida:
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2016.png)
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2017.png)
    
    El resultado anterior muestra que minikube creó un túnel para el servicio nginx que escucha en el puerto del nodo 30432 que está en nuestra computadora portátil.
    

Ahora que terminamos, podemos limpiar:

1. Detenemos el túnel hacia el clúster presionando Ctrl + C dentro de la ventana de Terminal.
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2018.png)
    
2. Elimina el servicio nginx y el pod en el clúster: $ kubectl delete service nginx. Usamos el siguiente comando:
    
    ```bash
    kubectl delete pod nginx
    ```
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2019.png)
    
3. Detén el clúster con el siguiente comando: 
    
    ```bash
    minikube stop
    ```
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2020.png)
    

# Ejercicios

1. Si queremos trabajar con un clúster que consta de varios nodos en minikube, podemos usar este comando:
    
    ```bash
    minikube start --nodes 3 -p demo
    ```
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/700a9bd3-6d07-4ce2-a814-e8bfceabd98b.png)
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2021.png)
    
2. Detenemos el cluster
    
    ```bash
    minikube stop -p demo
    ```
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2022.png)
    
3. Eliminamos todos los clústeres del sistema con este comando
    
    ```bash
    minikube delete –all
    ```
    

# Kind

1. En nuestra maquina podemos usar el siguiente script para instalar Kind desde sus archivos binarios
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2023.png)
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2024.png)
    
2. Ahora, intentemos crear un clúster de Kubernetes simple que consta de un nodo maestro y dos nodos trabajadores. Utiliza este comando para lograr esto:
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2025.png)
    
3. Para verificar que se ha creado un clúster, utilizamos este comando
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2026.png)
    
4.  Podemos crear un clúster adicional con un nombre diferente usando el parámetro --name, así:
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2027.png)
    
5. Enumeramos los clústeres 
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2028.png)
    
6. Ahora podemos usar kubectl para acceder y trabajar con los clústeres que acabamos de crear. Mientras creaba un clúster, Kind también actualizó el archivo de configuración del kubectl. Podemos verificar esto con el siguiente comando:
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2029.png)
    
7. Utiliza el siguiente comando para convertir el clúster de demo en tu clúster actual si el asterisco indica que hay otro clúster actual:
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2030.png)
    
8. Enumeramos todos los nodos del clúster de muestra
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2031.png)
    
9. Ahora, intenta ejecutar el primer contenedor en este clúster. Usa el servidor web Nginx de confianza, como hicistes antes. Utiliza el siguiente comando para ejecutarlo:
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2032.png)
    
10.  Para acceder al servidor Nginx, necesitamos realizar el reenvío de puertos usando kubectl. Utiliza este comando para hacerlo:
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2033.png)
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2034.png)
    
11. Podemos usa este comando para eliminar el pod del clúster
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2035.png)
    
12. Limpiemos y eliminemos los dos clústeres que acabamos de crear
    
    ![Untitled](Actividad%20Instalacio%CC%81n%20Docker%20d5a83267932b4622a68edb55204a496d/Untitled%2036.png)
    

# Preguntas

1. En tus propias palabras, usando analogías, explica qué es un contenedor.
    
    Un contenedor tiene todo lo necesario para que una aplicación pueda ejecutarse, sin importar el entorno donde se encuentre. Podemos compararlo con un carro de comida rápida: no es necesario comprar cada ingrediente y cocinarlo cada vez que preparamos un plato. En lugar de eso, todo lo que necesitamos se encuentra en el carro de comida rápida, listo para ser preparado en cualquier lugar y en cualquier momento.
    
2. ¿Por qué se considera que los contenedores cambian las reglas del juego en IT? Menciona tres o cuatro razones
    1. Separación de responsabilidades: Los desarrolladores se centran en la logica del negocio y las dependencias de esta, mientras que los equipos de operaciones de TI se dedican a desplegarlas.
    2. Portabilidad de las cargas de trabajo: Como los contenedores se pueden ejecutar casi en cualquier lugar, resulta facilisimo desarrollarlos y desplegarlos en sistemas operativos Linux, Windows y Mac.
    3. Aislamiento de aplicaciones: Los contenedores virtualizan recursos de CPU, memoria, almacenamiento y red, brindando a los desarrolladores un sistema operativo aislado para sus aplicaciones.
    
    Fuente: [https://cloud.google.com/learn/what-are-containers?hl=es#section-3](https://cloud.google.com/learn/what-are-containers?hl=es#section-3)
    
3. ¿Qué significa cuando afirmamos que, si un contenedor se ejecuta en una plataforma
determinada, entonces se ejecutará en cualquier lugar? Menciona dos o tres razones por las que esto es cierto.
    
    Esto significa que los contenedores son portables y consistentes a través de diferentes entornos. Esto es posible porque:
    
    1. Los contenedores incluyen todas las dependencias necesarias para ejecutar la aplicación, lo que asegura que la aplicación se comportará de la misma manera en diferentes sistemas.
    2. Los contenedores son independientes del sistema operativo subyacente, lo que permite que puedan ser ejecutados en cualquier plataforma que soporte la tecnología de contenedores.
4. ¿Es verdadera o falsa la siguiente afirmación: los contenedores Docker solo son útiles para
aplicaciones modernas y totalmente nuevas basadas en microservicios? Por favor justifica tu
respuesta.
    
    Esta afirmación es falsa. Si bien los contenedores Docker son altamente útiles para aplicaciones modernas basadas en microservicios, también pueden ser utilizados para encapsular y ejecutar aplicaciones más antiguas. De esta manera, se facilita la migración, implementación y escalabilidad de estas aplicaciones en entornos modernos.
    
5. ¿Por qué nos importaría instalar y usar un administrador de paquetes en nuestra computadora
local?
    
    Nos importaría instalar y usar un administrador de paquetes en nuestra computadora local porque facilita la gestión de software. Permite instalar, actualizar y eliminar software de manera eficiente y sistemática, lo que ahorra tiempo y reduce los posibles errores asociados con la gestión manual de software.
    
6. ¿Con Docker Desktop, puede desarrollar y ejecutar contenedores de Linux?
    
    Sí, con Docker Desktop puedo desarrollar, probar y ejecutar contenedores de Linux en mi computadora local. Esta funcionalidad permite a los desarrolladores trabajar en un entorno consistente con la producción, independientemente del sistema operativo de su máquina local.
    
7. ¿Por qué son esenciales buenas habilidades de programación (como Bash o PowerShell) para el
uso productivo de los contenedores?
    
    Tener buenas habilidades de programación puede ayudar en gran medida en adquirir la capacidad de leer y escribir scripts de Bash o PowerShell puede ayudar a resolver problemas en el uso de contenedores.
    
8. Nombra tres o cuatro distribuciones de Linux en las que Docker esté certificado para ejecutarse.
    
    Ubuntu, CentOS, Debian y Fedora.
    
9. Instalaste minikube en tu sistema. ¿Para qué tipo de tareas utilizarás esta herramienta?
    
    Se usara crear y administrar un clúster local de Kubernetes en la máquina. Minikube permite probar los despliegues y la configuración de Kubernetes en un entorno local antes de moverlos a un entorno de producción.