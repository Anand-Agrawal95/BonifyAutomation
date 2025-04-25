package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    private By emailInput     = By.id("user_email");
    private By passwordInput  = By.id("user_password");
    private By acceptTerms    = By.cssSelector("div.consent-checkbox-text");
    private By registerButton = By.id("form-submit");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage register(String email, String password) {
        // Wait for and type into email & password fields
        sendKeys(emailInput, email);
        sendKeys(passwordInput, password);

        // Wait for and click the Accept Terms checkbox
        clickVisible(acceptTerms);

        // Wait for and JavaScript-click the Register button
        jsClick(registerButton);

        return new LoginPage(driver);
    }
}
