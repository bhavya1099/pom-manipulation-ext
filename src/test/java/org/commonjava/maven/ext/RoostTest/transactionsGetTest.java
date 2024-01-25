// ********RoostGPT********
/*
Test generated by RoostGPT for test demoTestGitlab using AI Type Open AI and AI Model gpt-4

Test generated for /v1/accounts/{account-Id}/transactions_get for http method type GET in rest-assured framework

RoostTestHash=2b28de8802


*/

// ********RoostGPT********
package org.commonjava.maven.ext.RoostTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TransactionsGetTest {

    @Test
    public void transactionsGet_Test() {
        RestAssured.baseURI = System.getenv("BASE_URL");

        // Read CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/java/org/commonjava/maven/ext/RoostTest/transactionsGetTest.csv"))) {
            String headerLine = reader.readLine();
            String[] headers = headerLine.split(",");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                // Create a map of header to data
                Map<String, String> map = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    map.put(headers[i], data[i]);
                }

                Response response = given()
                        .pathParams(map)
                        .when()
                        .get("/v1/accounts/{account-Id}/transactions")
                        .then()
                        .extract().response();

                validateResponse(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateResponse(Response response) {
        int statusCode = response.getStatusCode();
        ValidatableResponse validatableResponse = response.then();
        switch (statusCode) {
            case 200:
                System.out.println("Description: Success");
                break;
            case 400:
                validateErrorResponse(validatableResponse);
                break;
            case 404:
                validateNotFoundResponse(validatableResponse);
                break;
            case 422:
                validateUnprocessableEntityResponse(validatableResponse);
                break;
            default:
                throw new AssertionError("Unexpected status code: " + statusCode);
        }
    }

    private void validateErrorResponse(ValidatableResponse response) {
        response.body("error", is("Bad Request"));
    }

    private void validateNotFoundResponse(ValidatableResponse response) {
        response.body("error", is("Not Found"));
    }

    private void validateUnprocessableEntityResponse(ValidatableResponse response) {
        response.body("error", is("Unprocessable Entity"));
    }
}
