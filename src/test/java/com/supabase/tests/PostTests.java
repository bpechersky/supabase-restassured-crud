package com.supabase.tests;

import com.supabase.utils.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostTests extends TestBase {

    @Test
    public void createUpdateDeletePost() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "My Post");
        requestBody.put("content", "Hello world");

        int id = given()
            .queryParam("apikey", apiKey)
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("My Post"))
            .extract().path("id");

        requestBody.put("title", "Updated Post");

        given()
            .queryParam("apikey", apiKey)
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .put("/posts/" + id)
        .then()
            .statusCode(200)
            .body("title", equalTo("Updated Post"));

        given()
            .queryParam("apikey", apiKey)
        .when()
            .delete("/posts/" + id)
        .then()
            .statusCode(anyOf(is(200), is(204)));
    }
}
