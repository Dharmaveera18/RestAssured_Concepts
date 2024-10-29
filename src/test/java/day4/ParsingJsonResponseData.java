package day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Parsing ==> Traversing throughout the response data
public class ParsingJsonResponseData {

	//@Test(priority  =1)
	void test_jsonResponse()
	{
		/*    //Approach 1 : Validation
		given()
		.contentType("application/json")
		
		
		.when()
		.get("http://localhost:3000/store")
		
		
		.then()
		.statusCode(200)
		.header("Content-Type", "application/json")
		.body("book[3].title", equalTo("sayings of the century"));
		*/
		
		//  //Approach 2 : Validation (Preferable) you can use if condition, loops for validation
		
		
		Response res = given()
		//.contentType("application/json")
		.contentType(ContentType.JSON)
		
		.when()
		.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		String bookname = res.jsonPath().get("book[3].title").toString();
		 
		Assert.assertEquals(bookname, "sayings of the century");
		
		
		
		
	}
	
	
	@Test
	void test_jsonResponseBodyData()
	{
		
		
		
		//Approach 2 : Validation (Preferable) you can use if condition, loops for validation
		
		
		Response res = given()
		.contentType(ContentType.JSON)
		
		.when()
		.get("http://localhost:3000/store");
		
		/*Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		String bookname = res.jsonPath().get("book[3].title").toString();
		 
		Assert.assertEquals(bookname, "sayings of the century");
		*/
		
		
		
		
		
		
		
		//Validation 1
		//I have books from each object i want capture and print the titles
		//Searching the books in the response body          
		
		
		JSONObject jo = new JSONObject(res.asString()); //Converting response type to Json Object type
		boolean status = false;
		for(int i=0; i<jo.getJSONArray("book").length(); i++)
		{
			String bookname = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			
			//System.out.println(bookname); // print all title of the book
			if(bookname.equals("sword of honour"))
			{
				status = true;
				break;
			}
			
			
		}
		Assert.assertEquals(status, true);
		
		
		
		
		
		
		
		//Validation 2
		//Sum of all the book prices    
		
		double totalPrice = 0;
		for(int i = 0; i<jo.getJSONArray("book").length(); i++)
		{
			String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			//System.out.println(price);
			
			totalPrice = totalPrice +Double.parseDouble(price); //Convert string to double
			
		}
		
		System.out.println(totalPrice);
		Assert.assertEquals(totalPrice, 526.0);
		
		
	}
}
