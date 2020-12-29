package com.app.services;

public class MyServices {

	public String sayHello(String name) {
		if (name != null)
			return "Hello " + name;
		else
			return null;
	}
	
}
