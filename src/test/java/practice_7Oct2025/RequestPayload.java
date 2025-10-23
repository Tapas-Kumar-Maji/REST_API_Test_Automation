package practice_7Oct2025;

public class RequestPayload {

    public static String addPlaceRequestBody() {

	return "{\n"
		+ "    \"location\": {\n"
		+ "        \"lat\": -38.383494,\n"
		+ "        \"lng\": 33.427362\n"
		+ "    },\n"
		+ "    \"accuracy\": 50,\n"
		+ "    \"name\": \"Tapas Maji\",\n"
		+ "    \"phone_number\": \"(+91) 983 893 3937\",\n"
		+ "    \"address\": \"29, side layout, cojen 09\",\n"
		+ "    \"types\": [\n"
		+ "        \"shoe park\",\n"
		+ "        \"shop\"\n"
		+ "    ],\n"
		+ "    \"website\": \"http://google.com\",\n"
		+ "    \"language\": \"French-IN\"\n"
		+ "}";

    }
    
    public static String updatePlaceRequestBody(String placeName, String place_id) {

	return "{\n"
		+ "\"place_id\":\"" + place_id + "\",\n"
		+ "\"address\":\"" + placeName + "\",\n"
		+ "\"key\":\"qaclick123\"\n"
		+ "}\n"
		+ "";

    }

    public static String deletePlaceRequestBody(String place_id) {
	
	return "{\n"
		+ "    \"place_id\":\"" + place_id + "\"\n"
		+ "}";
	
    }
    
    public static String mockResponseBody() {
	
	return "{\n"
		+ "    \"dashboard\": {\n"
		+ "        \"purchaseAmount\": 910,\n"
		+ "        \"website\": \"rahulshettyacademy.com\"\n"
		+ "    },\n"
		+ "    \"courses\": [\n"
		+ "        {\n"
		+ "            \"title\": \"Selenium Python\",\n"
		+ "            \"price\": 50,\n"
		+ "            \"copies\": 6\n"
		+ "        },\n"
		+ "        {\n"
		+ "            \"title\": \"Cypress\",\n"
		+ "            \"price\": 40,\n"
		+ "            \"copies\": 4\n"
		+ "        },\n"
		+ "        {\n"
		+ "            \"title\": \"RPA\",\n"
		+ "            \"price\": 45,\n"
		+ "            \"copies\": 10\n"
		+ "        }\n"
		+ "    ]\n"
		+ "}";	

    }
    
    public static String addBook(String isbn, String aisle) {
	
	return "{\n"
		+ "    \"name\": \"Learn Rest Assured with Java\",\n"
		+ "    \"isbn\": \"" + isbn + "\",\n"
		+ "    \"aisle\": \"" + aisle + "\",\n"
		+ "    \"author\": \"John foe\"\n"
		+ "}";

    }
}
