package string;

public class StringMain {

	public static void main(String[] args) {
		String msg2 = "Amount from Account : 100001";
		String[] arr = msg2.split(" ");
		for(String s:arr) {
			if(s.matches("\\d{6}"))
				System.out.println(s);
		}
	}

}
