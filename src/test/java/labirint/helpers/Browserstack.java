package labirint.helpers;

import labirint.config.MobileConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {

    public static String videoUrl(final String sessionId) {

        MobileConfig mobileConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic(mobileConfig.browserstackUser(), mobileConfig.browserstackKey())
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}