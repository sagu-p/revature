package eg.impl;

import demo.A;
import demo.impl.AImpl;
import eg.AB;

public class ABImpl implements AB {

	A o = new AImpl();
	
	@Override
	public int sumation(int a, int b) {
		// TODO Auto-generated method stub
		int c = a + b + o.sumation(a, b);
		return c;
	}

}
