package com.app.services2.impl;

import com.app.exception.BussinessException;
import com.app.services2.AppSerives;

public class AppServicesImpl implements AppSerives {

	@Override
	public boolean isValidPrimeNumber(int num) {
		boolean c = true;
		for(int i=2; i<num; i++)
		{
			if(num % i == 0)
			{
				c = false;
				break;
			}
		}
		return c;
	}

	@Override
	public boolean isValidMobileNumber(String contact) throws BussinessException {
		boolean c = false; 
		if(contact != null && contact.matches("\\+1-[0-9]{10}")) 
			c = true;
		else
			throw new BussinessException("Enter valid Cintact number...");
		
		return c;
	}

}
