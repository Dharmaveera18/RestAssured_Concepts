package day1;

import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 
        given()  ==> Headers, Cookies,..common information and so on.
		
		.when()  ==> Requests
	
		.then()  ==> Validations
 
 */


public class HTTPRequests {

	int id;
	
	
	
	@Test(priority = 1)
	void getUsers()
	{
		given()
		.contentType("application/json")
		
		.when()
	    .get("https://reqres.in/api/users?page=2")
	     
	    
		.then()
		.statusCode(200)
		//.body("page", equalTo(2))
		.log().all(); //It will prints entire response 
	}
	
	
	
	@Test(priority = 2)
	void create_User()
	{
		
		HashMap data = new HashMap();
		data.put("name", "Dharma");
		data.put("job", "Learner_1");
		
		
		
	  id=given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		
		
		/*
		.then()
		.statusCode(201)
		.log().all();
		*/
	}
	
	@Test(priority = 3, dependsOnMethods = {"create_User"})
	void update_User()
	{
		HashMap data = new HashMap();
		data.put("name", "Veera");
		data.put("job", "Learner_2");
		
		
		
	    given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.put("https://reqres.in/api/users/"+ id)
		
		.then()
		.statusCode(200)
		.log().all();
		
	}
	
	
	@Test(priority = 4, dependsOnMethods = {"create_User"})
	void delete_User()
	{
		given()
		
		.when()
		.delete("https://reqres.in/api/users/"+ id)
		
		.then()
		.statusCode(204)
		.log().all();
	}
	
	
	
	
	
	
	
}
