package com.app.services.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.services.MyServices;

class MyServicesTest {

	private static MyServices myServies = null;
	
	@BeforeAll
	public static void intializeMySerices () {
		myServies = new MyServices();
	}
	
	@Test
	void testSayHello1() {
		String output = "Hello Sagar";
		assertEquals(output, myServies.sayHello("Sagar"));
	}

	@Test
	void testSayHello2() {
		assertNull(myServies.sayHello(null));
	}
	
}
