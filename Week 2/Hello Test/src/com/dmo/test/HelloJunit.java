package com.dmo.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HelloJunit {
	
	@BeforeAll
	public static void helloBeforeAll() {
		System.out.println("Hello from HelloBeforeAll()");
	}
	
	@BeforeEach
	public void helloBeforeEach() {
		System.out.println("Hello from HelloBeforeEach()");
	}
	
	@Test
	public void testHello1() {
		System.out.println("Testing testHello1()");
	}
	
	@Test
	public void testHello2() {
		System.out.println("Testing testHello2()");
	}
	
	@AfterEach
	public void helloAfterEach() {
		System.out.println("Hello from HelloAterEach()");
	}
	
	@AfterAll
	public static void helloAfterAll() {
		System.out.println("Hello from HelloAfterAll()");
	}
	
	/*
	 * @BeforeAll
	public static void helloBeforeAll() {
		System.out.println("Hello from helloBeforeAll()");
	}
	
	@BeforeEach
	public void helloBeforeEach() {
		System.out.println("Hello from helloBeforeEach(). This willl be executed before any test case within this class");
	}
	
	
	
	@Test
	public void testHello1() {
		System.out.println("Hello from testHello1()");
	}
	
	@Test
	public void testHello2() {
		System.out.println("Hello from testHello2()");
	}
	@AfterEach
	public void helloAfterEach() {
		System.out.println("Hello from helloAfterEach(). This willl be executed after every test case within this class");
	}
	
	@AfterAll
	public static void helloAfterAll() {
		System.out.println("Hello from helloAfterAll()");
	}
	 */

}
