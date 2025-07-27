package com.supabase.tests;

import com.supabase.utils.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CommentTests extends TestBase {

    @Test
    public void createUpdateDeleteComment() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("text", "Nice post!");
        requestBody.put("author", "TestUser");

        int id = given()
            .queryParam("apikey", apiKey)
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/comments")
        .then()
            .statusCode(201)
            .body("text", equalTo("Nice post!"))
            .extract().path("id");

        requestBody.put("text", "Updated comment");

        given()
            .queryParam("apikey", apiKey)
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .put("/comments/" + id)
        .then()
            .statusCode(200)
            .body("text", equalTo("Updated comment"));

        given()
            .queryParam("apikey", apiKey)
        .when()
            .delete("/comments/" + id)
        .then()
            .statusCode(anyOf(is(200), is(204)));
    }
}
