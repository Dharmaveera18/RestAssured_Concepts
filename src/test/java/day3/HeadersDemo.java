package day3;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class HeadersDemo {

	
	//@Test
	void testHeaders()
	{
		
		
		RestAssured.useRelaxedHTTPSValidation(); // Disable SSL validation for testing

		 // Set cookies
        RestAssured.given()
            .contentType("application/json")
            
            .when()
            .get("https://www.google.com/")
            
            
            .then()
            .header("Content-Type", "text/html; charset=ISO-8859-1")
            .header("Content-Encoding", "gzip")
            .and()
            .header("Server", "gws");
            

	}
	
	@Test(priority =1)
	void getHeaders()
	{
		
		
		RestAssured.useRelaxedHTTPSValidation(); // Disable SSL validation for testing

		 // Set cookies
        Response res = RestAssured.given()
            .contentType("application/json")
            
            .when()
            .get("https://www.google.com/");
            
            
           // String headerValue = res.getHeader("Content-Type");
            //System.out.println("The value of Content-Type header is "+headerValue);
            
        Headers headersValues = res.getHeaders();
        
        
        for(Header hd : headersValues)
        {
        	System.out.println(hd.getName() + "  :  " + hd.getValue()); 
        }
        
            

	}
}
