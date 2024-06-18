package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.example.StringUtils.regexSubstringsBetween;
import static org.example.StringUtils.substringsBetween;

public class stringUtilsTest {

    // Cadena de longitud 1
    @Test
    void strOfLength1() {
        // T7: el carácter único en str coincide con la etiqueta open.
        assertThat(substringsBetween("a", "a", "b")).isEqualTo(null);
        // T8: El carácter único en str coincide con la etiqueta de close.
        assertThat(substringsBetween("a", "b", "a")).isEqualTo(null);
        // T9: El carácter único en str no coincide ni con la etiqueta de open ni con la de close.
        assertThat(substringsBetween("a", "b", "b")).isEqualTo(null);
        // T10: el carácter único en str coincide con las etiquetas de open y close.
        assertThat(substringsBetween("a", "a", "a")).isEqualTo(null);
    }

    // cadena de longitud > 1, longitud de open = 1, longitud de close = 1:
    @Test
    void openAndCloseOfLength1() {
        // T11: str no contiene ni la etiqueta de open ni la de close.
        assertThat(substringsBetween("abc", "x", "y")).isEqualTo(null);
        // T12: str contiene la etiqueta open pero no contiene la etiqueta close.
        assertThat(substringsBetween("abc", "a", "y")).isEqualTo(null);
        // T13: str contiene la etiqueta de close pero no contiene la etiqueta de open.
        assertThat(substringsBetween("abc", "x", "c")).isEqualTo(null);
        // T14: str contiene las etiquetas de open y close.
        assertThat(substringsBetween("abc", "a", "c")).isEqualTo(new String[]{"b"});
        // T15: str contiene las etiquetas de open y close varias veces. <- falta implementar
        assertThat(substringsBetween("abcabcabc", "a", "c")).isEqualTo(new String[]{"b", "b", "b"});
        // ... previas aseveraciones aquí
        assertThat(substringsBetween("abcabyt byrc", "a", "c")).isEqualTo(new String[]{"b", "byt byr"});
    }

    // cadena de longitud > 1, longitud de open > 1, longitud de close > 1:
    @Test
    void openAndCloseTagsOfDifferentSizes() {
        // T16: str no contiene ni la etiqueta de open ni la de close.
        assertThat(substringsBetween("aabcc", "xx", "yy")).isEqualTo(null);
        // T17: str contiene la etiqueta open pero no contiene la etiqueta close.
        assertThat(substringsBetween("aabcc", "aa", "yy")).isEqualTo(null);
        // T18: str contiene la etiqueta de close pero no contiene la etiqueta de open.
        assertThat(substringsBetween("aabcc", "xx", "cc")).isEqualTo(null);
        // T19: str contiene las etiquetas de open y close.
        assertThat(substringsBetween("aabbcc", "aa", "cc")).isEqualTo(new String[]{"bb"});
        // T20: str contiene las etiquetas de open y close varias veces.
        assertThat(substringsBetween("aabbccaaeecc", "aa", "cc")).isEqualTo(new String[]{"bb", "ee"});
        // ... previas aseveraciones aquí
        assertThat(substringsBetween("a abb ddc ca abbcc", "a a", "c c")).isEqualTo(new String[]{"bb dd"});
    }

    //  str contiene las etiquetas de open y close sin caracteres entre ellas.
    @Test
    void notSubstringBetweenOpenAndCloseTags() {
        // T21: str contiene las etiquetas de open y close sin caracteres entre ellas.
        assertThat(substringsBetween("aabb", "aa", "bb")).isEqualTo(new String[]{""});
    }

    @Test
    void specialCharactersInStr() {
        // T22: contiene caracteres especiales, open y close de longitud 1.
        assertThat(substringsBetween("a*b$c", "a", "c"))
                .isEqualTo(new String[]{"b"});
        // T23: contiene caracteres especiales, open y close de longitud > 1.
        assertThat(substringsBetween("a*abb$cc", "aa", "cc"))
                .isEqualTo(new String[]{"bb"});
    }

    @Test
    void openOrCloseDelimiterCannotBeEmpty() {
        // T25: open es nulo.
        assertThatThrownBy(() -> substringsBetween("abc", null, "b"))
                .isInstanceOf(InvalidDelimiterException.class)
                .hasMessage("Los delimitadores no pueden estar vacíos");
        // T26: open está vacío.
        assertThatThrownBy(() -> substringsBetween("abc", "", "c"))
                .isInstanceOf(InvalidDelimiterException.class)
                .hasMessage("Los delimitadores no pueden estar vacíos");
        // T27: close es nulo.
        assertThatThrownBy(() -> substringsBetween("abc", "a", null))
                .isInstanceOf(InvalidDelimiterException.class)
                .hasMessage("Los delimitadores no pueden estar vacíos");
        // T28: close está vacío.
        assertThatThrownBy(() -> substringsBetween("abc", "a", ""))
                .isInstanceOf(InvalidDelimiterException.class)
                .hasMessage("Los delimitadores no pueden estar vacíos");
    }

    @Test
    void regexSimpleCase() {
        assertThat(regexSubstringsBetween("abcd", "a", "d")).isEqualTo(new String[] { "bc" });
    }

    @Test
    void regexManyStrings() {
        assertThat(regexSubstringsBetween("abcdabcdab", "a", "d")).isEqualTo(new String[]
                { "bc", "bc" });
    }

    @Test
    void regexOpenAndCloseTagsThatAreLongerThan1Char() {
        assertThat(regexSubstringsBetween("aabcddaabfddaab", "aa", "dd")).isEqualTo(new
                String[] { "bc", "bf" });
    }
}
