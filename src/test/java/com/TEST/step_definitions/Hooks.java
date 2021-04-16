package com.TEST.step_definitions;

import com.TEST.utilities.ConfigurationReader;
import io.cucumber.java.Before;

import static io.restassured.RestAssured.baseURI;

public class Hooks {


    @Before()
    public void api(){
        baseURI= ConfigurationReader.get("placeholder_api_url");
    }
}
