import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TodoistTests {

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
    public void testCreateNewTask() {
        login();

        // Wait for main page loading by waiting for "Add task" button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Add task')]")));

        // Click the "Add task" button to open new task dialog
        WebElement addTaskButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='Add task']")));
        addTaskButton.click();

        // Wait for the task input area to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-label='New task content']")));

        // Locate the new task input field and enter task content
        WebElement taskInput = driver.findElement(By.cssSelector("div[aria-label='New task content']"));
        taskInput.click();
        taskInput.sendKeys("Selenium automation task");

        // Click the confirm "Add task" button inside the dialog to save the task
        WebElement confirmAddTaskButton = driver.findElement(By.cssSelector("button[aria-label='Add task']"));
        confirmAddTaskButton.click();

        // Wait for task to appear in the list
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Selenium automation task')]")));

        // Verify task is added by locating it in the task list
        boolean taskAdded = driver.findElements(By.xpath("//div[contains(text(),'Selenium automation task')]")).size() > 0;

        Assert.assertTrue(taskAdded, "Task was not created successfully!");
    }

    private void login() {
        // Navigate to Todoist login page
        driver.get("https://todoist.com/users/showLogin");

        // Wait for login email input to be visible and enter credentials
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("element-0")));

        // Enter email
        WebElement emailInput = driver.findElement(By.id("element-0"));
        emailInput.sendKeys("sunny122sh@gmail.com");  // Replace with valid email

        // Enter password
        WebElement passwordInput = driver.findElement(By.id("element-2"));
        passwordInput.sendKeys("Task@123456");  // Replace with valid password

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

