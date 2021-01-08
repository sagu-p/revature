package com.mokito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

class ClaculatorTest {
	
	static Claculator cal;
	
	//this is call STUB
/*	CalService service = new CalService() {
		
		@Override
		public int add(int i, int j) {
			// TODO Auto-generated method stub
			return 0;
		}
	};
*/	
	//dumy object of CalService in memory for testing
	// THERE 2 WAY TO ACHIVE IT...
	// (1)  >>>
	CalService service = mock(CalService.class);
	
	/*
	 *  (2)   >>>
	@Mock
	CalService service;
	
	@Rule MockitoRule rule = MockitoJUnit.rule(); 
	*/
	
	
	@BeforeEach
	public void setUp() {
		cal = new Claculator(service);
		System.out.println("Hello Before");
	}

	@Test
	void testPerform() {
		when(service.add(2, 3)).thenReturn(5);
		assertEquals(5, cal.perform(2, 3));
		verify(service).add(2, 3);
	}

}
