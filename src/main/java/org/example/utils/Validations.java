package org.example.utils;

import org.testng.Assert;

public class Validations {
	Validations() {
		super();
	}

	public static void validateTrue(boolean condition, String message) {
		Assert.assertTrue(condition, message);
	}
	public static void validateFalse(boolean condition, String message) {
		Assert.assertFalse(condition, message);
	}
	public static void validateEquals(Object actual, Object expected, String message) {
		Assert.assertEquals(actual, expected, message);
	}
	public static void validateNotEquals(Object actual, Object expected, String message) {
		Assert.assertNotEquals(actual, expected, message);
	}
	public static void validateUrl(Object expected, String message) {
		Assert.assertTrue();
	}
}
