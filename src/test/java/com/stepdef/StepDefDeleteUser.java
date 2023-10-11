package com.stepdef;
import static org.testng.Assert.assertEquals;

import com.rest.Wrapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefDeleteUser extends Wrapper{
   
    RequestSpecification request;
    Response response;

    @Given("^Delete user API is provided$")
    public void deleteUserAPIIsProvided() throws Exception {
        System.out.println("Started...");
    	prop.load(file);
        RestAssured.baseURI  = prop.getProperty("baseUrl");
        request = RestAssured.given();
    }
    
    @When("^User call Delete User API$")
    public void userCallDeleteUserAPI() throws Exception {
        response = request.delete("api/users/2");
    }

    @Then("^User is deleted with \"([^\"]*)\"$")
    public void userIsDeleted(int statusCode) throws Exception {
    	response.prettyPrint();
    	assertEquals(statusCode, response.statusCode());
    }
    
}
