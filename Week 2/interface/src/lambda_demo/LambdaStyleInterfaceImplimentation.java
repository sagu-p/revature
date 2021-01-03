package lambda_demo;

public class LambdaStyleInterfaceImplimentation {

	public static void main(String[] args) {
		
		// Funtional Interface without Parameter
		HelloFunctonal h = () -> System.out.println("Hello Arrow Funtion in Class.");
		h.helloFromFunctional();
		
		// Funtional Interface with Parameter
		FunctionalByName hn0= (String name) -> System.out.println("Hello "+name+" from Arrow Function in Class.");
		hn0.helloFunctionalByName("Chandrakant Patel");
		
		FunctionalByName hn = (s) -> System.out.println("Hello "+s+" from Arrow Function in Class.");
		hn.helloFunctionalByName("Sagar Patel");
		
		FunctionalByName hn1 = (String s) -> System.out.println("Hello "+s+" from Arrow Function in Class.");
		hn1.helloFunctionalByName("Rita Patel");
		
		
		// Funtional Interface return type
		GetNameFunctional gn = (name) -> {
			String s = "Hello "+name;
			return s;
		};
		System.out.println(gn.getNameFromInterface("ZEEL TANK"));

	}

}
