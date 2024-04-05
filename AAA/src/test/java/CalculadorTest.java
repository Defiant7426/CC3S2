import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculadorTest {

    @Test
    public void testSum_PositiveNumbers_ShouldReturnCorrectSum() {
        // Arrange
        Calculador calculador = new Calculador();
        int numeroA = 10;
        int numeroB = 5;

        // Act
        int resultado = calculador.sumar(numeroA, numeroB);

        // Assert
        assertEquals(15, resultado, "10 + 5 deberia ser 15");
    }

    @Test
    public void testSum_NegativeNumbers_ShouldReturnCorrectSum(){
        //Arrange
        Calculador calculador = new Calculador();
        int numeroA = -4;
        int numeroB = -7;

        //Act
        int respuesta = calculador.sumar(numeroA,numeroB);

        //Assert
        assertEquals(-11, respuesta, "La suma de -4 y -7 deberia de ser -11");
    }

    @Test
    public void testSum_SumByZero_ShouldReturnSameNumber(){
        //Arrange
        Calculador calculador = new Calculador();
        int numeroA = 5;
        int numeroB = 0;

        //Act
        int respuesta = calculador.sumar(numeroA, numeroB);

        //Assert
        assertEquals(5, respuesta, "La suma entre 5 y 0 es 5");
    }

    @Test
    public void testRest_PositiveNumbers_ShouldreturnCorrectRest(){
        // Arrange
        Calculador calculador = new Calculador();
        int numeroA = 10;
        int numeroB = 3;

        //Act
        int resultado = calculador.restar(numeroA, numeroB);

        //Assert
        assertEquals(7, resultado, "10 - 3 deberia ser 7");
    }

    @Test
    public void testRest_NegativeNumbers_ShouldReturnCorrestRest(){
        // Arrange
        Calculador calculador = new Calculador();
        int numeroA = -4;
        int numeroB = -3;

        //Act
        int resultado = calculador.restar(numeroA,numeroB);

        //Assert
        assertEquals(-1, resultado, "La resta de -4 y -3 deberia de ser -1");
    }

    @Test
    public void testRest_NoNumberZeroRestByZero_ShouldReturnSameNumber(){
        //Arrange
        Calculador calculador = new Calculador();
        int numeroA = 5;
        int numeroB = 0;

        //Act
        int resultado = calculador.restar(numeroA,numeroB);

        //Assert
        assertEquals(5,resultado, "La resta entre 5 y 0 deberia de ser 5");
    }

    @Test
    public void testRest_ZeroRestByNoNumberZeroPositive_ShouldReturnNegativeNumber(){
        //Arrange
        Calculador calculador = new Calculador();
        int numeroA = 0;
        int numeroB = 5;

        //Act
        int respuesta = calculador.restar(numeroA,numeroB);

        //Assert
        assertEquals(-5,respuesta,"La resta entre 0 y 5 deberia de ser -5");
    }

    @Test
    public void testRest_ZeroRestByNoNumberZeroNegative_ShouldReturnPositiveNumber(){
        //Arrange
        Calculador calculador = new Calculador();
        int numeroA = 0;
        int numeroB = -5;

        //Act
        int respuesta = calculador.restar(numeroA,numeroB);

        //Assert
        assertEquals(5,respuesta,"La resta entre 5 y 0 deberia de ser 5");
    }

    @Test
    public void testMultiplication_PositiveNumbers_ShouldreturnCorrectMultiplication(){
        // Arrange
        Calculador calculador = new Calculador();
        int numeroA = 4;
        int numeroB = 5;

        //Act
        int resultado = calculador.multiplicacion(numeroA, numeroB);

        //Assert
        assertEquals(20, resultado, "4*5 deberia ser 20");
    }

    @Test
    public void testMultiplication_NegativeNumbers_ShouldreturnCorrectMultiplication(){
        // Arrange
        Calculador calculador = new Calculador();
        int numeroA = -6;
        int numeroB = -3;

        //Act
        int respuesta = calculador.multiplicacion(numeroA,numeroB);

        //Assert
        assertEquals(18, respuesta, "La multiplicacion entre -6 y -3 deberia de ser 18");
    }

    @Test
    public void testMultiplication_ByZero_ShouldreturnZero(){
        //Arrange
        Calculador calculador = new Calculador();
        int numeroA = 5;
        int numeroB = 0;

        //Act
        int respuesta = calculador.multiplicacion(numeroA,numeroB);

        //Assert
        assertEquals(0, respuesta, "La multiplicacion de 5 y 0 deberia de ser 0");
    }

    @Test
    public void testMultiplicacion_ByTwoNumbersOppositeSign_ShouldReturnNegativeNumber(){
        //Arrange
        Calculador calculador = new Calculador();
        int numeroA = 4;
        int numeroB = -2;

        //Act
        int respuesta = calculador.multiplicacion(numeroA,numeroB);

        //Assert
        assertEquals(-8, respuesta, "La multiplicacion de -4 y -2 deberia de ser -8");
    }

    @Test
    public void testDivision_DivideByNoZero_ShouldreturnCorrectDivision(){
        // Arrange
        Calculador calculador = new Calculador();
        int numeroA = 18;
        int numeroB = 3;

        //Act
        double resultado = calculador.division(numeroA, numeroB);

        //Assert
        assertEquals(6, resultado, "18 / 3 deberia ser 6");
    }
    @Test
    public void testDivision_DivideByZero_ShouldThrowArithmeticException(){
        //Arrange
        Calculador calculador = new Calculador();
        int numeroA = 10;
        int numeroB = 0;

        //Act y Assert
        assertThrows(ArithmeticException.class, () -> {
            calculador.division(numeroA, numeroB);
        }, "Division por 0 debe de lanzar una excepcion");
    }

    @Test
    public void testDivision_DivideZeroByNoZero_ShouldReturnZero(){
        //Arrange
        Calculador calculador = new Calculador();

        int numeroA = 0;
        int numeroB = 7;

        //Act
        double resultado = calculador.division(numeroA,numeroB);

        //Assert
        assertEquals(0.0, resultado, "0 / 7 deberia de ser 0.0");

    }

    @Test
    public void testDivision_NegativeNumbers_ShouldReturnCorrectResult(){
        //Arrange
        Calculador calculador = new Calculador();
        int numeroA = -12;
        int numeroB = -3;

        //Act
        double resultado = calculador.division(numeroA,numeroB);

        //Assert
        assertEquals(4, resultado, "La division entre -12 y -3 deberia de ser 4");
    }

    @Test
    public void testDivision_ByTwoNumberOppositeSign_ShouldReturnCorrectResult(){
        //Arrange
        Calculador calculador = new Calculador();
        int numeroA = -12;
        int numeroB = 4;

        //Act
        double resultado = calculador.division(numeroA,numeroB);

        //Assert
        assertEquals(-3, resultado, "La division entre -12 y 4 deberia de ser -3");
    }

}
