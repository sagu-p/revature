package PolimorphisamWithAbstractClass;

public class ChechPoly {
	
	public static void main(String[] args) {
		
		Shape s;
		
		//Triangle area
		s = new Triangle();
		s.area(3,3);
		
		// Rect angle area
		s = new Rectangle();
		s.area(5,6);
		
		// circle area
		s = new Circle();
		s.area(2, 3.14f);
		
		
	}

}
