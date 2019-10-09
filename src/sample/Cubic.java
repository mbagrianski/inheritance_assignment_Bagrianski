package sample;

public class Cubic extends Function implements Calculations{

	protected double a;
	protected double b;
	protected double c;
	protected double d;
	protected double x1;

	public Cubic( double a, double b, double c, double d, double x1){
		super(0, 0); //domain for now
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.x1 = x1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public double val(double x) {
		double y = a*(x - x1)*(x - x1)*(x - x1) + b*(x - x1)*(x - x1)
				+c*(x - x1) + d;
		return y;
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
