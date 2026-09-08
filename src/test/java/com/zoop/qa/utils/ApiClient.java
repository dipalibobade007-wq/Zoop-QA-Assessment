package com.zoop.qa.utils;

import com.zoop.qa.config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

public final class ApiClient {
    private ApiClient() {}

    public static Response getStatusCode(String baseUrl, int expectedCode) {
        int timeoutMillis = ConfigReader.getInt("apiTimeoutSeconds") * 1_000;
        return RestAssured.given()
                .baseUri(baseUrl)
                .redirects().follow(false)
                .relaxedHTTPSValidation()
                .config(RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", timeoutMillis)
                        .setParam("http.socket.timeout", timeoutMillis)
                        .setParam("http.connection-manager.timeout", (long) timeoutMillis)))
                .when()
                .get("/status_codes/" + expectedCode)
                .then()
                .extract()
                .response();
    }
}
