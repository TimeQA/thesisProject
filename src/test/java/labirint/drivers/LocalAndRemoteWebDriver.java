package labirint.drivers;

import labirint.config.WebConfig;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class LocalAndRemoteWebDriver {

    private final WebConfig webConfig;

    public LocalAndRemoteWebDriver(final WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public final void projectConfig() {

        Configuration.browser = webConfig.browserName();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.baseUrl = webConfig.baseUrl();
        Configuration.pageLoadStrategy = "eager";

        MutableCapabilities capabilities = new DesiredCapabilities();

        if (webConfig.isRemote()) {
            Configuration.remote = webConfig.remoteUrl();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true,
                    "enableLog", true
            ));
        }

        switch (webConfig.browserName()) {
            case "chrome":
                setChromeOptions(capabilities);
                break;
            case "firefox":
                setFirefoxOptions(capabilities);
                break;
            default:
                Configuration.browserCapabilities = capabilities;
        }
    }

    public static void setChromeOptions(final MutableCapabilities capabilities) {

        Configuration.browserCapabilities = new ChromeOptions()
                .addArguments("enable-automation")
                .addArguments("--no-sandbox")
                .addArguments("--disable-extensions")
                .addArguments("--disable-dev-shm-usage")
                .addArguments("--disable-gpu")
                .merge(capabilities);
    }

    public static void setFirefoxOptions(final MutableCapabilities capabilities) {

        FirefoxProfile profile = new FirefoxProfile();
        Configuration.browserCapabilities = new FirefoxOptions()
                .setProfile(profile)
                .merge(capabilities);
    }
}