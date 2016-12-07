package com.automation.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by priyankshah on 12/7/16.
 */
public class BaseTest {
    private static AppiumDriverLocalService service;
    private static Properties properties;
    private static String APPLICATION_NAME;
    private static String AUTOMATION_INSTRUMENTATION;
    private static int APPIUM_PORT;
    private static String WAIT_ACTIVITY;
    private static String NEW_COMMAND_TIMEOUT;
    private static String DEVICE_NAME;
    private static String BROWSER_NAME;
    private static String PLATFORM_NAME;
    private static String PLATFORM_VERSION;
    private static String DEVICE_READY_TIMEOUT;
    private static String PERFORMANCE_LOGGING;

    public AppiumDriver<? extends MobileElement> driver;

    private static void loadConfigProp(String propertyFileName) throws IOException {
        properties = new Properties();
        properties.load(ClassLoader.getSystemResource(propertyFileName).openStream());
        APPLICATION_NAME = properties.getProperty("application.path");
        WAIT_ACTIVITY = properties.getProperty("wait.activity");
        APPIUM_PORT = Integer.parseInt(properties.getProperty("appium.server.port"));
        AUTOMATION_INSTRUMENTATION = properties.getProperty("automation.instrumentation");
        DEVICE_NAME = properties.getProperty("device.name");
        BROWSER_NAME = properties.getProperty("browser.name");
        PLATFORM_NAME = properties.getProperty("platform.name");
        PLATFORM_VERSION = properties.getProperty("platform.version");
        NEW_COMMAND_TIMEOUT = properties.getProperty("new.command.timeout");
        DEVICE_READY_TIMEOUT = properties.getProperty("device.ready.timeout");
    }

    @BeforeClass
    public static void startAppium() {
        try {
            loadConfigProp("config.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String osName = System.getProperty("os.name").toLowerCase();

        String nodePath = null;
        String appiumPath = null;

        if (osName.contains("mac")) {
//            mac path
            nodePath = "/usr/local/bin/node";
            appiumPath = "/usr/local/lib/node_modules/appium/build/lib/main.js";
        } else if (osName.contains("linux")) {
//      linux path
            nodePath = System.getenv("HOME") + "/.linuxbrew/bin/node";
            appiumPath = System.getenv("HOME") + "/.linuxbrew/lib/node_modules/appium/build/lib/main.js";
        }
        if (appiumPath != null) {
            service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File(nodePath))
                    .usingPort(APPIUM_PORT)
                    .withAppiumJS(new File(appiumPath)));
        }
        service.start();
    }

    @AfterClass
    public static void stopAppium() {
        service.stop();
    }

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_INSTRUMENTATION);
        capabilities.setCapability(MobileCapabilityType.APP, new File(ClassLoader.getSystemResource(APPLICATION_NAME)
                .getFile()).getAbsolutePath());
        capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, WAIT_ACTIVITY);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
        URL serverUrl = new URL("http://localhost:" + APPIUM_PORT + "/wd/hub");
        driver = new AndroidDriver<AndroidElement>(serverUrl, capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
