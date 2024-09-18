package org.example.TestCases;


import org.example.enums.Driver_Mode;
import org.example.enums.Drivers;
import org.example.tools.DriverManager;
import org.example.tools.JsonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;


public class Hooks
{

    WebDriver driver;
    public final static String BaseUrl = JsonUtils.getValueFromJsonFile("src/main/resources/config.json", "BaseUrl");


    @BeforeMethod
    public void initialize()
    {
        DriverManager.initializeDriver(Drivers.Chrome, Driver_Mode.UI);
        driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ;

//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        getUrl();

    }

    private void getUrl()
    {
        int count = 3;
        while (count != 0)
        {
            try
            {
                DriverManager.getDriver().get(BaseUrl);
                break;
            } catch (WebDriverException e)
            {
                if (e.getMessage().contains("ERR_CONNECTION_TIMED_OUT"))
                {
                    getUrl();
                    count--;
                } else
                {
                    throw new WebDriverException(e);
                }
            }
        }
    }

//    @BeforeTest()
//    public void runPostManCollection() {
//        File file = new File("src/main/resources/postman_collections/somePostManCollection.json");
//        NewMan.runPostmanTestCase(file.getAbsolutePath());
//
//    }

    @AfterMethod
    public void tearDown() throws Exception
    {

        try
        {
            Thread.sleep(800);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        DriverManager.quitDriver();
    }


}

