package dados;
/*
 Paso 6: Integrar y probar la clase LanzamientoDados en una aplicación de producción,
inyectando la implementación real de NumerosAleatorios.
* */

public class LanzamientoDados {
    private final int NUMERO_LADOS = 6;
    private final NumerosAleatorios rnd;

    public LanzamientoDados(NumerosAleatorios numerosAleatorios) {
        this.rnd = numerosAleatorios;
    }

    public int lanzar() {
        return rnd.nextInt(NUMERO_LADOS) + 1;
    }
}
