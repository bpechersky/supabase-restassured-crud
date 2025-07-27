package com.supabase.tests;

import com.supabase.utils.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserTests extends TestBase {

    @Test
    public void getAllUsers() {
        given()
            .queryParam("apikey", apiKey)
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }
}
