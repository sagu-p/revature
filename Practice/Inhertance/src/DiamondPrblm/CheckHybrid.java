package DiamondPrblm;

public class CheckHybrid {
	
	public static void main(String[] args) {
		
		//			interface
		//interface				class
		//			class
		System.out.println("Hybride Inheritance type 1:");
		D o =new D();
		o.showA();
		o.showB();
		o.showC();
		
		//			interface
		//interface				interface
		//			class
		System.out.println("\nHybride Inheritance type 2:");		
		DD oo = new DD();
		oo.showA();
		oo.showB();
		oo.showCC();
		
	}

}
