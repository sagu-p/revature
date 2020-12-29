package com.app.services2.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.app.exception.BussinessException;
import com.app.services2.AppSerives;
import com.app.services2.impl.AppServicesImpl;

class AppServicesImplTest {

	private static AppSerives appServices = null;
	
	@BeforeAll
	public static void intializeApplService() {
		appServices = new AppServicesImpl();
	}
	
	@Test
	public void testIsValidPrimeNumberForTrue() {
		System.out.println("testIsValidPrimeNumberForTrue() ");
		assertTrue(appServices.isValidPrimeNumber(3));
	}

	@Test
	public void testIsValidPrimeNumberForFalse() {
		System.out.println("testIsValidPrimeNumberForFalse() ");
		assertFalse(appServices.isValidPrimeNumber(4));
	}
	
	
	@Test
	public void testIsValidMobileNumberForTrue() {
		System.out.println("testIsValidMobileNumberForTrue() ");
		assertTrue(appServices.isValidMobileNumber("+1-1234567890"));
	}
	
	/*
	@Test
	public void testIsValidMibileNumberForFalse() {
		assertFalse(appServices.isValidMibileNumber("+91-1234567890"));
	}*/
	
	@Test
	public void testIsValidMobileNumberForBussinessException() {
		
		Executable executable = new Executable() {
			
			@Override
			public void execute() throws Throwable {
				appServices.isValidMobileNumber("6328");
				
			}
		};
		System.out.println("testIsValidMibileNumberForBussinessException() ");
		assertThrows(BussinessException.class , executable);
		
	}
	
	@Test
	public void testIsValidMobileNumberForBussinessExceptionForNull() {
		System.out.println("testIsValidMibileNumberForBussinessExceptionForNull() ");
		assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				appServices.isValidMobileNumber(null);
				
			}
		});
	}
	
}