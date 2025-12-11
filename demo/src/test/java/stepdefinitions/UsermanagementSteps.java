package stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.SharedContext;

public class UsermanagementSteps {

    WebDriver driver = DriveManager.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("user launch the browser and logs into the application")
    public void user_launch_the_browser_and_logs_into_the_application() {
        WebDriver driver = DriveManager.getDriver();
        WebDriverWait wait = DriveManager.getWait();
        driver.get("https://app-dev.innerkraft.com/");
        // Wait for Email field to appear before interacting
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("email")));
        emailField.sendKeys("karthi.s@spritle.com");

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("Password$5000");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Login')]")));
        loginButton.click();

        // Optional: wait until dashboard or home element is visible
        wait.until(ExpectedConditions.urlContains("/dashboard"));
        System.out.println("✅ Logged in successfully!");
    }

    @And("user navigates to the User Management screen")
    public void user_navigates_to_the_user_management_screen() {
        driver.get("https://app-dev.innerkraft.com/user-access-management/student?page=1");
        WebDriverWait wait = DriveManager.getWait();
        wait.until(ExpectedConditions.urlContains("student"));
    }

    @When("user navigates to the User Creation Popup")
    public void user_navigates_to_the_user_creation_page() throws InterruptedException {
        Thread.sleep(10000);
        WebElement addButton = driver.findElement(By.xpath(
                "//button[contains(normalize-space(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')), 'add student')]"));
        addButton.click();
    }

    @When("user enters first name as {string}")
    public void user_enters_first_name_as(String firstName) throws InterruptedException {
        Thread.sleep(2000);
        WebElement firstNameField = driver.findElement(By.name("first_name"));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        SharedContext.createdFirstName = firstName;
    }

    @And("user enters last name as {string}")
    public void user_enters_last_name_as(String lastName) {
        WebElement lastNameField = driver.findElement(By.name("last_name"));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    @And("user selects origin as {string}")
    public void user_selects_origin_as(String origin) {
        // Locate the dropdown element
        Select originDropdown = new Select(driver.findElement(By.name("student_origin"))); // replace with actual
                                                                                           // locator

        // Select the option by visible text
        originDropdown.selectByVisibleText(origin);
    }

    @And("user enters roll number as {string}")
    public void user_enters_roll_number_as(String rollNumber) {
        WebElement rollNumberField = driver.findElement(By.name("roll_number"));
        rollNumberField.clear();
        rollNumberField.sendKeys(rollNumber);
    }

    @And("user selects college option and search college name as {string} and user selects the checkbox for college named as {string}")
    public void user_selects_college_option_and_search_college_name_as(String collegeName, String collegeCheckboxName)
            throws InterruptedException {
        WebElement selectcollege = driver.findElement(By.name("college_id"));
        selectcollege.click();
        WebElement collegeSearchBox = driver
                .findElement(By.xpath("//input[@type='search' and @placeholder='Search by name...!']"));
        collegeSearchBox.sendKeys(collegeName);
        WebElement collegeCheckbox = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//td[normalize-space(text())='"
                        + collegeCheckboxName + "']/following-sibling::td//input[@type='checkbox']")));
        collegeCheckbox.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement selectCollege = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/dialog[2]/div/div[3]/div/button")));
        Thread.sleep(3000);
        selectCollege.click();
    }

    @And("user enters email as {string}")
    public void user_enters_email_as(String email) {
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    @And("user clicks on Create button")
    public void user_clicks_on_create_button() {
        WebElement createButton = driver.findElement(By.xpath("//button[normalize-space(text())='Create']"));
        createButton.click();
    }

    @Then("new student should be created successfully with toaster message {string}")
    public void new_student_should_be_created_successfully(String expectedMsg) throws InterruptedException {
        WebDriver driver = DriveManager.getDriver();
        WebElement toaster = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(text(),'You have successfully added a new user')]")));
        System.out.println("Toaster text: " + toaster.getText());
        String actualMsg = toaster.getText().trim();
        assertTrue(actualMsg.contains(expectedMsg), "Toaster message mismatch!" + actualMsg);
        Thread.sleep(2000);
        String firstName = SharedContext.createdFirstName;
        SharedContext.searchBox = driver.findElement(SharedContext.searchBoxLocator);
        System.out.println("Search field found? " + SharedContext.searchBox.isDisplayed());
        System.out.println("Search field enabled? " + SharedContext.searchBox.isEnabled());
        System.out.println("Search field value: " + SharedContext.searchBox.getAttribute("value"));
        SharedContext.searchBox.clear();
        SharedContext.searchBox.sendKeys(firstName);
        Thread.sleep(2000);
        WebElement userIDfield = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//td[starts-with(normalize-space(text()),'IK')]")));
        String userId = userIDfield.getText().trim();
        SharedContext.createdUserId = userId;
        System.out.println("Created User ID: " + userId);

    }

    @Then("error message for email already exist should be displayed as {string}")
    public void error_message_for_email_already_exists_should_be_displayed_as(String expectedMsg) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMessage = wait.until(ExpectedConditions
                .visibilityOfElementLocated(
                        By.xpath("//div[@data-title and contains(normalize-space(.), '" + expectedMsg + "')]")));
        System.out.println("Actual toaster text: " + errorMessage.getText());
        Assert.assertTrue(errorMessage.getText().contains(expectedMsg));
    }

    @When("user searches for the new created user")
    public void user_searches_for_the_user_with_first_name() {
        WebDriver driver = DriveManager.getDriver();
        WebDriverWait wait = DriveManager.getWait();
        try {
            // Try using the existing WebElement
            if (SharedContext.searchBox == null || !SharedContext.searchBox.isDisplayed()) {
                SharedContext.searchBox = driver.findElement(SharedContext.searchBoxLocator);
            }
        } catch (Exception e) {
            // Re-find the element if stale or null
            SharedContext.searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(SharedContext.searchBoxLocator));
        }

        // Use it
        SharedContext.searchBox.clear();
        SharedContext.searchBox.sendKeys(SharedContext.createdUserId);
        System.out.println("Entered User ID: " + SharedContext.createdUserId);

    }

    @And("user updates first name to {string}")
    public void user_updates_first_name_to(String newFirstName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement editButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//label[.//span[normalize-space(text())='Edit']]")));
        editButton.click();
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("first_name")));
        firstNameField.clear();
        firstNameField.sendKeys(newFirstName);
    }

    @And("user updates last name to {string}")
    public void user_updates_last_name_to(String newLastName) {
        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("last_name")));
        lastNameField.clear();
        lastNameField.sendKeys(newLastName);
    }

    @And("user clicks on Update button")
    public void user_clicks_on_update_button() {
        WebElement updateButton = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[@type='submit' and normalize-space(text())='Update']")));
        updateButton.click();
    }

    @Then("student should be updated successfully with toaster message {string}")
    public void user_details_should_be_updated_successfully(String expectedMsg) {
        WebElement toaster2 = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(text(),'Updated successfully')]")));
        String actualMsg = toaster2.getText().trim();
        assertTrue(actualMsg.contains(expectedMsg), "Toaster message mismatch!" + actualMsg);
    }

    @And("user updates email to {string}")
    public void user_updates_email_to(String newEmail) {
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        emailField.clear();
        emailField.sendKeys(newEmail);
    }

    @And("user clicks on Delete button")
    public void user_clicks_on_delete_button() {
        WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[.//span[normalize-space(text())='Delete']]")));
        deleteButton.click();
        WebElement confirmDeleteButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space(text())='Delete']")));
        confirmDeleteButton.click();

    }

    @Then("student should be deleted successfully with toaster message {string}")
    public void user_should_be_deleted_successfully(String expectedMsg) {
        WebElement toaster = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[contains(text(),'User has been successfully deleted')]")));
        String actualMsg = toaster.getText().trim();
        assertTrue(actualMsg.contains(expectedMsg), "Toaster message mismatch!" + actualMsg);
        driver.quit();
    }
}
