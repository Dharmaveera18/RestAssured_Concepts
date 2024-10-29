package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;


public class Update_student {

	@Test
	void test_update_Student(ITestContext context)
	{
		String id = (String) context.getSuite().getAttribute("user_id");
		
		Faker faker = new Faker();
		

		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		String courseArr[] = {"C", "C++"};
		data.put("courses", courseArr);
		
             given()
		    .contentType("application/json")
		    .body(data.toString())
		    .pathParam("id",id)
		
		
		    .when()
	    	.put("http://localhost:3000/students/{id}")
		    
	    	.then()
	    	.statusCode(200)
		    .log().all();
	       
             
             System.out.println("Generated id is put :   "+id); 
             
	}
	
	
	
	
}
