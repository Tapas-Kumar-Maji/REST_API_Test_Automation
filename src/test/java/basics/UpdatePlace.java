package basics;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class UpdatePlace {

	public static void main(String[] args) throws IOException {
		baseURI = "https://rahulshettyacademy.com";

		// Read .json file as a String and modify the String
		String filePath = "src\\test\\resources\\UpdatePlace_PUT_request.json";
		String jsonString = new String(Files.readAllBytes(Paths.get(filePath))); // File object to String.

		// Getting place_Id and creating the request body.
		jsonString = jsonString.replaceFirst("#add", UUID.randomUUID().toString());
		
		// Creating PUT request.
		given().queryParam("place_id", "076c8dcbaecbca8082169ae1c59c984e").
				queryParam("key", "qaclick123").
				header("Content-Type", "application/json").
				body(jsonString).
				log().all().
		when().put("/maps/api/place/update/json").
		then().log().all(). 
				assertThat().
				statusCode(200).
				body("msg", equalTo("Address successfully updated"));
	}
}
