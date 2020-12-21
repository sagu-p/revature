package PolimorphisamWithAbstractClass;

public class Circle extends Shape {

	@Override
	void area(float a, float b) {
		float ar = b * a * a;
		System.out.println("Area of Circle: "+ ar);		
	}

}
