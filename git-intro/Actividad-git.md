# Actividad git
Autor: De la Cruz Valdiviezo, Pedro Luis David

Repositorio de esta actividad: https://github.com/Defiant7426/git-intro

## Iniciando git

Como ya tenia inicializado git en mi computadora entonces puedo insertar el siguiente comando para verificar mis credenciales:

```~$ git config --list```

![Imagen_1](Imagenes/img_1.png)

Inicializamos git y vemos los archivos ocultos:

![Imagen_2](Imagenes/img_2.png)

Vemos el estado inicial del repositorio:

![Imagen_3](Imagenes/img_3.png)

Creamos el archivo CC3S2.txt con el mensaje "Estoy en camino para pasar el examen CC3S2" en el.

![Imagen_4](Imagenes/img_4.png)

Verificamos el estado del repositorio

![Imagen_5](Imagenes/img_5.png)

Ejecutamos el archivo y lo confirmamos con commit y tambien verificamos el estado actual del repositorio

![Imagen_6](Imagenes/img_6.png)

Con el comado ```~$ git log``` para mostrar todas las confirmaciones de la rama actual
![imagen_7](Imagenes/img_7.png)

Modificamos el archivo con el comando echo y verificamos el estado del repositorio

![imagen_8](Imagenes/img_8.png)

Agregamos el achivo al git y lo confirmamos con un commit y comprovamos nuevamente los cambios usando el ```~$ git log```

![imagen_9](Imagenes/img_9.png)

Comparamos los commit usando ```~$ git diff```

![Imagen_10](Imagenes/img_10.png)

## Ramas y fusiones

Creamos una nueva rama con el comando ```git branch``` y verificamos que la rama fue creada

![Imagen_11](Imagenes/img_11.png)

Ahora cambiamos de la rama creada y verificamos

![Imagen_12](Imagenes/img_12.png)

Modificamos el archivo en la rama actual y verificamos

![Imagen_13](Imagenes/img_13.png)

Agregamos el achivo al git y verificamos el estado, notamos que el archivo se encuentra en la rama "feature"

![Imagen_14](Imagenes/img_14.png)

Confirmamos el archivo con un commit y verificamos todas las confirmaciones con ```~$ git log```. Notamos las confirmaciones tanto en la rama feature como en la rama master

![Imagen_15](Imagenes/img_15.png)

Cambiamos a la rama master usando ```git branch```

![Imagen_16](Imagenes/img_16.png)

Combinamos el archivo de la rama master con la rama feature

![Imagen_17](Imagenes/img_17.png)

Eliminamos la rama feature y verificamos su eliminacion

![Imagen_18](Imagenes/img_18.png)

## Manejo de conflictos de fusión.

Creamos una rama llamada test, luego verificamos el estado actual del archivo CC3S2

![Imagen_19](Imagenes/img_21.png)

Cambiamos la palabra 'CC3S2' por Java en el archivo CC3S2.txt, luego realizamos la confirmacion con el mensaje "Cambiar CC3S2 a Java"

![Imagen_20](Imagenes/img_20.png)

