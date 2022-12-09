package Swing;

import Swerve.Module;
import Swerve.SK;
import common.Point;
import common.Vector;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Frame {


    public JFrame frame = new JFrame();
    public static panel panel =new panel(200,200);
    public Frame(){
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("Swerve Kinematics");
        frame.setResizable(true);
        frame.setLayout(new GridLayout(2,2));
        frame.add(new JLabel());
        frame.add(new JLabel());
        frame.add(new JLabel());
        frame.add(panel);
    }
    public void Update(){
//        frame.setVisible(false);
        frame.repaint();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.Update();
        TimerTask task = new TimerTask() {
            @Override
            public void run(){
                panel.setSize(frame.frame.getWidth()/2,frame.frame.getHeight()/2);
                frame.Update();
            }
        };
        Timer t = new Timer();
        t.scheduleAtFixedRate(task,1,100);


    }
}
class panel extends JPanel {
    Module fr;
    Module fl;
    Module br;
    Module bl;
    SK chassis;

    public static double direction = 90;
    public static double magnitude = 100;
    public static double angularVelocity = 0;
    public static double currentAngle =45;

    public  static int w,h;
    public panel(int w,int h) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(new Color(0));
        this.w=w;
        this.h=h;
        this.fr = new Module(50,50, SK.MLoc.Front_Right);
        this.fl = new Module(-50,50, SK.MLoc.Front_Left);
        this.br = new Module(50,-50, SK.MLoc.Back_Right);
        this.bl = new Module(-50,-50, SK.MLoc.Back_Left);
        this.chassis = new SK();
        chassis.setPos(new Point(100,100));
    }



    public void setSize(int w,int h){
        this.w=w;
        this.h=h;
    }

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }
    public void drawLine(Graphics g,Point a,Point b){
        g.drawLine((int) a.getX()+(w/2), (int) -a.getY()+(h/2), (int) b.getX()+(w/2), (int) -b.getY()+(h/2));

    }
    public void drawDebugLine(Graphics g,SK.MLoc  mloc,SK chassis){

        Vector trajectory = new Vector(currentAngle, 0, chassis.getPos());

        Vector rotationTrajectory;

        //fr
        rotationTrajectory = new Vector(trajectory.calcHeadingPoint()
                , Point.add(mloc.getModule().getPos(),trajectory.calcHeadingPoint()));
        rotationTrajectory.setDirection(rotationTrajectory.getDirection() - currentAngle);

        g.setColor(new Color(0,0,255));
        Vector tran = new Vector(chassis.getTransform());
        Vector ang = new Vector(mloc.getValue(),angularVelocity,rotationTrajectory.calcHeadingPoint());
        tran.setPos(ang.calcHeadingPoint());
        drawLine(g,tran.getPos(),tran.calcHeadingPoint());
        g.setColor(new Color(125,0,125));
        drawLine(g,ang.getPos(),ang.calcHeadingPoint());
    }

    /**
     *         gl.glVertex2d(rotationTrajectoryFR.calcHeadingPoint().getX(),rotationTrajectoryFR.calcHeadingPoint().getY());
     *         gl.glVertex2d(rotationTrajectoryFL.calcHeadingPoint().getX(),rotationTrajectoryFL.calcHeadingPoint().getY());
     *
     *         gl.glVertex2d(rotationTrajectoryFL.calcHeadingPoint().getX(),rotationTrajectoryFL.calcHeadingPoint().getY());
     *         gl.glVertex2d(rotationTrajectoryBL.calcHeadingPoint().getX(),rotationTrajectoryBL.calcHeadingPoint().getY());
     *
     *         gl.glVertex2d(rotationTrajectoryBL.calcHeadingPoint().getX(),rotationTrajectoryBL.calcHeadingPoint().getY());
     *         gl.glVertex2d(rotationTrajectoryBR.calcHeadingPoint().getX(),rotationTrajectoryBR.calcHeadingPoint().getY());
     *
     *         gl.glVertex2d(rotationTrajectoryBR.calcHeadingPoint().getX(),rotationTrajectoryBR.calcHeadingPoint().getY());
     *         gl.glVertex2d(rotationTrajectoryFR.calcHeadingPoint().getX(),rotationTrajectoryFR.calcHeadingPoint().getY());
     * */

    public void drawChassis (Graphics g){

        Vector trajectory = new Vector(currentAngle, 0, chassis.getPos());

        Vector rotationTrajectoryFR;
        Vector rotationTrajectoryFL;
        Vector rotationTrajectoryBR;
        Vector rotationTrajectoryBL;

        //fr
        rotationTrajectoryFR = new Vector(trajectory.calcHeadingPoint()
                , Point.add(fr.getPos(),trajectory.calcHeadingPoint()));
        rotationTrajectoryFR.setDirection(rotationTrajectoryFR.getDirection() - currentAngle);
        //fl
        rotationTrajectoryFL = new Vector(trajectory.calcHeadingPoint()
                , Point.add(fl.getPos(),trajectory.calcHeadingPoint()));
        rotationTrajectoryFL.setDirection(rotationTrajectoryFL.getDirection() - currentAngle);
        //br rotation trajectory
        rotationTrajectoryBR = new Vector(trajectory.calcHeadingPoint()
                , Point.add(br.getPos(),trajectory.calcHeadingPoint()));
        rotationTrajectoryBR.setDirection(rotationTrajectoryBR.getDirection() - currentAngle);
        //bl rotation trajectory

        rotationTrajectoryBL = new Vector(trajectory.calcHeadingPoint()
                , Point.add(bl.getPos(),trajectory.calcHeadingPoint()));
        rotationTrajectoryBL.setDirection(rotationTrajectoryBL.getDirection() - currentAngle);

        g.setColor(new Color(255,0,0));

        drawLine(g, rotationTrajectoryFR.calcHeadingPoint(),rotationTrajectoryFL.calcHeadingPoint());
        drawLine(g, rotationTrajectoryFL.calcHeadingPoint(), rotationTrajectoryBL.calcHeadingPoint());
        drawLine(g, rotationTrajectoryBL.calcHeadingPoint(), rotationTrajectoryBR.calcHeadingPoint());
        drawLine(g, rotationTrajectoryBR.calcHeadingPoint(),rotationTrajectoryFR.calcHeadingPoint());




        //3316
        drawDebugLine(g,SK.MLoc.Front_Right,chassis);
        drawDebugLine(g,SK.MLoc.Front_Left,chassis);
        drawDebugLine(g,SK.MLoc.Back_Right,chassis);
        drawDebugLine(g,SK.MLoc.Back_Left,chassis);

        g.setColor(new Color(0,255,0));
        drawLine(g,rotationTrajectoryFR.calcHeadingPoint(),Vector.add(rotationTrajectoryFR,fr.getFvec()).calcHeadingPoint());
        drawLine(g,rotationTrajectoryFL.calcHeadingPoint(),Vector.add(rotationTrajectoryFL,fl.getFvec()).calcHeadingPoint());
        drawLine(g,rotationTrajectoryBR.calcHeadingPoint(),Vector.add(rotationTrajectoryBR,br.getFvec()).calcHeadingPoint());
        drawLine(g,rotationTrajectoryBL.calcHeadingPoint(),Vector.add(rotationTrajectoryBL,bl.getFvec()).calcHeadingPoint());
    }
    public void drawGrid(Graphics g){
        g.setColor(new Color(255,255,255));
        g.drawLine(0,h/2,w,h/2);
        g.drawLine(w/2,0,w/2,h);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        drawGrid(g);
         //TODO get localizer pos and angle
        //3 sec
        //trajectory painting
//        double angle = angularVelocity * 5; //todo add a global double time; that changes trajectoey and so
//        Vector trajectory = new Vector(direction, magnitude * 3, chassis.getPos());
//
//        g.setColor(new Color(200, 153,100));
//        drawLine(g,new Point(0,0), new Point(trajectory.calcHeadingPoint().getX(), trajectory.calcHeadingPoint().getY()));
//
//        drawChassis(g,trajectory.calcHeadingPoint(), angle % 360);



        chassis.setTransform(new Vector(direction,magnitude,chassis.getPos()));
        chassis.setAngleVelocity(angularVelocity);
        chassis.Update();
        drawChassis(g);
        //summon barkuni to fix bugs



    }
}

