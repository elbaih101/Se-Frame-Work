package org.example.drivers;



import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.HashMap;

/**
 * <h>DriverManager</h>
 * the driver manager class handles the webDrivers for the parralel excution of the project by creating a local instance of a web driver
 * and using it across the project
 */
public class DriverManager {
	private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

	/**
	 * retreeaves the thread webDriver
	 *
	 * @return WebDriver the local Thread WebDriver
	 */
	public static WebDriver getDriver() {
		return driverThreadLocal.get();
	}

	/**
	 * stores  the driver provided in its local thread reference
	 *
	 * @param driver WebDriver inistantiated in the thread
	 */
	public static void setDriver(WebDriver driver) {
		driverThreadLocal.set(driver);
	}


	public static void quitDriver() {
		WebDriver driver = getDriver();
		if (driver != null) {
			driver.quit();
			driverThreadLocal.remove();
		}
	}

	/**
	 * this function initialize a local driver of any type provided then it stores it in its local thread so its thread safe
	 *
	 * @param driverName enum Drivers driver name provided from list of drivers
	 * @param mode       enum Driver_Mode the running mode of the driver
	 */
	public static void initializeDriver(Drivers driverName, Driver_Mode mode) {
		WebDriver driver;
		switch (driverName) {
			case Chrome -> {
//
				ChromeOptions op = getChromeOptions(mode);
				driver = new ChromeDriver(op);
			}
			case Edge -> {

				EdgeOptions op = getEdgeOptions(mode);
				driver = new EdgeDriver(op);

			}
			case FireFox -> {

				FirefoxOptions op = getFirefoxOptions(mode);
				driver = new FirefoxDriver(op);
			}
			case Safari -> {

				SafariOptions op = new SafariOptions();
				driver = new SafariDriver(op);
			}
			default -> throw new IllegalStateException("Unexpected value: " + driverName);
		}
		driver.manage().window().setSize(new Dimension(1920, 1080));
		setDriver(driver);


	}

	private static FirefoxOptions getFirefoxOptions(Driver_Mode mode) {
		FirefoxOptions op = new FirefoxOptions();
		op.addArguments("start-maximized", "--ignore-certificate-errors", "--ignore-urlfetcher-cert-requests", "--guest");
		if (mode.equals(Driver_Mode.Headless))
			op.addArguments("headless");
		return op;
	}

	private static EdgeOptions getEdgeOptions(Driver_Mode mode) {
		EdgeOptions op = new EdgeOptions();
		op.addArguments("start-maximized", "--ignore-certificate-errors", "--ignore-urlfetcher-cert-requests", "--guest");
		op.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		if (mode.equals(Driver_Mode.Headless))
			op.addArguments("headless");
		return op;
	}

	private static ChromeOptions getChromeOptions(Driver_Mode mode) {
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--start-maximized", "--ignore-certificate-errors", "--ignore-urlfetcher-cert-requests","--disable-infobars","--guest");
		if (mode.equals(Driver_Mode.Headless))
			op.addArguments("--headless");
//		op.addArguments("profile.password_manager_enabled=false");
		op.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//		op.setBinary("/usr/bin");
//		op.addArguments("--no-sandbox");
//		op.addArguments("--disable-dev-shm-usage");
		return op;
	}

	/**
	 * gets the webDriver Type
	 *
	 * @param driver the webDriver
	 * @return the webDriver tyoe
	 */
	public static Drivers getWebDriverType(WebDriver driver) {
		Drivers driverType = null;

		if (driver instanceof ChromeDriver) {
			driverType = Drivers.Chrome;
		} else if (driver instanceof EdgeDriver) {
			driverType = Drivers.Edge;
		} else if (driver instanceof FirefoxDriver) {
			driverType = Drivers.FireFox;
		} else if (driver instanceof SafariDriver) {
			driverType = Drivers.Safari;
		}

		return driverType;
	}

	/**
	 * function to return pre sett edge printing and download options
	 *
	 * @return EdgeOptions pre configured
	 */
	public static EdgeOptions edgePrintingAndDownloadOptions() {
		EdgeOptions options = new EdgeOptions();
		options.setExperimentalOption("prefs", new String[]{"download.default_directory", "download_path"});

		//printer config
		options.addArguments("--kiosk-printing");
		//download config    // relates to this import ::   import com.microsoft.edge.seleniumtools.EdgeOptions;
		HashMap<String, Object> edgePrefs = new HashMap<>();
		edgePrefs.put("download.default_directory", "F:\\java maven projects\\Nazeel-Project\\src\\main\\resources\\downloaded");
		options.setExperimentalOption("prefs", edgePrefs);

		options.addArguments("print.printer_Microsoft_Print_to_PDF.print_to_filename", "F:\\java maven projects\\Nazeel-Project\\src\\main\\resources\\downloaded");
		return options;

	}
}

