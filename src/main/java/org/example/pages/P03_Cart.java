package org.example.pages;

import io.qameta.allure.Step;
import org.example.pojos.Item;
import org.example.utils.ElementsActions;
import org.example.utils.LogUtils;
import org.example.utils.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class P03_Cart extends BasePage {
	By removeButtonBy = By.className("cart_button");
	By cartItemsBy = By.className("cart_item");
	By continueShoppingButtonBy = By.id("continue-shopping");
	By checkOutButtonBy = By.id("checkout");

	/**
	 * initiate the pages using the custom field decorator factory
	 *
	 * @param driver Web Driver to inotiate PageFactory
	 */
	public P03_Cart(WebDriver driver) {
		super(driver);

	}

	@Step("get all items in the cart")
	public List<Item> getCartItems() {
		List<Item> items = new ArrayList<>();
		for (WebElement i : ElementsActions.findElements(cartItemsBy)) {
			items.add(new Item(i));
		}
		return items;
	}

	@Step("validate Items in the cart matches selected items")
	public void validateItems(List<Item> selectedItems) {
		LogUtils.logInfo("Validating items in the cart matches selected items");
		Validations.validateEquals(selectedItems, getCartItems(), "Items added to cart are not the same as selected items");
	}

	@Step("click on Checkout button")
	public P04_CheckOut navigateToCheckOut() {
		ElementsActions.clickElement(checkOutButtonBy);
		return new P04_CheckOut(driver);
	}


}
