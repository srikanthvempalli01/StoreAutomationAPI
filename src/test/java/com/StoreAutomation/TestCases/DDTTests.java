package com.StoreAutomation.TestCases;

import java.text.ParseException;

import java.text.SimpleDateFormat;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.StoreAutomation.Endpoints.StoreEndPoints;
import com.StoreAutomation.Payloads.Store;
import com.StoreAutomation.Utilities.DataProviders;

import io.restassured.response.Response;

public class DDTTests 
{
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String id, String petId, String quantity, String date, String status, String complete) throws ParseException 
	{
	    Store userPayload = new Store();
	    userPayload.setId(Integer.parseInt(id));
	    userPayload.setPetId(Integer.parseInt(petId));
	    userPayload.setQuantity(Integer.parseInt(quantity));
	    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	    userPayload.setShipDate(dateFormat.parse(date)); 
	    
	    userPayload.setStatus(status.trim()); 
	    userPayload.setComplete(Boolean.parseBoolean(complete));
	    
	    Response response = StoreEndPoints.createdStore(userPayload);
	    response.then().log().all();
	    Assert.assertEquals(response.statusCode(), 200);
	}
}
