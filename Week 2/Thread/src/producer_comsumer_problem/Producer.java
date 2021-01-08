package producer_comsumer_problem;

public class Producer implements Runnable {
	
	
	Data data;
	
	public Producer(Data data) {
		this.data = data;
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		
		int i=1;
		while(i < 6) {
			data.setN(i++);			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
