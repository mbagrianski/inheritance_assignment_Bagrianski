package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Logarithm extends Function implements Calculations, Drawable{
	
	protected double a;
	protected double b;
	protected double x;


	public Logarithm(double a, double b, double x) {
		super(0, 0); //domain for now
		this.a = a;
		this.b = b;
		this.x = x;
		super.setName("Logarithm"); 

		// TODO Auto-generated constructor stub
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
        double i = super.x1;
        double deltaX = 0.1;
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1);
        gc.setStroke(super.getColour());

        while (i <= super.x2) {
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
