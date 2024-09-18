package org.example.custom_elements;

import org.example.templates.CustomWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectElement extends CustomWebElement
{
    private Select select;

    public SelectElement(WebElement element)
    {
        super(element);


    }

    private void initSelect()
    {
        select = new Select(element);
    }

    public void selectByIndex(int i)
    {
        initSelect();
        select.selectByIndex(i);
    }

    public void selectByValue(String value)
    {
        initSelect();
        select.selectByValue(value);
    }

    public void selectByVisibleText(String value)
    {
        initSelect();
        select.selectByVisibleText(value);
    }

    public WebElement getFirstSelectedOption()
    {
        initSelect();
        return select.getFirstSelectedOption();
    }

    public List<WebElement> getOptions()
    {
        initSelect();
        return select.getOptions();
    }

    public List<WebElement> getAllSelectedOptions()
    {
        initSelect();
        return select.getAllSelectedOptions();
    }

    public void getAllSelectedOptions(int i)
    {
        initSelect();
        select.deselectByIndex(i);
    }

    public void getAllSelectedOptions(String value)
    {
        initSelect();
        select.deselectByValue(value);
    }

}
