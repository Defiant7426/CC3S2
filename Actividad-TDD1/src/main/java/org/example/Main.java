package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("\t----Juego Wordz----");
        String palabra_adivinar = "GRANT";
        Scanner consola = new Scanner(System.in);
        int puntos=6;

        var word = new Word(palabra_adivinar);

        for(puntos=6; puntos>0;puntos--) {
            System.out.println("Puntos actual: " + puntos);
            System.out.print("Inserte la palabra con " + palabra_adivinar.length() + " letras: ");
            String palabra_usuario = consola.nextLine();
            System.out.println("Resultado: ");
            int correcto = 0;
            for(int i=0; i<palabra_adivinar.length(); i++){
                var score = word.guess(String.valueOf(palabra_usuario.charAt(i)));
                var result = score.letter(i);
                System.out.print(result.getpuntuacion() + "/");
                if (result.getpuntuacion()==1) ++correcto;
            }
            if(correcto==palabra_adivinar.length()) break;
            System.out.println("\n-----------------------------------------------------------\n");
        }
        System.out.println("\n\nPUNTOS EN TOTAL ACOMULADO: " + puntos);
    }
}