package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Arc extends Function implements Calculations, Drawable {

    protected double r;
    protected double xcenter;
    protected double ycenter;

    public Arc(double r, double xcenter, double ycenter) {
        super(0, 0); //domain for now
        this.r = r;
        this.xcenter = xcenter;
        this.ycenter = ycenter;
        super.setName("Arc");
    }

    @Override
    public String toString() {
        String strr = new String(String.valueOf(r));
        if (r == 0.0) {
            strr = "";
        } else {
            strr = "(" + strr + ")² - ";
        }

        String strXcenter = new String(String.valueOf(-xcenter));
        if (xcenter == 0.0) {
            strXcenter = "";
        } else if (xcenter < 0.0) {
            strXcenter = " + " + strXcenter;
        } else {
            strXcenter = " " + strXcenter;
        }
        String strYcenter = new String(String.valueOf(ycenter));
        if (ycenter == 0.0) {
            strYcenter = "";
        } else if (ycenter > 0.0) {
            strYcenter = " + " + strYcenter;
        } else {
            strYcenter = " " + strYcenter;
        }

        return "f(x) = sqrt[ " + strr + "(x" + strXcenter
                + ")² ]" + strYcenter;
    }

    @Override
    public double val(double x) {
        double y = Math.sqrt(r * r - (x - xcenter) * (x - xcenter)) + ycenter;
        return y;
    }

    @Override
    public boolean undefined(double x) {
        if (super.x1 <= x && x <= super.x2) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public double getArea(double x_start, double x_end) {
        double deltaX = 0.01;
        double area = 0;
        for (double i = x_start; i <= x_end; i += deltaX) {
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

    @Override
    public void draw(Canvas canvas) {

        GraphicsContext gc = canvas.getGraphicsContext2D();

        double width = canvas.getWidth();
        double height = canvas.getHeight();

        double deltaX = 0.1;
        double highest = val(super.getStartDomain());
        double lowest = val(super.getEndDomain());

        for(double x = super.getStartDomain(); x <= super.getEndDomain(); x+= deltaX){ //calculate range
            if(val(x) > highest) highest = val(x);
            if(val(x) < lowest) lowest = val(x);
        }

        highest = Math.abs(Math.round((highest) * 10.0) / 10.0);
        lowest = Math.round((lowest) * 10.0) / 10.0;

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
            i = (Math.round((i + deltaX) * 10.0) / 10.0);
            double startX = Xscale * (prevX- adjustX) + width/2;
            double startY = (-val(prevX)+adjustY) * Yscale + height/2;
            double endX = Xscale * (i - adjustX) + width/2;
            double endY = (-val(i)+adjustY) * Yscale + height/2;
            gc.strokeLine(startX, startY, endX, endY);
        }
    }
}
