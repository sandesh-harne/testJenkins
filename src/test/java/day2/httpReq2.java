package day2;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

public class httpReq2 {

	
	@Test
	void testCreateRecord() {
		
		HashMap record  = new HashMap();
		record.put("name", "Tester1");
		record.put("job", "Test Lead");
		
		given()
			.contentType("application/json")
			.body(record)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name", equalTo("Tester1"))
			.body("job", equalTo("Test Lead"));
		
			
		
		
		
		
	}
	
	
}
