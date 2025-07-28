package com.supabase.tests;

import com.supabase.utils.TestBase;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class OrderTests extends TestBase {

    @BeforeClass
    public void setupParser() {
        // Treat responses without content-type as JSON
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void createOrder() {
        String requestBody = """
            {
              "user_id": "1ec6bfb7-7f14-4271-aca6-0c8faa42efdb",
              "product_id": "268e9d13-45a5-4dce-b4d6-ed86b3486c62",
              "quantity": "1"
            }
            """;

        given()
                .queryParam("apikey", apiKey)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/orders")
                .then()
                .statusCode(201)
                .log().all();
    }
}
