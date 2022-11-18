package Swerve;

import common.Point;
import common.Vector;

public class SK {
    private Point pos;
    private Vector transform;
    private double angleVelocity;

    public void setTransform(Vector transform) {this.transform = transform;}
    public void setAngleVelocity(double angleVelocity) {this.angleVelocity = angleVelocity;}
    public double getAngleVelocity(){return this.angleVelocity;}
    public Vector getTransform(){return this.transform;}

    public enum MLoc{
        Front_Right(-45,null),
        Front_Left(45,null),
        Back_Right (-135,null),
        Back_Left(-225,null),
        ;
        private double value;
        private Module module;
        MLoc(double value,Module module) {
            this.value=value;
        }
        public double getValue() {return value;}
        public Module getModule() {return module;}
        public void setModule(Module module) {this.module = module;}
    }
    public SK(){
        pos=new Point(0,0);
    }
    public void Update(){
        Module fr = MLoc.Front_Right.getModule();
        Module fl =MLoc.Front_Left.getModule();
        Module br =MLoc.Back_Right.getModule();
        Module bl =MLoc.Back_Left.getModule();
        fr.setPos(Point.add(fr.getPos(),pos));
        fl.setPos(Point.add(fl.getPos(),pos));
        br.setPos(Point.add(br.getPos(),pos));
        bl.setPos(Point.add(bl.getPos(),pos));

        fr.setTransform(transform);
        fl.setTransform(transform);
        br.setTransform(transform);
        bl.setTransform(transform);

        fr.setAngVel(angleVelocity);
        fl.setAngVel(angleVelocity);
        br.setAngVel(angleVelocity);
        bl.setAngVel(angleVelocity);

        fr.Update();
        fl.Update();
        br.Update();
        bl.Update();
    }

}
