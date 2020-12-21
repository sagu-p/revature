package eg1;

public class D extends A {
	
	void showD()
	{
		System.out.println("Class D. [ShowD()]");
	}
	
	void show()
	{
		super.show();
		System.out.println("Class D.");
	}
}