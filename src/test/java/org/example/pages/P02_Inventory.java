package org.example.pages;

import org.example.custom_elements.SelectElement;
import org.example.pojos.Item;
import org.example.templates.BasePage;
import org.example.tools.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class P02_Inventory extends BasePage
{
    /**
     * initiate the pages using the custom field decorator factory
     *
     * @param driver Web Driver to inotiate PageFactory
     */
    public P02_Inventory(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(className = "product_sort_container")
    private SelectElement sortSelect;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    By addToCartButtonBy = By.className("btn_inventory");

    public void sortInventory(String value)
    {
        sortSelect.selectByValue(value);
    }

    public List<Item> getAllItems()
    {
        List<Item> items = new ArrayList<>();
        for (WebElement i : inventoryItems)
        {
            items.add(new Item(i));
        }
        return items;
    }


    public boolean assertItemsOrder(String order)
    {
        List<String> itemsNames = new ArrayList<>();
        for (Item i : getAllItems())
        {
            itemsNames.add(i.getName());
        }
        return Utils.isSorted(itemsNames, order);
    }

    public List<Item> selectItems(int count)
    {
        List<Item> selectedItems = new ArrayList<>();
        for (int i = 0; i < count; i++)
        {
            WebElement item = inventoryItems.get(i);
            WebElement addtoCartButtn = item.findElement(addToCartButtonBy);
            addtoCartButtn.click();
            selectedItems.add(new Item(item));

        }
        return selectedItems;
    }

    public void navigateToCart()
    {
        cartLink.click();
    }


}
