package Api_Test_Validations;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class Api_Testing_Validations {

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
        assertTrue(response.getTime() < 1000);

        // Validate response body
        List<?> jsonData = response.jsonPath().getList("$");
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

        // Validate specific entries
        Map<String, Object> firstItem = (Map<String, Object>) jsonData.get(0);
        assertEquals("1", firstItem.get("id"));
        assertEquals("john", firstItem.get("name"));
        assertEquals("india", firstItem.get("location"));
        assertEquals("1234567890", firstItem.get("phone"));

        // Validate courses
        List<String> courses = (List<String>) firstItem.get("courses");
        assertTrue(courses.contains("java"));
        assertTrue(courses.containsAll(List.of("java", "selenium")));

        // Validate JSON schema (optional)
        // JsonSchemaValidator.validateSchema(jsonData, "path/to/schema.json");
    }
}