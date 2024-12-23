package com.StoreAutomation.Endpoints;
import static io.restassured.RestAssured.*;


import com.StoreAutomation.Payloads.Store;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class StoreEndPoints 
{
	 public static Response createdStore(Store payload)
	  {
		  Response response=given()
		  	.contentType(ContentType.JSON)
		  	.accept(ContentType.JSON)
		  	.body(payload)
		  .when()
		  	.post(Routes.post_url);
		  return response;
	  }
	 public static Response readStore(int orderId)
	  {
		  Response response=given()
		  	.pathParam("orderId", orderId)
		  .when()
		  	.get(Routes.get_url);
		  return response;
	  }
	 public static Response updateStore(int orderId,Store payload)
	  {
		  Response response=given()
		  	.contentType(ContentType.JSON)
		  	.accept(ContentType.JSON)
		  	.pathParam("orderId", orderId)
		  	.body(payload)
		  .when()
		  	.put(Routes.update_url);
		  return response;
	  }
	 public static Response deleteStore(int orderId)
	  {
		  Response response=given()
		  	.pathParam("orderId", orderId)
		  .when()
		  	.delete(Routes.delete_url);
		  return response;
	  }
	 
}
