package day8;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;



public class Get_student {

	@Test
	void test_get_Student(ITestContext context)
	{
		
	  String id = (String) context.getSuite().getAttribute("user_id");     //This should come from create user request
		
		given()
		.contentType("application/json")
		.pathParam("id", id)
		
		
		.when()
		.get("http://localhost:3000/students/{id}")
		
		
		
		.then()
		.statusCode(200)
		.log().all();
		
		System.out.println("Generated id is Get :   "+id);
	}
	
	
	
}
