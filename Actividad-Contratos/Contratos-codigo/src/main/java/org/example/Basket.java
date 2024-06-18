package org.example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Basket {

    private BigDecimal totalValue = BigDecimal.ZERO;
    // Usamos BigDecimal en lugar de double para evitar problemas de redondeo en Java.
    private Map<Product, Integer> basket = new HashMap<>();

    public void add(Product product, int qtyToAdd) { //Escribe para el método add() sus pre/postcondiciones.
        // add the product
        // update the total value
        // las precondiciones es de que product no sea null y qtyToAdd sea mayor a 0
        if(product == null){
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if(qtyToAdd <= 0){
            throw new IllegalArgumentException("La cantidad a agregar debe ser mayor a 0");
        }
        // si se cumplen las pre-condiciones entonces:
        // post-condiciones

        // 1. El producto debe de estar en basket con la cantidad correcta (incrementada en qtyToAdd)
        // se agrega el producto al carrito
        basket.put(product, basket.getOrDefault(product, 0) + qtyToAdd);

        // 2. El valor total debe de ser el valor anterior más el precio del producto multiplicado por qtyToAdd
        // se actualiza el valor total
        BigDecimal previusTotalValue = totalValue.add(product.getPrice().multiply(BigDecimal.valueOf(qtyToAdd)));

        totalValue = previusTotalValue;

        //nueva postcondicion:
        //el nuevo valor total debe ser mayor que el valor total anterior
        if(totalValue.compareTo(previusTotalValue) < 0){
            throw new IllegalArgumentException("El valor total no puede ser negativo");
        }
    }

    public void remove(Product product) { // Escribe las pre/post condiciones del método remove().
        // precondiciones:
        // el producto no puede ser nulo
        if(product == null){
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }

        // postcondiciones:

        // 1. Eliminamos el producto del carrito
        basket.remove(product);

        // 2. El producto debe de estar en basket con la cantidad correcta (decrementada en 1)
        totalValue = totalValue.subtract(product.getPrice().multiply(BigDecimal.valueOf(basket.getOrDefault(product, 0))));

        // 3. el nuevo valor total debe ser menor que cero
        if(totalValue.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("El valor total no puede ser negativo");
        }
    }
}
