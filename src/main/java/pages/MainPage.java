package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By addTaskButton = By.xpath("//button[contains(text(),'Add task')]");
    private By cancelTaskButton = By.xpath("//button[@aria-label='Cancel']");
    private By taskTitle = By.xpath("//div[@aria-label='Task name']");
    private By taskDesc = By.xpath("");
    private By priorityButton = By.xpath("//div[@data-action-hint='task-actions-priority-picker']");
    private By priorityList = By.xpath("//ul[@aria-label='Select a priority']/li");
    private By priorityOptionLevel1 = By.cssSelector("li[aria-label='Priority 1']");
    private By priorityOptionLevel4 = By.cssSelector("li[aria-label='Priority 4']");
    private By confirmAddTaskButton = By.cssSelector("button[data-testid='task-editor-submit-button']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    /**
     * Click "Add task" button to open new task dialog
     */
    public void clickAddTaskButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addTaskButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
    }

    /**
     * Click "Cancel task" button to close task dialog
     */
    public void clickCancelTaskButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(cancelTaskButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
    }

    /**
     * Enter task content text
     * @param taskContent Task title
     */
    public void enterTaskContent(String taskContent) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(taskTitle));
        input.click();
        input.clear();
        input.sendKeys(taskContent);
    }

    /**
     * Select priority level for the task.
     * Todoist priority levels: 4 (lowest), 3, 2, 1 (highest)
     * @param level Priority level between 1 and 4
     */
    public void selectPriority(int level) {
        wait.until(ExpectedConditions.elementToBeClickable(priorityButton)).click();

        By optionSelector;
        switch (level) {
            case 1:
                optionSelector = priorityOptionLevel1;
                break;
            case 4:
                optionSelector = priorityOptionLevel4;
                break;
            default:
                throw new IllegalArgumentException("Unsupported priority level: " + level);
        }

        wait.until(ExpectedConditions.elementToBeClickable(optionSelector)).click();
    }

    /**
     * Click the confirm button to add the task
     */
    public void confirmAddTask() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(confirmAddTaskButton));
        button.click();
    }

    /**
     * Verify that the task with given content is present in the task list
     * @param taskContent Task title to verify
     * @return true if task is found; false otherwise
     */
    public boolean isTaskPresent(String taskContent) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + taskContent + "')]")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
