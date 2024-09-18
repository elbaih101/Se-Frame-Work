package org.example.pages;

import org.example.pojos.Item;
import org.example.templates.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class P03_Cart extends BasePage
{
    /**
     * initiate the pages using the custom field decorator factory
     *
     * @param driver Web Driver to inotiate PageFactory
     */
    public P03_Cart(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id="checkout")
    private WebElement checkOutButton;
    private final By removeButtonBy = By.className("cart_button");

    public List<Item> getCartItems()
    {
        List<Item> items = new ArrayList<>();
        for (WebElement i : cartItems)
        {
            items.add(new Item(i));
        }
        return items;
    }

    public void navigateToCheckOut(){
        checkOutButton.click();
    }


}
