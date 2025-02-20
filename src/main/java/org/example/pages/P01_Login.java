package org.example.pages;

import io.qameta.allure.Step;
import org.example.utils.BrowsersActions;
import org.example.utils.ElementsActions;
import org.example.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class P01_Login extends BasePage {

	By userNameFieldBy = By.id("user-name");
	By passWordFieldBy = By.id("password");
	By loginButtonBy = By.id("login-button");

	/**
	 * initiate the pages using the custom field decorator factory
	 *
	 * @param driver Web Driver to inotiate PageFactory
	 */
	public P01_Login(WebDriver driver) {
		super(driver);

	}
//Actions
	@Step("Navigate to the Login Page")
	public P01_Login navigateTo() {
		BrowsersActions.navigateToUrl("https://www.saucedemo.com/");
		return this;
	}

	@Step("Enter User Name: {userName}")
	public P01_Login enterUSerName(String userName) {
		ElementsActions.sendKeys(userNameFieldBy, userName);
		LogUtils.logInfo("Entered User Name: ", userName);
		return this;
	}

	@Step("Enter Password: {password}")
	public P01_Login enterPassword(String password) {
		ElementsActions.sendKeys(passWordFieldBy, password);
		LogUtils.logInfo("Entered Password: ", password);
		return this;
	}

	@Step("Click on Login Button")
	public P01_Login clickLoginButton() {
		ElementsActions.clickElement(loginButtonBy);
		LogUtils.logInfo("Clicked on Login Button");
		return this;
	}

	//Getters



}
