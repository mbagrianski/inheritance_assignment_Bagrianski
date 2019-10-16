package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

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
    	
        double i = super.getStartDomain();
        
        double deltaX = 0.1;
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1);
        gc.setStroke(super.getColour());

        while (i <= super.getEndDomain()) {
            double prevX = i;
            i = Math.round((i + deltaX) * 10.0) / 10.0;
            if (undefined(i)) continue;
            double startX = prevX + width / 2.0;
            double startY = -val(prevX) + height / 2.0;
            double endX = i + width / 2.0;
            double endY = -val(i) + height / 2.0;
            gc.strokeLine(startX, startY, endX, endY);
        }
    }
}
