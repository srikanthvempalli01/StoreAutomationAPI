package com.StoreAutomation.Endpoints;

public class Routes 
{
	//base  URL
	
	public static String base_url="https://petstore.swagger.io/v2";
	//store model
	
	public static String post_url=base_url+"/store/order";
	public static String get_url=base_url+"/store/order/{orderId}";
	public static String update_url=base_url+"/store/order/{orderId}";
	public static String delete_url=base_url+"/store/order/{orderId}";
}
