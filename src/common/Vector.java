package common;

import edu.wpi.first.math.geometry.Translation2d;

import java.awt.*;
import java.awt.Point;

public class Vector {//all the code is self-explanatory
    private double direction,magnitude;
    private java.awt.Point pos;
    public Vector(double direction, double magnitude, java.awt.Point a){
        this.direction =direction;
        this.magnitude=magnitude;
        this.pos=a;
    }
    public Vector(double direction, double magnitude, Translation2d a){
        this.direction =direction;
        this.magnitude=magnitude;
        this.pos=new java.awt.Point(a);
    }
    public Vector(double direction,double magnitude){
        this.direction =direction;
        this.magnitude=magnitude;
        this.pos=new java.awt.Point(0,0);
    }
    public Vector(){}
    public Vector(Vector a){
        this.direction=a.getDirection();
        this.magnitude=a.getMagnitude();
        this.pos=a.getPos();
    }
    public Vector(java.awt.Point a){this.pos=a;}
    public Vector(java.awt.Point a, java.awt.Point b){
        this.pos=a;
        headTo(b);
    }

    public void headTo(java.awt.Point b){
        direction=Math.toDegrees(Math.atan2(b.getY()- pos.getY(),b.getX()- pos.getX()));
        magnitude = Math.sqrt(Math.pow(b.getX()- pos.getX(),2)+Math.pow(b.getY()- pos.getY(),2));
    }

    public java.awt.Point calcHeadingPoint(){
        return new java.awt.Point(Math.cos(Math.toRadians(direction))*magnitude+pos.getX(),Math.sin(Math.toRadians(direction))*magnitude+pos.getY());
    }

    public static Vector add(Vector vec1,Vector vec2){
        Vector a = new Vector(vec1);
        Vector b = new Vector(vec2);
        b.setPos(a.calcHeadingPoint());
        java.awt.Point f = b.calcHeadingPoint();
        return new Vector(a.getPos(),f);

//        return new Vector(vec1.getPos(),
//                new Point(vec1.calcHeadingPoint().getX()+vec2.calcHeadingPoint().getX()
//                        ,vec1.calcHeadingPoint().getY()+vec2.calcHeadingPoint().getY()));
    }
    public void paint(Graphics g){
        g.drawLine((int) pos.getX(), (int) pos.getY(), (int) calcHeadingPoint().getX(), (int) calcHeadingPoint().getY());
    }

    public double getDirection() {return direction;}
    public void setDirection(double direction) {this.direction = direction;}
    public double getMagnitude() {return magnitude;}
    public void setMagnitude(double magnitude) {this.magnitude = magnitude;}
    public java.awt.Point getPos(){return pos;}
    public void setPos(Point pos){this.pos=pos;}


    @Override
    public String toString() {
        return "Vector{" +
                "direction=" + direction +
                ", magnitude=" + magnitude +
                ", pos=" + pos.toString() +
                '}';
    }




}
