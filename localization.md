# Localization Test Framework with Cucumber and Selenium

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           ├── pages/            # Page Object classes
│   │           ├── utils/            # Utility classes
│   │           └── localization/     # Localization support
│   └── resources/
│       └── locales/                  # Locale-specific resource files
│           ├── en.properties
│           ├── fr.properties
│           ├── es.properties
│           └── ...
└── test/
    ├── java/
    │   └── com/
    │       └── example/
    │           ├── stepdefs/         # Step definitions
    │           └── runners/          # Test runners
    └── resources/
        └── features/                # Feature files
            └── login.feature        # Language-agnostic features
```

## Key Components

### 1. Properties Files for Each Language

**src/main/resources/locales/en.properties**
```properties
login.title=Welcome to our application
login.username=Username
login.password=Password
login.button=Sign In
login.error=Invalid credentials
```

**src/main/resources/locales/es.properties**
```properties
login.title=Bienvenido a nuestra aplicación
login.username=Nombre de usuario
login.password=Contraseña
login.button=Iniciar sesión
login.error=Credenciales inválidas
```

### 2. LocalizationManager to Load Resources

```java
package com.example.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationManager {
    private static final String BUNDLE_BASE_NAME = "locales/";
    private static ThreadLocal<String> currentLanguage = new ThreadLocal<>();
    
    public static void setLanguage(String language) {
        currentLanguage.set(language);
    }
    
    public static String getLanguage() {
        return currentLanguage.get();
    }
    
    public static String getMessage(String key) {
        String language = currentLanguage.get();
        if (language == null) {
            throw new IllegalStateException("Language not set");
        }
        
        Locale locale = new Locale(language);
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME + language, locale);
        return bundle.getString(key);
    }
}
```

### 3. Page Objects with Localization Support

```java
package com.example.pages;

import com.example.localization.LocalizationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;
    
    // Locators
    private By titleLocator = By.cssSelector("h1.login-title");
    private By usernameFieldLocator = By.id("username");
    private By passwordFieldLocator = By.id("password");
    private By loginButtonLocator = By.id("login-button");
    private By errorMessageLocator = By.cssSelector(".error-message");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public boolean isPageTitleCorrect() {
        String expectedTitle = LocalizationManager.getMessage("login.title");
        String actualTitle = driver.findElement(titleLocator).getText();
        return expectedTitle.equals(actualTitle);
    }
    
    public boolean isUsernameFieldLabelCorrect() {
        String expectedLabel = LocalizationManager.getMessage("login.username");
        String actualLabel = driver.findElement(By.xpath("//label[@for='username']")).getText();
        return expectedLabel.equals(actualLabel);
    }
    
    public boolean isPasswordFieldLabelCorrect() {
        String expectedLabel = LocalizationManager.getMessage("login.password");
        String actualLabel = driver.findElement(By.xpath("//label[@for='password']")).getText();
        return expectedLabel.equals(actualLabel);
    }
    
    public boolean isLoginButtonTextCorrect() {
        String expectedText = LocalizationManager.getMessage("login.button");
        String actualText = driver.findElement(loginButtonLocator).getText();
        return expectedText.equals(actualText);
    }
    
    public void enterUsername(String username) {
        driver.findElement(usernameFieldLocator).sendKeys(username);
    }
    
    public void enterPassword(String password) {
        driver.findElement(passwordFieldLocator).sendKeys(password);
    }
    
    public void clickLoginButton() {
        driver.findElement(loginButtonLocator).click();
    }
    
    public boolean isErrorMessageCorrect() {
        String expectedMessage = LocalizationManager.getMessage("login.error");
        String actualMessage = driver.findElement(errorMessageLocator).getText();
        return expectedMessage.equals(actualMessage);
    }
}
```

### 4. Language-Agnostic Feature File

**src/test/resources/features/login.feature**
```gherkin
Feature: Login functionality

  # This scenario will be run for each language specified in the test runner
  Scenario: Verify login page localization
    Given I am on the login page
    Then the login page title should be correctly localized
    And the login form field labels should be correctly localized
    And the login button text should be correctly localized
    
  Scenario: Verify error message localization
    Given I am on the login page
    When I enter invalid credentials
    And I click the login button
    Then the error message should be correctly localized
```

### 5. Step Definitions

```java
package com.example.stepdefs;

import com.example.localization.LocalizationManager;
import com.example.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

public class LoginStepDefs {
    private WebDriver driver;
    private LoginPage loginPage;
    
    public LoginStepDefs(CommonStepDefs commonSteps) {
        this.driver = commonSteps.getDriver();
        this.loginPage = new LoginPage(driver);
    }
    
    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        driver.get("https://example.com/login");
    }
    
    @Then("the login page title should be correctly localized")
    public void theLoginPageTitleShouldBeCorrectlyLocalized() {
        Assert.assertTrue("Login page title is not correctly localized", 
                         loginPage.isPageTitleCorrect());
    }
    
    @Then("the login form field labels should be correctly localized")
    public void theLoginFormFieldLabelsShouldBeCorrectlyLocalized() {
        Assert.assertTrue("Username field label is not correctly localized", 
                         loginPage.isUsernameFieldLabelCorrect());
        Assert.assertTrue("Password field label is not correctly localized", 
                         loginPage.isPasswordFieldLabelCorrect());
    }
    
    @Then("the login button text should be correctly localized")
    public void theLoginButtonTextShouldBeCorrectlyLocalized() {
        Assert.assertTrue("Login button text is not correctly localized", 
                         loginPage.isLoginButtonTextCorrect());
    }
    
    @When("I enter invalid credentials")
    public void iEnterInvalidCredentials() {
        loginPage.enterUsername("invalid");
        loginPage.enterPassword("invalid");
    }
    
    @When("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLoginButton();
    }
    
    @Then("the error message should be correctly localized")
    public void theErrorMessageShouldBeCorrectlyLocalized() {
        Assert.assertTrue("Error message is not correctly localized", 
                         loginPage.isErrorMessageCorrect());
    }
}
```

### 6. Hooks to Set Up Language for Each Test Run

```java
package com.example.stepdefs;

import com.example.localization.LocalizationManager;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class LocalizationHooks {
    
    @Before
    public void setUpLanguage(Scenario scenario) {
        // Extract language tag from scenario tags
        String language = "en"; // Default language
        
        for (String tag : scenario.getSourceTagNames()) {
            if (tag.startsWith("@lang:")) {
                language = tag.substring(6); // Extract language code after @lang:
                break;
            }
        }
        
        LocalizationManager.setLanguage(language);
    }
}
```

### 7. Test Runner with Language Parameter

```java
package com.example.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.example.stepdefs"},
    plugin = {"pretty", "html:target/cucumber-reports"},
    tags = "@lang:en" // Run tests for English language
)
public class EnglishTestRunner {
}
```

```java
package com.example.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.example.stepdefs"},
    plugin = {"pretty", "html:target/cucumber-reports"},
    tags = "@lang:es" // Run tests for Spanish language
)
public class SpanishTestRunner {
}
```

## 8. Alternative: Parameterized Testing

You can also use a parameterized approach with a single test runner:

```java
package com.example.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParameterizedLanguageRunner {
    
    @Parameterized.Parameters
    public static Collection<String> languages() {
        return Arrays.asList("en", "es", "fr", "de", "ja");
    }
    
    @Parameterized.Parameter
    public String language;
    
    @RunWith(Cucumber.class)
    @CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.example.stepdefs"}
    )
    public class LocalizedCucumberRunner {
        public LocalizedCucumberRunner() {
            System.setProperty("cucumber.language", language);
        }
    }
}
```

## 9. Usage with Maven

Configure Maven to run tests for different languages:

```xml
<profiles>
    <profile>
        <id>en</id>
        <properties>
            <cucumber.filter.tags>@lang:en</cucumber.filter.tags>
        </properties>
    </profile>
    <profile>
        <id>es</id>
        <properties>
            <cucumber.filter.tags>@lang:es</cucumber.filter.tags>
        </properties>
    </profile>
    <profile>
        <id>all-languages</id>
        <properties>
            <cucumber.filter.tags>not @ignore</cucumber.filter.tags>
        </properties>
    </profile>
</profiles>
```

Run with: `mvn test -Pen` or `mvn test -Pes` or `mvn test -Pall-languages`
