package org.example;

public class TaxCalculator {

    /**
     * Este método calcula el impuesto para un valor dado.
     * Precondición: el valor de entrada debe ser positivo
     * Postcondición: el impuesto calculado no puede ser negativo
     *
     * @param value el valor para el cual se calcula el impuesto. Debe ser un número positivo.
     * @return el valor del impuesto calculado
     * @throws RuntimeException si el valor es negativo o si el impuesto calculado es negativo
     */

    public double calculateTax(double value) {

        if(value < 0) {
            throw new RuntimeException("Value has to be positive");
        }

        double taxValue = 0;

        // codigo adicional aqui...

        if(taxValue < 0) {
            throw new RuntimeException("Calculated tax cannot be negative");
        }

        return taxValue;
    }
}
