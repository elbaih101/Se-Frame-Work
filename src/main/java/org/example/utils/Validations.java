package org.example.utils;

import io.qameta.allure.Step;
import org.testng.Assert;

public class Validations {
	Validations() {
		super();
	}

	@Step("Validate True: {condition}")
	public static void validateTrue(boolean condition, String message) {
		Assert.assertTrue(condition, message);
	}
	@Step("Validate False: {condition}")
	public static void validateFalse(boolean condition, String message) {
		Assert.assertFalse(condition, message);
	}
	@Step("Validate Equals: {actual} = {expected}")
	public static void validateEquals(Object actual, Object expected, String message) {
		Assert.assertEquals(actual, expected, message);
	}
	@Step("Validate Not Equals: {actual} != {expected}")
	public static void validateNotEquals(Object actual, Object expected, String message) {
		Assert.assertNotEquals(actual, expected, message);
	}
	@Step("Validate Null: {expectedUrl}")
	public static void validateUrl(String expectedUrl) {
		Assert.assertEquals(BrowsersActions.getCurrentUrl(), expectedUrl);
	}
}
