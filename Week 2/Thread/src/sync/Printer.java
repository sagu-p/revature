package sync;

public class Printer {
	
	public synchronized static void print(Page p) {
		System.out.print(p.getP1());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(" "+p.getP2());
		
		
	}

}
