package practice_7Oct2025.section14_cucumber;

import java.util.ArrayList;
import java.util.List;

import practice_7Oct2025.AddPlaceRequestPojo;
import practice_7Oct2025.Location;

public class GoogleMapsApi_RequestPayload {

    public AddPlaceRequestPojo getAddPlaceApiPayload(String name, String language, String address) {

	Location location = new Location();
	location.setLat(-38.383494);
	location.setLng(33.427362);

	AddPlaceRequestPojo reqBody = new AddPlaceRequestPojo();
	reqBody.setLocation(location);
	reqBody.setAccuracy(50);
	reqBody.setName(name);
	reqBody.setPhone_number("(+91) 983 893 3937");
	reqBody.setAddress(address);
	reqBody.setTypes(new ArrayList<String>(List.of("shoe park", "shoe")));
	reqBody.setWebsite("http://google.com");
	reqBody.setLanguage(language);

	return reqBody;

    }
}
