import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Set path to your chromedriver executable
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver-win64/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void successLogin() {
        login("sunny122sh@gmail.com","Task@123456");

        // Wait for main page loading by waiting for "Add task" button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Add task')]")));
    }

    @Test
    public void invalidUsername() {
        login("sunny@gmail","Task@123456");

        // Wait for error message to be displayed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Please enter a valid email address.')]")));
    }

    @Test
    public void invalidPassword() {
        login("sunny122sh@gmail.com","Task@@12345678");

        // Wait for error message to be displayed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Wrong email or password.')]")));
    }

    private void login(String userName, String password) {
        // Navigate to Todoist login page
        driver.get("https://todoist.com/users/showLogin");

        // Wait for login email input to be visible and enter credentials
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("element-0")));

        // Enter email
        WebElement emailInput = driver.findElement(By.id("element-0"));
        emailInput.sendKeys(userName);

        // Enter password
        WebElement passwordInput = driver.findElement(By.id("element-2"));
        passwordInput.sendKeys(password);

        // Click login button
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

