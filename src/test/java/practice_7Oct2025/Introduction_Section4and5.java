package practice_7Oct2025;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import java.time.Duration;
import java.util.HashMap;

public class Introduction_Section4and5 {
    String place_id = null;
    String newAddress = "Electronics City Phase 1";

    @Test(priority = -1)
    public void addPlace() {

	RestAssured.baseURI = "https://rahulshettyacademy.com/";
	
	Response response =
	given()
	.baseUri("https://rahulshettyacademy.com/")
	.queryParam("key", "qaclick123")
	.body(RequestPayload.addPlaceRequestBody())

	.when()
	.post("/maps/api/place/add/json")
	
	.then()
	// .log().all()
	
	.assertThat()
	.statusCode(200)
	.body("scope", equalTo("APP"))
	.header("Server", equalTo("Apache/2.4.52 (Ubuntu)"))
	
	.extract()
	.response();
	
	// System.out.println("Add Place API response body :\n" + response.asPrettyString());
	this.place_id = response.jsonPath().getString("place_id");
	System.out.println("Place ID : " + this.place_id);
	System.out.println("\n\n");

    }

    @Test
    public void updatePlace() throws InterruptedException {

	Thread.sleep(Duration.ofSeconds(2L));
	
	given()
	.queryParam("place_id", this.place_id)
	.queryParam("key", "qaclick123")
		.body(RequestPayload.updatePlaceRequestBody(newAddress, this.place_id))
	
	.when()
	.put("/maps/api/place/update/json")
	
	.then()
	.assertThat()
	.statusCode(200)
	.body("msg", containsStringIgnoringCase("successfully updated"))
	
	.extract()
	.response();
	
    }

    @Test(dependsOnMethods = { "updatePlace" })
    public void getPlace() throws InterruptedException {

	Thread.sleep(Duration.ofSeconds(2L));
	
	given()
	.queryParam("key", "qaclick123")
	.queryParam("place_id", this.place_id)
	
	.when()
	.get("/maps/api/place/get/json")
	
	.then()
	.assertThat()
	.statusCode(200)
	.body("address", equalTo(this.newAddress));
	
    }
    
    @Test(dependsOnMethods = { "getPlace" })
    public void deletePlace() throws InterruptedException {
	
	Thread.sleep(Duration.ofSeconds(2L));
	
	Response response = 
	given()
	.baseUri("https://rahulshettyacademy.com")
	.queryParam("key", "qaclick123")
	.body(RequestPayload.deletePlaceRequestBody(this.place_id))
	
	.when()
	.post("/maps/api/place/delete/json")
	
	.then()
	.assertThat()
	.statusCode(200)
	
	.extract()
	.response();
	
	Assert.assertEquals(response.jsonPath().getString("status"), "OK");
	response.jsonPath().getList("courses", new HashMap<String, String>().getClass());

    }
}
