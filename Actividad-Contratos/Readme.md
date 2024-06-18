# Actividad-Contratos

Se tomo de guía el  siguiente enlace: https://github.com/jhonnatan1806/Pruebas-Funcionales-Estructurales/tree/main/Contratos

¿Qué son los contratos en el desarrollo de software?

Los contratos en el desarrollo de software son acuerdos formales que especifican cómo deben interactuar los componentes del software. Estos acuerdos definen las responsabilidades y expectativas de cada componente, lo que permite un desarrollo más ordenado y predecible.

¿Qué son las pre condiciones y post condiciones de un método?

Las precondiciones de un método son las condiciones o reglas que deben ser verdaderas antes de que se pueda ejecutar el método. Por otro lado, las postcondiciones son las condiciones o reglas que deben ser verdaderas después de que se ejecuta el método.

¿Cuál es la regla de cambio del contrato?

La regla de cambio del contrato establece que si las precondiciones de un método son satisfechas antes de su ejecución, entonces, el método debe asegurar que las postcondiciones serán verdaderas después de su ejecución.

¿En que consiste un cambio seguro del contrato de un método?

Un cambio seguro en un método puede reemplazar la precondición existente por una que sea igual o más débil, y la postcondición existente por una que sea igual o más fuerte.

# Ejercicio 1

Escribe el Javadoc del método calculateTax describiendo su contrato, en el código anterior.

Revisa el archivo TaxCalculator.java

```java
public class TaxCalculator {

    public double calculateTax(double value) {
        //La precondición: un simple if para garantizar que no pasen valores no válidos
        if(value < 0) {
            throw new RuntimeException("Value has to be positive");
        }

        double taxValue = 0;

        // some complex business rule here...
        // final value goes to 'taxValue'

        //La postcondición también se implementa como un simple if. Si algo sale mal, lanzamos una excepción,
        //alertando al consumidor que la postcondición no se cumple
        if(taxValue < 0) {
            throw new RuntimeException("Calculated tax cannot be negative");
        }

        return taxValue;
    }
}
```

El javadoc lo podemos construir de la siguiente manera:

```java
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
```

# Ejercicio 2

Escribe una versión de TaxCalculator usando asserts para ello completa el archivo TaxCalculator1.java

```java
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
```

# Ejercicio 3

Una forma de evitar detener el programa debido a números negativos sería debilitar la precondición. En otras palabras, en lugar de aceptar solo valores mayores que cero, el método podría aceptar cualquier valor, positivo o negativo.

Listado 2 TaxCalculator con una precondición más débil

```java
public double calculateTax(double value) {
    //No hay precondiciones para verificar,cualquier valor es válido
    // methods continues  
}
```

¿puedes aplicar el mismo razonamiento a las postcondiciones? , ¿como relacionas el siguiente listado que devuelve un código de error en lugar de una excepción?

```java
public double calculateTax(double value) { 
// pre-condition check 
// Si la precondición  no se cumple, el método devuelve 0. El cliente de este método no necesita 
// preocuparse por las excepciones. 
 
 if (value < 0) { 
               return 0; 
 } 
 
  //… 
}
```

Respuesta

La misma lógica se puede aplicar a las postcondiciones. En lugar de lanzar una excepción si la postcondición no se cumple, podríamos devolver un código de error o un valor predeterminado. Esto debilita la postcondición, ya que ya no aseguramos que el impuesto calculado siempre será un número no negativo. En cambio, ahora decimos que el impuesto calculado será un número no negativo o cero en caso de que algo salga mal. Esto implica un cambio en el contrato del método.

```java
public double calculateTax(double value) {
    //No hay precondiciones para verificar,cualquier valor es válido
    // Simulación de lógica de cálculo de impuestos
    double taxValue = value * 0.1; // Ejemplo de cálculo simple: 10% del valor

    // Postcondición: el valor del impuesto debe ser no negativo
    if (taxValue < 0) {
        return 0;  // Código de error indicando que algo salió mal
    }

    return taxValue;
}
```

# Ejercicio 4

Escribimos la clase Basket.java:

```java
package org.example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Basket {

    private BigDecimal totalValue = BigDecimal.ZERO;
    // Usamos BigDecimal en lugar de double para evitar problemas de redondeo en Java.
    private Map<Product, Integer> basket = new HashMap<>();

    public void add(Product product, int qtyToAdd) {
        // add the product
        // update the total value
    }

    public void remove(Product product) {
        // remove the product from the basket
        // update the total value
    }
}
```

Escribe para el método add() sus pre/postcondiciones.

Respuesta:

```java
public void add(Product product, int qtyToAdd) { 
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
    totalValue = totalValue.add(product.getPrice().multiply(BigDecimal.valueOf(qtyToAdd)));
}
```

# Ejercicio 5

Modelar otra postcondiciones aquí, como "el nuevo valor total debe ser mayor que el valor total anterior". Usa la clase BigDecimal en lugar de un double. BigDecimals se recomienda siempre que desees evitar problemas de redondeo que pueden ocurrir cuando usas doubles

Respuesta:

```java
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
```

# Ejercicio 6

Escribe las pre/post condiciones del método remove().

Independientemente de los productos que se agreguen o eliminen de basket, el valor total de basket nunca debe ser negativo. Esta no es una precondición ni una poscondición: es un invariante, y la clase es responsable de mantenerlo

```java
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
```

Explicacion:

En este caso, las precondiciones aseguran que el producto que se desea eliminar no es nulo. Las postcondiciones se encargan de actualizar el total del carrito tras la eliminación del producto y garantizan que el total nunca sea negativo. Si el total es negativo, se lanza una excepción, lo que indica que algo salió mal.