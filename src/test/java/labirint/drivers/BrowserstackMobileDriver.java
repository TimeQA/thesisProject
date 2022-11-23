package labirint.drivers;

import labirint.config.MobileConfig;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.String.format;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class BrowserstackMobileDriver implements WebDriverProvider {

    private static final String APK_UPLOAD_ENDPOINT = "https://%s:%s@api-cloud.browserstack.com/app-automate/upload";
    private static final String APK_PATH = "src/test/resources/apk/chitai-gorod.apk";

    private final MobileConfig mobileConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());

    @Override
    @Nonnull
    public final WebDriver createDriver(final @Nonnull Capabilities capabilities) {

        Configuration.browserSize = null;

        MutableCapabilities mutableCapabilities = new MutableCapabilities();

        mutableCapabilities.merge(capabilities);
        mutableCapabilities.setCapability("browserstack.user", mobileConfig.browserstackUser());
        mutableCapabilities.setCapability("browserstack.key", mobileConfig.browserstackKey());
        mutableCapabilities.setCapability("appUrl", uploadAPK());
        mutableCapabilities.setCapability("device", mobileConfig.device());
        mutableCapabilities.setCapability("os_version", mobileConfig.osVersion());
        mutableCapabilities.setCapability("project", mobileConfig.project());
        mutableCapabilities.setCapability("build", mobileConfig.build());
        mutableCapabilities.setCapability("name", mobileConfig.name());
        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

    public final URL getBrowserstackUrl() {
        try {
            return new URL(mobileConfig.remoteUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Nonnull
    private String uploadAPK() {
        return given()
                .multiPart("file", new File(APK_PATH))
                .when()
                .post(format(APK_UPLOAD_ENDPOINT, mobileConfig.browserstackUser(), mobileConfig.browserstackKey()))
                .then()
                .statusCode(200)
                .body("app_url", is(notNullValue()))
                .extract().path("app_url").toString();
    }
}