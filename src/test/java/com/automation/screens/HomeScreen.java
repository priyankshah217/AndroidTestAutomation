package com.automation.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by priyankshah on 12/7/16.
 */
public class HomeScreen extends AbstractScreen {

    @FindBy(id = "iv_close")
    private MobileElement dialogCloseButton;

    @FindBy(id = "llContent")
    private MobileElement contentMessage;

    @FindBy(id = "content")
    private MobileElement contentArea;

    @FindBy(id = "iv_menu")
    private MobileElement sideMenu;

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Music\")")
    private MobileElement musicMenu;

    @FindBy(id = "iv_search")
    private MobileElement searchMediaButton;

    @FindBy(id = "search_box")
    private MobileElement searchMediaTextBox;

    @FindBy(id = "search_result")
    private List<MobileElement> searchList;


    public HomeScreen(AppiumDriver<? extends MobileElement> driver) {
        super(driver);
    }

    public MusicScreen navigateToMusicScreen() {
        dialogCloseButton.click();
        waitForElementToBePresent(contentMessage);
        contentArea.click();
        sideMenu.click();
        musicMenu.click();
        return new MusicScreen(driver);
    }


    public MusicScreen searchMusic(String songName) {
        dialogCloseButton.click();
        waitForElementToBePresent(contentMessage);
        contentArea.click();
        searchMediaButton.click();
        searchMediaTextBox.sendKeys(songName);
        searchList.get(0).click();
        return new MusicScreen(driver);
    }
}
