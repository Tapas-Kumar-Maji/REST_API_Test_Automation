package practice_7Oct2025;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Section10_Deserialization {
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
	
	CourseDetailsResponsePojo res =
	given()
	.baseUri("https://rahulshettyacademy.com")
	.queryParam("access_token", this.access_token)
	
	.when()
	.get("/oauthapi/getCourseDetails")
	
	.then()
	
	.extract()
	.as(CourseDetailsResponsePojo.class);
	
	Assert.assertEquals(res.getCourses().getApi().get(0).getPrice(), "50");

	// get price of SoapUI Webservices testing
	List<Course> ls = res.getCourses().getApi();
	for(Course course : ls) {
	    if(course.getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
		System.out.println("Price of " + course.getCourseTitle() + " : " + course.getPrice());
	    }
	}
	
	// print all course titles in Web Automation
	ls = res.getCourses().getWebAutomation();
	for (Course course : ls) {
	    System.out.println(course.getCourseTitle());
	}

    }
}
