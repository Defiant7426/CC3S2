package dados;

/*
Paso5:
Implementar una clase NumerosGeneradosAleatoriamente que utilice un generador de
números aleatorios real y que cumpla con la interfaz NumerosAleatorios.
* */

import java.util.Random;

public class NumerosGeneradosAleatoriamente implements NumerosAleatorios{
    private final Random rnd = new Random();

    @Override
    public int nextInt(int upperBoundExclusive) {
        return rnd.nextInt(upperBoundExclusive);
    }
}
