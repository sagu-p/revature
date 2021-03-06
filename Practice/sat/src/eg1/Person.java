package eg1;

import java.util.Objects;

public class Person {
	
	int id;
	String name;
	
		
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}


	@Override
	public boolean equals(Object obj) {
		
		Person p = (Person) obj;
		
		if(this.getId() == p.id)
			return true;
		else
			return false;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Id:" + this.getId() + "\tName:" + this.getName();
	}
	
	

}
