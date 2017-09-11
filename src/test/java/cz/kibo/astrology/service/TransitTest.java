package cz.kibo.astrology.service;

import static junit.framework.TestCase.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spark.Spark;

public class TransitTest {
	
	@BeforeClass
	public static void beforeClass() {
		API newRoutes = new API();
        newRoutes.setupEndpoints();
		Spark.awaitInitialization();
	}
		 
	@AfterClass
	public static void afterClass() {
	    Spark.stop();
	}
		
	@Test
	public void corruptedJSON(){
		String reguestJSON = "{}";
		
		URLResponse res = new URLResponse("POST", API.API_CONTEXT +"/calc", reguestJSON);
		
		assertEquals(200, res.getStatus());
		assertEquals( "application/json", res.getHeaders().get("Content-Type").get(0));		
		assertEquals("{\"status\":\"running\"}", res.getBody());	
	}

}
