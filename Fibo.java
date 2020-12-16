package FiboSeries;

public class Fibo {
	
	public static void main(String[] args) {
		
		int x=0, y=1, sum=0;
		int n = 10;
		
		System.out.println("Fibonacci Series is as follow:");
		System.out.print(x + "\s" + y);
		for(int i=1; i<n;i++)
		{
			sum = x+y;
			x=y;
			y=sum;
			System.out.print("\s"+ sum);
		}
		
	}

}
