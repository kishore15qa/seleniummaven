package stepdefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SharedClassDriver {

    public static WebDriver driver;
    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver","C:\\seleniummaven\\newcucumberproject\\src\\driver\\chromedriver.exe");
                driver = new ChromeDriver();
                driver.get("https://app-dev.innerkraft.com/");
                String actUrl = driver.getCurrentUrl();
                assertEquals("https://app-dev.innerkraft.com/", actUrl);
        }
        return driver;
        
    }
     public static WebDriver closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        return driver;
    }

    
}
