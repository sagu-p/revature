package dem1;

public class ChechPoly {
	
	public static void main(String[] args) {
		
		System.out.println("Method Overloading:");
		//Methid Overloading
		A a = new A();
		a.show();
		a.show(2611);
		
		System.out.println("\nMethod Overriding:");
		
		//Method Overriding
		B o = new B();
		o.show();
	}

}
