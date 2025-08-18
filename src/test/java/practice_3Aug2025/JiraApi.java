package practice_3Aug2025;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.io.File;

public class JiraApi {
	String id = null;

	@BeforeClass
	public void initialize() {
		RestAssured.baseURI = "https://tapasmaji908-1754336723796.atlassian.net";
	}

	@Test
	public void createBugTest() {
		id = 
		given()
		.auth().basic("tapasmaji908@gmail.com", RequestPayloads.basicAuthPasswordJira())
		.header("Content-Type", "application/json")
		.header("Accept", "application/json")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"RSAP\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Dropdowns are not working.\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}\r\n"
				+ "")

		.when()
		.post("/rest/api/3/issue")

		.then()
		.log().body()
		
		.assertThat()
		.statusCode(201)
		.body("", Matchers.equalTo(""))
		
		.extract()
		.response()
		.jsonPath()
		.getString("id");
	}
	
	@Test(dependsOnMethods = {"createBugTest"})
	public void addAttachment() {
		given()
		.header("X-Atlassian-Token", "no-check")
		.header("Authorization", RequestPayloads.basicAuthPasswordJira())
		.multiPart("file", new File("C:\\Users\\tapos\\Pictures\\hqdefault.jpg"))
		.pathParam("id", id)
		
		.when()
		.post("/rest/api/3/issue/{id}/attachments")
		
		.then()
		.assertThat()
		.statusCode(200);
	}

	@Test(dependsOnMethods = { "addAttachment" })
	public void getIssue() {
		given()
		.auth().basic("tapasmaji908@gmail.com", RequestPayloads.basicAuthPasswordJira())
		.header("Accept", "application/json")
		.pathParam("issueId", id)
		
		.when()
		.get("/rest/api/3/issue/{issueId}")
		
		.then()
		.assertThat()
		.statusCode(200)
		.body("fields.project.id", Matchers.equalTo(id));
	}
}
