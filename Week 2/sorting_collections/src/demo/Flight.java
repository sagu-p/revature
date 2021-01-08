package demo;

public class Flight implements Comparable<Flight> {
	
	private int id;
	private String name;
	private float cost;
	private float rate;
	private boolean avalable;

	public Flight() {
	}

	public Flight(int id, String name, float cost, float rate, boolean avalable) {
		super();
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.rate = rate;
		this.avalable = avalable;
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

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public boolean isAvalable() {
		return avalable;
	}

	public void setAvalable(boolean avalable) {
		this.avalable = avalable;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", name=" + name + ", cost=" + cost + ", rate=" + rate + ", avalable=" + avalable
				+ "]";
	}

	@Override
	public int compareTo(Flight o) {
		Integer id1 = this.id;
		Integer id2 = o.id;
		return id1.compareTo(id2);
	}
	
	
	

}
