package lambda_demo;

public class OldStyleInterfaceImplimentation implements HelloFunctonal {

	@Override
	public void helloFromFunctional() {
		System.out.println("Hello from Overriden Method in Class. [ Using Class Referance ]");

	}

	public static void main(String[] args) {
		
		HelloFunctonal h0 = new OldStyleInterfaceImplimentation();
		h0.helloFromFunctional();
		
		HelloFunctonal h1 = new HelloFunctonal() {
			
			@Override
			public void helloFromFunctional() {
				System.out.println("Hello from Overriden Method in Class. [ Using Interface Referance ]");
			}
		};
		h1.helloFromFunctional();

	}

}
