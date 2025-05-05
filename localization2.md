// TestNG XML Configuration for Parallel Language Testing
// src/test/resources/testng.xml

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Localization Test Suite" parallel="tests" thread-count="2">
    <test name="Arabic Tests">
        <parameter name="language" value="ar"/>
        <classes>
            <class name="com.example.runners.LocalizationTestRunner"/>
        </classes>
    </test>
    <test name="English Tests">
        <parameter name="language" value="en"/>
        <classes>
            <class name="com.example.runners.LocalizationTestRunner"/>
        </classes>
    </test>
</suite>
```

// TestNG Runner Class for Cucumber + Parameter Handling
// src/test/java/com/example/runners/LocalizationTestRunner.java

```java
package com.example.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.example.stepdefs"},
    plugin = {"pretty", "html:target/cucumber-reports/#{language}",
             "json:target/cucumber-reports/#{language}/cucumber.json"}
)
public class LocalizationTestRunner extends AbstractTestNGCucumberTests {
    
    @Parameters({"language"})
    @BeforeClass
    public void setUpLanguage(String language) {
        // Set language as system property to be accessed by hooks
        System.setProperty("test.language", language);
    }
}
```

// Updated Hooks to use parameter from TestNG
// src/test/java/com/example/stepdefs/LocalizationHooks.java

```java
package com.example.stepdefs;

import com.example.localization.LocalizationManager;
import io.cucumber.java.Before;

public class LocalizationHooks {
    
    @Before
    public void setUpLanguage() {
        // Get language from system property set by TestNG
        String language = System.getProperty("test.language", "en");
        LocalizationManager.setLanguage(language);
    }
}
```

// Maven Configuration for TestNG
// pom.xml (partial)

```xml
<dependencies>
    <!-- TestNG dependency -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.7.1</version>
        <scope>test</scope>
    </dependency>
    <!-- Cucumber TestNG integration -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-testng</artifactId>
        <version>7.12.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.1.2</version>
            <configuration>
                <suiteXmlFiles>
                    <suiteXmlFile>src/test/resources/testng.xml</suiteXmlFile>
                </suiteXmlFiles>
            </configuration>
        </plugin>
    </plugins>
</build>
```
