package com.stepdef;
import org.testng.Assert;

import com.rest.Wrapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefGetSingleUser extends Wrapper{
   
    RequestSpecification request;
    Response response;
    
    @Given("^Get user API is provided$")
    public void getUserAPIIsProvided() throws Exception {
        System.out.println("Started...");
    	prop.load(file);
        RestAssured.baseURI  = prop.getProperty("baseUrl");
        request = RestAssured.given();
    }
    
    @When("^User call Get User API$")
    public void userCallGetUserAPI() throws Exception {
        response = request.get("api/users/2");
    }

    @Then("^User details will be shown with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void userDetailsWillBeShown(int statusCode, String id, String firstName, String lastName) throws Exception {
    	//System.out.println("--> "+response.prettyPrint());
    	response.prettyPrint();
    	Assert.assertEquals(statusCode, response.statusCode());
    }
 
}
