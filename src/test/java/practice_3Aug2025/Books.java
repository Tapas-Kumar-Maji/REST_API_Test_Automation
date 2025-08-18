package practice_3Aug2025;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

import java.io.File;

public class Books {
	String bookId;

	@BeforeClass
	public void initialize() {
		RestAssured.baseURI = "http://216.10.245.166";
	}
	
	@Test(dataProvider = "Isbn_Aisle")
	public void addBookApiTest(String[] arr) {
		this.bookId = 
		given()
		.header("Content-Type", "application/json")
		// .body(RequestPayloads.addBook("queen", "" + Math.random() * 100))
		.body(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\library_api_resource\\AddBook.json"))
		// .body(RequestPayloads.addBook(arr[0], arr[1]))
		.when()
		.post("Library/Addbook.php")
		
		.then()
		.assertThat()
		.statusCode(200)
		
		.extract()
		.jsonPath()
		.getString("ID");

		System.out.println("Book ID : " + this.bookId);
	}

	@DataProvider(name = "Isbn_Aisle")
	public String[][] data() {
		return new String[][] { { "qwerty", "1235" }, { "rghj", "456" }, { "rtyui", "9632" } };
	}
}
