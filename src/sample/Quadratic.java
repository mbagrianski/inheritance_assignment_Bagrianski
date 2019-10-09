package sample;

public class Quadratic extends Function implements Calculations{
	
	protected double a;
	protected double b;
	protected double c;
	protected double x1;

	public Quadratic(double a, double b, double c, double x1) {
		super(0, 0); //domain for now
		this.a = a;
		this.b = b;
		this.c = c;
		this.x1 = x1;

		if (x1 < 0){

		}

		// TODO Auto-generated constructor stub
	}

	@Override
	public float val(float x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean undefined() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getArea(float x_start, float x_end) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getSlope(float x) {
		// TODO Auto-generated method stub
		return 0;
	}

}
