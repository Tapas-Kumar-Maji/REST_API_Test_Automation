package practice_3Aug2025;

public class RequestPayloads {

	public static String addPlace() {
		return "{\r\n"
				+ "    \"location\": {\r\n"
				+ "        \"lat\": -38.383494,\r\n"
				+ "        \"lng\": 33.427362\r\n"
				+ "    },\r\n"
				+ "    \"accuracy\": 50,\r\n"
				+ "    \"name\": \"Tapas Kumar\",\r\n"
				+ "    \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "    \"address\": \"29, side layout, cojen 09\",\r\n"
				+ "    \"types\": [\r\n"
				+ "        \"shoe park\",\r\n"
				+ "        \"shop\"\r\n"
				+ "    ],\r\n"
				+ "    \"website\": \"http://google.com\",\r\n"
				+ "    \"language\": \"French-IN\"\r\n"
				+ "}";
	}
	
	public static String buisnessLogic() {
		return "{\r\n"
				+ "  \"dashboard\": {\r\n"
				+ "    \"purchaseAmount\": 910,\r\n"
				+ "    \"website\": \"rahulshettyacademy.com\"\r\n"
				+ "  },\r\n"
				+ "  \"courses\": [\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"Selenium Python\",\r\n"
				+ "      \"price\": 50,\r\n"
				+ "      \"copies\": 6\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"Cypress\",\r\n"
				+ "      \"price\": 40,\r\n"
				+ "      \"copies\": 4\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"RPA\",\r\n"
				+ "      \"price\": 45,\r\n"
				+ "      \"copies\": 10\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
	}
	
	public static String addBook(String aisle, String isbn) {
		return "{\r\n"
				+ "  \"name\": \"Learn Appium Automation with Java\",\r\n"
				+ "  \"isbn\": \"" + aisle + "\",\r\n"
				+ "  \"aisle\": \"" + isbn + "\",\r\n"
				+ "  \"author\": \"John foe\"\r\n"
				+ "}";
	}

	public static String basicAuthPasswordJira() {
		return "dGFwYXNtYWppOTA4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjAwR3dOUnVQNWdodkQyWWpGNnBYTmx3NWNPSUh0ZDloMUpTR2lhVHdwUi1Lc3ptUjh1Nm9haW5PcUVjc0FCeWE2eEJUWEd4NXZDY043dDVoRTVFV29kcVI1b19OM0ZlcWpfd080NHllQXlOWkFjQU5uUEZCeVZuYkZYQkh5MzdXaS1USF9QOUZRRnN1MEdmR0tMbzZHTUw1OXJ6dXlwUlZVV2Q2QndDc2J6Q2M9RDJDQTE0MTQ=";
	}
}
