package DiamondPrblm;

public class D extends C implements B {

	@Override
	public void showB() {
		System.out.println("Overriden Method of interface B in calss D. [showB()] >>> b = " + BB);		
	}

	@Override
	public void showA() {
		System.out.println("Overriden Method of interface A in calss D. [showA()] >>> a = " + AA);
		
	}
}
