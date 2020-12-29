package com.dmo.service.test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.dmo.service.MyService;

public class MyServiceTest {
	
	private static MyService myService = null;
	
	@BeforeAll
	public static void setUpMyServices() {
		myService = new MyService();
	}
	
	@Test
	public void testSetHelooByName() {
		String output = "Hello Sagar";
		assertEquals(output, myService.sayHello("Sagar"));
	}
	
	@Test
	public void testSayHelloForNull() {
		assertNull(myService.sayHello(null));
	}

}
