package sync;

public class Page implements Runnable {

	private String p1;
	private String p2;
	
	public Page(){
		
	}
	
	public Page(String p1, String p2) {
			super();
		this.p1 = p1;
		this.p2 = p2;
		Thread t = new Thread(this);
		t.start();
	}

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	@Override
	public void run() {
		
		Printer.print(this);
		
	}
	
	
	
	
}
