package producer_comsumer_problem;

public class Data {

	private int n;
	boolean flag = false;

	public synchronized void getN() {
		while(!flag) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		flag = false;
		System.out.println("Get: " + this.n );
		notifyAll();
	}

	public synchronized void setN(int n) {
		while(flag) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.n = n;
		System.out.println("Put: " + n );
		flag = true;
		notifyAll();
	}
}
