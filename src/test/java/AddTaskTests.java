import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MainPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.Duration;

public class AddTaskTests {

    private WebDriver driver;
    private MainPage mainPage;
    private ExtentReports extent;
    private ExtentTest test;

    private final String TODOIST_EMAIL = "sunny122sh@gmail.com";
    private final String TODOIST_PASSWORD = "Task@123456";

    @BeforeClass
    public void setUp() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("AddTaskTestReports.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver-win64/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);

        loginPage.open();
        loginPage.login(TODOIST_EMAIL, TODOIST_PASSWORD);
    }

    @Test(description = "Add Task with Valid Input")
    public void testAddTaskWithValidInput(){
        test = extent.createTest("Add Task with Valid Input");
        String taskTitle = "Task with valid input " + System.currentTimeMillis();

        try {
            mainPage.clickAddTaskButton();
            mainPage.enterTaskContent(taskTitle);
            mainPage.confirmAddTask();

            // Wait a short time for the task to appear
            Thread.sleep(3000);

            Assert.assertTrue(mainPage.isTaskPresent(taskTitle), "Task was not added successfully.");
            mainPage.clickCancelTaskButton();
            test.pass("Task added successfully: " + taskTitle);
        } catch (Exception e) {
            test.fail("Test failed due to: " + e.getMessage());
        }
    }

    @Test(description = "Add Task with Priority Level")
    public void testAddTaskWithPriorityLevel() {
        test = extent.createTest("Add Task with Priority Level");
        String taskTitle = "Task with priority level 1 " + System.currentTimeMillis();

        try {
            mainPage.clickAddTaskButton();
            mainPage.enterTaskContent(taskTitle);
            mainPage.selectPriority(1);
            mainPage.confirmAddTask();

            // Wait a short time for the task to appear
            Thread.sleep(3000);

            Assert.assertTrue(mainPage.isTaskPresent(taskTitle), "Priority task was not added successfully.");
            mainPage.clickCancelTaskButton();
            test.pass("Priority task added successfully: " + taskTitle);
        } catch (Exception e) {
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
