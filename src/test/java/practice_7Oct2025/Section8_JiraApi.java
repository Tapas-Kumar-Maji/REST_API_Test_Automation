package practice_7Oct2025;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.io.File;

public class Section8_JiraApi {
    String bugId = null;

    @Test(priority = -1)
    public void createBug() {
	
	RestAssured.baseURI = "https://tapasmaji909.atlassian.net";

	Response response =
	given()
	.header("Content-Type", "application/json")
	.header("Accept", "application/json")
	.header("Authorization", "Basic QVRBVFQzeEZmR0YwWnZFQlg4MXlIRHlLYlBZNUlqaWVrdGE4TEJUQ2gwVFRCVEdkRnh0QnNCUVl6VjlFUkNfQ09zcjBhdU9CclM3SDc5eDJ5dHFKZHA1VGNoU0htSGFWeTFZd3pmbk54QlJHOWNYOGFzdU4wdlFGTy1IRURyMGppc3Z0N0JOTlJiM0ppNHc1Z1E4THVQanlEZXRuTGlhd0pHdDJoOExjMXZxZDh6Ni1aTEg3bU1zPTE0RkI2NDE0")
	.body("{\n"
		+ "    \"fields\": {\n"
		+ "        \"project\": {\n"
		+ "            \"key\": \"SCRUM\"\n"
		+ "        },\n"
		+ "        \"summary\": \"Dropdown not working\",\n"
		+ "        \"issuetype\": {\n"
		+ "            \"name\": \"Bug\"\n"
		+ "        }\n"
		+ "    }\n"
		+ "}")
	
	.when()
	.post("/rest/api/3/issue")
	
	.then()
	.log().all()
	
	.assertThat()
	.statusCode(201)
	
	.extract()
	.response();

	this.bugId = response.jsonPath().getString("id");
	System.out.println("Bug ID : " + this.bugId);

    }

    @Test
    public void addAttachment() {

	given()
	.header("X-Atlassian-Token", "no-check")
	.header("Authorization", "Basic dGFwYXNtYWppOTA4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBFSWNZRm9XdFh5ZXJ1S0dLVkRIajBUME4wOXhBbk9WNUl4eHNnc3M5UUtIdEZUaFA0ejFBeHRuc0RzeVNtWHl6MzMwbkhiVlNycmhSWnZMdEtYaTJJdEFrQWhSNEhDVTRaTjBaU2oxVTRLR2lUblYyYzA3MHNmTGtYWlE5amZiVkRTcVphbkFIWDlPNmhfdE5QV1VITkxINnI2cjJRZ3hyeC03OFp4QWFfY009RDg0M0RCRDU=")
	.multiPart(new File(System.getProperty("user.dir") + "/dummy_screenshot.png"))
	.pathParam("bugId", bugId)
	
	.when()
	.post("/rest/api/3/issue/{bugId}/attachments")
	
	.then()
	
	.assertThat()
	.statusCode(200);
    }
}
