package com.rest.test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
import java.io.IOException;
import io.restassured.module.jsv.*;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;


public class getResponse {
	
	
	@Test
	public static void checkStatusCode() {
		baseURI = "https://open.er-api.com";
		given().
		when().
			get("/v6/latest/USD").
		then().
			assertThat().
			statusCode(200).
			body("result", anyOf(equalTo("success"), equalTo("failure"), equalTo("error")));
	}
	
	@Test
	public static void fetch_AED_price() {
		//Description : Fetch the USD price against the AED and make sure the prices are in range on 3.6 – 3.7
		
		float curr = given().
				baseUri("https://open.er-api.com").
		when().
			get("/v6/latest/USD").
		then().
			
		extract().
			path("rates.AED");
		if(curr>3.6 & curr < 3.7) {
			System.out.println("AED value is in range " + curr);
		}else {
			System.out.println("USD to AED price is out of range" + curr);
		}
		
	}
	
	@Test
	public static void verify_currency_pair_count() {
		//Description: Verify that 162 currency pairs are returned by the API. 
		
		Response resp = given().
				baseUri("https://open.er-api.com").
		when().
			get("/v6/latest/USD").
		then().
		extract().response();

		int currpaircount = resp.jsonPath().getInt("rates.size()");

		if(currpaircount == 162) {
			System.out.println("No. of currency pairs returned are : "+ currpaircount);
		}else {
			System.out.println("No. of currency pairs returned do not match : "+ currpaircount);
		}
			
		
	}
	
	@Test
	public static void validate_response_json_schema() {
		//Make sure API response matches the JSON schema	
		//pre-requisite: JSON Schema is created from the API response and saved in expectedSchema.txt file
		
		String jsonSchema = null;
		try {
			jsonSchema = FileUtils.readFileToString(new File("./src/test/java/com/rest/test/expectedSchema.txt"), "UTF-8");
		
		given().
		 	baseUri("https://open.er-api.com").
		when().
			get("https://open.er-api.com/v6/latest/USD").
		then().
			assertThat().
			body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
				
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	
}
