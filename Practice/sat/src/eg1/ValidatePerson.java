package eg1;

import java.util.HashSet;
import java.util.Set;

public class ValidatePerson {
	
	public static void main(String[] args) {
		
		Person p = new Person(100, "Sagar");
		Person p1 = new Person(101, "Sagar");
		
		System.out.println(p);
		System.out.println(p1);
		
		System.out.println("p.equals(p1): "+p.equals(p1));
		
		
		
		Set<Person> per = new HashSet<>();
		
		per.add(new Person( 200 , "Bhumi"));
		per.add(new Person( 200 , "Bhumi"));
		per.add(new Person( 222 , "Zeel"));
		per.add(new Person( 200 , "Zeel"));
		per.add(new Person( 201 , "Bhumi"));
		
		System.out.println("\n");
		for(Person o: per)
		{
			System.out.println(o.hashCode());
			System.out.println(o+"\n");
		}
			
		
	}

}
