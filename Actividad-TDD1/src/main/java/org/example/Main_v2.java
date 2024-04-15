package org.example;

import java.util.Scanner;

public class Main_v2 {
    public static void main(String[] args) {
        System.out.println("\t----JUEGO WORDZ----");
        Scanner consola = new Scanner(System.in);
        int puntos = 6;
        var word = new Word("GRANT");

        while(puntos!=0){
            System.out.println("Puntos Acomulados: " + puntos);
            String palabra_usuario = PedirPalabra(consola);
            if (palabra_usuario==null) continue;
            System.out.print("Resultado: ");
            int numero_letras_correctas = EvaluarPalabra(palabra_usuario, word);
            if(numero_letras_correctas==word.getCorrectWord().length()) break;
            System.out.println("\n-----------------------------------------------------------\n");
            --puntos;
        }
        System.out.println("\n\nPUNTOS EN TOTAL ACOMULADO: " + puntos);
    }

    private static int EvaluarPalabra(String palabraUsuario, Word word) {
        int correcto = 0;
        for(int i=0; i<5; i++){
            var score = word.guess(String.valueOf(palabraUsuario.charAt(i)));
            var result = score.letter(i);
            System.out.print(result.getpuntuacion() + "/");
            if (result.getpuntuacion()==1) ++correcto;
        }
        return correcto;
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
