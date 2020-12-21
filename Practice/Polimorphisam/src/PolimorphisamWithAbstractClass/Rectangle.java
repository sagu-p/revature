package PolimorphisamWithAbstractClass;

public class Rectangle extends Shape {

	@Override
	void area(float a, float b) {
		float ar = a * b;
		System.out.println("Area of Rectangle: "+ ar);
	}

}
