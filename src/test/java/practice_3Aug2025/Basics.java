package practice_3Aug2025;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Basics {
	String place_id = null;
	String updatedAddress = "Bangalore Bangalore HSR";

	@BeforeClass
	public void initialize() {
		baseURI = "https://google.com";
	}

	@Test
	public void testFlow() throws InterruptedException {
		
		addPlace();

		Response update = updatePlace(this.updatedAddress);
		System.out.println("Update Place response : \n " + update.asPrettyString() + "\n\n");

		Response get = getPlace();
		System.out.println("Get Place response : \n " + get.asPrettyString() + "\n\n");
		Assert.assertEquals(get.jsonPath().getString("address"), this.updatedAddress);
	}

	public void addPlace() {
		place_id = 
		given()
		// .log().all()
		.baseUri("https://rahulshettyacademy.com")
		.queryParam("key", "qaclick123")
		.header("Content-Type", "application/json")
		.body(RequestPayloads.addPlace())
		.when()
		.post("/maps/api/place/add/json")
		
		.then()
		// .log().body()
		
		.assertThat()
		.statusCode(200)
		.body("status", equalTo("OK"))
		.header("Server", containsStringIgnoringCase("Apache/2.4.52 (Ubuntu)"))
		
		.extract()
		// .response()
		.jsonPath()
		.getString("place_id");

		System.out.println("Place ID : " + this.place_id + "\n\n");
	}
	
	public Response getPlace() {
		return
		given()
		.baseUri("https://rahulshettyacademy.com")
		.queryParam("place_id", this.place_id)
		.queryParam("key", "qaclick123")
		
		.when()
		.get("/maps/api/place/get/json")
		
		.then()
		.assertThat()
		.statusCode(200)
		
		.extract()
		.response();
	}
	
	public Response updatePlace(String address) {
		return
		given()
		.baseUri("https://rahulshettyacademy.com")
		.queryParam("place_id", this.place_id)
		.queryParam("key", "qaclick123")
		.body("{\r\n"
                + "\"place_id\":\"" + this.place_id + "\",\r\n"
                + "\"address\":\"" + address + "\",\r\n"
                + "\"key\":\"qaclick123\"\r\n"
                + "}")
		
		.when()
		.put("/maps/api/place/update/json")
		
		.then()
		// .log().all()
		.assertThat()
		.statusCode(200)
		
		.extract()
		.response();
	}

}
