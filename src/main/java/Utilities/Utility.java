package Utilities;

import java.io.File;
import java.io.InputStream;

import io.restassured.path.json.JsonPath;

public class Utility {

	public static JsonPath getJsonPath(Object obj) {
		if (obj instanceof String)
			return new JsonPath((String) obj);
		else if (obj instanceof File)
			return new JsonPath((File) obj);
		else if (obj instanceof InputStream)
			return new JsonPath((InputStream) obj);
		else {
			System.err.println("Illegal argument passed : " + obj.getClass().getSimpleName());
			return null;
		}
	}
}
