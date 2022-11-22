package edu.greenblitz.robot.subsystems.swerve;

import SwervweKin.common.Point;
import SwervweKin.common.Vector;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;
import utils.PIDObject;

public class Module{

    //calcs
    private Vector transform;
    private double angularVelocity;
    private Translation2d position;
    private Vector finalVector;


    private TalonFX angMotor;
    private TalonFX linMotor;
    private AnalogInput magEncoder;

    public Module(int linID, int angularID, int magEncoderID, PIDObject linearPID, PIDObject angularPID , Translation2d location){
        this.angMotor = new TalonFX(angularID);

        //angle motor pidconf
        this.angMotor.config_kP(0, angularPID.getKp());
        this.angMotor.config_kI(0, angularPID.getKi());
        this.angMotor.config_kD(0, angularPID.getKd());
        this.angMotor.config_kF(0, angularPID.getKf());
        this.angMotor.config_IntegralZone(0, angularPID.getIZone());

        this.linMotor = new TalonFX(linID);
        //lin motor set pid

        this.linMotor.config_kP(0, linearPID.getKp());
        this.linMotor.config_kI(0, linearPID.getKi());
        this.linMotor.config_kD(0, linearPID.getKd());
        this.linMotor.config_kF(0, linearPID.getKf());
        this.linMotor.config_IntegralZone(0, linearPID.getIZone());

        this.magEncoder = new AnalogInput(magEncoderID);


        //calculations
        this.transform = new Vector();

        this.finalVector = new Vector();
        this.position = location;

    }

    public void setRotPower (double power){
        angMotor.set(ControlMode.PercentOutput, power);
    }
    public void setLinMotor (double power){
        linMotor.set(ControlMode.PercentOutput, power);
    }

    public void setAngle (double angle){//by degrees
        angMotor.set(TalonFXControlMode.Position, angle);
    }
    public void setLinSpeedByPID (double speed){
        linMotor.set(ControlMode.Velocity, speed);
    }


    public void setDesiredState(SwerveModuleState state){
        setAngle(state.angle.getDegrees());
        setLinSpeedByPID(state.speedMetersPerSecond);
    }


    public double getMotorAngle(AnalogInput encoder) {//degrees!!!
        double angle = (1.0 - encoder.getVoltage() / RobotController.getVoltage5V()) * 2.0 * Math.PI;
        angle %= 2.0 * Math.PI;
        if (angle < 0.0) {
            angle += 2.0 * Math.PI;
        }
        return angle;
    }











    public Vector getTransform() {return transform;}
    public void setTransform(Vector transform) {this.transform = transform;}
    public double getAngularVelocity() {return angularVelocity;}
    public void setAngularVelocity(double angularVelocity) {this.angularVelocity = angularVelocity;}
    public Translation2d getPos() {return position;}

    public Vector getFinalVector() {return finalVector;}
    public double getBaseRotationAngle(){
        return new Vector(new Point(0,0),new Point(position)).getDirection() - 90;
    }
    public void update(){
        Vector ang = new Vector(getBaseRotationAngle(), angularVelocity,position);
        //transform.setPos(getPos());
        finalVector = Vector.add(ang,transform);
    }
}
