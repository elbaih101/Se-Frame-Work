package org.example.pages;

import io.qameta.allure.Step;

import org.example.pojos.Item;
import org.example.utils.BrowsersActions;
import org.example.utils.ElementsActions;
import org.example.utils.JsonUtils;
import org.example.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class P02_Inventory extends BasePage {
	/**
	 * initiate the pages using the custom field decorator factory
	 *
	 * @param driver Web Driver to inotiate PageFactory
	 */
	public P02_Inventory(WebDriver driver) {
		super(driver);
	}

	By addToCartButtonBy = By.className("btn_inventory");
	By inventoryItemsBy = By.className("inventory_item");
	By cartLinkBy = By.className("shopping_cart_link");
	By sortSelectBy = By.className("product_sort_container");

	@Step("sort inventory by {value}")
	public P02_Inventory sortInventory(String value) {
		ElementsActions.selectOptionByValue(sortSelectBy, value);
		return this;
	}

	@Step("navigate to inventory page")
	public P02_Inventory navigateTo() {
		BrowsersActions.navigateToUrl("https://www.saucedemo.com/inventory.html");
		return this;
	}

	@Step("get all items")
	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<>();
		for (WebElement i : ElementsActions.findElements(inventoryItemsBy)) {
			items.add(new Item(i));
		}
		return items;
	}

	@Step("assert items order is {order}")
	public void assertItemsOrder(String order) {
		List<String> itemsNames = new ArrayList<>();
		for (Item i : getAllItems()) {
			itemsNames.add(i.getName());
		}
		Assert.assertTrue(Utils.isSorted(itemsNames, order));
	}

	@Step("select {count} items")
	public List<Item> selectItems(int count) {
		List<Item> selectedItems = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			WebElement item = ElementsActions.findElements(inventoryItemsBy).get(i);
			WebElement addtoCartButtn = item.findElement(addToCartButtonBy);
			addtoCartButtn.click();
			selectedItems.add(new Item(item));

		}
		JsonUtils.writeObjectToJsonFile(Item.json, selectedItems);
		return selectedItems;
	}

	@Step("navigate to cart page")
	public P03_Cart navigateToCart() {
		ElementsActions.clickElement(cartLinkBy);
		return new P03_Cart(driver);
	}


}
