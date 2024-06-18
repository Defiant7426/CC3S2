package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class stringUtilsExploracionTest {

    @Test
    void testSubstringsBetween() {
        // Act
        String[] respuesta = SubstringBetween.substringsBetween("axcaycazc", "a", "c");

        // Assert
        assertThat(respuesta).isEqualTo(new String[]{"x", "y", "z"});
    }

    @Test
    void singleSubStringBetween() {
        // Act
        String[] respuesta = SubstringBetween.substringsBetween("abcd", "a", "d");

        // Assert
        assertThat(respuesta).isEqualTo(new String[]{"bc"});
    }

    @Test
    void manySubStringBetween(){
        // Act
        String[] respuesta = SubstringBetween.substringsBetween("abcdabcdab", "a", "d");

        // Assert
        assertThat(respuesta).isEqualTo(new String[]{"bc", "bc"});
    }

    @Test
    void openAndCloseAreDouble(){
        // Act
        String[] respuesta = SubstringBetween.substringsBetween("aabcddaabfddaab", "aa", "dd");

        // Assert
        assertThat(respuesta).isEqualTo(new String[]{"bc", "bf"});
    }

    @Test
    void noSubStringBetween(){
        // Act
        String[] respuesta = SubstringBetween.substringsBetween("abcd", "e", "f");
        // Assert
        assertThat(respuesta).isEqualTo(null);
    }

    @Test
    void SubStringBetweenWithEmptyString(){
        // Act
        String[] respuesta = SubstringBetween.substringsBetween("", "a", "d");
        // Assert
        assertThat(respuesta).isEqualTo(SubstringBetween.EMPTY_STRING_ARRAY);
    }

    @Test
    void SubStringBetweenWithNull(){
        // Act
        String[] respuesta = SubstringBetween.substringsBetween(null, "a", "d");
        // Assert
        assertThat(respuesta).isEqualTo(null);
    }

    @Test
    void SubStringBetweenWithEmptyOpen(){
        // Act
        String[] respuesta = SubstringBetween.substringsBetween("abcd", "", "d");
        // Assert
        assertThat(respuesta).isEqualTo(null);
    }

    @Test
    void SubStringBetweenWithEmptyClose(){
        // Act
        String[] respuesta = SubstringBetween.substringsBetween("abcd", "a", "");
        // Assert
        assertThat(respuesta).isEqualTo(null);
    }

    @Test
    void singleCharSubStringBetween(){
        // Act
        String[] respuesta = SubstringBetween.substringsBetween("abcd", "a", "c");
        // Assert
        assertThat(respuesta).isEqualTo(new String[]{"b"});
    }

    @Test
    void openAndCloseAreEmpty(){
        // Act
        String[] respuesta = SubstringBetween.substringsBetween("abcd", "", "");
        // Assert
        assertThat(respuesta).isEqualTo(null);
    }

    @Test
    void openAndCloseAreNull(){
        // Act
        String[] respuesta = SubstringBetween.substringsBetween("abcd", null, null);
        // Assert
        assertThat(respuesta).isEqualTo(null);
    }

    @Test
    void AllIsEmpty(){
        // Act
        String[] respuesta = SubstringBetween.substringsBetween("", "", "");
        // Assert
        assertThat(respuesta).isEqualTo(null);
    }

    @Test
    void AllIsNull(){
        // Act
        String[] respuesta = SubstringBetween.substringsBetween(null, null, null);
        // Assert
        assertThat(respuesta).isEqualTo(null);
    }
}
