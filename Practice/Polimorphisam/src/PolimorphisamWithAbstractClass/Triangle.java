package PolimorphisamWithAbstractClass;

public class Triangle extends Shape {

	@Override
	void area( float a, float b) {
		
	    float ar = (a * b) / 2;
		
		System.out.println("Area of Triangle: "  + ar);
		
	}

}
