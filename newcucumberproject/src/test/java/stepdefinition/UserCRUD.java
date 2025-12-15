package stepdefinition;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserCRUD {
    WebDriver driver;
    public UserCRUD(SharedClassDriver SharedClass) {
        driver = SharedClassDriver.getDriver();
    }
    @Given("logging in as psych admin user")
    public void logging_in_as_psych_admin_user() {
        SharedClassDriver.psaLogin();
    }

    @When("user is on the user creation page")
    public void user_is_on_the_user_creation_page() {
        driver.get("https://app-dev.innerkraft.com/user-access-management/student");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Students')]")));
        String currenturlstring = driver.getCurrentUrl();
        assertEquals("https://app-dev.innerkraft.com/user-access-management/student", currenturlstring);
     }
     @And("user clicks Add student button")
     public void user_clicks_add_student_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add student')]"))).click();
     }
     @And("user fills in the student details")
     public void user_fills_in_the_student_details(){
        driver.findElement(org.openqa.selenium.By.name("first_name")).sendKeys("TestFirstName");
        driver.findElement(org.openqa.selenium.By.name("last_name")).sendKeys("TestLastName");
        Select orgindrpn = new Select(driver.findElement(By.name("student_origin")));
        orgindrpn.selectByVisibleText("B2C students");
        driver.findElement(org.openqa.selenium.By.name("email")).sendKeys("test@example.com");
     }
     @And("user clicks Create button")
     public void user_clicks_create_button() {
        driver.findElement(org.openqa.selenium.By.xpath("//button[contains(text(),'Create')]")).click();
     }
     @Then("user should redirected to the student list page")
     public void user_should_redirected_to_the_student_list_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'You have successfully added a new user')]")));
        String successmsg = driver.findElement(By.xpath("//div[contains(text(),'You have successfully added a new user')]")).getText();
        assertEquals("You have successfully added a new user", successmsg);
     }
    @And("user searches for the student by name") 
    public void user_searches_for_the_student_by_name(){
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys("TestFirstName");
    }
    @And("user edits the student details")
    public void user_edits_the_student_details(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[.//span[normalize-space()='Edit']]"))).click();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.name("first_name")));
        driver.findElement(org.openqa.selenium.By.name("first_name")).clear();
        driver.findElement(org.openqa.selenium.By.name("first_name")).sendKeys("UpdatedFirstName");
        driver.findElement(org.openqa.selenium.By.xpath("//button[contains(text(),'Update')]")).click();
    }
    @Then("user should see the updated student details in the list")
    public void user_should_see_the_updated_student_details_in_the_list() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Updated successfully')]")));
        String updatemsg = driver.findElement(By.xpath("//div[contains(text(),'Updated successfully')]")).getText();
        assertEquals("Updated successfully", updatemsg);
    }
    @And("user deletes the student")
    public void user_deletes_the_student() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[.//span[normalize-space()='Delete']]"))).click();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Delete')]"))).click();
    }
    @Then("user should not see the student in the list anymore")
    public void user_should_not_see_the_student_on_the_list() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'User has been successfully deleted')]")));
        String deletemsg = driver.findElement(By.xpath("//div[contains(text(),'User has been successfully deleted')]")).getText();
        assertEquals("User has been successfully deleted", deletemsg);
    }
}