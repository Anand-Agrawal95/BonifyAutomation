package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    private By emailInput = By.id("loginId");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        sendKeys(emailInput, email);
        sendKeys(passwordInput, password);
        WebElement loginBtn = waitForClickable(loginButton);
        jsClick(loginBtn);
    }

    public void validateLoginSuccessfulAfterRegistration() {
        wait.until(driver -> driver.getCurrentUrl().contains("authorize") || driver.getCurrentUrl().contains("kyc"));
        String currentUrl = driver.getCurrentUrl();
        assert (currentUrl.contains("authorize") || currentUrl.contains("kyc")) :
                "Login failed - URL does not contain 'authorize' or 'kyc'. Current URL: " + currentUrl;
    }

    public void validateLoginSuccessful() {
        waitForDashboard();
        assert driver.getCurrentUrl().contains("dashboard") : "Login failed - Dashboard not visible";
    }

    public void waitForDashboard() {
        wait.until(ExpectedConditions.urlContains("dashboard"));
    }

    public void handle2FAPageIfAppearsAndLogout() {

        // Wait for 5-6 seconds to see if the 2FA page appears
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

        try {
            // Check if the URL contains 'enable-mfa' within 6 seconds
            wait.until(driver -> driver.getCurrentUrl().contains("enable-mfa"));

            // If the URL contains 'enable-mfa', proceed to handle the 2FA page
            WebElement postponeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-testid='mfa-action-postpone']")));
            postponeButton.click();
            System.out.println("2FA page appeared, clicked 'Später aktivieren' to postpone.");
            proceedWithLogout();
        } catch (TimeoutException e) {
            // If the 2FA page does not appear within 6 seconds, proceed with logout
            System.out.println("No 2FA page appeared, proceeding with logout.");
            proceedWithLogout();
        }
    }

    public void proceedWithLogout() {
        HomePage homePage = new HomePage(driver);
        homePage.hoverAndLogout();
    }
}
