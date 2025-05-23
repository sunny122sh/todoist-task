import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MainPage;

import java.time.Duration;

public class AddTaskTests {

    private WebDriver driver;
    private MainPage mainPage;

    private final String TODOIST_EMAIL = "sunny122sh@gmail.com";
    private final String TODOIST_PASSWORD = "Task@123456";

    @BeforeClass
    public void setUp() {
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
    public void testAddTaskWithValidInput() throws InterruptedException {
        String taskTitle = "Task with valid input " + System.currentTimeMillis();

        mainPage.clickAddTaskButton();
        mainPage.enterTaskContent(taskTitle);
        mainPage.confirmAddTask();

        // Wait a short time for the task to appear
        Thread.sleep(3000);

        Assert.assertTrue(mainPage.isTaskPresent(taskTitle), "Task was not added successfully.");
        mainPage.clickCancelTaskButton();
    }

    @Test(description = "Add Task with Priority Level")
    public void testAddTaskWithPriorityLevel() throws InterruptedException {
        String taskTitle = "Task with priority level 1 " + System.currentTimeMillis();

        mainPage.clickAddTaskButton();
        mainPage.enterTaskContent(taskTitle);
        mainPage.selectPriority(1);
        mainPage.confirmAddTask();

        // Wait a short time for the task to appear
        Thread.sleep(3000);

        Assert.assertTrue(mainPage.isTaskPresent(taskTitle), "Priority task was not added successfully.");
        mainPage.clickCancelTaskButton();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
