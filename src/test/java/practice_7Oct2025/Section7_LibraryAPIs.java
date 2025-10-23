package practice_7Oct2025;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.Utility;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Section7_LibraryAPIs {
    String bookId;

    @Test(enabled = false)
    public void addBook() {
	
	String isbn = Utility.generateRandomString();
	String isle = "" + Utility.generateRandomInteger();

	Response response =
        	given()
        	.baseUri("http://216.10.245.166")
        	.header("Content-Type", "application/json")
        	.body(RequestPayload.addBook(isbn, isle))
        	 
        	.when()
        	.post("Library/Addbook.php")
        	
        	.then()
        	.assertThat()
        	.statusCode(200)
        	
        	.extract()
        	.response();
	
	System.out.println(response.asPrettyString());
	this.bookId = response.jsonPath().getString("ID");

    }

    @Test(dataProvider = "getIsbnAndIsle")
    public void addBook_usingDataProvider(String[] str) {

	Response response =
		given()
		.baseUri("http://216.10.245.166")
		.header("Content-Type", "application/json")
		.body(RequestPayload.addBook(str[0], str[1]))
		 
		.when()
		.post("Library/Addbook.php")
		
		.then()
		.assertThat()
		.statusCode(200)
		
		.extract()
		.response();

	System.out.println(response.asPrettyString());
	this.bookId = response.jsonPath().getString("ID");

    }

    @DataProvider
    public String[][] getIsbnAndIsle() {

	int rows = 5;
	String[][] str = new String[rows][2];

	for (int r = 0; r < str.length; r++) {
	    str[r][0] = Utility.generateRandomString();
	    str[r][1] = "" + Utility.generateRandomInteger();
	}

	return str;

    }

}
