package application;

public class Quadratic extends Function implements Calculations{
	
	protected double a;
	protected double b;
	protected double c;
	protected double x1;

	public Quadratic(double a, double b, double c, double x1) {
		super(); //domain for now
		this.a = a;
		this.b = b;
		this.c = c;
		this.x1 = x1;	
		super.setName("Quadratic"); 
	}
	
	@Override
	public String toString() {
		String stra = new String(String.valueOf(a));
		if(a == 1.0){
			stra = "";
		}else if (a == -1.0){
			stra = "-";
		}
		String strb = new String(String.valueOf(b));
		if(b == 1.0){
			strb = "+";
		}else if (b == -1.0){
			strb = "-";
		}else if (b > 0) {
			strb = "+ " + strb;
		}
		String strc = new String(String.valueOf(c));
		if (c == 0 && a == 0 && b == 0) {
			strc = "0";
		}else if(c == 0) {
			strc = "";
		}else if(c > 0.0){
			strc = "+ " + strc;
		}
		String strx1 = new String(String.valueOf(-x1));
		if(x1 == 0.0){
			strx1 = "";
		}else if (x1 < 0.0){
			strx1 = " + " + strx1;
		}else{
			strx1 = " " + strx1;
		}
		

		if (a == 0 && b ==0){
			return "f(x) = " + strc;
		}else if(a == 0){
			return "f(x) = " + strb + " * (x" + strx1 + ") " + strc;
		}else if(b == 0) {
			return "f(x) = " + stra + " * (x" + strx1 + ")^2 " + strc;
		}else {
			return "f(x) = " + stra + " * (x" + strx1 + ")^2 "+ strb + " * (x" + strx1 + ") " + strc;
		}
	}

	@Override
	public double val(double x) {
		double y = a * (x - x1) * (x - x1) + b * (x - x1) + c;
		// TODO Auto-generated method stub
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
