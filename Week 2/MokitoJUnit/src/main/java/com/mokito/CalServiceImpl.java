package com.mokito;

import com.mokito.ope.CalServiceOPE;
import com.mokito.ope.CalServiceOPEImpl;

public class CalServiceImpl implements CalService {

	CalServiceOPE calServiceOPE = new CalServiceOPEImpl();
	CalServiceImpl cal = new CalServiceImpl();
	
	@Override
	public int sagu(int i, int j) {
		// TODO Auto-generated method stub
		//int c = calServiceOPE.sagu(i, j);
		return calServiceOPE.sagu(i, j);
		//return cal.a();
	}
	
	@Override
	public int a() {
		
		int c = 2611;
		
		return c;
	}

}
