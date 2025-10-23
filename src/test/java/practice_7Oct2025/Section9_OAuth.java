package practice_7Oct2025;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Section9_OAuth {
    String access_token = null;

    @Test(priority = -1)
    public void getApiToken() {

	Response response =
	given()
	.baseUri("https://rahulshettyacademy.com/")
	.multiPart("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
	.multiPart("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
	.multiPart("grant_type", "client_credentials")
	.multiPart("scope", "trust")
	
	.when()
	.post("oauthapi/oauth2/resourceOwner/token")
	
	.then()
	// .log().all()
	
	.assertThat()
	.statusCode(200)
	
	.extract()
	.response();
	
	this.access_token = response.jsonPath().getString("access_token");
	System.out.println("Access token : " + this.access_token);

    }
    
    @Test
    public void getCourseDetails() {
	
	given()
	.baseUri("https://rahulshettyacademy.com/")
	.queryParam("access_token", this.access_token)
	
	.when()
	.get("oauthapi/getCourseDetails")
	
	.then()
	.log().body()
	
	.assertThat()
	.body("instructor", Matchers.equalTo("RahulShetty"));

    }
}
