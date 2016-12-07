package com.automation.tests;


import com.automation.screens.HomeScreen;
import com.automation.screens.MusicScreen;
import org.junit.Assert;
import org.junit.Test;


public class DownloadMediaTest extends BaseTest {
    private HomeScreen homeScreen;
    private MusicScreen musicScreen;

    @Test
    public void testDownloadRandomAudio() {
        homeScreen = new HomeScreen(driver);
        musicScreen = homeScreen.navigateToMusicScreen();
        musicScreen.downloadMusic();
        Assert.assertTrue(musicScreen.isVideoDownloaded());
        Assert.assertTrue(musicScreen.isVideoReplySuccessfully());
    }

    @Test
    public void testSearchAndDownloadAudio() {
        homeScreen = new HomeScreen(driver);
        musicScreen = homeScreen.searchMusic("Nashe si chadh gayi");
        musicScreen.downloadMusic();
        Assert.assertTrue(musicScreen.isVideoDownloaded());
        Assert.assertTrue(musicScreen.isVideoReplySuccessfully());
    }

}
