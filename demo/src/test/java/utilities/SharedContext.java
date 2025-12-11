package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SharedContext {

    public static String createdFirstName;
    public static String createdUserId;
    public static WebElement searchBox;

     public static final By searchBoxLocator = By.xpath("//input[@placeholder='Search by name / Id']");
}
