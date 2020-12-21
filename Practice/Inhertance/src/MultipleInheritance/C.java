package MultipleInheritance;

public class C extends B implements A {

	@Override
	public void show() {
		System.out.println("Overriden Methid of interface A in class C [ show() ]");		
	}

}
