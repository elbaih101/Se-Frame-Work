package org.example.utils;

import org.example.drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.Duration;

public class Waits {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	private Waits() {
		super();
	}

	//wait for element present
	public static WebElement waitForElementPresent(By locator) {
		return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
				.until((driver ->
						driver.findElement(locator)));
	}

	//wait for element visible
	public static WebElement waitForElementVisible(By locator) {
		return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
				.until(driver -> {
							WebElement element = waitForElementPresent(locator);
							return element.isDisplayed() ? element : null;
						}
				);

	}

	//wait for element clickable
	public static WebElement waitForElementClickable(By locator) {
		return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
				.until(driver -> {
							WebElement element = waitForElementVisible(locator);
							return element.isEnabled() ? element : null;
						}
				);
	}
}
