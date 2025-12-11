package stepdefinitions;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginSteps {
     WebDriver driver;
     WebDriverWait wait;
    LoginPage loginPage;

     @Given("User is on Login Page")
    public void user_is_on_login_page() {
       driver = DriveManager.getDriver();
       wait = DriveManager.getWait();
        driver.get("https://app-dev.innerkraft.com/"); 
        loginPage = new LoginPage(driver);
    }

    @When("User enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }
     @And("Clicks on Login button")
    public void clicks_on_login_button() {
        loginPage.clickLogin();
    }
     @Then("User should be redirected to Home Page")
    public void user_should_be_redirected_to_home_page() {
        String expectedUrl = "https://app-dev.innerkraft.com/"; // Adjust based on your site
        String actualUrl = driver.getCurrentUrl();
        System.out.println("Redirected to: " + actualUrl);
        assertEquals(expectedUrl, actualUrl, "Login failed! URL mismatch.");
        driver.quit();
    }
    @Then("User should see an error message {string}")
public void user_should_see_error_message(String expectedMessage) {
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-title and contains(text(),'" + expectedMessage + "')]")));
        assertTrue("Expected error message was not displayed", errorElement.isDisplayed());
    } catch (NoSuchElementException e) {
        fail("Error message element not found on the page.");
        
    }
    driver.quit();
} 

}
