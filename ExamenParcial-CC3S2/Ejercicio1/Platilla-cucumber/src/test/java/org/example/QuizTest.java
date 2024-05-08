package org.example;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

public class QuizTest {
    Quiz quiz = new Quiz(10);

    @ParameterizedTest
    @CsvSource({
            "1,1",
            "2,2",
            "3,0",
            "4,2",
            "5,3",
            "6,0",
            "7,1",
            "8,3",
            "9,0",
            "10,2"
    })
    public void testeandoLasRespuestasCorrectas(int pregunta, int respuesta){
        assertThat(quiz.getQuestion(pregunta).getRespuesta())
                .isEqualTo(respuesta)
                .as("La respuesta deberia de ser: " + respuesta);
    }

    @ParameterizedTest
    @CsvSource({
            "-1,1",
            "-2,2",
            "11,0",
            "12,2"
    })
    public void testeandoLasRespuestasFueraDeRango(int pregunta, int respuesta){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()->quiz.getQuestion(pregunta));
    }
    @ParameterizedTest
    @CsvSource({
            "1,0",
            "2,3",
            "3,4",
            "4,1",
            "5,1",
            "6,2",
            "7,4",
            "8,1",
            "9,3",
            "10,4"
    })
    public void testandoLasRespuestasIncorrectas(int pregunta, int respuesta){
        assertThat(quiz.getQuestion(pregunta).getRespuesta())
                .isNotEqualTo(respuesta)
                .as("La respuesta no deberia de ser: " + respuesta);
    }


}
