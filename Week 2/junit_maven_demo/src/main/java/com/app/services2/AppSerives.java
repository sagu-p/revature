package com.app.services2;

import com.app.exception.BussinessException;

public interface AppSerives {
	
	boolean isValidPrimeNumber( int num);
	boolean isValidMobileNumber(String contact) throws BussinessException;

}
