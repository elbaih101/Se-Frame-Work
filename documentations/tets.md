# T01_InventoryItems Class Documentation

This document explains the code in the `T01_InventoryItems` class, which is a part of the test automation suite for a web-based application. The tests in this class focus on verifying the functionality of the inventory, cart, and checkout process of the application.

## Package: `org.example.TestCases`

 asdqwe `Hooks`  sadqe



This package includes test cases for verifying various features of the application. The `T01_InventoryItems` class in particular tests the behavior of inventory sorting, cart functionality, and the checkout process.


### Imports

- **`com.github.javafaker.Faker`**: Used to generate fake user data for testing purposes, such as names and addresses.
- **`org.example.pages.P01_Login`, `P02_Inventory`, `P03_Cart`, `P04_CheckOut`**: These are the page object model (POM) classes representing different pages of the application (Login, Inventory, Cart, and Checkout pages).
- **`org.example.pojos.Item`, `User`, `UserDataReader`**: POJOs (Plain Old Java Objects) representing entities in the application. `UserDataReader` is used to read test user data.
- **`org.example.utils.CustomAssert`, `DriverManager`, `JsonUtils`**: Custom utilities for driver management, assertions, and JSON file handling.
- **`org.openqa.selenium.WebDriver`**: Selenium WebDriver to interact with the browser for UI testing.
- **`org.testng.Assert`, `org.testng.annotations.BeforeMethod`, `org.testng.annotations.Test`**: TestNG framework annotations and assertions for structuring and running test cases.



### Class: `T01_InventoryItems`
This class extends `Hooks`, which likely contains setup and teardown logic for the test environment, such as initializing the WebDriver.

#### Fields:
- **`P01_Login loginPage`**: Represents the login page of the application.
- **`P02_Inventory inventory`**: Represents the inventory page of the application.
- **`P03_Cart cart`**: Represents the cart page.
- **`P04_CheckOut checkOut`**: Represents the checkout page.
- **`Faker faker`**: Used to generate random user data during the checkout process.

#### `@BeforeMethod` initPages():
This method initializes the page objects before each test case. It retrieves the WebDriver instance using `DriverManager.getDriver()` and passes it to the constructors of each page object (`loginPage`, `inventory`, `cart`, `checkOut`).

#### Private Method: `login()`
This method handles the login process by retrieving a user from the `UserDataReader` and logging in using the credentials provided.

### Test Cases

#### `@Test(description = "Check sorting of inventory from z to a")`
**Method**: `CheckInventorySorting()`

- **Purpose**: Verifies that items in the inventory are correctly sorted from Z to A.
- **Steps**:
    1. Log in to the application.
    2. Sort the inventory by name in descending order (Z to A).
    3. Assert that the inventory items are correctly ordered.

#### `@Test(description = "check items addition in cart is successful")`
**Method**: `checkItemsAddedCorrectlyToCart()`

- **Purpose**: Ensures that items are added to the cart correctly.
- **Steps**:
    1. Log in to the application.
    2. Select 3 random items from the inventory.
    3. Navigate to the cart.
    4. Verify that the selected items are correctly reflected in the cart.
    5. Save the selected items to a JSON file using `JsonUtils.writeObjectToJsonFile()`.

#### `@Test(description = "finalizing the checkout process and checking totals", dependsOnMethods = {"checkItemsAddedCorrectlyToCart"})`
**Method**: `checkItemsTotalsAndSuccessfulCheckOutMessage()`

- **Purpose**: Validates the checkout process, ensuring that the total price is correct and that the success message appears.
- **Steps**:
    1. Run `checkItemsAddedCorrectlyToCart()` to ensure items are in the cart.
    2. Retrieve the items from the JSON file and calculate the expected total price.
    3. Navigate to the cart and then proceed to checkout.
    4. Fill in random user information using `faker`.
    5. Assert that the total price displayed matches the expected total price.
    6. Complete the checkout process and verify the success message matches the expected message: "Your order has been dispatched, and will arrive just as fast as the pony can get there!"

### Utilities Used:

- **`CustomAssert`**: A custom assertion utility that enhances the built-in `Assert` class, potentially with additional logging or functionality.
- **`DriverManager`**: Handles the WebDriver instance, ensuring proper management across test cases.
- **`JsonUtils`**: Provides functionality for reading from and writing to JSON files. In this case, it saves and retrieves item data for later validation.

### Dependencies

- **`@BeforeMethod`**: This annotation ensures the `initPages()` method runs before each test to initialize page objects.
- **`dependsOnMethods`**: The `checkItemsTotalsAndSuccessfulCheckOutMessage()` test depends on the `checkItemsAddedCorrectlyToCart()` method, ensuring that the cart has items before proceeding to checkout.

---

This file provides an overview of the code structure, the purpose of each method, and the testing logic employed to verify the functionality of the application. The tests are designed to validate key features such as inventory sorting, cart functionality, and the checkout process.