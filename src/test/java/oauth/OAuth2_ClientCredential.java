package oauth;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pojo.oauth.Course;
import pojo.oauth.ResponsePojo;

public class OAuth2_ClientCredential {
	String baseURI = "https://rahulshettyacademy.com/";
	String accessToken = "";

	@Test
	public void authorizationServerEndPoint() {
		this.accessToken =
		given().
			baseUri(this.baseURI).
			multiPart("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
			multiPart("client_secret", "erZOWM9g3UtwNRj340YYaK_W").		// works with formParam()
			multiPart("grant_type", "client_credentials").
			multiPart("scope", "trust").
		when().
			post("oauthapi/oauth2/resourceOwner/token").
		then().
			assertThat().
			statusCode(200).
		extract().
			path("access_token");
	}
	
	@Test(dependsOnMethods = { "authorizationServerEndPoint" })
	public void getCourseDetails() { // Deserialization using POJO classes.
		ResponsePojo responsePojo =
		given().
			baseUri(this.baseURI).
			queryParam("access_token", this.accessToken).
		when().
			get("oauthapi/getCourseDetails").
		then().
		extract().
			response().
			as(ResponsePojo.class);		// Jackson library is used here.

//		System.out.println(responsePojo.getCourses().getWebAutomation().get(2).getCourseTitle());
//		List<Course> ls = responsePojo.getCourses().getApi();
//		for (Course c : ls) {
//			if (c.getCourseTitle().contains("Rest Assured"))
//				System.out.println(c.getPrice());
//		}

		String[] expectedCourseTitles = { "Selenium Webdriver Java", "Cypress", "Protractor" };
		List<Course> ls = responsePojo.getCourses().getWebAutomation();
		int i = 0;
		for (Course c : ls) {
			if (i >= expectedCourseTitles.length)
				break;
			System.out.println("Course title : " + c.getCourseTitle());
			Assert.assertEquals(expectedCourseTitles[i++], c.getCourseTitle());
		}
	}
}
