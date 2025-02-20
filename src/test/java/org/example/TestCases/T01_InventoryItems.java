package org.example.TestCases;


import io.github.serpro69.kfaker.Faker;
import org.example.pages.P01_Login;
import org.example.pages.P02_Inventory;
import org.example.pages.P03_Cart;
import org.example.pages.P04_CheckOut;
import org.example.pojos.Item;
import org.example.pojos.User;
import org.example.pojos.UserDataReader;
import org.example.utils.BrowsersActions;
import org.example.utils.CustomAssert;
import org.example.drivers.DriverManager;
import org.example.utils.JsonUtils;
import org.example.utils.Validations;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class T01_InventoryItems extends Hooks {

	P01_Login loginPage;
	P02_Inventory inventory;
	P03_Cart cart;
	P04_CheckOut checkOut;
	Faker faker = new Faker();
	User user = UserDataReader.getNextUser();

	@BeforeMethod
	public void initPages() {
		BrowsersActions.navigateToUrl(BaseUrl);

	}


	@Test(description = "Check sorting of inventory from z to a")
	public void CheckInventorySorting() {
		new P01_Login(DriverManager.getDriver()).enterUSerName(user.getUserName())
				.enterPassword(user.getPassword())
				.clickLoginButton();
		new P02_Inventory(DriverManager.getDriver()).sortInventory("za").assertItemsOrder("za");
	}

	@Test(description = "check items addition in cart is successful")
	public void checkItemsAddedCorrectlyToCart() {
		new P01_Login(DriverManager.getDriver()).enterUSerName(user.getUserName())
				.enterPassword(user.getPassword())
				.clickLoginButton();

		List<Item> selectedItems = inventory.selectItems(3);

		inventory.navigateToCart().validateItems(selectedItems);

	}

	@Test(description = "finalizing the checkout process and checking totals", dependsOnMethods = {"checkItemsAddedCorrectlyToCart"})
	public void checkItemsTotalsAndSuccessfulCheckOutMessage() {
		checkItemsAddedCorrectlyToCart();

		CustomAssert asrt = new CustomAssert();
		List<Item> selectedItems = List.of(Objects.requireNonNull(JsonUtils.readJsonFromFile(Item.json, Item[].class)));
		inventory.navigateToCart();
		cart.navigateToCheckOut();
		checkOut.fillInfo(faker.getName().firstName(), faker.getName().lastName(), faker.getAddress().postcode());
		checkOut.continueCheckOut();
		float expectedItemsTotalPrice = 0;
		float actualItemsTotalPrice = checkOut.getItemsTotalPrice();
		for (Item i : selectedItems) {
			expectedItemsTotalPrice += i.getPrice();
		}
		asrt.assertEquals(actualItemsTotalPrice, expectedItemsTotalPrice);
		checkOut.finisCheckOut();
		String expectedSuccessMessage = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
		String actualSuccessMessage = checkOut.getConfirmationMessage();

		asrt.assertEquals(actualSuccessMessage, expectedSuccessMessage);
		asrt.assertAll();
	}


}
