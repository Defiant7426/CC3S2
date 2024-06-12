package dados;
// Paso 1: Crear una interfaz dados.NumerosAleatorios que defina un método para obtener números
//aleatorios dentro de un rango específico.

public interface NumerosAleatorios {
    int nextInt(int upperBoundExclusive);
}
