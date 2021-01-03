package eg1;

public interface Java8Interface {
	
	static void helloFromInterface() {
		System.out.println("Hello User from Java8 Interface Static mathod.");
	}
	
	public default void helloDefault() {
		System.out.println("Hello User from Java8 Interface Default mathod.");
	}

}
