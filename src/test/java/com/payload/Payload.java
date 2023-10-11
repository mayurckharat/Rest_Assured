package com.payload;

public enum Payload {

	createUser("{\r\n"
			+ "    \"name\": \"mayuresh\",\r\n"
			+ "    \"job\": \"leader\"\r\n"
			+ "}"),
	
	updateUser("{\r\n"
			+ "    \"name\": \"morpheus\",\r\n"
			+ "    \"job\": \"zion resident\"\r\n"
			+ "}"),
	
	updateFieldUser("{\r\n"
			+ "    \"name\": \"morpheus\",\r\n"
			+ "    \"job\": \"zion resident\"\r\n"
			+ "}");
	
	private String payload;
	
	Payload(String payload){
		this.payload = payload;
	}
	
	public String getPayload() {
		return payload;
	}
}
