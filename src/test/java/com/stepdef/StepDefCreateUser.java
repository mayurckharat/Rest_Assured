package com.stepdef;
import org.testng.Assert;

import com.payload.Payload;
import com.pojos.CreatedUser;
import com.rest.Wrapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefCreateUser extends Wrapper{
    
    RequestSpecification request;
    Response response;
    
    @Given("^1Create user API is provided with \"([^\"]*)\"$")
    public void createUserAPIIsProvided(String payloadType) throws Exception {
        System.out.println("Started...");
    	prop.load(file);
        RestAssured.baseURI  = prop.getProperty("baseUrl");
        Payload payload = Payload.valueOf(payloadType);
        request = RestAssured.given().header(prop.getProperty("contentTypeKey"), prop.getProperty("contentTypeValue"))
        		.body(payload.getPayload());
    }
    
    @When("^1User call Create User API$")
    public void userCallCreateUserAPI() throws Exception {
        response = request.post("api/users");
    }

    @Then("^1New user details will be shown with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void newUserDetailsWillBeShown(int statusCode, String name) throws Exception {
    	//response.prettyPrint();
    	Assert.assertEquals(response.statusCode(), statusCode);
    	CreatedUser user = response.as(CreatedUser.class);
    	Assert.assertEquals(user.getName(), name);
    }
  
}
