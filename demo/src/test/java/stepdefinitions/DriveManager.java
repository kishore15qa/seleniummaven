package stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriveManager {
    private static WebDriver driver;
    private static WebDriverWait wait;

    DriveManager() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\seleniummaven\\demo\\src\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return wait;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }

    public static byte[] getDriverTakesScreenshot() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDriverTakesScreenshot'");
    }
     public void enterPsychologistName(String name) {
        WebElement nameField = driver.findElement(By.name("name"));
        nameField.sendKeys(name);
    }
    public void selectGender(String gender) {
        WebElement genderSelect = driver.findElement(By.name("psychologist_admin_detail_attributes.gender"));
        genderSelect.sendKeys(gender);
    }

    public void enterDateOfBirth(String month, String day, String year) {
        driver.findElement(By.name("month")).sendKeys(month);
        driver.findElement(By.name("day")).sendKeys(day);
        driver.findElement(By.name("year")).sendKeys(year);
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys(email);
    }

    public void enterMobileNumber(String mobile) {
        WebElement mobileField = driver.findElement(By.name("phone_number"));
        mobileField.sendKeys(mobile);
    }

    public void selectPronouns(String pronouns) {
        WebElement pronounsSelect = driver.findElement(By.name("psychologist_admin_detail_attributes.pronounce"));
        pronounsSelect.sendKeys(pronouns);
    }

    public void enterEducation(String education) {
        WebElement educationField = driver.findElement(By.name("psychologist_admin_detail_attributes.education"));
        educationField.sendKeys(education);
    }

    public void enterResidence(String residence) {
        WebElement residenceField = driver.findElement(By.name("psychologist_admin_detail_attributes.residence"));
        residenceField.sendKeys(residence);
    }

    public void enterDateOfJoining(String month, String day, String year) {
        driver.findElement(By.name("month")).sendKeys(month);
        driver.findElement(By.name("day")).sendKeys(day);
        driver.findElement(By.name("year")).sendKeys(year);
    }

    public void enterExperience(String experience) {
        WebElement experienceField = driver.findElement(By.name("psychologist_admin_detail_attributes.experience"));
        experienceField.sendKeys(experience);
    }

    public void selectLanguage(String language) {
        WebElement languageSelect = driver.findElement(By.name("psychologist_admin_detail_attributes.language"));
        languageSelect.sendKeys(language);
    }

    public void enterTotalClients(String totalClients) {
        WebElement totalClientsField = driver.findElement(By.name("psychologist_admin_detail_attributes.total_clients_attended"));
        totalClientsField.sendKeys(totalClients);
    }

    public void selectExpertise(String expertise) {
        WebElement expertiseSelect = driver.findElement(By.name("psychologist_admin_detail_attributes.expertise"));
        expertiseSelect.sendKeys(expertise);
    }

    public void selectRelationshipsComfortableWith(String relationships) {
        WebElement relationshipsSelect = driver.findElement(By.name("psychologist_admin_detail_attributes.relationships"));
        relationshipsSelect.sendKeys(relationships);
    }

    public void selectDomains(String domains) {
        WebElement domainsSelect = driver.findElement(By.name("psychologist_admin_detail_attributes.domains"));
        domainsSelect.sendKeys(domains);
    }

    public void uploadAvatar(String filePath) {
        WebElement uploadButton = driver.findElement(By.id("attachment"));
        uploadButton.sendKeys(filePath);
    }

    public void submitForm() {
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
        submitButton.click();
    }

}
