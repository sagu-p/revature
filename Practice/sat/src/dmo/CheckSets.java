package dmo;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class CheckSets {
	
	public static void main(String[] args) {
		
		System.out.println("HashSet:");
		Set<String> st = new HashSet<>();
		//HashSet (no duplicate, doesn't maintain iseration order, only one null value)
		st.add("Sagar");
		st.add("Patel");
		st.add(null);
		st.add("Rita");
		st.add("Chandrakant");
		st.add("Zeel");
		st.add(null);
		st.add(null);
		st.add(null);
		st.add("Rita");
		st.add("Chandrakant");
		st.add("Zeel");
		
		System.out.println(st);
		
		st.remove("Zeel");
		System.out.println(st);
		
		
		System.out.println("\n\nLinkedHashSet:");
		st = new LinkedHashSet<>();
		//LinkedHashSet (no duplicate, maintain iseration order, only one null value)
		
		st.add("Sagar");
		st.add("Patel");
		st.add(null);
		st.add("Rita");
		st.add("Chandrakant");
		st.add("Zeel");
		st.add(null);
		st.add(null);
		st.add(null);
		st.add("Rita");
		st.add("Chandrakant");
		st.add("Zeel");
		st.add("Bhumi");
		
		
		System.out.println(st);
		st.remove("Bhumi");
		System.out.println(st);
		
		
		System.out.println("\n\nTreeSet:");
		//st = new TreeSet<>(); //for assending ordeer
		st = new TreeSet<>(Collections.reverseOrder()); // for desending order
		//TreeSet (no duplicate, no null value, store in sorted order)
		
		st.add("Sagar");
		st.add("Patel");
		
		st.add("Rita");
		st.add("Chandrakant");
		st.add("Zeel");
		
		st.add("Rita");
		st.add("Chandrakant");
		st.add("Zeel");
		st.add("Bhumi");
		
		
		System.out.println(st);
		
		
		
	}

}
