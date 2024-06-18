package org.example;

public class TaxCalculator1 {

    public double calculateTax(double value) {
        // Precondición: el valor debe ser no negativo
        assert value >= 0 : "Value has to be positive";

        double taxValue = 0;

        // alguna regla de negocio compleja aquí...
        // el valor final va a 'taxValue'

        // Postcondición: el valor del impuesto debe ser no negativo
        assert taxValue >= 0 : "Calculated tax cannot be negative";

        return taxValue;
    }
}
