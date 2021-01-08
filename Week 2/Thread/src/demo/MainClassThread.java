package demo;

public class MainClassThread {

	public static void main(String[] args) {
		
		System.out.println(Thread.currentThread());
		Thread t = Thread.currentThread();
		t.setName("My Main");
		t.setPriority(6);
		System.out.println(Thread.currentThread());
		
		MyThread m1 =new MyThread();
		m1.setName("My Thread 1");
		MyThread m2 =new MyThread();
		m1.setName("My Thread 2");
		MyThread m3 =new MyThread();
		m1.setName("My Thread 3");
		MyThread m4 =new MyThread();
		m1.setName("My Thread 4");
		MyThread m5 =new MyThread();
		m1.setName("My Thread 5");
		
		m1.setDaemon(true);
		m2.setDaemon(true);
		m3.setDaemon(true);
		m4.setDaemon(true);
		m5.setDaemon(true);
		
		m1.start();
		m2.start();
		m3.start();
		m4.start();
		m5.start();
		
		
		//join() will help to complete help to wait till thread has done it's execution
		try {
			m1.join();
			m2.join();
			m3.join();
			m4.join();
			m5.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*for(int i=1;i<5;i++)
		{
			System.out.println("Thread: "+ Thread.currentThread().getName()+"\ti = "+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		System.out.println("Endes Here");

	}

}
