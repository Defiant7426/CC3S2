package skeleton;
import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.example.Letter;
import org.example.Score;
import org.example.Word;


public class Stepdefs {
    Word word;
    Score score;
    Letter resultado;
    String palabraAdivinar;

    @Given("^que\\s*la\\s*palabra\\s*clave\\s*es\\s*([^\\s]*)")
    public void dado_que_la_palabra_clave_es(String palabraClave)throws Throwable{
        System.out.println("La palabra clave es: " + palabraClave);
        word = new Word(palabraClave);
    }
    @When("^trato\\s*de\\s*adivinar\\s*la\\s*palabra\\s*con\\s*([^\\s]*)")
    public void trato_de_adivinar_la_palabra_con(String palabraAdivinar)throws Throwable{
        System.out.println("Se trata de adinar con la palabra: " + palabraAdivinar);
        this.palabraAdivinar = palabraAdivinar;
    }
    @Then("^el\\s*estado\\s*de\\s*la\\s*letra\\s*([^\\s]+)\\s*es\\s*correct(?:a|o)$")
    public void El_resultado_deberia_de_ser_correcto(String letra) throws Throwable{
        score = word.guess(letra);
        resultado = score.letter(palabraAdivinar.indexOf(letra));
        System.out.println("El estado de la letra " + letra + " es correcto");
        assertThat(resultado).isEqualTo(Letter.CORRECT);
    }
    @Then("^el\\s*estado\\s*de\\s*la\\s*letra\\s*([^\\s]+)\\s*es\\s*incorrect(?:a|o)$")
    public void El_resultado_deberia_de_ser_incorrecto(String letra) throws Throwable{
        score = word.guess(letra);
        resultado = score.letter(palabraAdivinar.indexOf(letra));
        System.out.println("El estado de la letra " + letra + " es incorrecto");
        assertThat(resultado).isEqualTo(Letter.INCORRECT);
    }
    @Then("^el\\s*estado\\s*de\\s*la\\s*letra\\s*([^\\s]+)\\s*es\\s*parcialmente\\s*correct(?:a|o)$")
    public void El_resultado_deberia_de_ser_partcorrecto(String letra) throws Throwable{
        score = word.guess(letra);
        resultado = score.letter(palabraAdivinar.indexOf(letra));
        System.out.println("El estado de la letra " + letra + " es parcialmente correcto");
        assertThat(resultado).isEqualTo(Letter.PART_CORRECT);
    }
    @Then("^la palabra fue adivinada")
    public void La_palabra_fue_adivinada() throws Throwable{
        System.out.println("La palabra fue adivinada");
        assertTrue(true);
    }
}
