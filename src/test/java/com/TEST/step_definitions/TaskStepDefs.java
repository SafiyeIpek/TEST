package com.TEST.step_definitions;

import com.TEST.utilities.ConfigurationReader;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
public class TaskStepDefs {

    int postId = 1;
    Object userId;
    Response response;

    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String name) {

        Response response1 = given().contentType("application/json")
                .and().queryParam("name", name)
                .when().get("users");
        List<Map<String, Object>> details = response1.body().as(List.class);
        userId = details.get(0).get("id");
    }

    @When("the user make a post")
    public void the_user_make_a_post() {

        String jsonBody = "{\"title\": \"foo\",\n" +
                "    \"body\": \"bardsflvldfbkjdlfbjsljblsdjblfsjd\",\n" +
                "    \"userId\": " + userId + "}";
        response = given().contentType("application/json")
                .and().body(jsonBody)
                .when().post("posts");
    }

    @Then("the server should return {string} response")
    public void theServerShouldReturnStatus(String status) {

        int statusCode = 0;
        switch (status.toLowerCase()) {
            case "ok":
                statusCode = 200;
                break;
            case "created":
                statusCode = 201;
                break;
            case "not found":
                statusCode = 404;
                break;
        }
        Assert.assertEquals(statusCode, response.statusCode());
    }

    @And("response should be in {string} format")
    public void responseShouldBeInFormat(String format) {

        Assert.assertTrue(response.contentType().contains(format));
    }

    @When("the user update the post")
    public void theUserUpdateThePost() {

        String jsonbody = "{\n" +
                "    \"id\": " + postId + ",\n" +
                "    \"title\": \"update\",\n" +
                "    \"body\": \"update needed\",\n" +
                "    \"userId\": " + userId + "\n" +
                "  }";
        response = given().contentType("application/json")
                .and().body(jsonbody)
                .and().pathParam("postId", postId)
                .when().put("posts/{postId}");
    }

    @When("the user patch the post")
    public void theUserPatchThePost() {

        Map<String, String> patchTitle = new HashMap<>();
        patchTitle.put("title", "changing the title");
        response = given().contentType("application/json")
                .and().body(patchTitle)
                .and().pathParam("postId", postId)
                .when().patch("posts/{postId}");
    }

    @When("the user delete the post")
    public void theUserDeleteThePost() {

        response = given().contentType("application/json")
                .and().pathParam("postId", postId)
                .when().delete("posts/{postId}");
    }

    @When("using wrong endpoints")
    public void usingWrongEndpoints() {

        response = when().get("wrongEndPoint");
    }

    @When("the user make the comment")
    public void the_user_make_the_comment() {

        String jsonbody = " {\n" +
                "        \"postId\": \"+postId+\",\n" +
                "        \"name\": \"Safiye\",\n" +
                "        \"email\": \"Safiye@gardner.biz\",\n" +
                "        \"body\": \"test project\"\n" +
                "    }";

        response = given().contentType("application/json")
                .and().body(jsonbody)
                .when().post("comments");

    }
}
