import org.example.StringCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {
    @Test
    public void stringVacio() { //si la entrada en vacia, se espera que el retorno sea 0
        assertEquals(0, StringCalculator.add(""));
    }
    @Test
    public void stringNumero(){//si la entrada es un numero se espera que retorne el mismo numero
        assertEquals(10, StringCalculator.add("10"));
    }
    @Test
    public void stringNumeros(){//si la entrada son dos numeros separados por coma, se espera la suma de ambos
        assertEquals(117, StringCalculator.add("17,100"));
    }
}
