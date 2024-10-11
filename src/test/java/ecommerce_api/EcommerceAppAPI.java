package ecommerce_api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;
import java.io.File;
import io.restassured.path.json.JsonPath;

public class EcommerceAppAPI {

	private String token;
	private String userId;
	private String productId;
	private String orderId;

	@Test
	public void loginApi() {
		JsonPath jsonPath =
		given()
				.baseUri("https://rahulshettyacademy.com")
				.header("content-type", "application/json")
				.body("{\"userEmail\":\"tapasmaji908@gmail.com\",\"userPassword\":\"#1Tapasmaji\"}").
		when()
				.post("api/ecom/auth/login").
		then()
				.assertThat()
				.statusCode(200)
				.body("message", equalTo("Login Successfully")).
		extract()
				.jsonPath();
		
		this.token = jsonPath.getString("token");
		this.userId = jsonPath.getString("userId");
		System.out.println("Token : " + this.token);
		System.out.println("User ID : " + this.userId);
	}


	@Test(dependsOnMethods = { "loginApi" })
	public void createProduct() {
		this.productId =
		given()
				.baseUri("https://rahulshettyacademy.com/")
				.header("Authorization", this.token)
				.multiPart("productName", "Malaika Arora")
				.multiPart("productAddedBy", this.userId)
				.multiPart("productCategory", "mujra wali")
				.multiPart("productSubCategory", "mujra")
				.multiPart("productPrice", "5612")
				.multiPart("productDescription", "very good mujra")
				.multiPart("productFor", "men")
				.multiPart("productImage" ,new File("C:\\Users\\tapos\\Pictures\\hqdefault.jpg")).
		when()
				.post("api/ecom/product/add-product").
		then()
				.assertThat()
				.statusCode(201)
				.body("message", equalTo("Product Added Successfully")).
		extract()
				.path("productId");
				
		System.out.println("Product Id : " + this.productId);
	}


	@Test(dependsOnMethods = { "createProduct" })
	public void createOrder() {
		this.orderId = 
		given()
				.baseUri("https://rahulshettyacademy.com/")
				.header("authorization", this.token)
				.header("content-type", "application/json")
				.body("{\"orders\":[{\"country\":\"India\",\"productOrderedId\":\"66fc49c2ae2afd4c0b8bc517\"}]}").
		when()
				.post("api/ecom/order/create-order").
		then()
				.assertThat()
				.statusCode(201)
				.body("message", equalTo("Order Placed Successfully")).
		extract()
				.path("orders[0]");
				
		System.out.println("Order Id : " + this.orderId);
	}


	@Test(dependsOnMethods = { "createOrder" })
	public void orderDetails() {
			given()
					.baseUri("https://rahulshettyacademy.com/")
					.queryParam("id", this.orderId)
					.header("authorization", this.token).
			when()
					.get("api/ecom/order/get-orders-details").
			then()
					.assertThat()
					.body("message", equalTo("Orders fetched for customer Successfully"));
	}


	@Test(dependsOnMethods = { "orderDetails" })
	public void deleteProduct() {
		given()
				.relaxedHTTPSValidation() // bypasses SSL validations.
				.baseUri("https://rahulshettyacademy.com/")
				.header("authorization", this.token)
				.pathParam("productId", this.productId).
		when()
				.delete("api/ecom/product/delete-product/{productId}").
		then()
				.assertThat()
				.statusCode(200)
				.body("message", equalTo("Product Deleted Successfully"));
	}

}
