package jira_api;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.annotations.Test;

public class JiraAPI {
	String id = "";
	String baseURL = "https://tapasmaji908.atlassian.net/";

	@Test
	public void createBug() {
		baseURI = this.baseURL;
		this.id =
		given().header("Authorization", "Basic dGFwYXNtYWppOTA4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBFSWNZRm9XdFh5ZXJ1S0dLVkRIajBUME4wOXhBbk9WNUl4eHNnc3M5UUtIdEZUaFA0ejFBeHRuc0RzeVNtWHl6MzMwbkhiVlNycmhSWnZMdEtYaTJJdEFrQWhSNEhDVTRaTjBaU2oxVTRLR2lUblYyYzA3MHNmTGtYWlE5amZiVkRTcVphbkFIWDlPNmhfdE5QV1VITkxINnI2cjJRZ3hyeC03OFp4QWFfY009RDg0M0RCRDU=").
				header("Content-Type", "application/json").
				body(new File("src\\test\\resources\\Jira_api_resource\\CreateBug.json")).
		when().post("rest/api/3/issue").
		then().assertThat().
				statusCode(201).
		extract().path("id");
		System.out.println("ID : " + this.id + "\n"); // ID : 10002 and 10001
	}


	@Test
	public void addAttachment() {
		baseURI = this.baseURL;
		given().header("Authorization", "Basic dGFwYXNtYWppOTA4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBFSWNZRm9XdFh5ZXJ1S0dLVkRIajBUME4wOXhBbk9WNUl4eHNnc3M5UUtIdEZUaFA0ejFBeHRuc0RzeVNtWHl6MzMwbkhiVlNycmhSWnZMdEtYaTJJdEFrQWhSNEhDVTRaTjBaU2oxVTRLR2lUblYyYzA3MHNmTGtYWlE5amZiVkRTcVphbkFIWDlPNmhfdE5QV1VITkxINnI2cjJRZ3hyeC03OFp4QWFfY009RDg0M0RCRDU=").
				header("X-Atlassian-Token", "no-check").
				pathParam("issueIdOrKey", "10002").
				multiPart("file", new File("C:\\Users\\tapos\\Pictures\\TapasMaji.png")).
		when().post("rest/api/3/issue/{issueIdOrKey}/attachments").
		then().assertThat().
				statusCode(200);
	}

	@Test
	public void getIssue() {
		baseURI = this.baseURL;
		given().header("Accept", "application/json").
				header("Authorization","Basic dGFwYXNtYWppOTA4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBFSWNZRm9XdFh5ZXJ1S0dLVkRIajBUME4wOXhBbk9WNUl4eHNnc3M5UUtIdEZUaFA0ejFBeHRuc0RzeVNtWHl6MzMwbkhiVlNycmhSWnZMdEtYaTJJdEFrQWhSNEhDVTRaTjBaU2oxVTRLR2lUblYyYzA3MHNmTGtYWlE5amZiVkRTcVphbkFIWDlPNmhfdE5QV1VITkxINnI2cjJRZ3hyeC03OFp4QWFfY009RDg0M0RCRDU=").
				pathParam("issueIdOrKey", "10002").
		when().get("rest/api/3/issue/{issueIdOrKey}").
		then().log().all().assertThat().
				statusCode(200).
				body("fields.attachment[0].filename", equalTo("TapasMaji.png"));
	}
}
