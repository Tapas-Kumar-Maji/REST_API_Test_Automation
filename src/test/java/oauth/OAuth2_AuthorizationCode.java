package oauth;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

public class OAuth2_AuthorizationCode {

	private static String accessToken;
	private static String authorizationCode;
//	private static WebDriver driver;
	
	@Test
	public void getAuthorizationcode() throws InterruptedException {
		// Get responseUrl.
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\tapos\\chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2L));
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//
//		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("tapasmaji908@gmail.com");
//		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Keys.ENTER);
//		driver.findElement(By.xpath("//span[contains(text(), 'Try another way')]")).click();
//		driver.findElement(By.xpath("//div[contains(text(), 'passkey')]")).click();
//		Thread.sleep(3000);
//		String responseUrl = driver.getCurrentUrl();

		String responseUrl = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AVG7fiR_hWnAODBzqURxb77dg9Y4qR2E1osSXBZqavkJ1NX-dd1K1oG5Sx3dt6FDyzMZxQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";

		// Extract code value from responseUrl.
		String searchString = "code=";
		int startIndex = responseUrl.indexOf(searchString);

		if (startIndex != -1) {
			startIndex += searchString.length();		  // Move the start index to the position after "code="
			int endIndex = responseUrl.indexOf("&scope", startIndex);
			if (endIndex == -1)
				endIndex = responseUrl.length();
			authorizationCode = responseUrl.substring(startIndex, endIndex);
		}
		System.out.println("authorizationCode : " + authorizationCode);
	}
	
	@Test(dependsOnMethods = { "getAuthorizationcode" })
	public void getAccessToken() {
		accessToken =
		given()
				.urlEncodingEnabled(false)
				.baseUri("https://www.googleapis.com/oauth2/v4/token")
				.queryParam("code", authorizationCode)
				.queryParam("grant_type", "authorization_code")
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").
		when()
				.post().
		then()				
				.extract()
				.path("access_token");
				
		System.out.println("\n\naccess token : "+accessToken);
	}

	@Test(dependsOnMethods = { "getAccessToken" })
	public void getCourses() {
		String coursesJson =
		given()
				.baseUri("https://rahulshettyacademy.com/getCourse.php")
				.queryParam("access_token", accessToken).
		when()
				.get().
		then()
				.log().all()
				.extract()
				.asPrettyString();
		System.out.println("\n\n\n" + coursesJson);
	}
}
