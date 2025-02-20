package org.example.TestCases;


import io.qameta.allure.Step;
import org.example.drivers.Driver_Mode;
import org.example.drivers.Drivers;
import org.example.drivers.DriverManager;
import org.example.utils.BrowsersActions;
import org.example.utils.JsonUtils;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;


public class Hooks
{

    public final static String BaseUrl = JsonUtils.getValueFromJsonFile("src/main/resources/config.json", "BaseUrl");


    @BeforeMethod @Step("Opening the browser")
    public void initialize()
    {
        DriverManager.initializeDriver(Drivers.Chrome, Driver_Mode.Headless);

//       driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

    }



//    @BeforeTest()
//    public void runPostManCollection() {
//        File file = new File("src/main/resources/postman_collections/somePostManCollection.json");
//        NewMan.runPostmanTestCase(file.getAbsolutePath());
//
//    }

    @AfterMethod @Step("Closing the browser")
    public void tearDown() throws Exception
    {
        DriverManager.quitDriver();
    }


}

