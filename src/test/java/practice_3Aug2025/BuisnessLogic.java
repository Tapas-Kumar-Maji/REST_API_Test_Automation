package practice_3Aug2025;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import pojo.google_maps.CoursePojo;

public class BuisnessLogic {

	@Test(enabled = false)
	public void test() {
		JsonPath jp = new JsonPath(RequestPayloads.buisnessLogic());
		System.out.println(jp.getString("courses[2].title"));
		System.out.println(jp.getInt("courses.size()"));
	}

	@Test(enabled = false)
	public void titleAndPrice() {
		JsonPath jp = new JsonPath(RequestPayloads.buisnessLogic());
		List<CoursePojo> ls = jp.getList("courses", CoursePojo.class);

		for (CoursePojo obj : ls) {
			System.out.println(obj.getTitle());
			System.out.println(obj.getPrice());
			System.out.println(obj.getCopies() + "\n");
		}
	}

	@Test(enabled = false)
	public void appiumPrice() {
		JsonPath jp = new JsonPath(RequestPayloads.buisnessLogic());
		List<CoursePojo> ls = jp.getList("courses", CoursePojo.class);

		for (CoursePojo obj : ls) {
			if (obj.getTitle().equalsIgnoreCase("RPA")) {
				System.out.println(obj.getPrice());
				return;
			}
		}
	}

	@Test
	public void checkingSum() {
		JsonPath jp = new JsonPath(RequestPayloads.buisnessLogic());
		List<CoursePojo> ls = jp.getList("courses", CoursePojo.class);

		double sum = 0.0;
		for (CoursePojo obj : ls) {
			sum += obj.getPrice() * obj.getCopies();
		}

		Assert.assertEquals(jp.getInt("dashboard.purchaseAmount"), sum);
	}
}
