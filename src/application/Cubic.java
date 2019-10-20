package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Cubic extends Function implements Calculations, Drawable{

    protected double a;
	protected double b;
	protected double c;
	protected double d;
	protected double x1;

	public Cubic( double a, double b, double c, double d, double x1){
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.x1 = x1;	
		super.setName("Cubic"); 
	}

	@Override
	public String toString() { //toString method which covers all possible cases, produces neat format
		String stra = new String(String.valueOf(a));
		if(a == 1.0){
			stra = "";
		}else if (a == -1.0){
			stra = "-";
		}
		String strb = new String(String.valueOf(b));
		if(b == 1.0 && a != 0){
			strb = "+ ";
		}else if (b == -1.0 && a != 0){
			strb = "-";
		}else if (b >= 0 && a != 0){
			strb = "+ " + strb;
		}
		String strc = new String(String.valueOf(c));
		if(c == 1.0 && b != 0 && a != 0){
			strc = "+ ";
		}else if (c == -1.0 && b != 0 && a != 0){
			strc = "-";
		}else if (c >= 0 && (b != 0 || a != 0)){
			strc = "+ "+ strc;
		}
		String strd = new String(String.valueOf(d));
		if (d == 0 && c == 0 && b == 0 && a == 0) {
			strd = "0";
		}else if(d == 0) {
			strd = "";
		}else if(d > 0.0){
			strd = "+ " + strd;
		}
		String strx1 = new String(String.valueOf(-x1));
		if(x1 == 0.0){
			strx1 = "";
		}else if (x1 < 0.0){
			strx1 = " + " + strx1;
		}else{
			strx1 = " " + strx1;
		}

		if (a == 0 && b ==0 && c == 0){		
			return "f(x) = " + strd;
		}else if(a == 0 && b == 0){
			return "f(x) = " + strc + "(x" + strx1 + ") " + strd;
		}else if(b == 0 && c == 0) {
			return "f(x) = " + stra + "(x" + strx1 + ")³ " + strd;
		}else if(a == 0 && c == 0) {
			return "f(x) = " + strb + "(x" + strx1 + ")² " + strd;
		}else if(a == 0){
			return "f(x) = " + strb + "(x" + strx1 + ")² "+ strc + "(x" + strx1 + ") " + strd;
		}else if(b == 0){
			return "f(x) = " + stra + "(x" + strx1 + ")³ "+ strc + "(x" + strx1 + ") " + strd;
		}else if(c == 0){
			return "f(x) = " + stra + "(x" + strx1 + ")³ "+ strb + "(x" + strx1 + ")² " + strd;
		}else {
			return "f(x) = " + stra + "(x" + strx1 + ")³ "+ strb + "(x" + strx1 + ")² " + strc + "(x" + strx1 + ") " + strd;
		}
		
	}
        @Override
        public double val(double x) {
				double y =  a * Math.pow((x - x1), 3) + b * Math.pow((x - x1), 2) + c * (x - x1) + d;
				return y;
        }

        @Override
        public boolean undefined(double x) {
            if(x < super.getStartDomain() || x > super.getEndDomain()){
            	return true;
			}else{
            	return false;
			}
	}

        @Override
        public double getArea(double x_start, double x_end) {
            double delta = 0.001;
            double i = x_start, area = 0;
            while (i <= x_end) {
                area += val(i) * delta;
                i += delta;
            }
            return area;
        }

        @Override
        public double getSlope(double x) {
			double deltaX = 0.001;
			return (val(x + deltaX) - val(x - deltaX)) / deltaX / 2.0;
	}

	@Override
	public void draw(Canvas canvas) {

		GraphicsContext gc = canvas.getGraphicsContext2D();

		double width = canvas.getWidth();
        double height = canvas.getHeight();

		double deltaX = 0.01; //delta by which x-val is incremented to draw relation on sreen
		double highest = val(super.getStartDomain());
		double lowest = val(super.getEndDomain());

		for(double x = super.getStartDomain(); x <= super.getEndDomain(); x+= deltaX){ //calculate range by finding max and min
			if(val(x) > highest) highest = val(x);
			if(val(x) < lowest) lowest = val(x);
		}

		highest = Math.round((highest) * 100.0) / 100.0; //round to speed up calculations (to 3 decimal places)
		lowest = Math.round((lowest) * 100.0) / 100.0;

		double startDomain = super.getStartDomain(), endDomain = super.getEndDomain();

		double Xscale = width / (endDomain - startDomain); //scale factor (for function to fit on-screen)
		double Yscale = height / (highest - lowest); //These are calculated as a ration of screen:domain or screen:range

		double shiftX = (endDomain + startDomain) / 2; //Calculate how much function has to be shifted to be centered on screen
		double shiftY = (highest + lowest) / 2;

		double i = super.getStartDomain()*Xscale;

		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		gc.strokeLine(0, height/2 +shiftY*Yscale, width, height/2 +shiftY*Yscale); //draw axis, shifted
        //in correct relation to function (i.e. if function is y=x+5, axis is shifted 5 right)
		gc.strokeLine(width/2-shiftX*Xscale, 0, width/2-shiftX*Xscale, height);

        gc.setStroke(super.getColour());
        gc.setLineWidth(1.5);

        while (i <= super.getEndDomain()) {
            double prevX = i;
            i = (Math.round((i + deltaX) * 100.0) / 100.0);
            double startX = Xscale * (prevX- shiftX) + width/2;
            double startY = (-val(prevX)+shiftY) * Yscale + height/2;
            double endX = Xscale * (i - shiftX) + width/2;
            double endY = (-val(i)+shiftY) * Yscale + height/2;
            gc.strokeLine(startX, startY, endX, endY); //draw a tangent approximating the function to be draw between
            //two very close points
        }
    }
}
