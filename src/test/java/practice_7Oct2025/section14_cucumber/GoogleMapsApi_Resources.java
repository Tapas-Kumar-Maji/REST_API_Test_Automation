package practice_7Oct2025.section14_cucumber;

public enum GoogleMapsApi_Resources {

    ADDPLACE("/maps/api/place/add/json"),
    GETPLACE("/maps/api/place/get/json"),
    DELETEPLACE("/maps/api/place/delete/json");

    private final String resource;

    GoogleMapsApi_Resources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

}

//public enum GoogleMapsApi_Endpoints {
//
//    ADDPLACE,
//    GETPLACE,
//    DELETEPLACE;
//    
//    public static String getEndpoint(String end) {
//
//	switch (end.toLowerCase()) {
//	case "addplace":
//	    return "/maps/api/place/add/json";
//	case "getplace":
//	    return "/maps/api/place/get/json";
//	case "deleteplace":
//	    return "/maps/api/place/delete/json";
//	default:
//	    throw new IllegalArgumentException("Invalid endpoint name : " + end);
//	}
//
//    }
//}
