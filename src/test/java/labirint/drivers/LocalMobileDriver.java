package labirint.drivers;

import labirint.config.MobileConfig;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class LocalMobileDriver implements WebDriverProvider {

    private static final String APK_PATH = "src/test/resources/apk/chitai-gorod.apk";

    private final MobileConfig mobileConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());

    @Override
    @Nonnull
    public final WebDriver createDriver(final @Nonnull Capabilities capabilities) {
        Configuration.browserSize = null;

        UiAutomator2Options options = new UiAutomator2Options();

        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName(mobileConfig.platformName());
        options.setDeviceName(mobileConfig.device());
        options.setPlatformVersion(mobileConfig.osVersion());
        options.setApp(getApk().getAbsolutePath());
        options.setAppPackage(mobileConfig.appPackage());
        options.setAppActivity(mobileConfig.appActivity());

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    public final URL getAppiumServerUrl() {
        try {
            return new URL(mobileConfig.remoteUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private File getApk() {
        return new File(APK_PATH);
    }
}