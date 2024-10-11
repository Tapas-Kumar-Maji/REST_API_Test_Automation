package json;

import java.io.File;

import org.testng.Assert;

import Utilities.Utility;
import io.restassured.path.json.JsonPath;

public class JsonParse {
	public static void main(String[] args) {
		File json = new File("src\\test\\resources\\Dummy.json");
		JsonPath jsonPath = Utility.getJsonPath(json);
//		jsonPath.prettyPrint();

		int coursesSize = jsonPath.getInt("courses.size()");
		System.out.println("No of courses : " + coursesSize);
		System.out.println("Purchase Amount : " + jsonPath.getInt("dashboard.purchaseAmount"));
		System.out.println("First Course Title : " + jsonPath.getString("courses[0].title"));

		int sumPrices = 0;
		for (int i = 0; i < coursesSize; i++) {
			int price = jsonPath.getInt("courses[" + i + "].price");
			sumPrices += price * jsonPath.getInt("courses[" + i + "].copies");
			System.out.println(jsonPath.getString("courses[" + i + "].title") + ", " + price);

			if (jsonPath.getString("courses[" + i + "].title").equalsIgnoreCase("RPA")) {
				System.out.println("No of copies of RPA : " + jsonPath.getInt("courses[" + i + "].copies"));
			}
		}
		Assert.assertEquals(jsonPath.getInt("dashboard.purchaseAmount"), sumPrices);
	}
}
