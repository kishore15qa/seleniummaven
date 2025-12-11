package stepdefinitions;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    
    @Before
    public void setUp(Scenario scenario) {
        // Initialize any test setup if needed
        WebDriver driver = DriveManager.getDriver();
        driver.manage().deleteAllCookies();
        driver.get("https://app-dev.innerkraft.com/");
        System.out.println("🚀 Browser launched for new scenario");
    
    }
    
    @After
    public void tearDown(Scenario scenario) {
        // Clean up resources or perform any necessary teardown actions
         DriveManager.closeDriver();
        System.out.println("🧹 Browser closed after scenario");
    }
}