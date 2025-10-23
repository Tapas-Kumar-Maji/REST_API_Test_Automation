package practice_7Oct2025.section14_cucumber;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GoogleMapsApi_StepDefinition extends Utility {
    
    RequestSpecification reqSpec = null;
    Response response = null;
    static String placeId = null;

    @Given("User has AddPlaceApi payload with {string}, {string}, {string}")
    public void user_has_add_place_api_payload_with(String name, String language, String address)
	    throws FileNotFoundException {

	GoogleMapsApi_RequestPayload req = new GoogleMapsApi_RequestPayload();
	reqSpec = 
		given()
		.spec(Utility.requestSpecification())
		.body(req.getAddPlaceApiPayload(name, language, address));

    }

    @When("User calls {string} API using {string} http request")
    public void user_calls_using_http_request(String api, String httpMethod) {
	
	GoogleMapsApi_Resources resources = GoogleMapsApi_Resources.valueOf(api.toUpperCase());
	
	if(httpMethod.equalsIgnoreCase("POST")) {
	    this.response =
		    reqSpec
		    .when()
		    .post(resources.getResource());	    
	}
	else if(httpMethod.equalsIgnoreCase("GET")) {
	    this.response =
		    reqSpec
		    .when()
		    .get(resources.getResource());
	}

    }

    @Then("The {string} Api response is success with status code {int}")
    public void the_api_response_is_success_with_status_code(String api, int statusCode) {
	
	ResponseSpecification resSpec =
		new ResponseSpecBuilder()
		.expectStatusCode(statusCode)
		.expectContentType(ContentType.JSON)
		.build();

	this.response =
		this.response
        	.then()
        	.spec(resSpec)
        	
        	.extract()
        	.response();

	// System.out.println(response.body().asPrettyString());

    }

    @And("{string} field in response body is {string}")
    public void field_in_response_body_is(String key, String value) {

	JsonPath jsonPath =
		this.response
		.jsonPath();
	
	Assert.assertEquals(jsonPath.getString(key), value);

    }

    @And("Verify place_id maps to {string} using {string} API")
    public void verify_place_id_maps_to_using_api(String name, String api) {
	
	placeId = response.jsonPath().getString("place_id");
	 
	reqSpec = 
        	given()
        	.spec(Utility.requestSpecification())
			.queryParam("place_id", placeId);
	
	user_calls_using_http_request(api, "GET");

	JsonPath jsonPath = 
		this.response
		.then()
		.extract()
		.jsonPath();

	Assert.assertEquals(jsonPath.getString("name"), name);

    }

    @Given("User has DeletePlaceApi payload")
    public void user_has_delete_place_api_payload() {

	reqSpec =
		given()
		.spec(Utility.requestSpecification())
		.body("{\n"
			+ "    \"place_id\":\""+ placeId +"\"\n"
			+ "}");

    }
}
