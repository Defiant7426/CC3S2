# Actividad-Mutadores

¿Qué es un mutante en el desarrollo de software?

Un mutante en el desarrollo de software es una versión modificada de un programa, en la que se han introducido pequeños cambios o "mutaciones" en el código fuente para probar la eficacia de las pruebas unitarias.

¿Qué significa “matar un mutante”?

significa que las pruebas unitarias han detectado y fallado debido a las mutaciones introducidas, lo cual es el resultado deseado. Esto demuestra la eficacia de las pruebas en la detección de errores o cambios en el código.

¿Que significa “sobrevivir a un mutante”?

Significa que las mutaciones introducidas no fueron detectadas por las pruebas unitarias y el código mutado pasó las pruebas. Esto sugiere que las pruebas no son suficientemente robustas y pueden necesitar revisión o mejora.

¿Que es la cobertura de mutacion?

La cobertura de mutación se refiere al porcentaje de mutantes que han sido "asesinados" por las pruebas unitarias. Es una medida de la eficacia de las pruebas unitarias y puede ayudar a identificar áreas del código que pueden necesitar pruebas adicionales.

Hipótesis del programador competente y Efecto de acoplamiento:

La Hipótesis del Programador Competente sugiere que la mayoría de los errores en el código serán pequeños y no radicales, por lo que las pruebas de mutación se centran en alteraciones menores en el código. El Efecto de Acoplamiento es la idea de que los errores simples pueden interactuar para crear fallos más complejos, por lo que al detectar errores simples, también se pueden detectar fallos más complejos.

# **Introducción a PIT (Pitest)**

Escribimos las clases dadas en la actividad:

1. Calculator.java
    
    ```java
    package org.example;
    
    public class Calculator {
        public int add(int a, int b) {
            return a + b;
        }
        public int subtract(int a, int b) {
            return a - b;
        }
        public int multiply(int a, int b) {
            return a * b;
        }
        public int divide(int a, int b) {
            if (b == 0) {
                throw new IllegalArgumentException("Divisor cannot be zero");
            }
            return a / b;
        }
    }
    ```
    
2. CalculatorTest.java
    
    ```java
    package org.example;
    
    import static org.junit.jupiter.api.Assertions.*;
    import org.junit.jupiter.api.Test;
    
    public class CalculatorTest {
    
        @Test
        void testAdd() {
            Calculator calc = new Calculator();
            assertEquals(5, calc.add(2, 3));
        }
    
        @Test
        void testSubtract() {
            Calculator calc = new Calculator();
            assertEquals(1, calc.subtract(3, 2));
        }
    
        @Test
        void testMultiply() {
            Calculator calc = new Calculator();
            assertEquals(6, calc.multiply(2, 3));
        }
    
        @Test
        void testDivide() {
            Calculator calc = new Calculator();
            assertEquals(2, calc.divide(6, 3));
        }
    
        @Test
        void testDivideByZero() {
            Calculator calc = new Calculator();
            Exception exception = assertThrows(IllegalArgumentException.class, () -> calc.divide(1, 0));
            assertEquals("Divisor cannot be zero", exception.getMessage());
        }
    }
    ```
    

Ejecutamos los test:

![Untitled](Actividad-Mutadores%202c073517a5e84571bfa39d2d7732a0d2/Untitled.png)

# Configurando nuestro proyecto para el reporte de pitest

nuestro archivo gradle sera el siguiente:

```java
plugins {
    id 'java'
    id 'info.solidsoft.pitest' version '1.15.0'
}

group = 'org.example'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testImplementation 'org.assertj:assertj-core:3.25.3'
    pitest 'org.pitest:pitest-junit5-plugin:1.1.0'
}

test {
    useJUnitPlatform()
}

pitest {
    targetClasses = ['org.example.*'] // Paquete de clases a mutar
    mutators = ['DEFAULTS'] // Conjunto de mutadores [OLD_DEFAULTS, DEFAULTS, STRONGER, ALL]
    outputFormats = ['HTML'] // Formato de salida del informe
    timestampedReports = false // Deshabilitar informes con marca de tiempo para facilitar la navegación
    verbose = true
}
```

Luego escribimos este comando en consola:

```java
./gradlew pitest
```

Salida:

![Untitled](Actividad-Mutadores%202c073517a5e84571bfa39d2d7732a0d2/Untitled%201.png)

Luego vamos al index.html de nuestro reporte de pitest:

![Untitled](Actividad-Mutadores%202c073517a5e84571bfa39d2d7732a0d2/Untitled%202.png)

Lo abrimos con algun navegador disponible, la salida es la siguiente:

![Untitled](Actividad-Mutadores%202c073517a5e84571bfa39d2d7732a0d2/Untitled%203.png)

![Untitled](Actividad-Mutadores%202c073517a5e84571bfa39d2d7732a0d2/Untitled%204.png)

En total, se generaron 9 mutantes.

**add (Línea 5)**

1. replaced int return with 0 → KILLED
2. Replaced integer addition with subtraction → KILLED

**subtract (Línea 8)**

1. Replaced integer subtraction with addition → KILLED
2. replaced int return with 0 → KILLED

**multiply (Línea 11)**

1. Replaced integer multiplication with division → KILLED
2. replaced int return with 0 → KILLED

**divide (Líneas 14 y 17)**

1. removed conditional - replaced equality check with false → KILLED
2. replaced int return with 0 → KILLED
3. Replaced integer division with multiplication → KILLED

**Cuántos mutantes fueron matados:**

Todos los mutantes fueron matados. Ninguno de los 9 mutantes generados sobrevivió.

# Mejorando el conjunto de pruebas

Podemos mejorar las pruebas añadiendo estas:

```java
    @Test
    void testAddWithNegativeNumbers() {
        Calculator calc = new Calculator();
        assertEquals(-1, calc.add(-2, 1));
    }

    @Test
    void testSubtractWithNegativeNumbers() {
        Calculator calc = new Calculator();
        assertEquals(-1, calc.subtract(-2, -1));
    }

    @Test
    void testMultiplyWithNegativeNumbers() {
        Calculator calc = new Calculator();
        assertEquals(-6, calc.multiply(-2, 3));
    }

    @Test
    void testDivideWithNegativeNumbers() {
        Calculator calc = new Calculator();
        assertEquals(-2, calc.divide(-6, 3));
    }

    @Test
    void testDivideWithZeroNumerator() {
        Calculator calc = new Calculator();
        assertEquals(0, calc.divide(0, 3));
    }
```

**¿Se han matado más mutantes?**

Con las pruebas adicionales, se mataron los siguientes mutantes:

**add (Línea 5)**

1. Replaced int return with 0 for org/example/Calculator::add → KILLED
2. Replaced integer addition with subtraction → KILLED

**subtract (Línea 8)**

1. Replaced integer subtraction with addition → KILLED
2. replaced int return with 0 for org/example/Calculator::subtract → KILLED

**multiply (Línea 11)**

1. Replaced integer multiplication with division → KILLED
2. Replaced int return with 0 for org/example/Calculator::multiply → KILLED

**divide (Líneas 14 y 17)**

1. removed conditional - replaced equality check with false → KILLED
2. replaced int return with 0 for org/example/Calculator::divide → KILLED
3. Replaced integer division with multiplication → KILLED

**¿Qué mutantes, si hay alguno, todavía sobreviven?**
No hay mutantes que sobrevivan después de agregar las pruebas adicionales.

**¿Qué pruebas adicionales podrían ser necesarias?**
Las pruebas adicionales podrían incluir casos de prueba para validar el comportamiento con números decimales,