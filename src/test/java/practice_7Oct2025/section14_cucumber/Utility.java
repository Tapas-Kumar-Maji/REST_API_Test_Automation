package practice_7Oct2025.section14_cucumber;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.Properties;

public class Utility {

    private static RequestSpecification reqSpec;
    private static PrintStream logStream;

    /**
     * Synchronized for thread-safe lazy initialization.
     * Logs the API request and response
     * 
     * @return RequestSpecification
     * @throws FileNotFoundException
     */
    public static synchronized RequestSpecification requestSpecification() {

	if (reqSpec == null) {
	    // Base URI setup
	    RestAssured.baseURI = Utility.getPropertyValue("baseUrl");

	    // Create logs directory if it doesn't exist
	    File logDir = new File(System.getProperty("user.dir") + "/logs");
	    if (!logDir.exists()) {
		logDir.mkdir();
	    }

	    // Create a unique log file for this test run
	    // String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String logFilePath = logDir + "/api_log" + ".txt";

	    try {
		logStream = new PrintStream(new File(logFilePath));
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    }

	    // Build the reusable RequestSpecification
	    reqSpec = new RequestSpecBuilder()
		    .addQueryParam("key", "qaclick123")
		    .setContentType(ContentType.JSON)
		    .addFilter(RequestLoggingFilter.logRequestTo(logStream))
		    .addFilter(ResponseLoggingFilter.logResponseTo(logStream))
		    .build();

	    // System.out.println("API log file created: " + logFilePath);
	}

	return reqSpec;
    }

    /**
     * Reads Config.properties file and returns the value of the specified key.
     * 
     * @param key
     * @return String
     */
    public static String getPropertyValue(String key) {

	Properties properties = new Properties();
	try {
	    properties.load(
		    new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/Config.properties"));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return properties.getProperty("baseUrl");

    }
}
