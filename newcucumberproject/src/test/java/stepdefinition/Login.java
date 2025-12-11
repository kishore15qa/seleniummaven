package stepdefinition;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {
    WebDriver driver;
public Login(SharedClassDriver sharedClassDriver) {
    this.driver = SharedClassDriver.getDriver();
}

    @Before(order = 1)
    public void setUp() {
        driver = SharedClassDriver.getDriver();

    }


    @When ("user enter email as {string} and password as {string}")
    public void user_enter_email_as_and_password_as(String string, String string2){
        driver.findElement(By.name("email")).sendKeys(string);
        driver.findElement(By.name("password")).sendKeys(string2);
        
    }
    @And("click on login button")
    public void click_on_login_button() throws InterruptedException{
        driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();
        Thread.sleep(2000);
    }
    @Then ("user is navigated to the home page {string}")
    public void user_is_navigated_to_the_home_page(String expectedUrlString){
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrlString, actualUrl);


    }
    @After
    public void tearDown() {
        driver = SharedClassDriver.closeDriver();
    }

}
