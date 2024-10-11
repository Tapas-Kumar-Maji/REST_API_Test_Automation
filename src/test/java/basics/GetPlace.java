package basics;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.Matchers.equalTo;

public class GetPlace {

	public static void main(String[] args) {
		baseURI = "https://rahulshettyacademy.com";
		
		// Create GET request.
		given().queryParam("place_id", "f0053825f266cf88c47529711df984bd").
				queryParam("key", "qaclick123").
		when().get("/maps/api/place/get/json").
		then().log().all().
				assertThat().
				statusCode(200).
				body("address", equalTo("29, side layout, cohen 092"));
	}
}