package org.example.utils;

import org.example.drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class Scrolling {

	//scroll to element
	public static void scrollToElement(By locator) {
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", DriverManager.getDriver().findElement(locator));
	}
}
