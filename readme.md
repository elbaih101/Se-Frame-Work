

---

# Ejada Task

## Table of Contents

1. [Maven POM Structure](#maven-pom-structure)
2. [Custom Web Element Framework](#custom-web-element-framework)


---

## Maven POM Structure

The `the Task` uses a Maven POM file to manage its dependencies, plugins, and build configurations. Key
dependencies include Testng, Selenium, and Jackson for JSON processing. T

---

## Custom Web Element Framework

This project includes a custom framework for managing web elements using Selenium. The `CustomFieldDecorator` class
allows for advanced handling of `WebElement` annotations, while the `CustomWebElement` class provides additional
utilities for interacting with web elements.



---


### Key Components

1. **DriverManager**: Manages WebDriver instances for each thread, ensuring thread safety.
2. **BaseTestNGCucumberRunner**: Configures Cucumber tests to run with TestNG, providing thread-local storage for user
   data.

## DriverManager Class

The `DriverManager` class handles WebDriver instances and ensures that each thread has its own WebDriver.

##### references

- [Parallel Driver Manager](documentations/driverManager.md)

### Key Methods

- **`initializeDriver(Drivers driverName, Driver_Mode mode)`**: Initializes and sets a WebDriver instance for the
  current thread.
- **`getDriver()`**: Retrieves the WebDriver instance for the current thread.
- **`quitDriver()`**: Quits the WebDriver instance and removes it from thread-local storage.
- **`getWebDriverType(WebDriver driver)`**: Determines the type of WebDriver instance.
- **`edgePrintingAndDownloadOptions()`**: Configures EdgeOptions for printing and download settings.

### Example Usage

```java
DriverManager.initializeDriver(Drivers.Chrome, Driver_Mode.Normal);

WebDriver driver = DriverManager.getDriver();
```


