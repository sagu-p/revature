package com.mokito;

public class Claculator {

	CalService service;
	
	
	
	public Claculator(CalService service) {
		this.service = service;
	}



	public int perform(int i, int j) {
		return service.add(i, j);
		//return (i+j);
	}
	
}
