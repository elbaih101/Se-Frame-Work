package org.example.pages;


import org.example.templates.CustomFieldDecorator;
import org.example.utils.CustomAssert;
import org.example.utils.Waits;
import org.example.utils.LogUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.time.Duration;

public class BasePage
{
   public final WebDriver driver;
   public final Actions actions;
   public final JavascriptExecutor js;
   public final CustomAssert asrt;

    /**
     * initiate the pages using the custom field decorator factory
     * @param driver Web Driver to inotiate PageFactory
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new CustomFieldDecorator(new DefaultElementLocatorFactory(driver)), this);
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
        asrt = new CustomAssert();
        LogUtils.logInfo("instantiated  page",this.getClass().getName());

    }
}
