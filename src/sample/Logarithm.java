package sample;

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
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public double getArea(double x_start, double x_end) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public double getSlope(double x) {
		// TODO Auto-generated method stub
		return 0;
	}

}