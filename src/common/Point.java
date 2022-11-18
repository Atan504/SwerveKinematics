package common;

public class Point {
    private double x,y;
    public Point(double x,double y){
        this.x=x;
        this.y=y;
    }
    public Point(){};
    public Point(Point a){
        this.x=a.getX();
        this.y=a.getY();
    };

    public static Point add(Point a, Point b){
        return new Point(a.getX()+b.getX(),a.getY()+b.getY());
    }
    public double getX() {return x;}
    public void setX(double x) {this.x = x;}
    public double getY() {return y;}
    public void setY(double y) {this.y = y;}


    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
