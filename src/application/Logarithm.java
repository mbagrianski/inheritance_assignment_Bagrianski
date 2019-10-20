package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Logarithm extends Function implements Calculations, Drawable{
	
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
        double y =  a * Math.log(x - x1) + b;
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
        double delta = 0.01;
        double i = x_start, area = 0;
        while (i <= x_end) {
            area += val(i) * delta;
            i += delta;
        }
        return area;
    }

    @Override
    public double getSlope(double x) {
        double deltaX = 0.01;
        return (val(x + deltaX) - val(x - deltaX)) / deltaX / 2.0;
    }

    @Override
    public void draw(Canvas canvas) {

        GraphicsContext gc = canvas.getGraphicsContext2D();

        double width = canvas.getWidth();
        double height = canvas.getHeight();

        double deltaX = 0.01;
        double start = super.getStartDomain();
        while(val(start) != val(start)){
            start += deltaX;
        }
        double highest = val(start);
        double lowest = val(super.getEndDomain());

        for(double x = super.getStartDomain(); x <= super.getEndDomain(); x+= deltaX){ //calculate range
            if(val(x) == val(x) && x != x1) {
                if (val(x) > highest) highest = val(x);
                if (val(x) < lowest) lowest = val(x);
                //System.out.println(Double.isNaN(val(x)));
                System.out.println(highest + " " + lowest);
            }
        }

        highest = Math.round((highest) * 100.0) / 100.0;
        lowest = Math.round((lowest) * 100.0) / 100.0;

        double startDomain = super.getStartDomain(), endDomain = super.getEndDomain();

        double Xscale = width / (endDomain - startDomain);
        double Yscale = height / (highest - lowest);

        double adjustX = (endDomain + startDomain) / 2;
        double adjustY = (highest + lowest) / 2;

        double i = super.getStartDomain()*Xscale;

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeLine(0, height/2 +adjustY*Yscale, width, height/2 +adjustY*Yscale);
        gc.strokeLine(width/2-adjustX*Xscale, 0, width/2-adjustX*Xscale, height);

        gc.setStroke(super.getColour());
        gc.setLineWidth(1.5);

        while (i <= super.getEndDomain()) {
            double prevX = i;
            i = (Math.round((i + deltaX) * 100.0) / 100.0);
            if(i != x1) {
                double startX = Xscale * (prevX - adjustX) + width / 2;
                double startY = (-val(prevX) + adjustY) * Yscale + height / 2;
                double endX = Xscale * (i - adjustX) + width / 2;
                double endY = (-val(i) + adjustY) * Yscale + height / 2;
                gc.strokeLine(startX, startY, endX, endY);
            }
        }
    }
}
