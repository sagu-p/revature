package eg1;

public class MyRunnable implements Runnable {

	@Override
	public void run() {
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
