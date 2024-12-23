package com.StoreAutomation.Endpoints;
import java.util.ResourceBundle;
import com.StoreAutomation.Payloads.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
public class StoreEndPoints2 {
	
	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}
	public static Response createdStore(Store payload)
	{
		String post_url=getURL().getString("post_url");
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);
		return response;
		
	}
	public static Response readStore(int orderId)
	{
		String get_url=getURL().getString("get_url");
		Response response=given()
			.pathParam("orderId", orderId)
		.when()
			.get(get_url);
		return response;
	}
	public static Response updateStore(int orderId,Store payload)
	{
		String update_url=getURL().getString("update_url");
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("orderId", orderId)
			.body(payload)
		.when()
			.put(update_url);
		return response;	
	}
	public static Response deleteStore(int orderId)
	{
		String delete_url=getURL().getString("delete_url");
		Response response=given()
			.pathParam("orderId", orderId)
		.when()
			.delete(delete_url);
		return response;
	}
}
