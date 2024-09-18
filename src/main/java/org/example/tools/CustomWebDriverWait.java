package org.example.tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.Duration;

public class CustomWebDriverWait extends WebDriverWait
{
    Logger logger= LoggerFactory.getLogger(this.getClass());

    public CustomWebDriverWait(WebDriver driver, Duration timeout)
    {
        super(driver, timeout);
    }

    public CustomWebDriverWait(WebDriver driver, Duration timeout, Duration sleep)
    {
        super(driver, timeout, sleep);
    }

    public CustomWebDriverWait(WebDriver driver, Duration timeout, Duration sleep, Clock clock, Sleeper sleeper)
    {
        super(driver, timeout, sleep, clock, sleeper);
    }
    public CustomWebDriverWait(Duration timeout){
        super(DriverManager.getDriver(),timeout);
    }


    public  void sleep(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
