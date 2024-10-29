package Api_Validations;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class Validations {

	
	@Test
    public void testApi() {
        // Set the base URI
        RestAssured.baseURI = "http://localhost:3000/students"; // Change to your API

        // Perform the GET request
        Response response = given()
                                .when()
                                .get("http://localhost:3000/students") // Change to your endpoint
                                .then()
                                .extract().response();

        // Validate status codes
        response.then().statusCode(anyOf(is(200), is(201)));
        
        // Validate status code text
        assertTrue(response.getStatusLine().contains("OK"));
        
        // Validate headers
        response.then().header("Content-Type", "application/json");
        
        // Validate response time
        System.out.println(response.getTime());
        assertTrue(response.getTime() < 1000);
        
        // Validate response body                            //<?> means list of items can be any types string, int and so on.
        List<?> jsonData = response.jsonPath().getList("$"); //"$" means root element of the response
        System.out.println(jsonData);
        assertTrue(jsonData instanceof List);
        
        
     // Validate each item in the response
        for (Object item : jsonData) {
            assertTrue(item instanceof Map);
            Map<String, Object> itemMap = (Map<String, Object>) item;
            assertTrue(itemMap.containsKey("id"));
            assertTrue(itemMap.containsKey("name"));
            assertTrue(itemMap.containsKey("location"));
            assertTrue(itemMap.containsKey("phone"));
            assertTrue(itemMap.containsKey("courses"));

            // Validate specific types
            assertTrue(itemMap.get("id") instanceof String);
            assertTrue(itemMap.get("name") instanceof String);
            assertTrue(itemMap.get("location") instanceof String);
            assertTrue(itemMap.get("phone") instanceof String);
            assertTrue(itemMap.get("courses") instanceof List);
        }
        
        

        
	
	}
	
	
}
