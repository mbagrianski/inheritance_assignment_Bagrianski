package sample;

public class Arc extends Function implements Calculations{
	
	protected double r;
	protected double xcenter;
	protected double ycenter;

	public Arc(double r, double xcenter, double ycenter) {
		super(0, 0); //domain for now
		this.r = r;
		this.xcenter = xcenter;
		this.ycenter = ycenter;
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
