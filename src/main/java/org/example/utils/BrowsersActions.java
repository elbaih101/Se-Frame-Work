package org.example.utils;

import io.qameta.allure.Step;
import org.example.drivers.DriverManager;
import org.openqa.selenium.WebDriver;

public class BrowsersActions {
	private static WebDriver driver = DriverManager.getDriver();

	BrowsersActions() {

	}

	public void scrollPage() {
		// Scroll the page
	}

	public static void refreshPage() {
		// Refresh the page
	}

	@Step("Navigate to URL {url}")
	public static void navigateToUrl(String url) {
		driver.get(url);
		LogUtils.logInfo("Navigating to URL", url);
	}

	@Step("Get Page Title")
	public static String getPageTitle() {
		String pageTitle = driver.getTitle();
		LogUtils.logInfo("Getting page title", pageTitle);
		return pageTitle;
	}
public static String getCurrentUrl() {
		String currentUrl = driver.getCurrentUrl();
		LogUtils.logInfo("Getting current URL", currentUrl);
		return currentUrl;
	}
	@Step("navigate back")
	public void navigateBack() {
		// Navigate back
	}

	@Step("navigate forward")
	public void navigateForward() {
		// Navigate forward
	}


	public void maximizeWindow() {
		// Maximize the window
	}

	public void minimizeWindow() {
		// Minimize the window
	}

	public void closeWindow() {
		// Close the window
	}

	public void switchToTab() {
		// Switch to a tab
	}

	public void switchToWindow() {
		// Switch to a window
	}

	public void switchToFrame() {
		// Switch to a frame
	}

	public void switchToAlert() {
		// Switch to an alert
	}

	public void acceptAlert() {
		// Accept an alert
	}

	public void dismissAlert() {
		// Dismiss an alert
	}

	public void takeScreenshot() {
		// Take a screenshot
	}

	public void executeJavaScript() {
		// Execute JavaScript code
	}


}
