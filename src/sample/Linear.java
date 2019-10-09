package sample;

public class Linear extends Function implements Calculations{
	
	protected double m;
	protected double b;
	protected double x1;

	public Linear(double m, double b, double x1) {
		super(300, 300); //domain for now
		this.m = m;
		this.b = b;
		this.x1 = x1;

		String strb = new String(String.valueOf(b));
		if(b == 0.0){
			strb = "";
		}else if(b > 0.0){
			strb = "+ " + strb;
		}
		String strm = new String(String.valueOf(m));
		if(m == 1.0){
			strm = "";
		}else if (m == -1.0){
			strm = "-";
		}
		String strx1 = new String(String.valueOf(x1));
		if(x1 == 0.0){
			strx1 = "";
		}else if (x1 > 0.0){
			strx1 = " + " + strx1;
		}else{
			strx1 = " " + strx1;
		}

		if (m == 0){
			super.name = "f(x) = " + strb;
		}else{
			super.name = "f(x) = " + strm + "(x" + strx1 + ") " + strb;
		}


		// TODO Auto-generated constructor stub
	}

	@Override
	public float val(float x) {
		float y = (float)(m*x + b);
		return y;
	}

	@Override
	public boolean undefined() { //check is m is undefined, works
		float pIn = Float.POSITIVE_INFINITY;
		float nIn = Float.NEGATIVE_INFINITY;
		if(this.m == pIn || this.m == nIn
	|| this.b == pIn || this.b == nIn
	|| this.x1 == pIn || this.x1 == nIn) {			
			return true;
		}else {
			return false;
		}
	}

	@Override
	public float getArea(float x_start, float x_end) {
		float deltaX = (float) 0.01;
		float area = (float) 0;
		for(float i = x_start; i <= x_end; i+= deltaX) {
			area += val(i) * deltaX;
		}
		return area;
	}

	@Override
	public float getSlope(float x) {
		// TODO Auto-generated method stub
		return 0;
	}

}
