package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class HomePage extends BasePage {
    private final String url = "https://www.bonify.de/";
    By profileIcon = By.cssSelector("a[data-testid='MenuTab-5']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(url);
        waitForVisibility(By.cssSelector("a[data-testid='login-button']"));
    }

    public void clickLoginButton() {
        WebElement loginButton = waitForClickable(By.cssSelector("a[data-testid='login-button']"));
        jsClick(loginButton);
        System.out.println("✅ Clicked login successfully");
    }

    public void clickRegisterButton() {
        WebElement registerButton = waitForClickable(By.cssSelector("a[data-testid='register-button']"));
        jsClick(registerButton);
        System.out.println("✅ Clicked register successfully");
    }

    public void hoverAndLogout() {
        try {
            handleCookiePopupIfPresent();
            // Locate the MenuTab element (hover over it)
            WebElement menuTab = driver.findElement(profileIcon);

            // Create an Actions object
            Actions actions = new Actions(driver);

            // Hover over the MenuTab element
            actions.moveToElement(menuTab).perform();

            // Wait for the logout link to be visible and clickable
            WebElement logoutButton = driver.findElement(By.cssSelector("a[data-testid='MenuList-5-5']"));

            // Click the logout button
            jsClick(logoutButton);
            System.out.println("✅ Successfully hovered and clicked 'Ausloggen'.");
            wait.until(ExpectedConditions.urlContains("logout"));
            Assert.assertTrue(driver.getCurrentUrl().contains("logout"), "Logout failed");
        } catch (Exception e) {
            System.out.println("❌ Failed to hover and click logout button: " + e.getMessage());
        }
    }
}
