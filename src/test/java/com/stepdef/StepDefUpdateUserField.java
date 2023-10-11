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

public class StepDefUpdateUserField extends Wrapper{
    
    RequestSpecification request;
    Response response;
    
    @Given("^Update user field API is provided with \"([^\"]*)\"$")
    public void updateUserFieldAPIIsProvided(String payloadType) throws Exception {
        System.out.println("Started...");
    	prop.load(file);
        RestAssured.baseURI  = prop.getProperty("baseUrl");
        Payload payload = Payload.valueOf(payloadType);
        request = RestAssured.given().header(prop.getProperty("contentTypeKey"), prop.getProperty("contentTypeValue"))
        		.body(payload.getPayload());
    }
    
    @When("^User call Update User field API$")
    public void userCallUpdateUserFieldAPI() throws Exception {
        response = request.patch("api/users/2");
    }

    @Then("^User field is updated with \"([^\"]*)\" \"([^\"]*)\"$")
    public void userFieldIsUpdated(int statusCode, String name) throws Exception {
    	response.prettyPrint();
    	assertEquals(statusCode, response.statusCode());
    }
  
}
