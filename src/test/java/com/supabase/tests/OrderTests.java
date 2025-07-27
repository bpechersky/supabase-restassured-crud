package com.supabase.tests;

import com.supabase.utils.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OrderTests extends TestBase {

    @Test
    public void createUpdateDeleteOrder() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("total", 100.0);
        requestBody.put("status", "pending");

        int id = given()
            .queryParam("apikey", apiKey)
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/orders")
        .then()
            .statusCode(201)
            .body("status", equalTo("pending"))
            .extract().path("id");

        requestBody.put("status", "shipped");

        given()
            .queryParam("apikey", apiKey)
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .put("/orders/" + id)
        .then()
            .statusCode(200)
            .body("status", equalTo("shipped"));

        given()
            .queryParam("apikey", apiKey)
        .when()
            .delete("/orders/" + id)
        .then()
            .statusCode(anyOf(is(200), is(204)));
    }
}
