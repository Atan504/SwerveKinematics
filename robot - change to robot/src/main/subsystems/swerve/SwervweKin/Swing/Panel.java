package SwervweKin.Swing;

import SwervweKin.Swerve.SwerveKinematics;
import SwervweKin.common.Point;
import SwervweKin.common.Vector;
import edu.greenblitz.robot.RobotMap;
import edu.greenblitz.robot.subsystems.swerve.Module;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private static final long serialVersionUID = 1;

    SwerveKinematics swerveKinematics;

    public static final double direction = 45;
    public static final double magnitude = 100;
    public static final double angularVelocity = 30;
    public  static int w,h;
    public Panel(int w,int h) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(new Color(0));
        Panel.w =w;
        Panel.h =h;
        this.swerveKinematics = SwerveKinematics.getInstance();
    }



    public void setSize(int w,int h){
        Panel.w =w;
        Panel.h =h;
    }

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }
    public void drawLine(Graphics g, SwervweKin.common.Point a, SwervweKin.common.Point b){
        g.drawLine((int) a.getX()+(w/2), (int) -a.getY()+(h/2), (int) b.getX()+(w/2), (int) -b.getY()+(h/2));

    }
    public void drawDebugLine(Graphics g, Module module, SwerveKinematics chassis){
        g.setColor(new Color(0,0,255));
        Vector tran = new Vector(chassis.getTransform());
        Vector ang = new Vector(module.getBaseRotationAngle(),angularVelocity,module.getPos());
        tran.setPos(ang.calcHeadingPoint());
        drawLine(g,tran.getPos(),tran.calcHeadingPoint());
        g.setColor(new Color(125,0,125));
        drawLine(g,ang.getPos(),ang.calcHeadingPoint());
    }
    public void drawChassis (Graphics g){

        g.setColor(new Color(255,0,0));
        drawLine(g,new Point(RobotMap.Robot.swerve.modulesLocations[0]),new Point(RobotMap.Robot.swerve.modulesLocations[1]));
        drawLine(g,new Point(RobotMap.Robot.swerve.modulesLocations[1]),new Point(RobotMap.Robot.swerve.modulesLocations[2]));
        drawLine(g,new Point(RobotMap.Robot.swerve.modulesLocations[2]),new Point(RobotMap.Robot.swerve.modulesLocations[3]));
        drawLine(g,new Point(RobotMap.Robot.swerve.modulesLocations[3]),new Point(RobotMap.Robot.swerve.modulesLocations[0]));
    }

    public void drawChassis (Graphics g, SwervweKin.common.Point center){

        double x= center.getX();
        double y = center.getY();

        g.setColor(new Color(255,0,0));
        drawLine(g,new Point(RobotMap.Robot.swerve.modulesLocations[0].getX() + x, RobotMap.Robot.swerve.modulesLocations[0].getY() + y)
                ,new Point(RobotMap.Robot.swerve.modulesLocations[1].getX() + x, RobotMap.Robot.swerve.modulesLocations[1].getY() + y));
        drawLine(g,new Point(RobotMap.Robot.swerve.modulesLocations[1].getX() + x, RobotMap.Robot.swerve.modulesLocations[1].getY() + y),
                new Point(RobotMap.Robot.swerve.modulesLocations[2].getX() + x, RobotMap.Robot.swerve.modulesLocations[2].getY() + y));
        drawLine(g,new Point(RobotMap.Robot.swerve.modulesLocations[2].getX() + x, RobotMap.Robot.swerve.modulesLocations[2].getY() + y),
                new Point(RobotMap.Robot.swerve.modulesLocations[3].getX() + x, RobotMap.Robot.swerve.modulesLocations[3].getY() + y));
        drawLine(g,new Point(RobotMap.Robot.swerve.modulesLocations[3].getX() + x, RobotMap.Robot.swerve.modulesLocations[3].getY() + y),
                new Point(RobotMap.Robot.swerve.modulesLocations[0].getX() + x, RobotMap.Robot.swerve.modulesLocations[0].getY() + y));

    }

    public void drawChassis (Graphics g, SwervweKin.common.Point center, double angle){

        double x= center.getX();
        double y = center.getY();

        Vector trajectory = new Vector(direction, magnitude * 3, swerveKinematics.getPos());

        Vector rotationTrajectoryFR;
        Vector rotationTrajectoryFL;
        Vector rotationTrajectoryBR;
        Vector rotationTrajectoryBL;

        //fr
        rotationTrajectoryFR = new Vector(
                trajectory.calcHeadingPoint()
                , new SwervweKin.common.Point(RobotMap.Robot.swerve.modulesLocations[0].getX() + trajectory.calcHeadingPoint().getX()
                ,RobotMap.Robot.swerve.modulesLocations[0].getY() + trajectory.calcHeadingPoint().getY()));
        rotationTrajectoryFR.setDirection(rotationTrajectoryFR.getDirection() - angle);
        //fl
        rotationTrajectoryFL = new Vector(
                trajectory.calcHeadingPoint()
                , new SwervweKin.common.Point(RobotMap.Robot.swerve.modulesLocations[1].getX() + trajectory.calcHeadingPoint().getX()
                ,RobotMap.Robot.swerve.modulesLocations[1].getY() + trajectory.calcHeadingPoint().getY()));
        rotationTrajectoryFL.setDirection(rotationTrajectoryFL.getDirection() - angle);
        //br rotation trajectory
        rotationTrajectoryBR = new Vector(
                trajectory.calcHeadingPoint()
                , new SwervweKin.common.Point(RobotMap.Robot.swerve.modulesLocations[2].getX() + trajectory.calcHeadingPoint().getX()
                ,RobotMap.Robot.swerve.modulesLocations[2].getY() + trajectory.calcHeadingPoint().getY()));
        rotationTrajectoryBR.setDirection(rotationTrajectoryBR.getDirection() - angle);
        //bl rotation trajectory

        rotationTrajectoryBL = new Vector(trajectory.calcHeadingPoint()
                , new SwervweKin.common.Point(RobotMap.Robot.swerve.modulesLocations[3].getX() + trajectory.calcHeadingPoint().getX()
                ,RobotMap.Robot.swerve.modulesLocations[3].getY() + trajectory.calcHeadingPoint().getY() ));
        rotationTrajectoryBL.setDirection(rotationTrajectoryBL.getDirection() - angle);



        g.setColor(new Color(255,0,0));

        drawLine(g, rotationTrajectoryFR.calcHeadingPoint(),rotationTrajectoryFL.calcHeadingPoint());
        drawLine(g, rotationTrajectoryFL.calcHeadingPoint(), rotationTrajectoryBL.calcHeadingPoint());
        drawLine(g, rotationTrajectoryBL.calcHeadingPoint(), rotationTrajectoryBR.calcHeadingPoint());
        drawLine(g, rotationTrajectoryBR.calcHeadingPoint(),rotationTrajectoryFR.calcHeadingPoint());
    }
    public void drawGrid(Graphics g){
        g.setColor(new Color(255,255,255));
        g.drawLine(0,h/2,w,h/2);
        g.drawLine(w/2,0,w/2,h);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        drawGrid(g);
        drawChassis(g);
        //3 sec
        //trajectory painting
        double angle = angularVelocity * 5; //todo add a global double time; that changes trajectoey and so
        Vector trajectory = new Vector(direction, magnitude * 3, swerveKinematics.getPos());

        g.setColor(new Color(200, 153,100));
        drawLine(g,new SwervweKin.common.Point(0,0), new Point(trajectory.calcHeadingPoint().getX(), trajectory.calcHeadingPoint().getY()));

        drawChassis(g,trajectory.calcHeadingPoint(), angle % 360);



        swerveKinematics.setTransform(new Vector(direction,magnitude));
        swerveKinematics.setAngleVelocity(angularVelocity);
        swerveKinematics.Update();

        //3316
        drawDebugLine(g, RobotMap.Robot.swerve.modules[0], swerveKinematics);
        drawDebugLine(g, RobotMap.Robot.swerve.modules[1], swerveKinematics);
        drawDebugLine(g, RobotMap.Robot.swerve.modules[2], swerveKinematics);
        drawDebugLine(g, RobotMap.Robot.swerve.modules[3], swerveKinematics);

        g.setColor(new Color(0,255,0));
        drawLine(g,new Point(RobotMap.Robot.swerve.modules[0].getPos()),RobotMap.Robot.swerve.modules[0].getFinalVector().calcHeadingPoint());
        drawLine(g,new Point(RobotMap.Robot.swerve.modules[1].getPos()),RobotMap.Robot.swerve.modules[1].getFinalVector().calcHeadingPoint());
        drawLine(g,new Point(RobotMap.Robot.swerve.modules[2].getPos()),RobotMap.Robot.swerve.modules[2].getFinalVector().calcHeadingPoint());
        drawLine(g,new Point(RobotMap.Robot.swerve.modules[3].getPos()),RobotMap.Robot.swerve.modules[3].getFinalVector().calcHeadingPoint());

    }
}

