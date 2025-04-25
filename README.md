Bonify Automation Suite

🚀 Overview

This project automates the registration and login/logout flows for the Bonify.de website using Selenium WebDriver, TestNG, and the Page Object Model (POM) design pattern.

🧱 Tech Stack

Java 11+

Selenium WebDriver

TestNG

Maven

WebDriverManager

📂 Project Structure

BonifyAutomation/
├── src/
│   └── main/
│       └── java/
│           └── base/
│               └── BaseTest.java
│           └── pages/
│               ├── BasePage.java
│               ├── HomePage.java
│               ├── LoginPage.java
│               └── RegistrationPage.java
├── src/
│   └── test/
│       └── java/
│           └── tests/
│               └── BonifyTests.java
├── testng.xml
├── pom.xml
└── README.md

🧪 Running Tests

Tests can be executed using TestNG via the testng.xml file:

Using IntelliJ:

Right-click on testng.xml > Run.

Or via Maven:

mvn clean test -DsuiteXmlFile=testng.xml

🔍 Test Scenarios

1. testUserRegistration

Opens the home page

Handles cookie banner

Clicks the Register button

Completes registration with a unique email

Logs in with the registered credentials

2. testLoginLogout

Opens the home page

Clicks on the login button

Logs in with an existing test user

Validates successful login

Logs out from the dashboard

⚙️ Utilities in BasePage

Dynamic waits (visibility, clickability)

JavaScript click handler

Shadow DOM support for cookie popup

📌 Notes

The test user is blocked after a few repeated signups.

Cookie banners reappear on various actions and are handled using Shadow DOM utilities.

Thread.sleep() is avoided and replaced with meaningful waits using WebDriverWait.

👨‍💻 Author: Anand Agrawal

Happy Testing! 🧪✨