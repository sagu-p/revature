package com.mokito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mokito.ope.CalServiceOPE;
import com.mokito.ope.CalServiceOPEImpl;

class CalServiceTest {

	CalServiceOPE mockCalServiceOPE = mock(CalServiceOPE.class);
	CalService calService = mock(CalService.class);
	
	@Test
	void test() {
		
		
		when(calService.a()).thenReturn(26);
		//Mockito.doReturn(261198).when(mockCalServiceOPE).sagu(0, 0);
		System.out.println(calService.a());
		System.out.println(calService.sagu(0, 0));
		assertEquals(26, calService.sagu(0, 0));
		
	}

}
