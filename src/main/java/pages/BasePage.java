package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Wait for element visibility
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element to be clickable
    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Click helper using locator
    protected void click(By locator) {
        waitForClickable(locator).click();
    }

    // JavaScript click using locator
    protected void jsClick(By locator) {
        WebElement el = waitForClickable(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    // JavaScript click using WebElement
    protected void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // Wait for visibility and sendKeys
    protected void sendKeys(By locator, String text) {
        WebElement el = waitForVisibility(locator);
        el.clear();
        el.sendKeys(text);
    }

    // Wait for visibility and click
    protected void clickVisible(By locator) {
        WebElement el = waitForVisibility(locator);
        el.click();
    }

    // Handle cookie popup inside shadow DOM
    public void handleCookiePopupIfPresent() {
        try {
            WebElement host = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector("#usercentrics-root"))
            );
            SearchContext shadow = host.getShadowRoot();
            By denyBtnLocator = By.cssSelector("button[data-testid='uc-deny-all-button']");
            WebElement denyBtn = shadow.findElement(denyBtnLocator);
            wait.until(ExpectedConditions.elementToBeClickable(denyBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", denyBtn);
            wait.until(ExpectedConditions.stalenessOf(host));
        } catch (TimeoutException | NoSuchElementException e) {
            // Popup not present; safe to proceed
        }
    }
}
