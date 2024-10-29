package day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;



public class Delete_student {

	@Test
	void test_delete_Student(ITestContext context)
	{
		String id = (String) context.getSuite().getAttribute("user_id");
		
		given()
		.contentType("application/json")
		.pathParam("id", id)
		
		.when()
		.delete("http://localhost:3000/students/{id}")
		
		.then()
		.statusCode(200)
		.log().all();
		
		System.out.println("Generated id is delete :   "+id);
	}
}
