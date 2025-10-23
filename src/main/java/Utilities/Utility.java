package Utilities;

import java.io.File;
import java.io.InputStream;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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

	/**
	 * Converts String JSON body into JsonPath.
	 * 
	 * @param jsonResponseBody
	 * @return
	 */
	public static JsonPath parseJsonBody(String jsonResponseBody) {

	    return new JsonPath(jsonResponseBody);

	}

	/**
	 * Generates a random 3 letter String
	 * 
	 * @return String
	 */
	public static String generateRandomString() {

	    String alphabets = "abcdefghijklmnopqrstuvwxyz";
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();

	    for (int i = 0; i < 3; i++) {
		int index = random.nextInt(alphabets.length());
		sb.append(alphabets.charAt(index));
	    }

	    return sb.toString();

	}

	/**
	 * Generates a random 3 digit Integer
	 * 
	 * @return int
	 */
	public static int generateRandomInteger() {

	    Random random = new Random();
	    return random.nextInt(100, 1000);

	}

	/**
	 * Indents the POJO class object
	 * 
	 * @param obj
	 * @return as pretty String
	 */
	public static String asPrettyString(Object obj) {

	    try {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		return mapper.writeValueAsString(obj);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    return null;

	}
}
