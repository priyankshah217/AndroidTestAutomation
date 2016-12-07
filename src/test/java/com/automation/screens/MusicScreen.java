package com.automation.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by priyankshah on 12/7/16.
 */
public class MusicScreen extends AbstractScreen {
    @FindBy(id = "iv_download")
    private MobileElement videoDownloadButton;
    @FindBy(id = "tv_low")
    private MobileElement lowQualityVideoButton;
    @FindBy(id = "tv_offline")
    private MobileElement offlineButton;
    @FindBy(id = "iv_close")
    private MobileElement closeButton;
    @FindBy(id = "iv_play")
    private MobileElement playVideoButton;
    @FindBy(id = "video_player_container")
    private MobileElement videoPlayerContainer;

    public MusicScreen(AppiumDriver<? extends MobileElement> driver) {
        super(driver);
    }

    public void downloadMusic() {
        waitForElementToBePresent(videoDownloadButton);
        videoDownloadButton.click();
        lowQualityVideoButton.click();
    }

    public boolean isVideoDownloaded() {
        return waitForElementToBePresent(offlineButton);
    }

    public boolean isVideoReplySuccessfully() {
        closeButton.click();
        playVideoButton.click();
        return waitForElementToBePresent(videoPlayerContainer);
    }
}
