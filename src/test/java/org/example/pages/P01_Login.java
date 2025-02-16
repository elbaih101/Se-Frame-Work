package org.example.pages;

import org.example.utils.LogUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class P01_Login extends BasePage
{

    /**
     * initiate the pages using the custom field decorator factory
     *
     * @param driver Web Driver to inotiate PageFactory
     */
    public P01_Login(WebDriver driver)
    {
        super(driver);

    }

    @FindBy(id="user-name")
    private WebElement userNameField;

    @FindBy(id="password")
    private WebElement passWordField;

    @FindBy(id="login-button")
    private WebElement loginButton;




    public void logIn(String userName,String password){
        userNameField.sendKeys(userName);
        passWordField.sendKeys(password);
        loginButton.click();
    }

}
