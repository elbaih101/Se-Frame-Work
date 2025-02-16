package org.example.pages;

import org.example.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class P01_Login extends BasePage
{

    By userNameFieldBy = By.id("user-name");
    By passWordFieldBy = By.id("password");
    By loginButtonBy = By.id("login-button");

    /**
     * initiate the pages using the custom field decorator factory
     *
     * @param driver Web Driver to inotiate PageFactory
     */
    public P01_Login(WebDriver driver)
    {
        super(driver);

    }



    public P01_Login navigateTo(){
        driver.get("https://www.saucedemo.com/");
        LogUtils.logInfo("Navigated to Login Page: ","https://www.saucedemo.com/");
        return this;
    }

    public P01_Login enterUSerName(String userName){
        userNameField.sendKeys(userName);
        LogUtils.logInfo("Entered User Name: ",userName);
        return this;
    }
    public P01_Login enterPassword(String password){
        passWordField.sendKeys(password);
        LogUtils.logInfo("Entered Password: ",password);
    return this;
    }
    public void logIn(String userName,String password){
        userNameField.sendKeys(userName);
        passWordField.sendKeys(password);
        loginButton.click();
    }

}
