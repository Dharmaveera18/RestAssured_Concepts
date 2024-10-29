package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CookiesDemo {

	//@Test
	void testCookies()
	{
		
		
		RestAssured.useRelaxedHTTPSValidation(); // Disable SSL validation for testing

		 // Set cookies
		RestAssured.given()
            .contentType("application/json")
            
            .when()
            .get("https://www.google.com/")
            
            
            .then()
            .log().all(); // Log response for debugging

	}
	
	@Test
	void getCookies()
	{
		
		
		RestAssured.useRelaxedHTTPSValidation(); // Disable SSL validation for testing

		 // Set cookies
        Response res = RestAssured.given()
            .contentType("application/json")
            
            .when()
            .get("https://www.google.com/");
            
            
            //Single cookie info
        
        //String cookie_Value = res.getCookie("AEC");
        //System.out.println("Value of Cookie ==>" + cookie_Value);
        
        //Get all cookie info
        
        Map<String, String> cookie_Values = res.getCookies();
        
       //System.out.println(cookie_Values.keySet()); 
        
        for( String k:cookie_Values.keySet())
        {
        	String cookie_Value = res.getCookie(k);
            System.out.println(k +":     Value of Cookie ==>" + cookie_Value);
        
        }
        
	}
	
	
}
