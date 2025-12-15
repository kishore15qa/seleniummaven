package stepdefinition;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SharedClassDriver {

    public static WebDriver driver;
    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver","C:\\seleniummaven\\newcucumberproject\\src\\driver\\chromedriver.exe");
                driver = new ChromeDriver();
                driver.get("https://app-dev.innerkraft.com/");
        }
        return driver;
        
    }
    public static void psaLogin() { 
        driver.get("https://app-dev.innerkraft.com/");
         driver.findElement(By.name("email")).sendKeys("kishore.r+psychadmin2@spritle.com");
        driver.findElement(By.name("password")).sendKeys("Password$5000");
        driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));
        assertEquals("https://app-dev.innerkraft.com/dashboard", driver.getCurrentUrl());

    }
     public static WebDriver closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        return driver;
    }

    
}
