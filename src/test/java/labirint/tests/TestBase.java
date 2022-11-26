package labirint.tests;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import labirint.helpers.AllureAttachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {
    @BeforeAll
    static void configure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
//        Configuration.headless = true;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--lang=en-en");

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();
        AllureAttachments.addPageSource();
        AllureAttachments.addVideo();
    }
}