package application;

public class Logarithm extends Function implements Calculations{
	
	protected double a;
	protected double b;
	protected double x;


	public Logarithm(double a, double b, double x) {
		super(0, 0); //domain for now
		this.a = a;
		this.b = b;
		this.x = x;
		// TODO Auto-generated constructor stub
	}


	@Override
	public double val(double x) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean undefined(double x) {
		if(super.x1 <= x && x <= super.x2) {			
			return false;
		}else {
			return true;
		}		
	}


	@Override
	public double getArea(double x_start, double x_end) {
		double deltaX = 0.01;
		double area = 0;
		for(double i = x_start; i <= x_end; i+= deltaX) {
			area += val(i) * deltaX;
		}
		return area;		
	}


	@Override
	public double getSlope(double x) {
		double deltaX = 0.01;
		double slope = (val(x + deltaX) - val(x - deltaX)) / (2 * deltaX);	
		return slope;		
	}

}
