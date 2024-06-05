package com.example;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CeldaTest {

    @Test
    void obtenerTipoEspecificado(){
        // Arrange
        Celda celda = new Celda('T');

        //Act
        char tipo = celda.obtenerTipoDeCelda();

        //Assert
        assertThat(tipo).isEqualTo('T')
                .as("El tipo deberia de ser T");
    }

}
