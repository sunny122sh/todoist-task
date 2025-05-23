import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.Duration;

public class LoginTests {

    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setUp() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("LoginTestReports.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver-win64/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test(description = "Login with valid credentials")
    public void successLogin() {
        test = extent.createTest("Login with valid credentials");
        try {
            loginPage.open();
            loginPage.login("sunny122sh@gmail.com","Task@123456");

            // Wait for main page loading by waiting for "Add task" button
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Add task')]")));
            test.pass("Login successfull.");
        } catch (Exception e) {
            test.fail("Test failed due to: " + e.getMessage());
        }

    }

    @Test(description = "Login with invalid username")
    public void invalidUsername() {
        test = extent.createTest("Login with invalid username");
        try {
            loginPage.open();
            loginPage.login("sunny@gmail","Task@123456");

            // Wait for error message to be displayed
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getInvalidUserError()));
            test.pass("Correct error message with invalid username.");
        }
        catch (Exception e) {
            test.fail("Test failed due to: " + e.getMessage());
        }
    }

    @Test(description = "Login with invalid password")
    public void invalidPassword() {
        test = extent.createTest("Login with invalid password");
        try {
            loginPage.open();
            loginPage.login("sunny122sh@gmail.com","Task@@12345678");

            // Wait for error message to be displayed
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.getInvalidPasswordError()));
            test.pass("Correct error message with invalid password.");
        }
        catch (Exception e) {
            test.fail("Test failed due to: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            extent.flush();
        }
    }
}

