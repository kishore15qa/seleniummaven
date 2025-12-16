package stepdefinition;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PsychologistUserCRUD {
    WebDriver driver;

    public PsychologistUserCRUD(SharedClassDriver SharedClass) {
        driver = SharedClassDriver.getDriver();
    }
    // @Given("logging in as psych admin user")
    // // public void logging_in_as_psych_admin_user2() {
    // SharedClassDriver.psaLogin();
    // }

    @When("user is on the psychologist user management page")
    public void when_user_is_on_the_psychologist_user_management_page() {
        driver.get("https://app-dev.innerkraft.com/user-access-management/psychologist");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add psychologist')]")));
        String currenturlstring = driver.getCurrentUrl();
        assertEquals("https://app-dev.innerkraft.com/user-access-management/psychologist", currenturlstring);
    }

    @And("user clicks Add psychologist button")
    public void user_clicks_add_psychologist_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add psychologist')]"))).click();
    }

    @And("user fills in the psychologist details")
    public void user_fills_in_the_psychologist_details() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys("TestPsychologist user");
        // driver.findElement(By.name("name")).sendKeys("TestPsychologist user");
        Select gender = new Select(driver.findElement(By.name("psychologist_admin_detail_attributes.gender")));
        gender.selectByContainsVisibleText("Male");
        driver.findElement(By.xpath("//input[@name='psychologist_admin_detail_attributes.dob']" +
                "/ancestor::div[contains(@class,'react-date-picker')]" +
                "//input[contains(@class,'inputGroup__month')]")).sendKeys("01");
        driver.findElement(By.xpath("//input[@name='psychologist_admin_detail_attributes.dob']" +
                "/ancestor::div[contains(@class,'react-date-picker')]" +
                "//input[contains(@class,'inputGroup__day')]")).sendKeys("01");
        driver.findElement(By.xpath("//input[@name='psychologist_admin_detail_attributes.dob']" +
                "/ancestor::div[contains(@class,'react-date-picker')]" +
                "//input[contains(@class,'inputGroup__year')]")).sendKeys("1990");
        driver.findElement(By.name("email")).sendKeys("john.doe@example.com");
        driver.findElement(By.name("phone_number")).sendKeys("9234567890");
        Select pronoun = new Select(driver.findElement(By.name("psychologist_admin_detail_attributes.pronounce")));
        pronoun.selectByContainsVisibleText("He, Him");
        driver.findElement(By.name("psychologist_admin_detail_attributes.education")).sendKeys("PhD in Psychology");
        driver.findElement(By.name("psychologist_admin_detail_attributes.residence"))
                .sendKeys("123 Main St, City, Country");
        driver.findElement(By.xpath("//input[@name='psychologist_admin_detail_attributes.doj']" +
                "/ancestor::div[contains(@class,'react-date-picker')]" +
                "//input[contains(@class,'inputGroup__month')]")).sendKeys("01");
        driver.findElement(By.xpath("//input[@name='psychologist_admin_detail_attributes.doj']" +
                "/ancestor::div[contains(@class,'react-date-picker')]" +
                "//input[contains(@class,'inputGroup__day')]")).sendKeys("01");
        driver.findElement(By.xpath("//input[@name='psychologist_admin_detail_attributes.doj']" +
                "/ancestor::div[contains(@class,'react-date-picker')]" +
                "//input[contains(@class,'inputGroup__year')]")).sendKeys("2005");
        driver.findElement(By.name("psychologist_admin_detail_attributes.experience")).sendKeys("5 years");
        Actions actions = new Actions(driver);
        driver.findElement(By.id("react-select-2-input")).sendKeys("English");
        actions.sendKeys(Keys.ENTER).perform();
        driver.findElement(By.name("psychologist_admin_detail_attributes.total_clients_attended")).sendKeys("50");
        Select expertise = new Select(driver.findElement(By.name("psychologist_admin_detail_attributes.expertise")));
        expertise.selectByContainsVisibleText("Developmental psychologist");

        actions.sendKeys(Keys.ENTER).perform();
        driver.findElement(By.id("react-select-3-input")).sendKeys("Parents");
        actions.sendKeys(Keys.ENTER).perform();
        driver.findElement(By.id("react-select-4-input")).sendKeys("Addiction Counseling");
        actions.sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000); // Adding a short wait to ensure the field is ready for input
        driver.findElement(By.id("react-select-5-input")).sendKeys("Behavioral Addiction Counseling");
        actions.sendKeys(Keys.ENTER).perform();

    }

    @And("user clicks Add button")
    public void user_clicks_add_button() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add')]"))).click();
    }

    @Then("user should be redirected to the psychologist user list page")
    public void user_should_be_redirected_to_the_psychologist_user_list_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'The Psychologist profile is created successfully')]")));
        String toaster = driver.findElement(By.xpath("//div[contains(text(),'The Psychologist profile is created successfully')]")).getText();
        assertEquals("The Psychologist profile is created successfully", toaster);
    }
    @And("user searches for the psychologist by name")
    public void user_searches_for_the_psychologist_by_name() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search by name...!']"))).sendKeys("TestPsychologist user");
        Thread.sleep(2000);
    }
    @And("user edits the psychologist details")
    public void user_edits_the_psychologist_details() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[.//span[normalize-space()='Edit']]"))).click();
        Thread.sleep(2000);
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys("UpdatedPsychologist user");
        driver.findElement(By.xpath("//button[contains(text(),'Update')]")).click();
    }
    @Then("user should see the success toaster in the psychologist list page")
    public void user_should_see_the_updated_psychologist_details_in_the_list() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Psychologist status updated')]")));
        String toaster = driver.findElement(By.xpath("//div[contains(text(),'Psychologist status updated')]")).getText();
        assertEquals("Psychologist status updated", toaster);
    }
    @And("user deletes the psychologist")
    public void user_deletes_the_psychologist() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[.//span[normalize-space()='Delete']]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Delete')]"))).click();
    }
    @Then("user should see the delete success toaster in the psychologist list page")
    public void user_should_see_the_delete_success_toaster_in_the_psychologist_list_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Psychologist deleted successfully')]")));
        String toaster = driver.findElement(By.xpath("//div[contains(text(),'Psychologist deleted successfully')]")).getText();
        assertEquals("Psychologist deleted successfully", toaster);
    }
}