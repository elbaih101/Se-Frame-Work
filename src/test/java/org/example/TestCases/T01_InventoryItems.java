package org.example.TestCases;

import com.github.javafaker.Faker;
import org.example.pages.P01_Login;
import org.example.pages.P02_Inventory;
import org.example.pages.P03_Cart;
import org.example.pages.P04_CheckOut;
import org.example.pojos.Item;
import org.example.pojos.User;
import org.example.pojos.UserDataReader;
import org.example.tools.CustomAssert;
import org.example.tools.DriverManager;
import org.example.tools.JsonUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class T01_InventoryItems extends Hooks
{

    P01_Login loginPage;
    P02_Inventory inventory;
    P03_Cart cart;
    P04_CheckOut checkOut;
    Faker faker = new Faker();

    @BeforeMethod
    public void initPages()
    {

        WebDriver driver = DriverManager.getDriver();
        loginPage = new P01_Login(driver);
        inventory = new P02_Inventory(driver);
        cart = new P03_Cart(driver);
        checkOut = new P04_CheckOut(driver);
    }

    private void login()
    {
        User user = UserDataReader.getNextUser();
        loginPage.logIn(Objects.requireNonNull(user).getUserName(), user.getPassword());
    }

    @Test(description = "Check sorting of inventory from z to a")
    public void CheckInventorySorting()
    {
        login();
        inventory.sortInventory("za");
        Assert.assertTrue(inventory.assertItemsOrder("za"));
    }

    @Test(description = "check items addition in cart is successful")
    public void checkItemsAddedCorrectlyToCart()
    {
        login();
        List<Item> selectedItems = inventory.selectItems(3);
        inventory.navigateToCart();
        List<Item> cartItems = cart.getCartItems();
        Assert.assertEquals(selectedItems, cartItems);
        JsonUtils.writeObjectToJsonFile(Item.json, selectedItems);


    }

    @Test(description = "finalizing the checkout process and checking totals",dependsOnMethods = {"checkItemsAddedCorrectlyToCart"})
    public void checkItemsTotalsAndSuccessfulCheckOutMessage()
    {
        checkItemsAddedCorrectlyToCart();

        CustomAssert asrt = new CustomAssert();
        List<Item> selectedItems = List.of(Objects.requireNonNull(JsonUtils.readJsonFromFile(Item.json, Item[].class)));
        inventory.navigateToCart();
        cart.navigateToCheckOut();
        checkOut.fillInfo(faker.name().firstName(), faker.name().lastName(), faker.address().zipCode());
        checkOut.continueCheckOut();
        float expectedItemsTotalPrice = 0;
        float actualItemsTotalPrice = checkOut.getItemsTotalPrice();
        for (Item i : selectedItems)
        {
            expectedItemsTotalPrice += i.getPrice();
        }
        asrt.assertEquals(actualItemsTotalPrice, expectedItemsTotalPrice);
        checkOut.finisCheckOut();
        String expectedSuccessMessage = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
        String actualSuccessMessage = checkOut.getConfirmationMessage();

        asrt.assertEquals(actualSuccessMessage, expectedSuccessMessage);
        asrt.assertAll();   }


}
