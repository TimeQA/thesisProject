package labirint.tests;

import labirint.config.*;
import labirint.drivers.BrowserstackMobileDriver;
import labirint.drivers.LocalAndRemoteWebDriver;
import labirint.drivers.LocalMobileDriver;
import labirint.helpers.AllureAttachments;
import labirint.helpers.DriverUtils;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Objects;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;

@ExtendWith({AllureJunit5.class})
public class TestBase {

    private static final WebConfig WEB_CONFIG = WebConfigReader.Instance.read();
    private static final String PLATFORM = System.getProperty("platform");
    private static final String ENV = System.getProperty("env");

    @BeforeAll
    static void beforeAll() throws Exception {
        selectDriver();
    }

    private static void selectDriver() throws Exception {

        switch (PLATFORM) {
            case "config/mobile":
                switch (ENV) {
                    case "browserstack":
                        browser = BrowserstackMobileDriver.class.getName();
                        break;
                    case "local":
                    case "real":
                        browser = LocalMobileDriver.class.getName();
                        break;
                    default:
                        throw new Exception("Wrong env");
                }
                break;
            case "ui":
                switch (ENV) {
                    case "remote":
                    case "local":
                        LocalAndRemoteWebDriver projectConfiguration = new LocalAndRemoteWebDriver(WEB_CONFIG);
                        projectConfiguration.projectConfig();
                        break;
                    default:
                        throw new Exception("Wrong env");
                }
                break;
            default:
                throw new Exception("Wrong platform");
        }
    }

    @BeforeEach
    public final void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        open();
    }

    @AfterEach
    public final void afterEach() {
        String sessionId = DriverUtils.getSessionId();

        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();

        if (Objects.equals(browser, "chrome")) {
            AllureAttachments.addBrowserConsoleLogs();
        }

        Selenide.closeWebDriver();

        if (PLATFORM.equals("ui") && ENV.equals("remote")) {
            AllureAttachments.addVideo(sessionId);
        } else if (PLATFORM.equals("config/mobile") && ENV.equals("browserstack")) {
            AllureAttachments.addVideoBrowserstack(sessionId);
        }
    }
}