package sample;

public class Linear extends Function implements Calculations{

    protected double m;
    protected double b;
    protected double x1;

    public Linear(double m, double b, double x1) {
        super(); //domain for now
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
        String strx1 = new String(String.valueOf(-x1));
        if(x1 == 0.0){
            strx1 = "";
        }else if (x1 < 0.0){
            strx1 = " + " + strx1;
        }else{
            strx1 = " " + strx1;
        }

        if (m == 0){
            super.name = "f(x) = " + strb;
        }else{
            super.name = "f(x) = " + strm + "(x" + strx1 + ") " + strb;
        }
    }

    @Override
    public double val(double x) {
        double y = (double)(m*(x-x1)+ b);
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
        double deltaX = (double) 0.01;
        double area = (double) 0;
        for(double i = x_start; i <= x_end; i+= deltaX) {
            area += val(i) * deltaX;
        }
        return area;
    }

    @Override
    public double getSlope(double x) {
        return (double) m;
    }

}