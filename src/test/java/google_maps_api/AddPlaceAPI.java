package google_maps_api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.google_maps.AddPlacePojo;
import pojo.google_maps.Location;

public class AddPlaceAPI {

	@Test(dataProvider = "HashMapData")
	public void addPlace(Map<String, Object> map) {
//		ObjectMapping.serialize(pojo, null, null, null, null, null); // rest assured method for serialization.
//		(can be used instead of Jackson library)

//		ObjectMapper mapper = new ObjectMapper();
//		String jsonString = mapper.writeValueAsString(this.getObject());

		String placeId =
		given().
			baseUri("https://rahulshettyacademy.com").
			queryParam("key", "qaclick123").
//			body(this.getObject()).
			body(map).
			log().all().
		when().
			post("/maps/api/place/add/json").
		then().
			assertThat().
			statusCode(200).
		extract().
			path("place_id");

		System.out.println("Place Id : " + placeId);
	}

	public AddPlacePojo getObject() {
		Location location = new Location();
		location.setLat(23.89);
		location.setLng(44.28);

		AddPlacePojo pojo = new AddPlacePojo();
		pojo.setLocation(location);
		pojo.setAccuracy(1562);
		pojo.setName("Tapas Kr. Maji");
		pojo.setPhone_number("8945672324");
		pojo.setAddress("Park Street, Kokata - 700001");
		pojo.setTypes(new String[] { "Dog", "Cat", "Cow" });
		pojo.setWebsite("http://TapasMaji.com/cool/dude");
		pojo.setLanguage("English - IN");

		return pojo;
	}

	@DataProvider(name = "HashMapData")
	public Object[][] dataAsHashMap() {
		Map<String, Object> location = new HashMap<>();
		location.put("lat", 23.56);
		location.put("lng", 89.14);

		Map<String, Object> map1 = new HashMap<>();
		map1.put("location", location);
		map1.put("accuracy", 9528);
		map1.put("name", "Dr. Fiza");
		map1.put("phone_number", "(+91)9678912358");
		map1.put("address", "Durgapur - Steel City");
		map1.put("types", new String[] { "meow", "bark", "moo" });
		map1.put("website", "www.tupac.com");
		map1.put("language", "French");

		Map<String, Object> map2 = new HashMap<>();
		map2.put("location", location);
		map2.put("accuracy", 5628);
		map2.put("name", "Mr. Pal");
		map2.put("phone_number", "(+91)65786532168");
		map2.put("address", "Siliguri - Wood City");
		map2.put("types", new String[] { "blue", "red", "green" });
		map2.put("website", "www.geo.com");
		map2.put("language", "Bengali");

		return new Object[][] { { map1 }, { map2 } };
	}
}
