package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    //tags = "@Smoke or @Regression",
    features = "src/test/java/feature/PsychologistUserCRUD.feature", 
    glue = "stepdefinition", 
    plugin = { "pretty",
    "html:target/cucumber-reports/cucumber-report.html",
    "json:target/cucumber-reports/cucumber.json",
    "junit:target/cucumber-reports/cucumber.xml"},
    monochrome = true)
public class TestRunner {

}
