package DiamondPrblm;

public class C implements A  {

	public void showC() {
		System.out.println("In calss C. [show()]");		
	}

	@Override
	public void showA() {
		System.out.println("Overriden Method of interface A in calss C. [showA()] >>> a = " + AA);
		
	}

}
