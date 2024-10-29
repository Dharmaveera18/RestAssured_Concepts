package day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDummyDataGenerate {

	
	
	@Test
	void testGenerateDummyData()
	{
		Faker faker = new Faker();
		
		String full_name = faker.name().fullName();
		String lastName = faker.name().lastName();
		String firstName = faker.name().firstName();
		String username = faker.name().username();
		String password = faker.internet().password();
		String cellPhone = faker.phoneNumber().cellPhone();
		String safeEmailAddress = faker.internet().safeEmailAddress();
		String animal_name = faker.animal().name();
		
		
		System.out.println("full_name:  " + full_name);
		System.out.println("lastName:  " + lastName);
		System.out.println("firstName:  " + firstName);
		System.out.println("username:  " + username);
		System.out.println("password:  " + password);
		System.out.println("cellPhone:  " + cellPhone);
		System.out.println("safeEmailAddress:  " + safeEmailAddress);
		System.out.println("animal_name:  " + animal_name);
		
	}
}
