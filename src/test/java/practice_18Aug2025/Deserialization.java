package practice_18Aug2025;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Deserialization {

    String accessToken = null;

    @Test
    public void authorizationServerEndPoint() {
	RestAssured.baseURI = "https://rahulshettyacademy.com/";
	this.accessToken =
	given()
		.multiPart("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.multiPart("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.multiPart("grant_type", "client_credentials")
		.multiPart("scope", "trust")
	.when()
		.post("oauthapi/oauth2/resourceOwner/token")
	.then()
		
	.assertThat()
	.statusCode(200)
	
	.extract()
	.path("access_token");

	System.out.println("Access Token : " + this.accessToken);
    }

    @Test
    public void getCourseApi() {
	Response_Lombok response =
	given()
	.queryParam("access_token", this.accessToken)
	
	.when()
	.get("oauthapi/getCourseDetails")
	
	.then()
	.extract()
	.as(Response_Lombok.class);

	System.out.println("Instructor name : " + response.getInstructor());

	String[] expectedCourses = { "Selenium Webdriver Java", "Cypress", "Protractor" };
	List<Course1> courses = response.getCourses().getWebAutomation();

	int i = 0;
	for(Course1 obj : courses) {
	    if (obj.getCourseTitle().equalsIgnoreCase(expectedCourses[i++])) {
		System.out.println(obj.getCourseTitle());
	    }
	}

    }

}
