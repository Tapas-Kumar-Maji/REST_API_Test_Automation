package graphql_api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GraphQLScript {

	@Test
	public void query() throws IOException {
		String filePath = "src\\test\\resources\\GraphQL_api_rescource\\Query.json";
		String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
		String characterId = "9627";			//   take input from .feature file
		String episodeId = "10886";
		jsonContent = jsonContent.replaceFirst("#characterId", characterId).replaceFirst("#episodeId", episodeId);
		
		
		String response =
		 given().baseUri("https://rahulshettyacademy.com/gq/graphql?")
				.contentType(ContentType.JSON)
				.body(jsonContent).
		 when()
		 		.post().
		 then()
				.assertThat()
				.statusCode(200)
				.body("data.character.name", equalTo("Tapas"))
		 		.extract()
		 		.asPrettyString();
		System.out.println(response);
		System.out.println("\n\n******************************************************************************\n\n");
	}

	@Test
	public void mutation() throws IOException {
		String filePath = "src\\test\\resources\\GraphQL_api_rescource\\Mutation.json";
		String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
		String locationName = "Germany,Dutch";
		String characterName = "Bugs Bunny";
		String episodeName = "Ep4";
		jsonContent = jsonContent.replaceFirst("#locationName", locationName)
				.replaceFirst("#characterName", characterName).replaceFirst("#episodeName", episodeName);
		
		
		String response =
				 given().baseUri("https://rahulshettyacademy.com/gq/graphql?")
						.contentType(ContentType.JSON)
						.body(jsonContent).
				 when()
				 		.post().
				 then()
				 		.assertThat()
						.statusCode(200)
				 		.extract()
				 		.asPrettyString();
		System.out.println(response);
		System.out.println("\n\n******************************************************************************\n\n");
	}
}
