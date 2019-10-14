package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

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
            strr = "(" + strr + ")^2 - ";
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
                + ")^2 ]" + strYcenter;
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