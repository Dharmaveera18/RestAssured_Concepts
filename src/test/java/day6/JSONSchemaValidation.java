package day6;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

//Need to run json-server store.json in cmd


//https://www.liquid-technologies.com/online-json-to-schema-converter
public class JSONSchemaValidation {

	
	@Test
	void testJsonSchemaValidation()
	{
		given()
		
		.when()
		.get("http://localhost:3000/store")
		
		.then()
		.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"));
	}
}
