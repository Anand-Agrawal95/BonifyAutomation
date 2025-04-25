package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;
import pages.LoginPage;

public class BonifyTests extends BaseTest {

    private String baseTestEmail = "qatest_" + System.currentTimeMillis() + "@bonify.de";
    private String password = "Password123!";

    @Test(priority = 1, description = "Validate registration flow and then logging in with same credentials")
    public void testUserRegistration() {
        // Navigate to Home Page and handle the cookie popup
        HomePage home = new HomePage(driver);
        home.open(); // Launch main URL
        home.handleCookiePopupIfPresent(); // Handle cookies popup

        // Click Register button and handle cookie popup if it appears again
        home.clickRegisterButton();
        home.handleCookiePopupIfPresent();

        // Close cookies popup and Register new user
        RegistrationPage reg = new RegistrationPage(driver);
        reg.handleCookiePopupIfPresent();
        reg.register(baseTestEmail, password); // Register with generated email and password

        // Login with the same credentials
        LoginPage login = new LoginPage(driver);
        login.login(baseTestEmail, password); // Log in with the same credentials

        // Validate login even if site blocks the login
        login.validateLoginSuccessfulAfterRegistration(); // Validate successful login
    }

    @Test(priority = 2, description = "Validate logging in with the credentials and then logging out from dashboard")
    public void testLoginLogout() {
        // Navigate to Home Page and handle the cookie popup
        HomePage home = new HomePage(driver);
        home.open(); // Launch main URL
        home.handleCookiePopupIfPresent(); // Handle cookies popup

        // Click on Login button and close the cookies popup
        home.clickLoginButton();
        home.handleCookiePopupIfPresent(); // Handle cookies popup again

        // Login with valid credentials and close cookies popup during navigation
        LoginPage login = new LoginPage(driver);
        login.login("qatest+sp10p@bonify.de", "passwordQA5#");
        login.handleCookiePopupIfPresent(); // Handle cookies popup after login

        // Validate login success by checking for dashboard URL and then logout
        login.validateLoginSuccessful();
        login.handle2FAPageIfAppearsAndLogout(); //close 2fa page if it appears and logout
    }
}
