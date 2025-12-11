package stepdefinitions;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class PsychologistmngtSteps {
    WebDriver driver = DriveManager.getDriver();
    WebDriverWait wait = DriveManager.getWait();

    @And("user navigates to the Psychologist Management screen")
    public void user_navigates_to_the_Psychologist_Management_screen() {
        driver.get("https://app-dev.innerkraft.com/user-access-management/psychologist");
        WebDriverWait wait = DriveManager.getWait();
        wait.until(ExpectedConditions.urlContains("psychologist"));
    }

    @When("user navigates to the Psychologist User Creation Screen")
    public void user_navigates_to_the_Psychologist_User_Creation_Screen() throws InterruptedException {
        Thread.sleep(2000);
        WebElement addPsychologistbtn = driver.findElement(By.xpath("//button[text()='Add psychologist']"));
        addPsychologistbtn.click();
        WebDriverWait wait = DriveManager.getWait();
        wait.until(ExpectedConditions.urlContains("psychologist/create"));
    }

    @And("user fills in the psychologist user details")
    public void user_fills_in_the_psychologist_user_details(io.cucumber.datatable.DataTable dataTable)throws InterruptedException {
     DriveManager driveManager = new DriveManager();
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        driveManager.enterPsychologistName(data.get("User Name"));
        System.out.println("Entered Psychologist Name: " + data.get("User Name"));
    



    }
    
}
