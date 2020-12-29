package com.app;

import org.apache.log4j.Logger;

import com.app.service.Services;

public class HelloLog4j {

	private static Logger log = Logger.getLogger(HelloLog4j.class);
	
	public static void main(String[] args) {

		log.trace("Hello from TRACE.");
		log.debug("Hello from DEBUG.");
		log.info("Hello from INFO.");
		log.warn("Hello from WARN.");
		log.error("Hello from ERROR.");
		log.fatal("Hello from FATAL.");
		
		Services services = new Services();
		services.SayHello();
		
	}

}
