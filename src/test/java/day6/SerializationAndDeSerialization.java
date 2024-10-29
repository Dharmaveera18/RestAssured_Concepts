package day6;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import day2.Pojo_PostRequest;




public class SerializationAndDeSerialization {

	
	//@Test
	void ConvertPojo2Json() throws JsonProcessingException
	{
		
		//Cretaed the java Object
        Student Stupojo = new Student();
		
        Stupojo.setName("scott");
        Stupojo.setLocation("France");
        Stupojo.setPhone("123456");
		
		String courseArr[] = {"C", "C++"};
		Stupojo.setCourses(courseArr);
		
		
		//Converting java object to Json (Serilizaton)
		
		
		// ObjectMapper should be import from the import com.fasterxml.jackson.databind.ObjectMapper;
		
		ObjectMapper objMapper = new ObjectMapper();
		
		String jsonData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(Stupojo);
		
		
		System.out.println(jsonData);
		
		
	}
	
	
	@Test
	void Convertjson2Pojo() throws JsonProcessingException
	{
		
		String jsonData = "{\r\n"
				+ "  \"name\" : \"scott\",\r\n"
				+ "  \"location\" : \"France\",\r\n"
				+ "  \"phone\" : \"123456\",\r\n"
				+ "  \"courses\" : [ \"C\", \"C++\" ]\r\n"
				+ "}";
		
		
		
		
		
		//Converting Json (De-Serilizaton) to  java object 
		
		
		// ObjectMapper should be import from the import com.fasterxml.jackson.databind.ObjectMapper;
		
		ObjectMapper objMapper = new ObjectMapper();
		
		Student stupojo  = objMapper.readValue(jsonData, Student.class);  //Convert json to student class object
		
		System.out.println("Name:" + stupojo.getName() );
		System.out.println("Location" + stupojo.getLocation());
		System.out.println("phone:" + stupojo.getPhone() );
		System.out.println("courses1:" + stupojo.getCourses()[0]);
		System.out.println("courses2:" + stupojo.getCourses()[1]);
		
		
		
		
		
		
	}
	
	
}
