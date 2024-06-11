# Pruebas basadas en especificaciones

1. Creando el metodo subStringBetween:

```java
package com.example;

/*
Cambios realizados con respecto al codigo propuesto:
1. implementamos el metodo privado y estatico isEmpty para verificar si una cadena es nula o vacia (linea 57)
2. agregamos la linea return new String[0]; que significa que devolvemos una arreglo vacio si la cadena esta vacia (linea 34)
3. agregamos la linea return list.toArray(new String[0]); que significa que devolvemos la lista como un arreglo (linea 54)
4. agregamos una funcion main para probar nuestro codigo (linea 16)
*/

import java.util.ArrayList;
import java.util.List;

public class stringUtils {

    public static void main(String[] args) {
        final String str = "axcaycazc";
        final String open = "a";
        final String close = "c";
        final String[] result = subStringBetween(str, open, close);
        System.out.println("Subcadenas entre " + open + " y " + close + " de str" + str +":");
        for (String s : result) {
            System.out.println(s);
        }
    }

    public static String[] subStringBetween(final String str,
                                            final String open,
                                            final String close) {
        if(str == null || isEmpty(open) || isEmpty(close)) {
            return null;
        }
        final int strLen = str.length();
        if(strLen == 0) { // si la cadena esta vacia se retorna un arreglo vacio
            return new String[0];
        }
        final int closeLen = close.length();
        final int openLen = open.length();
        final List<String> list = new ArrayList<>();
        int pos = 0;
        while(pos < strLen - closeLen) {
            int start = str.indexOf(open, pos);
            if(start < 0) { // si no se encuentra el caracter de apertura sale del bucle
                break;
            }
            start += openLen; // se incrementa la posicion para que no se tome en cuenta el caracter de apertura
           final int end = str.indexOf(close, start); // se busca el caracter de cierre a partir de la posicion de apertura
            if(end < 0) { // si no se encuentra el caracter de cierre sale del bucle
                break;
            }
            list.add(str.substring(start, end)); // se agrega la subcadena a la lista
            pos = end + closeLen; // se actualiza la posicion para continuar buscando
        }
        return list.toArray(new String[0]); // se retorna la lista como un arreglo
    }

    private static boolean isEmpty(final String cadena) {
        return cadena == null || cadena.isEmpty();
    }
}

/*
Codigo propuesto:
public static String[] substringsBetween(final String str, final String open, final String close) {
 if (str == null || isEmpty(open) || isEmpty(close)) {
 return null;
 }
 final int strLen = str.length();
 if (strLen == 0) {
 return EMPTY_STRING_ARRAY;
 }
 final int closeLen = close.length();
 final int openLen = open.length();
 final List<String> list = new ArrayList<>();
 int pos = 0;
 while (pos < strLen - closeLen) {
 int start = str.indexOf(open, pos);
 if (start < 0) {
 break;
 }
 start += openLen;
 final int end = str.indexOf(close, start);
 if (end < 0) {
 break;
 }
 list.add(str.substring(start, end));
 pos = end + closeLen;
 }
 if (list.isEmpty()) {
 return null;
 }
 return list.toArray(EMPTY_STRING_ARRAY);
}
* */
```

Escribiendo el codigo de prueba para el codigo superior:

```java
package com.expample;

import org.junit.jupiter.api.Test;

import static com.example.stringUtils.subStringBetween;
import static org.assertj.core.api.Assertions.assertThat;

public class stringUtilsExploracionTest {

    @Test //1
    void simpleCase() {
        final String str = "abcd";
        final String open = "a";
        final String close = "d";
        final String[] result = subStringBetween(str, open, close);
        assertThat(result)
                .as("El arreglo deberia de ser igual a [\"bc\"]")
                .isEqualTo(new String[]{"bc"});
    }

    @Test //2
    void manyStrings() {
        final String str = "abcdabcdab";
        final String open = "a";
        final String close = "d";
        final String[] result = subStringBetween(str, open, close);
        assertThat(result)
                .as("El arreglo deberia de ser igual a [\"bc\", \"bc\"]")
                .isEqualTo(new String[]{"bc", "bc"});
    }

    @Test //3
    void openAndCloseTagsThatAreLongerThan1Char() {
        final String str = "aabcddaabfddaab";
        final String open = "aa";
        final String close = "dd";
        final String[] result = subStringBetween(str, open, close);
        assertThat(result)
                .as("El arreglo deberia de ser igual a [\"bc\", \"bf\"]")
                .isEqualTo(new String[]{"bc", "bf"});
    }
}
```

Aquí se encuentran tres casos de prueba con sus respectivas explicaciones:

1. `simpleCase`: Probamos un caso sencillo para verificar si el método funciona correctamente. Dada la cadena `"abcd"`, con los parámetros `open = "a"` y `close = "d"`, el resultado debería ser un arreglo de strings que solo contenga `"bc"`. Por lo tanto, la salida debería ser `["bc"]`.
2. `manyStrings`: Este caso es más complejo que el anterior. La cadena a evaluar es `"abcdabcdab"`, con `open = "a"` y `close = "d"`. La salida debería ser `["bc", "bc"]`.
3. `openAndCloseTagsThatAreLongerThan1Char`: Este caso es una variación más compleja del anterior. Nuestra cadena es `"aabcddaabfddaab"`, con `open = "aa"` y `close = "dd"`. La salida debería ser `["bc", "bf"]`.

Corremos las pruebas para ver si nuestro codigo de produccion es el correcto:

![Untitled](Pruebas%20basadas%20en%20especificaciones%2033d0fcda98ff4aad8c85bc8fc901dcd6/Untitled.png)

Ahora que hemos comenzado con estas tres pruebas iniciales para nuestro método, vamos a revisar nuestros números de prueba para este ejercicio:

1. En la prueba número 1, estamos probando un ejemplo simple donde nuestra cadena `str` es "abcd", con `open = "a"` y `close = "d"`. Esto significa que el método retorna "bc". De igual forma, se puede probar con cualquier otra cadena `str` y cualquier subcadena `open` y `close` que se encuentre dentro de `str`. Si es así, entonces no habría ningún problema para obtener el resultado desead
2. De la misma manera, en la prueba número 2, se puede usar cualquier cadena e intencionalmente poner un `open` y `close` que se repita varias veces en la cadena. De esta manera, el resultado será un arreglo con todas las coincidencias de las cadenas que se encuentren entre `open` y `close`

Bien ahora vamos a ver mas pruebas dependiendo de la cantidad de particiones que tiene nuestro metodo, en este caso expandimos las pruebas de la siguiente manera

Entonces seguimos explicando estas nuevas pruebas:

1. `strIsNullOrEmpty`: Esta prueba verifica que la función devuelve `null` si `str` es `null`. Si `str` es una cadena vacía, debería devolver un arreglo vacío.

```java
@Test //4
void strIsNullOrEmpty() {
    final String str = "";
    final String open = "a";
    final String close = "d";
    assertThat(subStringBetween(null, open, close))
        .as("El arreglo debería ser igual a []")
        .isNull();
    assertThat(subStringBetween(str, open, close))
        .as("El arreglo debería ser igual a []")
        .isEqualTo(new String[]{});
}
```

1. `openIsNullOrEmpty`: En esta prueba se verifica que el método retorne `null` si el parámetro `open` es `null` o una cadena vacía.

```java
@Test //5
void openIsNullOrEmpty() {
    final String str = "abc";
    final String open = "";
    final String close = "d";
    assertThat(subStringBetween(str, null, close))
        .as("El arreglo debería ser igual a []")
        .isNull();
    assertThat(subStringBetween(str, open, close))
        .as("El arreglo debería ser igual a []")
        .isNull();
}

```

1. `closeIsNullOrEmpty`: Similarmente, se verifica si el retorno del método es `null` cuando el parámetro `close` es `null`.

```java
@Test //6
void closeIsNullOrEmpty() {
    final String str = "abc";
    final String open = "a";
    final String close = "";
    assertThat(subStringBetween(str, open, null))
        .as("El arreglo debería ser igual a []")
        .isNull();
    assertThat(subStringBetween(str, open, close))
        .as("El arreglo debería ser igual a []")
        .isNull();
}
```

1. `strOfLength1` : Se prueba el método cuando el parametro `str` tiene longitud igual a 1. En cualquier caso el retorno de la función es igual a `null`.
    
    ```java
    @Test //7
        void strOfLength1() {
            assertThat(subStringBetween("a", "a", "b"))
                    .as("El arreglo deberia de ser igual a nulo")
                    .isNull();
            assertThat(subStringBetween("a", "b", "a"))
                    .as("El arreglo deberia de ser igual a nulo")
                    .isNull();
            assertThat(subStringBetween("a", "b", "b"))
                    .as("El arreglo deberia de ser igual a nulo")
                    .isNull();
            assertThat(subStringBetween("a", "a", "a"))
                    .as("El arreglo deberia de ser igual a nulo")
                    .isNull();
        }
    ```
    
2. `openAndCloseOfLength1`: Se prueba el método cuando los parámetros `open` y `close` tienen una longitud de 1. Se verifica si estos caracteres se encuentran dentro de la cadena `str`. En el último caso, el retorno ya no debería ser `null`, y más bien nos está retornando la subcadena entre esos dos parámetros.

```java
@Test //8
void openAndCloseOfLength1() {
    assertThat(subStringBetween("abc", "x", "y"))
        .as("El arreglo debería ser igual a null")
        .isNull();
    assertThat(subStringBetween("abc", "a", "y"))
        .as("El arreglo debería ser igual a null")
        .isNull();
    assertThat(subStringBetween("abc", "x", "c"))
        .as("El arreglo debería ser igual a null")
        .isNull();
    assertThat(subStringBetween("abc", "a", "c"))
        .as("El arreglo debería ser igual a null")
        .isEqualTo(new String[]{"b"});
}
```

1. `openAndCloseTagsOfDifferentSizes`: Esta prueba es similar a la anterior, pero ahora nuestros parámetros `open` y `close` tienen una longitud mayor a 1.

```java
@Test //9
void openAndCloseTagsOfDifferentSizes() {
    assertThat(subStringBetween("aabcc", "xx", "yy"))
        .isNull();
    assertThat(subStringBetween("aabcc", "aa", "yy"))
        .isNull();
    assertThat(subStringBetween("aabcc", "xx", "cc"))
        .isNull();
    assertThat(subStringBetween("aabbcc", "aa", "cc"))
        .isEqualTo(new String[]{"bb"});
    assertThat(subStringBetween("aabbccaaeecc", "aa", "cc"))
        .isEqualTo(new String[]{"bb", "ee"});
}

```

1. `notSubstringBetweenOpenAndCloseTags`: En este caso, la prueba verifica si el retorno del método nos da un arreglo con una cadena vacía. Esto sucede porque entre los parámetros de `open` y `close` no existe ningún carácter.

```java
@Test //10
void notSubstringBetweenOpenAndCloseTags() {
    assertThat(subStringBetween("aabb", "aa", "bb"))
        .isEqualTo(new String[]{""});
}

```

Al parecer tenemos casos de pruebas que son robustas, pero dependiendo de nuestra creatividad y experiencia podemos encontrar casos que no hemos podido probar y nisiquiera se nos ha ocurrido, por ejemplo el siguiente caso de prueba:

```java
@Test //8
    void openAndCloseOfLength1() {
        //resto de los asserts
        assertThat(subStringBetween("abcabyt byrc", "a", "c")).
                isEqualTo(new String[]{"b", "byt byr"});

    }
```

Aumentamos este caso de prueba ahora considerando que existe un espacio en blanco entre los parámetros `open` y `close`.

De la misma manera que hemos considerado el espacio en el parámetro `str`podemos considerar espacios en los otros parámetros:

```java
@Test //9
    void openAndCloseTagsOfDifferentSizes() {
        //resto de los assert
        assertThat(subStringBetween("a abb ddc ca abbcc", "a a", "c c"))
                .isEqualTo(new String[]{"bb dd"});

    }
```

En este caso de prueba estamos considerando espacios en los parámetros `open` y `close`. 

Ahora modificaremos el método `substringsBetween` para que solo capture caracteres, excluyendo los caracteres especiales y números. Para ello, implementaremos un método que realice esta tarea:

```java
private static String strWithoutEspecialCharactersAndNumbers(String substring) {
    String result = "";
    for (int i = 0; i < substring.length(); i++) {
        char c = substring.charAt(i);
        if (Character.isLetter(c)) {
            result += c;
        }
    }
    return result;
}

```

Este método captura un string y solo considera aquellos caracteres que son letras. Como salida, proporciona un string que solo contiene las letras que encontró en la entrada.

Dentro de nuestro método `substringsBetween` usamos el método `str_without_especial_characters_and_number` de la siguiente manera:

```java
String ss = str_without_especial_characters_and_number(str.substring(start, end));
list.add(ss); // se agrega la subcadena a la lista
pos = end + closeLen;
```

Después de identificar la subcadena desde `start` hasta `end`, vamos a eliminar todos los caracteres especiales y números. Posteriormente, agregamos este resultado a la lista de respuestas. Finalmente, actualizamos la posición `pos` para seguir dentro del bucle while.

Luego de esto vamos a implementar un nuevo test para esta nueva implementacion:

```java
@Test //11
    void ignoresSpecialCharacters() {
        assertThat(subStringBetween("a*b?c!a@d", "a", "d"))
                .isEqualTo(new String[] { "bca" });
}
```

Bien, ahora vamos a implementar una excepción si es que `open` o`close` son vacíos o nulos de la siguiente manera:

![Untitled](Pruebas%20basadas%20en%20especificaciones%2033d0fcda98ff4aad8c85bc8fc901dcd6/Untitled%201.png)

y lo implementamos en el método `substringsBetween`  así:

```java
//resto del codigo linea 30
if(str == null) {
            return null;
}
if(isEmpty(open) || isEmpty(close)) {
    throw new InvalidDelimiterException("Open or close delimiter cannot be empty");
}
//sigue el codigo...
```

Cada vez que los valores de `open` o `close` son vacíos o nulos, se lanza esta excepción. Sin embargo, algunos tests que habíamos escrito previamente no pueden ejecutarse debido a esta excepción. Por lo tanto, descartamos esos tests y escribimos uno nuevo para capturar esta excepción. Los tests que se eliminarán son `openIsNullOrEmpty` y `closeIsNullOrEmpty`.

El test implementado es el siguiente:

```java
@Test //12
    void throwsExceptionWhenOpenOrCloseIsEmpty() {
        assertThrows(InvalidDelimiterException.class, () -> {
        subStringBetween("abc", "", "b");
        });
        assertThrows(InvalidDelimiterException.class, () -> {
        subStringBetween("abc", "a", "");
        });
        assertThrows(InvalidDelimiterException.class, () -> {
        subStringBetween("abc", null, "b");
        });
        assertThrows(InvalidDelimiterException.class, () -> {
        subStringBetween("abc", "a", null);
        });
 }
```

En esta prueba, si tenemos valores nulos o vacíos en los parámetros `open` o `close`, entonces se lanza la excepción creada anteriormente.

Ahora vamos a crear un nuevo método alternativo denominado `regexSubstringsBetween`. Este método utilizará expresiones regulares para encontrar subcadenas entre los parámetros `open` y `close`.

Entonces escribimos el codigo:

```java
package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//La especificacion en donde se esta cambiando el codigo con respecto al propuesto por la actividad se encuentra al ultimo

public class stringUtilsRegex {
    public static void main(String[] args) {
        final String str = "axc?a*yc!a@z/c";
        final String open = "a";
        final String close = "c";
        final String[] result = regexSubstringsBetween(str, open, close);
        System.out.println("Subcadenas entre " + open + " y " + close + " de str" + str +":");
        for (String s : result) {
            System.out.println(s);
        }
    }

    public static String[] regexSubstringsBetween(final String str,
                                            final String open,
                                            final String close) {
        if(str == null|| isEmpty(open) || isEmpty(close)) {
            return null;
        }

        //esta expresion regular se encarga de buscar las subcadenas que estan entre los caracteres de apertura y cierre
        final String regex = Pattern.quote(open) + "(.*?)" + Pattern.quote(close);
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(str);
        final List<String> list = new ArrayList<>();

        while (matcher.find()) {
            list.add(str_without_especial_characters_and_number(matcher.group(1)));
        }

        //return list.toArray(new String[]{}); // se retorna la lista como un arreglo
        if(list.isEmpty()) {
            return null;
        }
        return list.toArray(new String[]{});
    }

    private static String str_without_especial_characters_and_number(String substring) {
        String result = "";
        for (int i = 0; i < substring.length(); i++) {
            char c = substring.charAt(i);
            if (Character.isLetter(c) || Character.isWhitespace(c)) {
                result += c;
            }
        }
        return result;
    }

    private static boolean isEmpty(final String cadena) {

        return cadena == null || cadena.isEmpty();
    }
}

/*
codigo sugerido por la actividad:
public static String[] regexSubstringsBetween(final String str, final String open, final String close) {
 if (str == null || isEmpty(open) || isEmpty(close)) {
 return null;
 }
 final String regex = Pattern.quote(open) + "(.*?)" + Pattern.quote(close);
 final Pattern pattern = Pattern.compile(regex);
 final Matcher matcher = pattern.matcher(str);
 final List<String> list = new ArrayList<>();
 while (matcher.find()) {
 list.add(matcher.group(1));
 }
 if (list.isEmpty()) {
 return null;
 }
 return list.toArray(EMPTY_STRING_ARRAY);
}
* */

/*
Cambios realizados en el codigo propuesto por la actividad:
1. Se agrego un metodo main para poder probar el metodo regexSubstringsBetween en la linea 11
2. Se agrego un metodo str_without_especial_characters_and_number que se encarga de eliminar los caracteres especiales y numeros de una subcadena linea 46
3. Se agrego un metodo isEmpty que se encarga de verificar si una cadena es nula o vacia en la linea 57
4. Se agrego el metodo str_without_especial_characters_and_number en el metodo regexSubstringsBetween en la linea 36
* */

```

y ademas tambien agregamos los test:

```java
package com.expample;

import com.example.InvalidDelimiterException;
import org.junit.jupiter.api.Test;

import static com.example.stringUtilsRegex.regexSubstringsBetween;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class stringUtilsRegexTest {
     @Test //1
    void simpleCase() {
        final String str = "abcd";
        final String open = "a";
        final String close = "d";
        final String[] result = regexSubstringsBetween(str, open, close);
        assertThat(result)
                .as("El arreglo deberia de ser igual a [\"bc\"]")
                .isEqualTo(new String[]{"bc"});
    }

    @Test //2
    void manyStrings() {
        final String str = "abcdabcdab";
        final String open = "a";
        final String close = "d";
        final String[] result = regexSubstringsBetween(str, open, close);
        assertThat(result)
                .as("El arreglo deberia de ser igual a [\"bc\", \"bc\"]")
                .isEqualTo(new String[]{"bc", "bc"});
    }

    @Test //3
    void openAndCloseTagsThatAreLongerThan1Char() {
        final String str = "aabcddaabfddaab";
        final String open = "aa";
        final String close = "dd";
        final String[] result = regexSubstringsBetween(str, open, close);
        assertThat(result)
                .as("El arreglo deberia de ser igual a [\"bc\", \"bf\"]")
                .isEqualTo(new String[]{"bc", "bf"});
    }

     @Test //4
    void strIsNullOrEmpty() {
        final String str = "";
        final String open = "a";
        final String close = "d";
        assertThat(regexSubstringsBetween(null, open, close))
                .as("El arreglo deberia de ser igual a []")
                .isNull();
        assertThat(regexSubstringsBetween(str, open, close))
                .as("El arreglo deberia de ser igual a []")
                .isNull();
    }

    @Test //5
    void openIsNullOrEmpty() {
        final String str = "abc";
        final String open = "";
        final String close = "d";
        assertThat(regexSubstringsBetween(str, null, close))
                .as("El arreglo deberia de ser igual a []")
                .isNull();
        assertThat(regexSubstringsBetween(str, open, close))
                .as("El arreglo deberia de ser igual a []")
                .isNull();
    }

    @Test //6
    void closeIsNullOrEmpty() {
        final String str = "abc";
        final String open = "a";
        final String close = "";
        assertThat(regexSubstringsBetween(str, open, null))
                .as("El arreglo deberia de ser igual a []")
                .isNull();
        assertThat(regexSubstringsBetween(str, open, close))
                .as("El arreglo deberia de ser igual a []")
                .isNull();
    }

    @Test //7
    void strOfLength1() {
        assertThat(regexSubstringsBetween("a", "a", "b"))
                .as("El arreglo deberia de ser igual a nulo")
                .isNull();
        assertThat(regexSubstringsBetween("a", "b", "a"))
                .as("El arreglo deberia de ser igual a nulo")
                .isNull();
        assertThat(regexSubstringsBetween("a", "b", "b"))
                .as("El arreglo deberia de ser igual a nulo")
                .isNull();
        assertThat(regexSubstringsBetween("a", "a", "a"))
                .as("El arreglo deberia de ser igual a nulo")
                .isNull();
    }

    @Test //8
    void openAndCloseOfLength1() {
        assertThat(regexSubstringsBetween("abc", "x", "y"))
                .as("El arreglo deberia de ser igual a null")
                .isNull();
        assertThat(regexSubstringsBetween("abc", "a", "y"))
                .as("El arreglo deberia de ser igual a null")
                .isNull();
        assertThat(regexSubstringsBetween("abc", "x", "c"))
                .as("El arreglo deberia de ser igual a null")
                .isNull();
        assertThat(regexSubstringsBetween("abc", "a", "c"))
                .as("El arreglo deberia de ser igual a null")
                .isEqualTo(new String[]{"b"});
        assertThat(regexSubstringsBetween("abcabyt byrc", "a", "c")).
                isEqualTo(new String[]{"b", "byt byr"});

    }

    @Test //9
    void openAndCloseTagsOfDifferentSizes() {
        assertThat(regexSubstringsBetween("aabcc", "xx", "yy"))
                .isNull();
        assertThat(regexSubstringsBetween("aabcc", "aa", "yy"))
                .isNull();
        assertThat(regexSubstringsBetween("aabcc", "xx", "cc"))
                .isNull();
        assertThat(regexSubstringsBetween("aabbcc", "aa", "cc"))
                .isEqualTo(new String[]{"bb"});
        assertThat(regexSubstringsBetween("aabbccaaeecc", "aa", "cc"))
                .isEqualTo(new String[]{"bb", "ee"});
        assertThat(regexSubstringsBetween("a abb ddc ca abbcc", "a a", "c c"))
                .isEqualTo(new String[]{"bb dd"});

    }
    @Test //10
    void notSubstringBetweenOpenAndCloseTags() {
     assertThat(regexSubstringsBetween("aabb", "aa", "bb"))
             .isEqualTo(new String[]{""});
    }

    @Test //11
    void ignoresSpecialCharacters() {
        assertThat(regexSubstringsBetween("a*b?c!a@d", "a", "d"))
                .isEqualTo(new String[] { "bca" });
    }

}
```

Finalmente verificamos si todo pasa con exito:

![Untitled](Pruebas%20basadas%20en%20especificaciones%2033d0fcda98ff4aad8c85bc8fc901dcd6/Untitled%202.png)

Agregamos estos test nuevos:

```java
@Test //12
    void regexSimpleCase() {
        assertThat(regexSubstringsBetween("abcd", "a", "d"))
                .isEqualTo(new String[] { "bc" });
    }
    @Test //13
    void regexManyStrings() {
        assertThat(regexSubstringsBetween("abcdabcdab", "a", "d"))
                .isEqualTo(new String[]{"bc", "bc" });
    }
    @Test  //14
    void regexOpenAndCloseTagsThatAreLongerThan1Char() {
        assertThat(regexSubstringsBetween("aabcddaabfddaab", "aa", "dd"))
                .isEqualTo(new String[] { "bc", "bf" });
     }
```

y verificamos que pasen las pruebas viejas como nuevas:

![Untitled](Pruebas%20basadas%20en%20especificaciones%2033d0fcda98ff4aad8c85bc8fc901dcd6/Untitled%203.png)

Ahora vamos a optimizar el código donde se encuentra el método `subStringBetween` de la siguiente manera:

```java
package com.example;

import java.util.ArrayList;
import java.util.List;

// Los cambios se encuentran el a parte inferior del codigo

public class stringUtils {

    public static void main(String[] args) {
        final String str = "axc?a*yc!a@z/c";
        final String open = "a";
        final String close = "c";
        final String[] result = subStringBetween(str, open, close);
        System.out.println("Subcadenas entre " + open + " y " + close + " de str" + str +":");
        for (String s : result) {
            System.out.println(s);
        }
    }

    public static String[] subStringBetween(final String str,
                                            final String open,
                                            final String close) {
        if(str == null) {
            return null;
        }
        if(isEmpty(open) || isEmpty(close)) {
            throw new InvalidDelimiterException("Open or close delimiter cannot be empty");
        }
        final int strLen = str.length();
        if(strLen == 0) { // si la cadena esta vacia se retorna un arreglo vacio
            return new String[0];
        }
        final int closeLen = close.length();
        final int openLen = open.length();
        final List<String> list = new ArrayList<>();
        int pos = 0;
        while(pos < strLen - closeLen) {
            int start = str.indexOf(open, pos);
            if(start < 0) { // si no se encuentra el caracter de apertura sale del bucle
                break;
            }
            start += openLen; // se incrementa la posicion para que no se tome en cuenta el caracter de apertura
           final int end = str.indexOf(close, start); // se busca el caracter de cierre a partir de la posicion de apertura
            if(end < 0) { // si no se encuentra el caracter de cierre sale del bucle
                break;
            }
            String ss = str_without_especial_characters_and_number(str.substring(start, end));
            list.add(ss); // se agrega la subcadena a la lista
            pos = end + closeLen; // se actualiza la posicion para continuar buscando
        }
        //return list.toArray(new String[]{}); // se retorna la lista como un arreglo
        if(list.isEmpty()) {
            return null;
        }
        return list.toArray(new String[list.size()]);
    }

    private static String str_without_especial_characters_and_number(String substring) {
        String result = "";
        for (int i = 0; i < substring.length(); i++) {
            char c = substring.charAt(i);
            if (Character.isLetter(c) || Character.isWhitespace(c)) {
                result += c;
            }
        }
        return result;
    }

    private static boolean isEmpty(final String cadena) {
        return cadena == null || cadena.isEmpty();
    }
}

/*
Codigo propuesto:
public static String[] substringsBetween(final String str, final String open, final String close) {
 if (str == null || isEmpty(open) || isEmpty(close)) {
 return null;
 }
 final int strLen = str.length();
 if (strLen == 0) {
 return EMPTY_STRING_ARRAY;
 }
 final int closeLen = close.length();
 final int openLen = open.length();
 final List<String> list = new ArrayList<>();
 int pos = 0;
 while (pos < strLen - closeLen) {
 int start = str.indexOf(open, pos);
 if (start < 0) {
 break;
 }
 start += openLen;
 final int end = str.indexOf(close, start);
 if (end < 0) {
 break;
 }
 list.add(str.substring(start, end));
 pos = end + closeLen;
 }
 if (list.isEmpty()) {
 return null;
 }
 return list.toArray(new String[list.size()]);
}

* */

/*
Codigo cambiado con respecto al propuesto:
1. Agregamos la exepcion InvalidDelimiterException en el metodo subStringBetween en la linea 27
2. cambiamos el "return list.toArray(new String[list.size()]);" en la linea 56;"
 */
```

# Encontrar errores con las pruebas de especificaciones

Vamos a crear la clase  NumberUtils de la siguiente manera:

```java
package com.example;

//Los cambios realizados con respecto al codigo propuesto por la actividad se encuentran en el ultimo del codigo

import java.util.LinkedList;
import java.util.List;

public class NumberUtils {

    public static void main(String[] args) {
        List<Integer> left = List.of(1, 4, 4);
        List<Integer> right = List.of(1, 5, 6);
        List<Integer> result = add(left, right);
        System.out.println(result);
    }

    public static List<Integer> add(List<Integer> left, List<Integer> right) {
        //como entrada nos pide dos listas de nueros que representan los digitos de dos numeros
        if (left == null || right == null) {
            return null;
        }
        List<Integer> result = new LinkedList<>();
        int carry = 0;
        int max = Math.max(left.size(), right.size());
        for (int i = 0; i < max; i++) {
            int leftDigit = i < left.size() ? left.get(left.size() - 1 - i) : 0;
            int rightDigit = i < right.size() ? right.get(right.size() - 1 - i) : 0;
            if (leftDigit < 0 || leftDigit > 9 || rightDigit < 0 || rightDigit > 9) {
                throw new IllegalArgumentException();
            }
            int sum = leftDigit + rightDigit + carry;
            result.add(0,sum % 10);
            carry = sum / 10;
        }
        if (carry > 0) {
            result.add(0,carry);
        }
        return result;
    }

}

/*
Codigo propuesto por la actividad:
public class NumberUtils {
 public static List<Integer> add(List<Integer> left, List<Integer> right) {
 if (left == null || right == null) {
 return null;
 }
 Collections.reverse(left);
 Collections.reverse(right);
 LinkedList<Integer> result = new LinkedList<>();
 int carry = 0;
 for (int i = 0; i < Math.max(left.size(), right.size()); i++) {
 int leftDigit = left.size() > i ? left.get(i) : 0;
 int rightDigit = right.size() > i ? right.get(i) : 0;
 if (leftDigit < 0 || leftDigit > 9 || rightDigit < 0 || rightDigit > 9) {
 throw new IllegalArgumentException();
 }
 int sum = leftDigit + rightDigit + carry;
 result.addFirst(sum % 10);
 carry = sum / 10;
 }
 if (carry > 0) {
 result.addFirst(carry);
 }
 while (result.size() > 1 && result.get(0) == 0) {
 result.remove(0);
 }
 return result;
 }
}

* */

/*
Cambios realizados:
1. cree un variable max y lo puse fuera para que el codigo sea mas legible linea 24
2. en caso de que i se encuentre dentro del rango de un digito de un numero entonces empezare desde
el final de la lista de digitos de ese numero, para esto cambie la forma de obtener los digitos de los numeros
left.get(left.size() - 1 - i) y right.get(right.size() - 1 - i) linea 26 y 27
3. cambie la forma de agregar los digitos a la lista resultante, ahora se agrega al inicio de la lista
result.add(0,sum % 10); linea 32 y result.add(0,carry); linea 36
* */
```

Explicación del algoritmo:

El objetivo de este algoritmo es sumar dos números representados como listas de dígitos. Por ejemplo, si se quisiera sumar 123 y 456, las listas de entrada serían [1,2,3] y [4,5,6]. La suma se realiza dígito a dígito, de derecha a izquierda (al igual que en una suma manual), llevando cualquier "carry" al próximo dígito. Si el resultado de la suma de dos dígitos es mayor a 9, el "carry" se convierte en 1 y se suma al siguiente par de dígitos. Finalmente, si aún queda un "acarreo" después de sumar el último par de dígitos, se añade al final de la lista resultante.

Ahora realizando lo test a ver si pares de numeros resultan una suma exitosa luego de pasasr por la funcion:

```java
package com.expample;

import com.example.NumberUtils;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberUtilsNonSystematicTest {

    @Test
    void t1() {
        assertThat(new NumberUtils().add(numbers(1), numbers(1)))
                .isEqualTo(numbers(2));
        assertThat(new NumberUtils().add(numbers(1,5), numbers(1,0)))
                .isEqualTo(numbers(2, 5));
        assertThat(new NumberUtils().add(numbers(1,5), numbers(1,5)))
                .isEqualTo(numbers(3,0));
        assertThat(new NumberUtils().add(numbers(5,0,0), numbers(2,5,0)))
                .isEqualTo(numbers(7,5,0));
    }
    private static List<Integer> numbers(int... nums) {
        List<Integer> list = new ArrayList<>();
        for(int n : nums) {
            list.add(n);
        }
        return list;
    }

}
```

Veamos si pasa con éxito la prueba:

![Untitled](Pruebas%20basadas%20en%20especificaciones%2033d0fcda98ff4aad8c85bc8fc901dcd6/Untitled%204.png)

Ahora la actividad nos pide probar estas nuevas pruebas:

```java
package com.expample;

import com.example.NumberUtils;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberUtilsTest {

    @Test
    void t1() {
        assertThat(new NumberUtils().add(numbers(9), numbers(2)))
                .isEqualTo(numbers(1,1));
        assertThat(new NumberUtils().add(numbers(9, 9, 8), numbers(1,7,2)))
                .isEqualTo(numbers(1,1,7, 0));
    }
    private static List<Integer> numbers(int... nums) {
        List<Integer> list = new ArrayList<>();
        for(int n : nums) {
            list.add(n);
        }
        return list;
    }

}
```

Verificamos que pasen con éxito esta prueba:

![Untitled](Pruebas%20basadas%20en%20especificaciones%2033d0fcda98ff4aad8c85bc8fc901dcd6/Untitled%205.png)

Ahora escribimos las pruebas para asegurarnos que los parametros de entrada se encuentre en el rango especificado.

Para ello agregamos los siguientes test en el codigo:

```java
    @ParameterizedTest
    @MethodSource("digitsOutOfRange")
    void shouldThrowExceptionWhenDigitsAreOutOfRange(List<Integer> left, List<Integer> right) {
        assertThatThrownBy(() -> new NumberUtils().add(left, right))
                .isInstanceOf(IllegalArgumentException.class);
    }
    static Stream<Arguments> digitsOutOfRange() {
        return Stream.of(
            of(numbers(1,-1,1), numbers(1)),
            of(numbers(1), numbers(1,-1,1)),
            of(numbers(1,11,1), numbers(1)),
            of(numbers(1), numbers(1,11,1))
        );
    }
```

Explicacion:

Estos tests verifican que los números ingresados estén dentro del rango de 0 a 9, valores fuera de este rango deberían lanzar una excepción de tipo IllegalArgumentException. Si los tests son exitosos, podemos estar seguros de que nuestro código maneja correctamente los valores fuera del rango especificado.

Bien ahora vamos a evaluar la cobertura de codigo con Jacoco, hasta el momento nos sale lo siguiente:

![Untitled](Pruebas%20basadas%20en%20especificaciones%2033d0fcda98ff4aad8c85bc8fc901dcd6/Untitled%206.png)

Es evidente que vamos a ignorar el método `main` y nos enfocaremos en el método `add`. Observamos que este no está siendo probado cuando `left` o `right` son nulos. En este caso, el método `add` debería retornar `null`. Para cubrir esta parte en los tests, implementamos lo siguiente:

```java
@Test
void testAddWhenLeftOrRightIsNull(){
    assertThat(new NumberUtils().add(null, numbers(1, 2, 3)))
            .isNull();
    assertThat(new NumberUtils().add(numbers(1, 2, 3), null))
            .isNull();
}
```

Este test cubrirá los casos cuando `left` o `right` son `null`.

La salida despues de correr nuevamente los test:

![Untitled](Pruebas%20basadas%20en%20especificaciones%2033d0fcda98ff4aad8c85bc8fc901dcd6/Untitled%207.png)