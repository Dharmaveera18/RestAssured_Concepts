package day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;



//U have to run json-server students.json in CMD
public class Create_student {

	@Test
	void test_create_Student(ITestContext context)
	{
		//Post Request
		//create request body
		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		String courseArr[] = {"C", "C++"};
		data.put("courses", courseArr);
		
            String id = given()
		    .contentType("application/json")
		    .body(data.toString())
		
		
		
		    .when()
	    	.post("http://localhost:3000/students")
		    .jsonPath().getString("id");
            
		
            
            context.getSuite().setAttribute("user_id", id);
	        System.out.println("Generated id is post :   "+id);

	}
	
	
	
}
