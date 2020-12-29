package com.dmo.service;

public class MyService {
	
	public String sayHello (String name) {
		if (name == null)
			return null;
		else
			return "Hello "+name;
	}

}
