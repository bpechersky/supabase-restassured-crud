package com.supabase.tests;

import com.supabase.utils.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ProductTests extends TestBase {

    @Test
    public void createUpdateDeleteProduct() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Test Product");
        requestBody.put("price", 9.99);

        int id = given()
            .queryParam("apikey", apiKey)
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/products")
        .then()
            .statusCode(201)
            .body("name", equalTo("Test Product"))
            .extract().path("id");

        requestBody.put("name", "Updated Product");

        given()
            .queryParam("apikey", apiKey)
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .put("/products/" + id)
        .then()
            .statusCode(200)
            .body("name", equalTo("Updated Product"));

        given()
            .queryParam("apikey", apiKey)
        .when()
            .delete("/products/" + id)
        .then()
            .statusCode(anyOf(is(200), is(204)));
    }
}
