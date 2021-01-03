package eg1;

public class InterfaceMain implements Java8Interface {

	public static void main(String[] args) {
		
		Java8Interface.helloFromInterface();
		
		Java8Interface im = new InterfaceMain();
		im.helloDefault();

	}

}
