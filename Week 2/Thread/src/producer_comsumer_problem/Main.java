package producer_comsumer_problem;

public class Main {

	public static void main(String[] args) {
		
		Data data = new Data();
		
		new Producer(data);
		new Consumer(data);
		
	}
	
}
