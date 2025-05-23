# todoist-task

This project contains Selenium-based automated tests for few of the Todoist application features, implemented in Java with TestNG and Maven for build and dependency management.

## Project Structure

- **src/main/resources/chromedriver-win64/chromedriver.exe**: Contains the ChromeDriver binary used for browser automation.
- **src/test/java/pages/**: Contains POM (Page Object Model) classes representing different pages of the application and encapsulating page actions.
- **src/test/java/tests/**: Contains TestNG test classes that execute the automated tests.
- **pom.xml**: Maven configuration file managing dependencies and build process.
- **src/test/Resources/testng.xml**: Configuration file to define TestNG test suites and test classes to execute.
- **README.md**: Documentation and usage instructions for the project.

---

## Prerequisites

- Java JDK 11 or higher installed on your machine.
- [Maven](https://maven.apache.org/install.html) installed and configured in your PATH.
- Google Chrome browser installed.
- ChromeDriver executable compatible with your Chrome version placed in `src/main/resources/chromedriver-win64/chromedriver.exe`.

## Setup Instructions

1. **Clone the repository**

   ```bash
   git clone https://github.com/sunny122sh/todoist-task
   cd <repository-folder>
   ```

2. **Update ChromeDriver path**

   Ensure your test class sets the ChromeDriver system property with the relative path:

   ```java
   System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver-win64/chromedriver.exe");
   ```

3. **Configure your credentials**

   In the test class, replace placeholder email and password with valid Todoist login credentials:

   ```java
   emailInput.sendKeys("your-email@example.com");
   passwordInput.sendKeys("your-password");
   ```

## Running Tests

- Open the project in IDE.
- Right click on testng.xml > Run

## Test Details

- **Test Class**: `LoginTests`
- **Description**: Validates the login functionality of the Todoist web application including:
  - Successful login with valid credentials.
  - Login error with invalid username.
  - Login error with invalid password.

- **Test Class**: `AddTaskTests`
- **Description**: Validates the task addition feature of the Todoist web application including:
  - Successful task addition with a valid input.
  - Successful task addition with priority.    

## Troubleshooting

- **Element Click Intercepted or Element Not Clickable**  
  If you encounter exceptions like `ElementClickInterceptedException`, try:
  - Updating ChromeDriver to match your Chrome browser version.
  - Ensuring no overlays or popups are obstructing elements.
  - Adding explicit waits before interactions.

- **Driver Version Mismatch**  
  Make sure the ChromeDriver executable version matches your installed Chrome browser version.

## Dependencies

Key dependencies managed via Maven (`pom.xml`):

- Selenium Java
- TestNG
- extentreports

## Notes

- This project uses Maven to streamline build and dependency management.
- Ensure your ChromeDriver executable has executable permissions.
- Always keep dependencies up to date to avoid compatibility issues.

```
