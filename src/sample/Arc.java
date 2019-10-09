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

		String strr = new String(String.valueOf(r));
		if(r == 0.0){
			strr = "";
		}else {
			strr = "("+strr+")^2 - ";
		}

		String strXcenter = new String(String.valueOf(-xcenter));
		if(xcenter == 0.0){
			strXcenter = "";
		}else if (xcenter < 0.0){
			strXcenter = " + " + strXcenter;
		}else{
			strXcenter = " " + strXcenter;
		}
		String strYcenter = new String(String.valueOf(ycenter));
		if(ycenter == 0.0){
			strYcenter = "";
		}else if (ycenter > 0.0){
			strYcenter = " + " + strYcenter;
		}else{
			strYcenter = " " + strYcenter;
		}

		super.name = "f(x) = sqrt[ "+ strr + "(x" + strXcenter
				+ ")^2 ]" + strYcenter;
	}

	@Override
	public double val(double x) {
		double y = Math.sqrt(r * r - (x - xcenter)*(x - xcenter)) + ycenter;
		return y;
	}

	@Override
	public boolean undefined(double x) {
		if(super.x1 <= x && x <= super.x2) {
			return true;
		}else {
			return false;
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