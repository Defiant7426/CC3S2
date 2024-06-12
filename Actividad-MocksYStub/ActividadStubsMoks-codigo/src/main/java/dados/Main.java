package dados;

/*
Paso 6: Integrar y probar la clase LanzamientoDados en una aplicación de producción,
inyectando la implementación real de NumerosAleatorios.
* */

public class Main {
    public static void main(String[] args) {
        NumerosAleatorios numerosAleatorios = new NumerosGeneradosAleatoriamente();
        LanzamientoDados lanzamientoDados = new LanzamientoDados(numerosAleatorios);
        System.out.println("Primer Lanzamiento:");
        System.out.println(lanzamientoDados.lanzar());
        System.out.println("Segundo Lanzamiento:");
        System.out.println(lanzamientoDados.lanzar());
        System.out.println("Tercer Lanzamiento:");
        System.out.println(lanzamientoDados.lanzar());
        System.out.println("Cuarto Lanzamiento:");
        System.out.println(lanzamientoDados.lanzar());
    }
}
