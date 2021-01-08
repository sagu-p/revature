package demo;

public class MyThread extends Thread {
	
	@Override
	public void run() {
		//System.out.println(Thread.currentThread());
		
		
		
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
