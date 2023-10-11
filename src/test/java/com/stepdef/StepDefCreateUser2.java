package com.stepdef;
import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payload.Payload;
import com.pojos.Create;
import com.pojos.CreatedUser;
import com.rest.Wrapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefCreateUser2 extends Wrapper{
    
    RequestSpecification request;
    Response response;
    
    @Given("^Create user API is provided with \"([^\"]*)\"$")
    public void createUserAPIIsProvided(String payloadType) throws Exception {
        System.out.println("Started...");
    	prop.load(file);
        RestAssured.baseURI  = prop.getProperty("baseUrl");
        //Payload payload = Payload.valueOf(payloadType);
        ObjectMapper objectMapper = new ObjectMapper();
        Create create = new Create();
        create.setName("Amol");
        create.setJob("IT");
        String reqString = objectMapper.writeValueAsString(create);
        request = RestAssured.given().header(prop.getProperty("contentTypeKey"), prop.getProperty("contentTypeValue"))
        		.body(reqString);
    }
    
    @When("^User call Create User API$")
    public void userCallCreateUserAPI() throws Exception {
        response = request.post("api/users");
    }

    @Then("^New user details will be shown with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void newUserDetailsWillBeShown(int statusCode, String name) throws Exception {
    	response.prettyPrint();
    	System.out.println("Status Code --> "+response.statusCode());
    	System.out.println("JSON string --> "+response.body().asPrettyString());
    	Assert.assertEquals(response.statusCode(), statusCode);
    	//CreatedUser user = response.as(CreatedUser.class);
    	ObjectMapper objectMapper = new ObjectMapper();
    	CreatedUser user =  objectMapper.readValue(response.getBody().prettyPrint(), CreatedUser.class);
    	System.out.println("Name --> "+user.getName());
    	System.out.println("Job --> "+user.getJob());
    	System.out.println("Id --> "+user.getId());
    	System.out.println("Created At --> "+user.getCreatedAt());
    	Assert.assertEquals(user.getName(), name);
    }
  
}
