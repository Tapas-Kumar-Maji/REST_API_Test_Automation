package practice_7Oct2025;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.Utility;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.time.Duration;
import java.util.List;
 
public class Section13_ECommerce_ApiTest {
    
    RequestSpecification commonReqSpec = null;
    String token = null;
    String userId = null;
    String productId = null;
    String orderId = null;

    @Test(priority = -1)
    public void login() {
	
	// Request
	RequestSpecification reqSpec = new RequestSpecBuilder()
		.setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON)
		.build();
	
	LoginRequestPojo reqObj = new LoginRequestPojo();
	reqObj.setUserEmail("tapasmaji908@gmail.com");
	reqObj.setUserPassword("#1Tapasmaji");
	
	// Response
	LoginResponsePojo res =
		given()
		.spec(reqSpec)
        	.body(reqObj)
        	
        	.when()
        	.post("/api/ecom/auth/login")
        	
        	.then()
        	.assertThat()
        	.statusCode(200)
        	
        	.extract()
        	.response()
        	.as(LoginResponsePojo.class);
	
	this.token = res.getToken();
	this.userId = res.getUserId();
	System.out.println("Login Response : \n" + Utility.asPrettyString(res) + "\n");

    }
    
    @Test(dependsOnMethods = { "login" })
    public void createProduct() throws InterruptedException {
	Thread.sleep(Duration.ofSeconds(2L));

	// Request
	this.commonReqSpec = new RequestSpecBuilder()
		.setBaseUri("https://rahulshettyacademy.com")
		.addHeader("Authorization", this.token)
		.build();
	
	RequestSpecification reqSpec =
		given()
		.spec(this.commonReqSpec)
		.multiPart("productName", "Juliana Columbiana")
		.multiPart("productAddedBy", this.userId)
		.multiPart("productCategory", "MILF")
		.multiPart("productSubCategory", "XXX")
		.multiPart("productPrice", "69.69")
		.multiPart("productDescription", "BIG B00TY")
		.multiPart("productFor", "men")
		.multiPart("productImage", new File(System.getProperty("user.dir")+ "/src/test/resources/Juliana.jpeg"));
		
	// Response
	CreateProductResponsePojo res =
		reqSpec
		.when()
		.post("/api/ecom/product/add-product")
		
		.then()
		
		.assertThat()
		.statusCode(201)
		
		.extract()
		.as(CreateProductResponsePojo.class);

	this.productId = res.getProductId();
	Assert.assertEquals(res.getMessage(), "Product Added Successfully");
	System.out.println("Create Product Response : \n" + Utility.asPrettyString(res) + "\n");

    }

    @Test(dependsOnMethods = { "createProduct" })
    public void createOrder() throws InterruptedException {
	Thread.sleep(Duration.ofSeconds(2L));
	
	// Request
	Order order = new Order();
	order.setCountry("United Kingdom");
	order.setProductOrderedId(this.productId);
	
	CreateOrderRequestPojo reqObj = new CreateOrderRequestPojo();
	reqObj.setOrders(List.of(order));
	
	RequestSpecification reqSpec =
		given()
		.spec(this.commonReqSpec)
		.contentType(ContentType.JSON)
		.body(reqObj);
	
	// Response
	CreateOrderResponsePojo res =
		reqSpec
		.when()
		.post("/api/ecom/order/create-order")
		
		.then()
		.assertThat()
		.statusCode(201)
		
		.extract()
		.as(CreateOrderResponsePojo.class);

	this.orderId = res.getProductOrderId().get(0);
	Assert.assertEquals(res.getMessage(), "Order Placed Successfully");
	System.out.println("Create Order Response: \n" + Utility.asPrettyString(res) + "\n");

    }

    @Test(dependsOnMethods = { "createOrder" })
    public void deleteProduct() throws InterruptedException {
	Thread.sleep(Duration.ofSeconds(2L));
	
	// Request
	RequestSpecification reqSpec =
		given()
		.relaxedHTTPSValidation()
		.spec(this.commonReqSpec)
		.pathParam("productId", this.productId);
	
	// Response
	DeleteProductResponsePojo res =
        	reqSpec
        	
        	.when()
        	.delete("/api/ecom/product/delete-product/{productId}")
        	
        	.then()
        	.assertThat()
        	.statusCode(200)
        	
        	.extract()
        	.as(DeleteProductResponsePojo.class);
	
	Assert.assertEquals(res.getMessage(), "Product Deleted Successfully");
	System.out.println("Delete Product Response: \n" + Utility.asPrettyString(res) + "\n");
    }
}