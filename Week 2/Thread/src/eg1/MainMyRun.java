package eg1;

public class MainMyRun {

	public static void main(String[] args) {
		
		MyRunnable r1 = new MyRunnable();
		Thread t1 = new Thread(r1, "Runnable-1");
		
		MyRunnable r2 = new MyRunnable();
		Thread t2 = new Thread(r2, "Runnable-2");
		
		MyRunnable r3 = new MyRunnable();
		Thread t3 = new Thread(r3, "Runnable-3");
		
		t1.start();
		t2.start();
		t3.start();
		
		
		for(int i=1;i<5;i++)
		{
			try {
			Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Thread: "+ Thread.currentThread().getName()+"\ti = "+i);
		}
	}
}
