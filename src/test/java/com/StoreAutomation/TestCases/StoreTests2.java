package com.StoreAutomation.TestCases;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.StoreAutomation.Endpoints.StoreEndPoints2;
import com.StoreAutomation.Payloads.Store;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class StoreTests2 {
	Faker faker;
	Store userPayload;
	public Logger logger;
	@BeforeTest
	public void setUP()
	{

		faker=new Faker();
		userPayload=new Store();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setPetId(faker.number().numberBetween(1, 1000));
		userPayload.setQuantity(faker.number().numberBetween(1, 50));
		userPayload.setShipDate(faker.date().future(10, java.util.concurrent.TimeUnit.DAYS));
		userPayload.setStatus(faker.options().option("placed", "approved", "delivered"));
		userPayload.setComplete(faker.bool().bool());
		logger=LogManager.getLogger(this.getClass());
	}
	@Test(priority=1)
	public void testPostStore()
	{
		logger.info("===============creating the store===================");
		Response response=StoreEndPoints2.createdStore(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);	
		logger.info("=================store is created===================");
	}
	@Test(priority=2)
	public void testGetStore()
	{
		logger.info(" ================the store details are===================");
		Response response=StoreEndPoints2.readStore(this.userPayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);	
	}
	@Test(priority=3)
	public void testDeleteStore()
	{
		Response response=StoreEndPoints2.deleteStore(this.userPayload.getId());
		Assert.assertEquals(response.getStatusCode(), 200);	
		logger.info("=====================store is deleted=====================");
	}

}
