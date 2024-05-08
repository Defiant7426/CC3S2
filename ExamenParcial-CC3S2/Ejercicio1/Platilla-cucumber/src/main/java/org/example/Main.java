package org.example;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int preguntas = 10;
        Scanner consola = new Scanner(System.in);
        Quiz quiz = new Quiz(preguntas);
        System.out.println("\t----Trivia Game----");
        for (int i=0; i<preguntas; i++){
            var question = quiz.getQuestion(i+1);
            System.out.println(question.getDescripcion());
            for(int j=0; j<question.getOpciones().length; j++){
                System.out.println(question.getOpciones()[j]);
            }
            System.out.print("Respuesta: ");
            int respuesta = Integer.parseInt(consola.nextLine());
            if(quiz.verificarRespuesta(question,respuesta)){
                System.out.println("Tu respuesta es correcta :)\n");
            }
            else {
                System.out.println("Tu respuesta es incorrecta ):\n");
            }
        }
        System.out.println();
        System.out.println("\n\t-->SU PUNTUACION FINAL ES: " + quiz.getPuntuacion());
    }
}