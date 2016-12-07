package com.automation.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by priyankshah on 12/7/16.
 */
public class AbstractScreen {

    public WebDriverWait wait;
    public AppiumDriver driver;

    public AbstractScreen(AppiumDriver<? extends WebElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean waitForElementToBePresent(MobileElement mobileElement) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 120);
            wait.until(ExpectedConditions.elementToBeClickable(mobileElement));
            driver.manage().timeouts().implicitlyWait(19, TimeUnit.SECONDS);
        } catch (TimeoutException ex) {
            return false;
        }
        return true;
    }
}
