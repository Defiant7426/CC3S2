package microwave;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(Cucumber.class)
//@CucumberOptions(plugin = {"pretty"})
public class RunCukesTest {

    public RunCukesTest() {
        var x = 1;
        List<String> texts = new ArrayList<String>();
        if(texts != null) {
            texts.add("Hello");
        }
    }
}