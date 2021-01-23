package eg;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import demo.A;
import eg.impl.ABImpl;

class ABTest {
	
	A mockA = mock(A.class);

	@Test
	void test() {
		when(mockA.sumation(Mockito.anyInt(), Mockito.anyInt())).thenReturn(1);
		
		AB ab = new ABImpl();
		ab.sumation(2, 3);
		
		verify(mockA, times(1)).sumation(2, 3);
		
	}

}
