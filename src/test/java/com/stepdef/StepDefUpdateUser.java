package com.stepdef;
import static org.testng.Assert.assertEquals;

import com.payload.Payload;
import com.rest.Wrapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefUpdateUser extends Wrapper{
    
    RequestSpecification request;
    Response response;
    
    @Given("^Update user API is provided with \"([^\"]*)\"$")
    public void updateUserAPIIsProvided(String payloadType) throws Exception {
        System.out.println("Started...");
    	prop.load(file);
        RestAssured.baseURI  = prop.getProperty("baseUrl");
        Payload payload = Payload.valueOf(payloadType);
        request = RestAssured.given().header(prop.getProperty("contentTypeKey"), prop.getProperty("contentTypeValue"))
        		.body(payload.getPayload());
    }
    
    @When("^User call Update User API$")
    public void userCallUpdateUserAPI() throws Exception {
        response = request.put("api/users/2");
    }

    @Then("^User details are updated with \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userDetailsAreUpdated(int statusCode, String name, String job) throws Exception {
    	response.prettyPrint();
    	assertEquals(statusCode, response.statusCode());
    }
    
}
