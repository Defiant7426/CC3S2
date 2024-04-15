package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_v3 {
    public static void main(String[] args) {
        System.out.println("\t----JUEGO WORDZ----");
        Scanner consola = new Scanner(System.in);
        var word = new Word("GRANT");
        while(!word.isFinish()) {
            System.out.println("Puntos acomulados: " + word.getNumIntentos());
            String palabra_usuario = PedirPalabra(consola);
            if (palabra_usuario==null) continue;
            System.out.print("Resultado: ");
            List<Integer> estados = word.EvaluarPalabra(palabra_usuario);
            int cont = 0;
            for(int estado : estados){
                System.out.print("/" + estado);
                if(estado == 1) ++cont;
            }
            System.out.println("\n");
            if (cont==word.getCorrectWord().length()) break;
        }
        System.out.println("\n\nPUNTOS EN TOTAL ACOMULADO: " + word.getNumIntentos());
    }
    private static String PedirPalabra(Scanner consola) {
        System.out.print("Inserte la palabra con 5 letras: ");
        String palabra_usuario = consola.nextLine();
        if(palabra_usuario.length()!=5) {
            System.out.println("La palabra deberia de tener 5 caracteres");
            return null;
        }
        return palabra_usuario.toUpperCase();
    }
}
