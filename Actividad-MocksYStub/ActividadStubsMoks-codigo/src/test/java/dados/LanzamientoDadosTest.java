package dados;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/*
Paso 3: Desarrollar un stub de NumerosAleatorios para usar en pruebas unitarias, permitiendo
controlar los resultados de los lanzamientos.
* */

@ExtendWith(MockitoExtension.class)
class LanzamientoDadosTest {

    @Mock
    private NumerosAleatoriosStub stub;

    @InjectMocks
    private LanzamientoDados lanzamiento;

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void lanzarDadosConDiferentesResultados(int resultadoEsperado) {

        when(stub.nextInt(6)).thenReturn(resultadoEsperado);

        //Act
        int resultado = lanzamiento.lanzar();

        //Assert
        assertThat(resultado).isEqualTo(resultadoEsperado + 1);
    }

    private static class NumerosAleatoriosStub implements NumerosAleatorios {
        private int nextIntResult;

        public void setNextIntResult(int nextIntResult) {
            this.nextIntResult = nextIntResult;
        }

        @Override
        public int nextInt(int bound) {
            return nextIntResult;
        }
    }
}