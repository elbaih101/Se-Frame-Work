package org.example.pages;

import org.example.utils.LogUtils;
import org.example.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class P04_CheckOut extends BasePage
{
    /**
     * initiate the pages using the custom field decorator factory
     *
     * @param driver Web Driver to inotiate PageFactory
     */
    public P04_CheckOut(WebDriver driver)
    {
        super(driver);
    }

    /// first step //
    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id="last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement containueButton;
    /// end first step ///

    //second step //
    @FindBy(className = "summary_subtotal_label")
    private WebElement itemsTotalsDiv;

    @FindBy(id = "finish")
    private WebElement finishButton;
    //end second step //

    // final step //
    @FindBy(className = "complete-text")
    private WebElement confirmationText;
    //end final step //


    public void fillInfo(String fName,String lName,String zipCode){
        firstNameField.sendKeys(fName);
        lastNameField.sendKeys(lName);
        postalCodeField.sendKeys(zipCode);
    }

    public void continueCheckOut(){
        containueButton.click();
    }

    public float getItemsTotalPrice(){
       return  Utils.extractFloats(itemsTotalsDiv.getText()).getFirst();
    }
    public void finisCheckOut(){
        finishButton.click();
    }
    public String getConfirmationMessage(){
        return confirmationText.getText();
    }
}
