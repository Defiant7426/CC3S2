# Actividad-Pruebas Estructurales

Inicialmente tenemos el siguiente código dado por la actividad:

```java
package com.example;

public class CountWords {
    public int count(String str) {
        int words = 0;
        char last = ' ';
        for (int i = 0; i < str.length(); i++) { // 1
            if (!isLetter(str.charAt(i)) && (last == 's' || last == 'r')) { // 2
                words++;
            }
            last = str.charAt(i); // 3
        }
        if (last == 'r' || last == 's') {
            words++;
        }
        return words;
    }
    private boolean isLetter(char c) {
        return Character.isLetter(c);
    }
}
```

Explicación:

Aquí contamos el número de palabras en una cadena de texto. La función `count` recorre cada carácter de la cadena. Si el carácter no es una letra y el carácter anterior es una 's' o una 'r', incrementa el contador de palabras. La función `isLetter` se utiliza para verificar si un carácter es una letra.

Ahora escribimos los Test según nos dice la actividad:

```java
package com.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CountWordTest {

    @Test
    void twoWordsEndingWithS() { // 1
        int words = new CountWords().count("dogs cats");
        assertThat(words).isEqualTo(2);
    }
    @Test
    void noWordsAtAll() { // 2
        int words = new CountWords().count("dog cat");
        assertThat(words).isEqualTo(0);
    }
}
```

Explicación:

En el primer test, `twoWordsEndingWithS`, estamos probando una cadena con dos palabras que terminan en 's'. Esperamos que el contador de palabras sea 2. En el segundo test, `noWordsAtAll`, la cadena contiene palabras que no terminan en 's' o 'r', por lo que esperamos que el contador de palabras sea 0.

Aquí se encuentra el informe generado por Jacoco:

![Untitled](Actividad-Pruebas%20Estructurales%205e5faf3583094cb38031b85011571301/Untitled.png)

Podemos observar que las líneas pintadas de verde son las que se ejecutaron debido a las pruebas anteriores. Sin embargo, hay líneas pintadas de amarillo, lo que indica que solo se probaron parcialmente. Esto significa que aún necesitamos considerar más posibilidades para cubrir todas las eventualidades posibles de este código.

Ahora vamos a agregar un caso de prueba:

```java
@Test 
 void wordsThatEndInR() { // 1 
    int words = new CountWords().count("car bar"); 
    assertThat(words).isEqualTo(2); 
 }
```

Explicación:

En este test, `wordsThatEndInR`, estamos probando una cadena con dos palabras que terminan en 'r'. Al igual que en el primer test, esperamos que el contador de palabras sea 2, ya que ambas palabras terminan en 'r', lo que cumple con las condiciones establecidas en el método `count`.

Veamos si las pruebas pasan:

![Untitled](Actividad-Pruebas%20Estructurales%205e5faf3583094cb38031b85011571301/Untitled%201.png)

Ahora veamos si cambio algo en el reporte de Jacoco:

![Untitled](Actividad-Pruebas%20Estructurales%205e5faf3583094cb38031b85011571301/Untitled%202.png)

En este caso, todas las líneas están en verde porque los tests cubrieron todos los casos de la clase CountWords.

Ahora vamos a cambiar el método count para que sea mas legible al leerlo y se entienda mejor, para ello usamos expresiones regulares con el método split de la siguiente manera:

```java
package com.example;

public class CountWords {
    public int count(String str) {
        String[] words = str.split("\\s+");
        int count = 0;
        for (String word : words) {
            if (word.endsWith("s") || word.endsWith("r")) {
                count++;
            }
        }
        return count;
    }
}
```

Explicacion:

En esta versión, la función `count` divide la cadena en palabras usando la función `split` y un patrón de expresión regular que coincide con uno o más espacios. Luego, itera sobre cada palabra y si la palabra termina en 's' o 'r', incrementa el contador. Finalmente, devuelve el contador.

Agregamos dos test mas a los que ya existen:

```java
@Test
void multipleWordsNotEndingWithSOrR() {
    int words = new CountWords().count("dog cat");
    assertThat(words).isEqualTo(0);
}

@Test
void multipleWordsSomeEndingWithSOrR() {
    int words = new CountWords().count("dogs cat car");
    assertThat(words).isEqualTo(2);
}
```

Explicacion:

En el test `multipleWordsNotEndingWithSOrR`, estamos probando una cadena con dos palabras que no terminan ni en 's' ni en 'r'. Por lo tanto, esperamos que el contador de palabras sea 0. En el test `multipleWordsSomeEndingWithSOrR`, tenemos tres palabras, de las cuales dos terminan en 'r' y 's' respectivamente. Por lo tanto, esperamos que el contador de palabras sea 2.

Veamos de que pasen las pruebas.

![Untitled](Actividad-Pruebas%20Estructurales%205e5faf3583094cb38031b85011571301/Untitled%203.png)

Y vemos el reporte de Jacoco:

![Untitled](Actividad-Pruebas%20Estructurales%205e5faf3583094cb38031b85011571301/Untitled%204.png)

Notamos que todas las lineas estan en verde, por lo que han sido cubiertas por los test dados.

Diferencias entre el codigo y test iniciales y finales:

En los tests finales, se han añadido más casos de prueba para cubrir todas las posibilidades del código, resultando en un código más robusto y confiable. Además, el código final es más legible y fácil de entender gracias al uso de expresiones regulares, lo que mejora la mantenibilidad del código.

Ahora tenemos este codigo propuesto por la actividad:

```java
package com.example;

public class LeftPadUtils {
    private static final String SPACE = " ";
    private static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
    /**
    * @param str
    * @param size
    * @param padStr
    * @return left-padded string or {@code null}
    */
    public static String leftPad(final String str, final int size, String padStr) {
        if (str == null) { // 1
            return null;
        }
        if (isEmpty(padStr)) { // 2
            padStr = SPACE;
        }
        final int padLen = padStr.length();
        final int strLen = str.length();
        final int pads = size - strLen;
        if (pads <= 0) { // 3
            return str;
        }
        if (pads == padLen) { // 4
            return padStr.concat(str);
        } else if (pads < padLen) { // 5
            return padStr.substring(0, pads).concat(str);
        } else { // 6
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
            padding[i] = padChars[i % padLen];
        }
        return new String(padding).concat(str);
        } 
    }
}
```

Ahora vamos a explicar las lineas comentadas 1, 2, 3, 4, 5 y 6:

1. Si la cadena de entrada es nula, devuelve nulo.
2. Si la cadena de relleno es nula o vacía, se utiliza un espacio en blanco como relleno.
3. Si la longitud de la cadena de entrada es mayor o igual al tamaño deseado, se devuelve la cadena de entrada sin cambios.
4. Si el tamaño deseado menos la longitud de la cadena de entrada es igual a la longitud de la cadena de relleno, se concatena la cadena de relleno al principio de la cadena de entrada.
5. Si el tamaño deseado menos la longitud de la cadena de entrada es menor que la longitud de la cadena de relleno, se concatena una subcadena de la cadena de relleno al principio de la cadena de entrada.
6. Si ninguna de las condiciones anteriores se cumple, se rellena la cadena de entrada con los caracteres de la cadena de relleno hasta alcanzar el tamaño deseado.

Ahora escribimos los test:

```java
package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
public class LeftPadTest {
    @ParameterizedTest
    @MethodSource("generator")
    void test(String originalStr, int size, String padString, String expectedStr) { // 1
        assertThat(LeftPadUtils.leftPad(originalStr, size, padString)).isEqualTo(expectedStr);
    }
    static Stream<Arguments> generator() { // 2
        return Stream.of(
        Arguments.of(null, 10, "-", null), // T1
        Arguments.of("", 5, "-", "-----"), // T2
        Arguments.of("abc", -1, "-", "abc"), // T3
        Arguments.of("abc", 5, null, "  abc"), // T4
        Arguments.of("abc", 5, "", "  abc"), // T5
        Arguments.of("abc", 5, "-", "--abc"), // T6
        Arguments.of("abc", 3, "-", "abc"), // T7
        Arguments.of("abc", 0, "-", "abc"), // T8
        Arguments.of("abc", 2, "-", "abc") // T9
        );
    }
}

```

Explicacion:

En esta prueba, utilizamos un test parametrizado, que nos permite testear varios casos con la misma lógica de prueba. La función `generator` genera los casos de prueba. Cada caso de prueba consta de una cadena original, un tamaño, una cadena de relleno y una cadena esperada. Los casos de prueba cubren todos los escenarios posibles en la función `leftPad`.

Probamos los test:

![Untitled](Actividad-Pruebas%20Estructurales%205e5faf3583094cb38031b85011571301/Untitled%205.png)

Ahora el informa de Jacoco:

![Untitled](Actividad-Pruebas%20Estructurales%205e5faf3583094cb38031b85011571301/Untitled%206.png)

Ahora vamos a derivar mas casos de prueba:

```java
Arguments.of("abc", 5, "--", "--abc"), // T10
Arguments.of("abc", 5, "---", "--abc"), // T11
Arguments.of("abc", 5, "-", "--abc") // T12
```

Explicacion:

En el caso T10, la cadena de relleno tiene dos caracteres. Como necesitamos agregar dos caracteres a la cadena original para llegar al tamaño deseado, usamos la cadena de relleno tal como está. En el caso T11, la cadena de relleno tiene tres caracteres, pero solo necesitamos dos. Así que tomamos los dos primeros caracteres de la cadena de relleno. En el caso T12, es similar al caso T6, la cadena de relleno es de un solo carácter y necesitamos dos, así que repetimos la cadena de relleno dos veces.

Nuestro informe de Jacoco:

![Untitled](Actividad-Pruebas%20Estructurales%205e5faf3583094cb38031b85011571301/Untitled%207.png)

Añadimos una prueba adicional:

```java
@Test
void sameInstance() { 
	String str = "sometext";
	assertThat(LeftPadUtils.leftPad(str, 5, "-")).isSameAs(str);
}
```

Explicacion:

En el test `sameInstance`, comprobamos si la función `leftPad` devuelve la misma instancia de la cadena de entrada cuando la longitud de la cadena de entrada es igual o superior al tamaño deseado. Esto es una optimización, ya que evita la creación de una nueva cadena cuando no es necesario.

Ejecutamos las nuevas pruebas.

![Untitled](Actividad-Pruebas%20Estructurales%205e5faf3583094cb38031b85011571301/Untitled%208.png)

Ahora nuestro reporte de Jacoco luce asi:

![Untitled](Actividad-Pruebas%20Estructurales%205e5faf3583094cb38031b85011571301/Untitled%209.png)

Creamos la clase Clumps:

```java
package com.example;

public class Clumps {
    /**
    * @param nums
    *
    1 on y off points
    * @return …
    */
    public static int countClumps(int[] nums) {
        if (nums == null || nums.length == 0) { // 1
            return 0;
        }
        int count = 0;
        int prev = nums[0];
        boolean inClump = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == prev && !inClump) { // 2
                inClump = true;
                count += 1;
            }
            if (nums[i] != prev) { // 3
                prev = nums[i];
                inClump = false;
            }
        }
        return count;
    }
}

```

Explicacion de las lineas comentadas 1, 2 y 3:

1. Si el array de entrada es nulo o vacío, devuelve 0, ya que no hay grupos.
2. Si el número actual es igual al número anterior y no estamos en un grupo, incrementamos el contador de grupos y marcamos que estamos en un grupo.
3. Si el número actual es diferente del número anterior, actualizamos el número anterior y marcamos que no estamos en un grupo.

Ahora escribimos los casos de prueba:

```java
package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.of;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class ClumpsOnlyStructuralTest {
    @ParameterizedTest
    @MethodSource("generator")
    void testClumps(int[] nums, int expectedNoOfClumps) {
        assertThat(Clumps.countClumps(nums))
        .isEqualTo(expectedNoOfClumps);
    }
    static Stream<Arguments> generator() {
    return Stream.of(
    of(new int[]{}, 0), // vacío
    of(null, 0), // null
    of(new int[]{1,2,2,2,1}, 1), // 1 grupo
    of(new int[]{1}, 0), // 1 elemento
    // completa
    of(new int[]{2,2}, 1)
    );
    }
}
```

Explicacion:

Estos son casos de prueba parametrizados. Cada caso de prueba consta de un array de entrada y un número esperado de grupos. Los casos de prueba cubren diferentes escenarios: un array vacío, un array nulo, un array con un solo grupo, un array con un solo elemento y un array con dos elementos iguales (que forman un grupo).

Ahora corremos los test:

![Untitled](Actividad-Pruebas%20Estructurales%205e5faf3583094cb38031b85011571301/Untitled%2010.png)

Y luego el reporte de Jacoco:

![Untitled](Actividad-Pruebas%20Estructurales%205e5faf3583094cb38031b85011571301/Untitled%2011.png)

Por lo que estos test fueron suficientes para cubrir todas las posibilidades de las pruebas