package library_api;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import excel.ExcelData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Library_API {

	private static String id;

	@Test(dataProvider = "BookData_AsHashMap")
	public void addBook(Map<String, Object> map) throws IOException {
		baseURI = "http://216.10.245.166";
		
		// Updating json file data.
//		String filePath = "src\\test\\resources\\library_api_resource\\AddBook.json";
//		String json = new String(Files.readAllBytes(Paths.get(filePath)));
//		json = json.replaceFirst("#isbn", isbn).replaceFirst("#aisle", aisle);
		
		// Create POST request.
		id =
		given()
				.header("Content-Type", "application/json")
				.body(map).log().all().
		when()
				.post("Library/Addbook.php").
		then()
				.assertThat()
				.statusCode(200)
				.body("Msg", equalTo("successfully added")).
		extract()
				.path("ID")
				.toString();
	
		System.out.println("\n\nID : " + id);
	}

//	@Test(dependsOnMethods = { "addBook" })		 
	public void deleteBook() {					//  Delete book API does not work.
		baseURI = "http://216.10.245.166";
//		String id = isbn + aisle;
		
		// Create DELETE request.
		given().header("Content-Type", "application/json").
				body("{ \"ID\" : \""+ id + "\" }").
		when().post("/Library/DeleteBook.php").
				then().
				assertThat().log().all().
				statusCode(200).
				body("msg", equalTo("book is successfully deleted"));
	}

	@DataProvider(name = "BookData")
	public Object[][] getData() {
		return new Object[][] { { "fgjs", "274" }, { "ksbfo", "10" }, { "jswoc", "561" } };
	}

	@DataProvider(name = "BookData_AsHashMap")
	public Object[][] getDataAsHashMap() {
		Map<String, Object> map1 = new HashMap<>();
		map1.put("name", "Marut2");
		map1.put("isbn", "1953");
		map1.put("aisle", "4LR");
		map1.put("author", "Jimmy Carter");

		Map<String, Object> map2 = new HashMap<>();
		map2.put("name", "Truss2");
		map2.put("isbn", "1568");
		map2.put("aisle", "5JD6");
		map2.put("author", "Elizabeth Homes");

		return new Object[][] { { map1 }, { map2 } };
	}

	@DataProvider(name = "BookData_FromExcel")
	public Object[][] getDataFromExcel() throws IOException {
		ExcelData excelData = new ExcelData("src\\main\\resources\\AddBook.xlsx");
		List<String> listHeader = excelData.getCellValues_OfRowNo(0);
		List<String> list1 = excelData.getCellValues_OfRowNo(1);
		List<String> list2 = excelData.getCellValues_OfRowNo(2);

		Map<String, Object> map1 = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();
		for (int i = 0; i < listHeader.size(); i++) {
			map1.put(listHeader.get(i), list1.get(i));
			map2.put(listHeader.get(i), list2.get(i));
		}

		return new Object[][] { { map1 }, { map2 } };
	}
}
