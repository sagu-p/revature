package com.app.service;

import org.apache.log4j.Logger;

public class Services {
	
	private static Logger log = Logger.getLogger(Services.class);
	
	public void SayHello() {
		log.info("Hello from SayHello() in Class Services");
		log.trace("Hello from SayHello() in Class Services");
	}

}
