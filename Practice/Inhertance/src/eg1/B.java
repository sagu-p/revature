package eg1;

public class B extends A {
	
	void showB()
	{
		System.out.println("Class B. [ShowB()]");
	}
	
	void show()
	{
		super.show();
		System.out.println("Class B.");
	}
}