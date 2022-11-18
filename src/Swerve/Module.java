package Swerve;

import common.Point;
import common.Vector;


public class Module {
    private Vector transform;
    private double angVel;
    private Point pos;
    private Vector fvec;
    private SK.MLoc Mloc;
    public Module(double x, double y, SK.MLoc mloc){
        this.transform = new Vector();
        this.angVel=0;
        this.pos= new Point(x,y);
        this.fvec = new Vector();
        this.Mloc=mloc;
        Mloc.setModule(this);
    }

    public void Update(){
        Vector ang = new Vector(this.Mloc.getValue(),angVel/800.0,pos);
        //transform.setPos(getPos());
        fvec= Vector.add(ang,transform);
    }



    public Vector getTransform() {return transform;}
    public void setTransform(Vector transform) {this.transform = transform;}
    public double getAngVel() {return angVel;}
    public void setAngVel(double angVel) {this.angVel = angVel;}
    public Point getPos() {return pos;}
    public void setPos(Point pos) {this.pos = pos;}
    public Vector getFvec() {return fvec;}
}
