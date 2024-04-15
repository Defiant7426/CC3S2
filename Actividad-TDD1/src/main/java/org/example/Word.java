package org.example;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private final String correctWord;
    private int numIntentos;


    public Word(String correctWord) throws IllegalArgumentException{
        if(correctWord.isEmpty()) throw new IllegalArgumentException("La palabra no puede ser vacia");
        this.correctWord = correctWord;
        this.numIntentos = 6;
    }

    public Score guess(String attempt) { //adivinar intento
        if (attempt.isEmpty()) throw new IllegalArgumentException("El intento no puede ser vacio");
        else if (attempt.length()>1) throw new IllegalArgumentException("El intento debe de ser una letra");
        return new Score(this.correctWord, attempt);
    }

    public List<Integer> EvaluarPalabra(String palabraUsuario) {
        List<Integer> estados;
        estados = new ArrayList<>();
        for(int i=0; i<this.getCorrectWord().length(); i++){
            var score = this.guess(String.valueOf(palabraUsuario.charAt(i)));
            var result = score.letter(i);
            estados.add(result.getpuntuacion());
        }
        --this.numIntentos;
        return estados;
    }

//    public boolean esSolucion(String palabra){
//        if()
//    }

    public int getNumIntentos() {
        return numIntentos;
    }

    public boolean isFinish(){
        return this.numIntentos < 0;
    }

    public String getCorrectWord() {
        return correctWord;
    }

}
