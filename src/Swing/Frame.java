package Swing;

import Swerve.Module;
import Swerve.SK;
import common.Point;
import common.Vector;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Frame {


    public static JFrame frame = new JFrame();
    public static JPanel panel = new JPanel();

    public Frame(){

        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("Swerve Kinematics");
        frame.setResizable(true);
        frame.add(new panel(800,800));
    }
    public void Update(){

    }

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.Update();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                frame.Update();
                System.out.println("frame updated");
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

    public static final double direction = 90;
    public static final double magnitude = 100;
    public static final double angularVelocity = 45;
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
    }





    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }
    public void drawLine(Graphics g,Point a,Point b){
        g.drawLine((int) a.getX()+(w/2), (int) -a.getY()+(h/2), (int) b.getX()+(w/2), (int) -b.getY()+(h/2));

    }
    public void drawDebugLine(Graphics g,SK.MLoc  mloc,SK chassis){
        g.setColor(new Color(0,0,255));
        Vector tran = new Vector(chassis.getTransform());
        Vector ang = new Vector(mloc.getValue(),angularVelocity,mloc.getModule().getPos());
        tran.setPos(ang.calcHeadingPoint());
        drawLine(g,tran.getPos(),tran.calcHeadingPoint());
        g.setColor(new Color(125,0,125));
        drawLine(g,ang.getPos(),ang.calcHeadingPoint());
    }
    public void drawChassis (Graphics g){

        g.setColor(new Color(255,0,0));
        drawLine(g,fr.getPos(),fl.getPos());
        drawLine(g,fl.getPos(),bl.getPos());
        drawLine(g,bl.getPos(),br.getPos());
        drawLine(g,br.getPos(),fr.getPos());
    }

    public void drawChassis (Graphics g, Point center){

        double x= center.getX();
        double y = center.getY();

        g.setColor(new Color(255,0,0));
        drawLine(g, new Point(fr.getPos().getX() + x, fr.getPos().getY() + y),new Point(fl.getPos().getX() + x, fl.getPos().getY() + y));
        drawLine(g,new Point(fl.getPos().getX() + x, fl.getPos().getY() + y),new Point(bl.getPos().getX() + x, bl.getPos().getY() + y));
        drawLine(g,new Point(bl.getPos().getX() + x, bl.getPos().getY() + y),new Point(br.getPos().getX() + x, br.getPos().getY() + y));
        drawLine(g,new Point(br.getPos().getX() + x, br.getPos().getY() + y),new Point(fr.getPos().getX() + x, fr.getPos().getY() + y));
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

    public void drawChassis (Graphics g, Point center, double angle){

        double x= center.getX();
        double y = center.getY();

        Vector trajectory = new Vector(direction, magnitude * 3, chassis.getPos());

        Vector rotationTrajectoryFR;
        Vector rotationTrajectoryFL;
        Vector rotationTrajectoryBR;
        Vector rotationTrajectoryBL;

        //fr
        rotationTrajectoryFR = new Vector(
                trajectory.calcHeadingPoint()
                , new Point(fr.getPos().getX() + trajectory.calcHeadingPoint().getX()
                ,fr.getPos().getY() + trajectory.calcHeadingPoint().getY()));
        rotationTrajectoryFR.setDirection(rotationTrajectoryFR.getDirection() - angle);
        //fl
        rotationTrajectoryFL = new Vector(
                trajectory.calcHeadingPoint()
                , new Point(fl.getPos().getX() + trajectory.calcHeadingPoint().getX()
                ,fl.getPos().getY() + trajectory.calcHeadingPoint().getY()));
        rotationTrajectoryFL.setDirection(rotationTrajectoryFL.getDirection() - angle);
        //br rotation trajectory
        rotationTrajectoryBR = new Vector(
                trajectory.calcHeadingPoint()
                , new Point(br.getPos().getX() + trajectory.calcHeadingPoint().getX()
                ,br.getPos().getY() + trajectory.calcHeadingPoint().getY()));
        rotationTrajectoryBR.setDirection(rotationTrajectoryBR.getDirection() - angle);
        //bl rotation trajectory

        rotationTrajectoryBL = new Vector(trajectory.calcHeadingPoint()
                , new Point(bl.getPos().getX() + trajectory.calcHeadingPoint().getX()
                ,bl.getPos().getY() + trajectory.calcHeadingPoint().getY() ));
        rotationTrajectoryBL.setDirection(rotationTrajectoryBL.getDirection() - angle);



        g.setColor(new Color(255,0,0));

        drawLine(g, rotationTrajectoryFR.calcHeadingPoint(),rotationTrajectoryFL.calcHeadingPoint());
        drawLine(g, rotationTrajectoryFL.calcHeadingPoint(), rotationTrajectoryBL.calcHeadingPoint());
        drawLine(g, rotationTrajectoryBL.calcHeadingPoint(), rotationTrajectoryBR.calcHeadingPoint());
        drawLine(g, rotationTrajectoryBR.calcHeadingPoint(),rotationTrajectoryFR.calcHeadingPoint());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);



        drawChassis(g);
        //3 sec
        //trajectory painting
        double angle = angularVelocity * 5; //todo add a global double time; that changes trajectoey and so
        Vector trajectory = new Vector(direction, magnitude * 3, chassis.getPos());

        g.setColor(new Color(100,200,100));
        drawLine(g,new Point(0,0), new Point(trajectory.calcHeadingPoint().getX(), trajectory.calcHeadingPoint().getY()));

        drawChassis(g,trajectory.calcHeadingPoint(), angle % 360);



        chassis.setTransform(new Vector(direction,magnitude));
        chassis.setAngleVelocity(angularVelocity);
        chassis.Update();


        g.setColor(new Color(0,255,0));
        drawLine(g,fr.getPos(),fr.getFvec().calcHeadingPoint());
        drawLine(g,fl.getPos(),fl.getFvec().calcHeadingPoint());
        drawLine(g,br.getPos(),br.getFvec().calcHeadingPoint());
        drawLine(g,bl.getPos(),bl.getFvec().calcHeadingPoint());

        //Dbug
        drawDebugLine(g,SK.MLoc.Front_Right,chassis);
        drawDebugLine(g,SK.MLoc.Front_Left,chassis);
        drawDebugLine(g,SK.MLoc.Back_Right,chassis);
        drawDebugLine(g,SK.MLoc.Back_Left,chassis);
    }
}

