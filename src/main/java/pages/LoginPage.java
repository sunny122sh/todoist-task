package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By emailInput = By.id("element-0");
    private By passwordInput = By.id("element-2");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By invalidUserError = By.xpath("//div[contains(text(),'Please enter a valid email address.')]");
    private By invalidPasswordError = By.xpath("//div[contains(text(),'Wrong email or password.')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void open() {
        driver.get("https://todoist.com/users/showLogin");
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public By getInvalidUserError() {
        return invalidUserError;
    }

    public By getInvalidPasswordError() {
        return invalidPasswordError;
    }
}
