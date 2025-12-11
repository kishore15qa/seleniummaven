package stepdefinition;

import static org.junit.Assert.assertEquals;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// import io.cucumber.java.After;
// import io.cucumber.java.Before;
// import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;

public class InvalidLogin {
    WebDriver driver;
public InvalidLogin(SharedClassDriver SharedClass) {
    driver = SharedClassDriver.getDriver();
}

  
    @Then("user stayed on same page {string}")
    public void user_stayed_on_same_page(String loginurl){
        String actualurl2 = driver.getCurrentUrl();
        assertEquals(loginurl, actualurl2);

    }

}
