package com.zoop.qa.tests;

import com.zoop.qa.config.ConfigReader;
import com.zoop.qa.utils.ApiClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StatusCodesApiTest {

    @DataProvider(name = "statusCodes")
    public Object[][] statusCodes() {
        return new Object[][]{{200}, {301}, {404}, {500}};
    }

    @Test(dataProvider = "statusCodes", description = "Validate backend HTTP status code directly")
    public void shouldReturnExpectedApiStatusCode(int expectedCode) {
        Response response = ApiClient.getStatusCode(ConfigReader.get("baseUrl"), expectedCode);
        if (response.statusCode() != expectedCode) {
            response.then().log().all();
        }

        Assert.assertEquals(response.statusCode(), expectedCode,
                "Backend returned an unexpected status for /status_codes/" + expectedCode);
    }
}
