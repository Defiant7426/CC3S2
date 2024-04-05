public class Calculador {

    public int sumar(int numeroA, int numeroB) {
        return numeroA + numeroB;
    }

    public int restar(int numeroA, int numeroB) {
        return numeroA - numeroB;
    }

    public int multiplicacion(int numeroA, int numeroB) {
        return numeroA * numeroB;
    }

    public double division(int numeroA, int numeroB) {
        if (numeroB == 0) {
            throw new ArithmeticException("Division por cero");
        }
        return (double) numeroA / numeroB;
    }
}