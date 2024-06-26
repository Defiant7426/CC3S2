package org.example;

public class Tower {

    private char symbol; //simbolo de la torre

    /**
     *
     * @param symbol
     * pre-condiciones: symbol no puede ser vacío
     * post-condiciones: solo se admiten "B" y "T" (por poner un ejemplo)
     */
    public Tower(char symbol){
        if (symbol == 'B' || symbol == 'T') {
            this.symbol = symbol; // asignamos el simbolo de la torre
        } else {
            throw new IllegalArgumentException("El simbolo de la torre no es valido");
        }
    }

    public char getSymbol() {
        return symbol; // debolvemos el simbolo de la torre
    }
}
