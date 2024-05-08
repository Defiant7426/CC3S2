package skeleton;

import io.cucumber.java.en.Given;

public class Stepdefs {
    @Given("^Esto es una prueba$")
    public void esto_es_una_prueba(){
        System.out.println("La prueba paso con exito");
    }
}
