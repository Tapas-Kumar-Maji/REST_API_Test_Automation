package practice_7Oct2025;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;import java.util.ArrayList;
import java.util.List;

public class Section11and12 {

    @Test(enabled = false)
    public void addPlace() {
	
	Location location = new Location();
	location.setLat(-38.383494);
	location.setLng(33.427362);
	
	AddPlaceRequestPojo req = new AddPlaceRequestPojo();
	req.setLocation(location);
	req.setAccuracy(50);
	req.setName("Tapas Maji");
	req.setPhone_number("(+91) 983 893 3937");
	req.setAddress("29, side layout, cojen 09");
	req.setTypes(new ArrayList<String>(List.of("shoe park", "shoe")));
	req.setWebsite("http://google.com");
	req.setLanguage("French-IN");
	
	given()
	.log().all()
	.baseUri("https://rahulshettyacademy.com")
	.queryParam("key", "qaclick123")
	.body(req)
	
	.when()
	.post("/maps/api/place/add/json")
	
	.then()
	.assertThat()
	.statusCode(200);
	
    }

    @Test
    public void reqAndResSpecBuilder() {
	
	Location location = new Location();
	location.setLat(-38.383494);
	location.setLng(33.427362);
	
	AddPlaceRequestPojo reqBody = new AddPlaceRequestPojo();
	reqBody.setLocation(location);
	reqBody.setAccuracy(50);
	reqBody.setName("Tapas Maji");
	reqBody.setPhone_number("(+91) 983 893 3937");
	reqBody.setAddress("29, side layout, cojen 09");
	reqBody.setTypes(new ArrayList<String>(List.of("shoe park", "shoe")));
	reqBody.setWebsite("http://google.com");
	reqBody.setLanguage("French-IN");

	// RequestSpecBuilder and ResponseSpecBuilder classes are used to declare common parameters in a group of APIs. 
	// Advantage : If some parameter needs to be changed, it can be changed at one place.

	RequestSpecification reqSpec =
	new RequestSpecBuilder()
	.setBaseUri("https://rahulshettyacademy.com")
	.addQueryParam("key", "qaclick123")
	.setContentType(ContentType.JSON)
	.build();
	
	reqSpec = 
	given()
	.spec(reqSpec)
	.body(reqBody);
	
	ResponseSpecification resSpec =
	new ResponseSpecBuilder()
	.expectStatusCode(200)
	.expectContentType(ContentType.JSON)
	.build();
	
	// Sending POST request
	Response response =
	reqSpec
	.when()
	.post("/maps/api/place/add/json")
	
	.then()
	.spec(resSpec)
	
	.extract()
	.response();

	System.out.println(response.body().asPrettyString());
    }

}
