package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    tags = "@Smoke or @Regression",
    features = "src/test/java/feature", 
    glue = "stepdefinition", 
    plugin = { "pretty",
    "html:target/cucumber.html",
    "json:target/cucumber.json",
    "junit:target/cucumber.xml"},
    monochrome = true)
public class LoginRunner {

}
