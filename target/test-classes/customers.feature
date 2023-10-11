Feature: First API Testing

@smoke @regression
Scenario Outline: Get List of Users
	Given Get List of users API is provided
	When User call Get List of Users API
  	Then List of users will be shown with "<statusCode>"
  	
  	Examples:
  	|statusCode|
  	|200	   |
  	
@regression
Scenario Outline: Get User Details
	Given Get user API is provided
	When User call Get User API
	Then User details will be shown with "<statusCode>" "<id>" "<firstName>" "<lastName>"
	
	Examples:
	|statusCode|id|firstName|lastName|
	|200	   |2 |Janet	|Weaver  |

@smoke	@regression @current
Scenario Outline: Create New User
	Given Create user API is provided with "<payloadType>"
	When User call Create User API
	Then New user details will be shown with "<statusCode>" and "<name>"

	Examples:
	|payloadType|statusCode|name	|
	|createUser |201	   |Mayur|
	
@regression
Scenario Outline: Update User
	Given Update user API is provided with "<payloadType>"
	When User call Update User API
	Then User details are updated with "<statusCode>" "<name>" and "<job>"
	
	Examples:
	|payloadType|statusCode|name	|job		  |
	|updateUser |200	   |morpheus|zion resident|
	
@regression
Scenario Outline: Update User Field
	Given Update user field API is provided with "<payloadType>"
	When User call Update User field API
	Then User field is updated with "<statusCode>" "<name>"
	
	Examples:
	|payloadType	|statusCode|name	|
	|updateFieldUser|200	   |morpheus|
	
@regression
Scenario Outline: Delete User
	Given Delete user API is provided
	When User call Delete User API
	Then User is deleted with "<statusCode>"
	
	Examples:
	|statusCode|
	|204	   |