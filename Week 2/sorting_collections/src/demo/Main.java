package demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		List<Flight> flightList = new ArrayList<>();
		
		flightList.add(new Flight(100, "AI", 800, 3.5f, true));
		flightList.add(new Flight(900, "BA", 500, 4.7f, false));
		flightList.add(new Flight(400, "BA", 300, 3.5f, true));
		flightList.add(new Flight(200, "AI", 900, 4.5f, true));
		flightList.add(new Flight(500, "BA", 700, 4.5f, true));
		flightList.add(new Flight(600, "AI", 600, 3.7f, false));
		
		System.out.println("List of Flights:");
		printList(flightList);
		
		Collections.sort(flightList);
		System.out.println("\n\nList of Flights Sort by Id:");
		printList(flightList);
		
		Collections.sort(flightList, new FlightCostComparator());
		System.out.println("\n\nList of Flights Sort by Flight Cost (low to high):");
		printList(flightList);
		
		Collections.sort(flightList, (Flight f1, Flight f2)-> {
			Boolean b1 = f1.isAvalable();
			Boolean b2 = f2.isAvalable();
			return b2.compareTo(b1);
		});
		System.out.println("\n\nList of Flights Sort by Avability:");
		printList(flightList);
		
		Collections.sort(flightList, (Flight f1, Flight f2)-> {
			int flag = 0;
			
			Float r1 = f1.getRate();
			Float r2 = f2.getRate();
			flag = r2.compareTo(r1);			
			
			if(flag == 0) {
				Float c1 = f1.getCost();
				Float c2 = f2.getCost();
				flag = c1.compareTo(c2);
			}
			return flag;
		});
		System.out.println("\n\nList of Flights Sort by Rating (high to low) and if Rating is same the sort by Cost (low to high) :");
		printList(flightList);
		
		Collections.sort(flightList, (Flight f1, Flight f2)-> {
			int flag = 0;
			
			Boolean b1 = f1.isAvalable();
			Boolean b2 = f2.isAvalable();
			flag = b2.compareTo(b1);
			
			if(flag == 0) {
				Float r1 = f1.getRate();
				Float r2 = f2.getRate();
				flag = r2.compareTo(r1);
			}
			if(flag == 0) {
				Float c1 = f1.getCost();
				Float c2 = f2.getCost();
				flag = c1.compareTo(c2);
			}
			return flag;
		});
		System.out.println("\n\nList of Flights Sort Avabilty & by Rating (high to low) and if Rating is same the sort by Cost (low to high) :");
		printList(flightList);
		
	}
	
	public static void printList(List<Flight> flightList) {
		for(Flight f : flightList)
			System.out.println(f);
	}
	
}
