<!-- Maven Configuration for Parallel Language Testing -->
<!-- pom.xml -->

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>localization-tests</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <cucumber.version>7.12.1</cucumber.version>
        <selenium.version>4.10.0</selenium.version>
    </properties>

    <dependencies>
        <!-- Cucumber dependencies -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>${cucumber.version}</version>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>
        
        <!-- Selenium dependencies -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium.version}</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.1.2</version>
                <configuration>
                    <!-- Enable parallel test execution -->
                    <parallel>classes</parallel>
                    <threadCount>2</threadCount>
                    <perCoreThreadCount>false</perCoreThreadCount>
                    <forkCount>2</forkCount>
                    <reuseForks>true</reuseForks>
                </configuration>
            </plugin>
            
            <!-- Plugin for executing separate runs with different languages -->
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>3.1.0</version>
                <executions>
                    <execution>
                        <id>run-all-languages</id>
                        <goals>
                            <goal>java</goal>
                        </goals>
                        <configuration>
                            <mainClass>com.example.runners.LocalizationTestExecutor</mainClass>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
    
    <!-- Profiles for running tests with specific languages -->
    <profiles>
        <profile>
            <id>english</id>
            <properties>
                <cucumber.filter.tags>@english</cucumber.filter.tags>
            </properties>
        </profile>
        <profile>
            <id>arabic</id>
            <properties>
                <cucumber.filter.tags>@arabic</cucumber.filter.tags>
            </properties>
        </profile>
        <profile>
            <id>all-languages</id>
            <!-- This will run all tests -->
        </profile>
    </profiles>
</project>
```

// Test executor class to run tests for all languages
// src/test/java/com/example/runners/LocalizationTestExecutor.java

```java
package com.example.runners;

import org.junit.runner.JUnitCore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LocalizationTestExecutor {
    
    public static void main(String[] args) throws InterruptedException {
        // Define the languages to test
        final String[] languages = {"en", "ar"};
        
        // Create a thread pool for parallel execution
        ExecutorService executor = Executors.newFixedThreadPool(languages.length);
        
        // Submit each language test to the executor
        for (String language : languages) {
            executor.submit(() -> {
                System.out.println("Starting tests for language: " + language);
                
                // Set the language as a system property
                System.setProperty("test.language", language);
                
                // Run the tests for this language
                JUnitCore.runClasses(LocalizationTestRunner.class);
                
                System.out.println("Completed tests for language: " + language);
            });
        }
        
        // Shutdown the executor and wait for tests to complete
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
    }
}
```

// Runner class configured to use system property for language
// src/test/java/com/example/runners/LocalizationTestRunner.java

```java
package com.example.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.example.stepdefs"},
    plugin = {
        "pretty", 
        "html:target/cucumber-reports/${test.language}",
        "json:target/cucumber-reports/${test.language}/cucumber.json"
    }
)
public class LocalizationTestRunner {
    
    @BeforeClass
    public static void setUp() {
        // Language is already set by the executor
    }
}
```
