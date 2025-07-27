package com.supabase.utils;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestBase {
    protected String apiKey = System.getenv("SUPABASE_API_KEY");
    protected String baseUrl = "https://vhvxhqxvooujrtxpwmib.supabase.co/rest/v1";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseUrl;
    }
}
