package CustomExp;

public class ValidateExp {
	
	public static void main(String[] args) {
		
		int age = -1;
		ValidateExp v = new ValidateExp();

			try {
				v.validage(age);
			} catch (InvalidAgeException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		
	}

	void validage(int age) throws InvalidAgeException
	{
		if(age<0)
			throw new InvalidAgeException("Age Not be in -ve");
	}
}
