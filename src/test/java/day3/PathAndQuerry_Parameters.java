package day3;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PathAndQuerry_Parameters {

	
	//Path AND Query Parameters
	@Test
	void testpathAndQuerryParameters()
	{
		
		//Prerequisites
		//path and query
		
		
		given()
		.pathParam("mypath2", "users")
		.queryParam("page", 2)
		.queryParam("id", 2)
		
		
		.when()
		.get("https://reqres.in/api/{mypath2}")
		
		
		.then()
		.statusCode(200)
		.log().all();
		
	}
}
