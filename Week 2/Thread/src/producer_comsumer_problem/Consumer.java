package producer_comsumer_problem;

public class Consumer implements Runnable {

	Data data;
	
	public Consumer(Data data) {
		
		this.data = data;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		
		while(true) {
			data.getN();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
