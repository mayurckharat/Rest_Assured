package com.stepdef;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.pojos.User;
import com.rest.Wrapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefGetUsersList extends Wrapper {

	RequestSpecification request;
	Response response;

	@Given("^Get List of users API is provided$")
	public void getListOfUsersAPIIsProvided() throws Exception {
		System.out.println("Started...");
		prop.load(file);
		RestAssured.baseURI = prop.getProperty("baseUrl");
		request = RestAssured.given();
	}

	@When("^User call Get List of Users API$")
	public void userCallGetListOfUsersAPI() throws Exception {
		response = request.get("api/users");
	}

	@Then("^List of users will be shown with \"([^\"]*)\"$")
	public void listOfUsersWillBeShown(int statusCode) throws Exception {
		response.prettyPrint();
		Assert.assertEquals(statusCode, response.statusCode());
		/*
		 * List<User> userList = response.jsonPath().getList("data");
		 * 
		 * for (User user : userList) { System.out.println("*** "+user.toString()); }
		 */

		// int page = response.jsonPath().getInt("total");
		// System.out.println("********* "+page);
		JsonPath jsonPath = new JsonPath(response.body().asString());
		int totalRecords = jsonPath.getInt("total");
		System.out.println("Total records -> " + totalRecords);
		String fname = jsonPath.getString("data[0].first_name");
		System.out.println("First Name -> " + fname);
		/*
		 * List<User> list = jsonPath.getList("data", User.class);
		 * 
		 * for (User user : list) {
		 * System.out.println("Last Names -> "+user.getlName()); }
		 * 
		 * ObjectMapper mapper = new ObjectMapper(); mapper.readValue(list,
		 * User[].class);
		 */
		
		//WholeResponse wholeResponse = response.as(WholeResponse.class);
		JSONObject object = new JSONObject(response.body().asString());
		JSONArray jsonArray = (JSONArray) object.get("data");
		
		ObjectMapper objectMapper = new ObjectMapper();
		User[] asArray = objectMapper.readValue(jsonArray.toString(), User[].class);
		for(int i = 0;i<asArray.length;i++) {
			User user = asArray[i];
			System.out.println("ID --> "+user.getId());
			System.out.println("Email --> "+user.getEmail());
			System.out.println("F Name --> "+user.getFirst_name());
			System.out.println("L Name --> "+user.getLast_name());
			System.out.println("Avatar --> "+user.getAvatar());
		}
		System.out.println("************");
		ObjectMapper objectMapper1 = new ObjectMapper();
		CollectionType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, User.class);
		List<User> list = objectMapper1.readValue(jsonArray.toString(), javaType);
		for (User user : list) {
			System.out.println("ID --> "+user.getId());
			System.out.println("Email --> "+user.getEmail());
			System.out.println("F Name --> "+user.getFirst_name());
			System.out.println("L Name --> "+user.getLast_name());
			System.out.println("Avatar --> "+user.getAvatar());
		}
		
	}
	

}
