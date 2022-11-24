package labirint.tests.api.specs;

import labirint.config.AppConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static labirint.helpers.CustomAllureListener.withCustomTemplates;

public class Specification {

    private static final RequestSpecification REQUEST_SPECIFICATION = new RequestSpecBuilder()
            .addFilter(withCustomTemplates())
            .addCookie(AppConfigReader.Instance.read().cookieName(), AppConfigReader.Instance.read().cookieValue())
            .setBaseUri("https://www.chitai-gorod.ru")
            .log(LogDetail.METHOD)
            .log(LogDetail.URI)
            .build();

    public static RequestSpecification requestSpec() {
        return REQUEST_SPECIFICATION;
    }

    private static final RequestSpecification REQUEST_SPECIFICATION_WEB_API = new RequestSpecBuilder()
            .addFilter(withCustomTemplates())
            .addCookie(AppConfigReader.Instance.read().cookieName(), AppConfigReader.Instance.read().cookieValue())
            .setBaseUri("https://webapi.chitai-gorod.ru/web")
            .log(LogDetail.METHOD)
            .log(LogDetail.URI)
            .build();

    public static RequestSpecification requestSpecWebApi() {
        return REQUEST_SPECIFICATION_WEB_API;
    }

    private static final ResponseSpecification RESPONSE_SPECIFICATION = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .build();

    public static ResponseSpecification responseSpec() {
        return RESPONSE_SPECIFICATION;
    }
}