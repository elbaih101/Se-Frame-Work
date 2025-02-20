package org.example.utils;

import io.qameta.allure.Step;
import org.example.drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ElementsActions {

	final private static WebDriver driver = DriverManager.getDriver();


	public static WebElement findElement(By locator) {
		LogUtils.logInfo("Finding element: ", locator.toString());
		return driver.findElement(locator);
	}

	public static List<WebElement> findElements(By locator) {
		LogUtils.logInfo("Finding elements: ", locator.toString());
		return driver.findElements(locator);
	}

	@Step("Click on element: {locator}")
	public static void clickElement(By locator) {

		findElement(locator).click();
		LogUtils.logInfo("Clicked on element: ", locator.toString());
	}

	@Step("Send keys: {text} to element: {locator}")
	public static void sendKeys(By locator, String text) {

		findElement(locator).sendKeys(text);
		LogUtils.logInfo("Sent keys: ", text, " to element: ", locator.toString());

	}

	@Step("Get text from element: {locator}")
	public static String getText(By locator) {

		// Get text from an element
		String text = findElement(locator).getText();
		LogUtils.logInfo("retrieved text: ", text);
		return text;
	}

	@Step("select option By Value: {option} from element: {locator}")
	public static void selectOptionByValue(By locator, String option) {
		Select select = new Select(findElement(locator));
		select.selectByValue(option);
	}
@Step("select option By Visible Text: {option} from element: {locator}")
	public static void selectOptionByText(By locator, String option) {
		Select select = new Select(findElement(locator));
		select.selectByVisibleText(option);
	}

	@Step("select option By Index: {index} from element: {locator}")
	public static void selectOptionByValue(By locator, int index) {
		Select select = new Select(findElement(locator));
		select.selectByIndex(index);
	}

	@Step("Hover over element: {locator}")
	public void hoverOverElement(By locator) {
		// Hover over an element
		Actions actions = new Actions(driver);
		actions.moveToElement(findElement(locator)).perform();
		LogUtils.logInfo("Hovered over element: ", locator.toString());
	}


	@Step("Scroll to element: {locator}")
	public void scrollToElement(By locator) {
		LogUtils.logInfo("Scrolling to element: ", locator.toString());
		((JavascriptExecutor) driver).
				executeScript("arguments[0].scrollIntoView(true);", findElement(locator));
	}

	public void dragAndDrop() {
		// Drag and drop an element
	}

	public void doubleClick() {
		// Double click on an element
	}

	public void rightClick() {
		// Right click on an element
	}

	public void switchToFrame() {
		// Switch to a frame
	}

	public void switchToWindow() {
		// Switch to a window
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

	public void highlightElement() {
		// Highlight an element
	}

	public void uploadFile() {
		// Upload a file
	}

	public void downloadFile() {
		// Download a file
	}

	public void switchToIframe() {
		// Switch to an iframe
	}

	public void switchToDefaultContent() {
		// Switch to the default content
	}

	public void switchToParentFrame() {
		// Switch to the parent frame
	}

	public void switchToNewWindow() {
		// Switch to a new window
	}

	public void switchToParentWindow() {
		// Switch to the parent window
	}

	public void switchToNewTab() {
		// Switch to a
	}
}