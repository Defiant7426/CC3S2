package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SubstringBetweenTest {

    SubstringBetween sb = new SubstringBetween();

    @Test
    void testSubstringsBetween() {
        // Act
        String[] respuesta = sb.substringsBetween("axcaycazc", "a", "c");

        // Assert
        assertThat(respuesta).isEqualTo(new String[]{"x", "y", "z"});
    }

    @Test
    void singleSubStringBetween() {
        // Act
        String[] respuesta = sb.substringsBetween("abcd", "a", "d");

        // Assert
        assertThat(respuesta).isEqualTo(new String[]{"bc"});
    }

    @Test
    void manySubStringBetween(){
        // Act
        String[] respuesta = sb.substringsBetween("abcdabcdab", "a", "d");

        // Assert
        assertThat(respuesta).isEqualTo(new String[]{"bc", "bc"});
    }

    @Test
    void openAndCloseAreDouble(){
        // Act
        String[] respuesta = sb.substringsBetween("aabcddaabfddaab", "aa", "dd");

        // Assert
        assertThat(respuesta).isEqualTo(new String[]{"bc", "bf"});
    }
}
