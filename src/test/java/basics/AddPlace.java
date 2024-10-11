package basics;
// RestAssured.given() : this method creates the HTTP method except for path parameter/resource.
// when() : this method creates the HTTP method path parameter/resource.
// then() : this method is used for assertion.

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AddPlace {

	public static String placeId = "";

	public static RequestSpecification req = new RequestSpecBuilder()			// used for common specifications of HTTP request.
			.setBaseUri("https://rahulshettyacademy.com")
			.addHeader("Content-Type", "application/json")
			.setContentType(ContentType.JSON).build();
	
	public static ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200)
			.expectContentType(ContentType.JSON).build();

	public static void main(String[] args) {
		File jsonRequestBody = new File("src\\test\\resources\\AddPlace_POST_request.json");

		RequestSpecification reqSpec = given().spec(req).
				body(jsonRequestBody);

		placeId = 
		reqSpec.when().post("/maps/api/place/add/json").
		then().assertThat().
			   spec(res).
			   body("scope", equalTo("APP")).
			   header("Server", "Apache/2.4.52 (Ubuntu)").			   
		extract().path("place_id").toString();

		System.out.println("\n\nplace_id : " + placeId);
			   

//		JsonPath jsonPath = postResponse.jsonPath();
//		placeId = jsonPath.getString("place_id");
	}
}
