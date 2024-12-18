package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

//given() -> Content Type, Set Cookies, add auth, add params, set headers info....
//when() - > req type - get(), post(), put(), delete().
//then() -> validate status code, extract response(Log), extract header cookies and reponse body...
 


public class HttpReq {

	@Test (priority = 1)
	void getUsers() {
		
		given ()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
		
	}
	int id;
	
	@Test (priority = 2)
	void CreateUser() {
		
		HashMap data = new HashMap();
		data.put("name", "Sandesh");
		data.put("job","Tester");
		
		
		
		id=given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
//		.then()
//			.statusCode(201)
//			.log().all();
	}
	
	@Test(priority = 3, dependsOnMethods = {"CreateUser"})
	void UpdateUser() {
		
		HashMap data = new HashMap();
		data.put("name", "Shrirang");
		data.put("job","Dev");
		
		
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.put("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(200)
			.log().all();
	}
	
	
	@Test (priority = 4, dependsOnMethods = {"UpdateUser","CreateUser"})
	void DeleteUser() {
		
	given()
		.contentType("application/json")
	.when()
		.delete("https://reqres.in/api/users/"+id)
	.then()
		.statusCode(204)
		.log().all();
		
	}
	
}
