import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.LoginPage;

import java.time.Duration;

public class LoginTests {

    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        // Set path to your chromedriver executable
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver-win64/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void successLogin() {
        loginPage.open();
        loginPage.login("sunny122sh@gmail.com","Task@123456");

        // Wait for main page loading by waiting for "Add task" button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Add task')]")));
    }

    @Test
    public void invalidUsername() {
        loginPage.open();
        loginPage.login("sunny@gmail","Task@123456");

        // Wait for error message to be displayed
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getInvalidUserError()));
    }

    @Test
    public void invalidPassword() {
        loginPage.open();
        loginPage.login("sunny122sh@gmail.com","Task@@12345678");

        // Wait for error message to be displayed
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getInvalidPasswordError()));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

