package demo;

import java.util.Comparator;

public class FlightCostComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight o1, Flight o2) {

		Float c1 = o1.getCost();
		Float c2 = o2.getCost();
		return c1.compareTo(c2);
	}

}
