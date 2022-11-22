package subsystems.swerve.SwervweKin.Swerve;

import SwervweKin.common.Point;
import SwervweKin.common.Vector;
import edu.greenblitz.robot.RobotMap;
import subsystems.swerve.Module;
import edu.wpi.first.math.geometry.Translation2d;

public class SwerveKinematics {
    private Point pos;
    private Vector transform;
    private double angleVelocity;

    public void setTransform(Vector transform) {this.transform = transform;}
    public void setAngleVelocity(double angleVelocity) {this.angleVelocity = angleVelocity;}
    public double getAngleVelocity(){return this.angleVelocity;}
    public Vector getTransform(){return this.transform;}
    public Module[] modules;





    public Point getPos (){
        return this.pos;
    }

//    public enum MLoc{
//
//        Front_Right(-45,null),
//        Front_Left(45,null),
//        Back_Right (-135,null),
//        Back_Left(-225,null),
//        ;
//        private double value;
//        private Module module;
//        MLoc(double value,Module module) {
//            this.value=value;
//        }
//        public double getValue() {return value;}
//        public Module getModule() {return module;}
//        public void setModule(Module module) {this.module = module;}
//        public void setValue(double value) {this.value = value;}
//    }
    private static SwerveKinematics instance;
    public static SwerveKinematics getInstance(){

        if(instance == null){
            instance = new SwerveKinematics(RobotMap.Robot.swerve.modulesLocations, RobotMap.Robot.swerve.modules);
        }
        return instance;
    }

    private SwerveKinematics(Translation2d[] locations, Module[] modules){
        this.modules = modules;
        pos=new Point(0,0);
    }
    public void Update(){
        for (Module m : modules){
            m.setTransform(transform);
            m.setAngularVelocity(angleVelocity);
            m.update();

        }
    }




}
