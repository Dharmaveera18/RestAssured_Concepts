package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;


public class WaysToCreatePostRequestBody {

	String id;
	
	/*
	 1) Hashmap //Suitable for small set of data
     2) using org.json
     3) using POJO (Plain Old Java Object)
     4) using external json file
	 */
	
	
	// 1) Hashmap //Suitable for small set of data
	//@Test(priority = 1)
	void testPostUsing_HashMap()
	{
		HashMap data = new HashMap();
		data.put("name", "scott");
		data.put("location", "France");
		data.put("phone", "123456");
		
		String courseArr[] = {"C", "C++"};
		data.put("courses", courseArr);
		
		id=given()
		.contentType("application/json")
		.body(data)  //Body is Pre-requisite
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		
		.body("name", equalTo("scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("C++"))
		
		.header("Content-Type", "application/json")
		.extract()
        .path("id");
		
		System.out.println("Extracted ID: " + id);
		
	}
	
	//org.json  //need to be add org.json dependency
	
	
	//@Test(priority = 1)
	void testPostUsing_Org_Json()
	{
		
		JSONObject data = new JSONObject();
		data.put("name", "scott");
		data.put("location", "France");
		data.put("phone", "123456");
		
		String courseArr[] = {"C", "C++"};
		data.put("courses", courseArr);
		
	
		
		id=given()
		.contentType("application/json")
		.body(data.toString())
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		
		.body("name", equalTo("scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("C++"))
		
		.header("Content-Type", "application/json")
		.extract()
        .path("id");
		
		System.out.println("Extracted ID: " + id);
		
	}
	
	//3.Using POJO ==> Plane Old Java Object
	
	//@Test(priority = 1)
	void testPostUsing_PojoClass()
	{
		
		Pojo_PostRequest data = new Pojo_PostRequest();
		
		data.setName("scott");
		data.setLocation("France");
		data.setPhone("123456");
		
		String courseArr[] = {"C", "C++"};
		data.setCourses(courseArr);
		
		id=given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		
		.body("name", equalTo("scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("C++"))
		
		.header("Content-Type", "application/json")
		.extract()
        .path("id");
		
		System.out.println("Extracted ID: " + id);
		
	}
	
	
	// 4) using external json file
	@Test(priority = 1)
	void testPostUsing_External_JsonFile() throws FileNotFoundException
	{
		
		File f = new File("C:\\Users\\dharmaveerah\\eclipse-workspace\\RestAssured_API\\src\\test\\resources\\body.json");
		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		
		
		id=given()
		.contentType("application/json")
		.body(data.toString())
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		
		.body("name", equalTo("scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("C++"))
		
		.header("Content-Type", "application/json")
		.extract()
        .path("id");
		
		System.out.println("Extracted ID: " + id);
		
	}
	
	
	@Test(priority = 2)
	void testDelete()
	{
		given()
		.contentType("application/json")
		
		.when()
		.delete("http://localhost:3000/students/" + id)
		
		.then()
		.statusCode(200)
		.body("id", equalTo(id)); // Assuming the response has an id field
	}
	
}
