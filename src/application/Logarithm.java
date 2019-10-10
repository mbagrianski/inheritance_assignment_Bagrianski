package application;

public class Logarithm extends Function implements Calculations{
	
	protected double a;
	protected double b;
	protected double x1;


	public Logarithm(double a, double b, double x1) {
		super(0, 0); //domain for now
		this.a = a;
		this.b = b;
		this.x1 = x1;
		super.setName("Logarithm"); 

		// TODO Auto-generated constructor stub
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
		if (a == 0) {
			strb = "0";
		}else if(b == 0) {
			strb = "";
		}else if(b > 0.0){
			strb = "+ " + strb;
		}
		String strx1 = new String(String.valueOf(-x1));
		if(x1 == 0.0){
			strx1 = "";
		}else if (x1 < 0.0){
			strx1 = " + " + strx1;
		}else{
			strx1 = " " + strx1;
		}

		if (a == 0){		
			return "f(x) = " + strb;
		}else{
			return "f(x) = " + stra + " * log(x" + strx1 + ") " + strb;
		}
	}

	@Override
	public double val(double x) {
		double y = a * Math.log(x - x1) + b;
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
