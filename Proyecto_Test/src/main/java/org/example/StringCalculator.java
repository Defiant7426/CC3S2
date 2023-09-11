package org.example;

public class StringCalculator {
    public static int add(String cadena){
        if(cadena.isEmpty()){ //si es una cadena vacia retorna 0
            return 0;
        }
        String [] numeros = cadena.split(",");//separamos la cadena con split
        if (numeros.length == 1){ //si solo tiene un elemento retorna el mismo elemento
            return Integer.parseInt(cadena);
        }
        else{ //sino entonces va a retornar la suma de ambos elementos
            return Integer.parseInt(numeros[0]) + Integer.parseInt(numeros[1]);
        }
    }
}
