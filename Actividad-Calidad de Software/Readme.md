# Actividad - Calidad de software

Alumno: De la Cruz Valdiviezo, Pedro Luis David

Sitio web seleccionado: Wikipedia

![Untitled](Actividad%20-%20Calidad%20de%20software%2062f41e8b549045d9ad2f92a1fdb63124/Untitled.png)

# Discución de importancia

Atributos de calidad seleccionados:

1. Usabilidad: Wikipedia recibe visitas de millones de personas cada día que buscan información académica. Por lo tanto, la usabilidad del sitio es fundamental para garantizar una navegación fluida y sin confusiones.
2. Confiabilidad: Como Wikipedia es un sitio de intercambio de información, es crucial que dicha información sea confiable. Si la confiabilidad es baja, los usuarios perderán la confianza en la información proporcionada, que es el principal objetivo del sitio.
3. Rendimiento: Como mencionamos anteriormente, el sitio debe soportar visitas de millones de usuarios. Por lo tanto, debe ser capaz de manejar un gran número de usuarios simultáneos para garantizar una navegación fluida para todos.

# Escenarios:

1. Usabilidad:
    
    ```bash
    Dado que estoy buscando una informacion en wikipedia
    Cuando pongo dicha informacion en el cuadro de busqueda
    Y presiono enter
    Entonces el sistema me muestra dicha informacion si la encuentra
    ```
    
2. Confiabilidad:
    
    ```bash
    Dado que estoy viendo informacion en wikipedia
    Cuando lo comparo con una fuente confiable
    Entonces deberia de reportar alguna discrepancia
    ```
    
3. Rendimiento:
    
    ```bash
    Dado que hay varios usuarios ingresando a wikipedia
    Cuando busco informacion en wikipedia
    Entonces navego fluidamente por la web
    ```