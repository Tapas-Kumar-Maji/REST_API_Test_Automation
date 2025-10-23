package practice_7Oct2025;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Section6 {

    /**
     * Only JsonPath class of rest assured library is used to write the logic.
     */
    @Test
    public void buisnessLogic() {

	JsonPath jsonPath = new JsonPath(
		new File(System.getProperty("user.dir") + "/src/test/resources/MockResponseBody.json"));

	// number of courses present
	int totalCourses = jsonPath.getInt("courses.size()");
	System.out.println("Total number of courses : " + totalCourses);

	// purchase amount
	int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
	System.out.println("Purchase Amount : " + purchaseAmount);

	// print course titles and their price
	for (int i = 0; i < totalCourses; i++) {
	    System.out.print("Title : " + jsonPath.getString("courses[" + i + "].title"));
	    System.out.println("\t | Price : " + jsonPath.getString("courses[" + i + "].price"));
	}

	// validate purchase amount
	int actualSum = 0;
	for (int i = 0; i < totalCourses; i++) {
	    int price = jsonPath.getInt("courses[" + i + "].price");
	    int copies = jsonPath.getInt("courses[" + i + "].copies");
	    actualSum += price * copies;
	}

	System.out.println("Actual sum : " + actualSum);
	Assert.assertEquals(actualSum, purchaseAmount);
    }

}
