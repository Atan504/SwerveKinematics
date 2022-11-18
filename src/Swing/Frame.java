package Swing;

import Swerve.Module;
import Swerve.SK;
import common.Point;
import common.Vector;

import javax.swing.*;
import java.awt.*;

public class Frame {


    public static JFrame frame = new JFrame();
    public static JPanel panel = new JPanel();

    public Frame(){

        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("Swerve Kinematics - By Atan");
        frame.add(new panel(800,800));
    }
    public void Update(){


    }

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.Update();
    }
}
class panel extends JPanel {
    public static final double direction = 90;
    public static final double magnitude = 100;
    public static final double angularVelocity = 45;
    public  static int w,h;
    public panel(int w,int h) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(new Color(0));
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
        g.setColor(new Color(0,0,255));
        Vector tran = new Vector(chassis.getTransform());
        Vector ang = new Vector(mloc.getValue(),angularVelocity,mloc.getModule().getPos());
        tran.setPos(ang.calcHeadingPoint());
        drawLine(g,tran.getPos(),tran.calcHeadingPoint());
        g.setColor(new Color(125,0,125));
        drawLine(g,ang.getPos(),ang.calcHeadingPoint());
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Module fr = new Module(100,100, SK.MLoc.Front_Right);
        Module fl = new Module(-100,100, SK.MLoc.Front_Left);
        Module br = new Module(100,-100, SK.MLoc.Back_Right);
        Module bl = new Module(-100,-100, SK.MLoc.Back_Left);

        SK chassis = new SK();



        chassis.setTransform(new Vector(direction,magnitude));
        chassis.setAngleVelocity(angularVelocity);
        chassis.Update();
        g.setColor(new Color(255,0,0));
        drawLine(g,fr.getPos(),fl.getPos());
        drawLine(g,fl.getPos(),bl.getPos());
        drawLine(g,bl.getPos(),br.getPos());
        drawLine(g,br.getPos(),fr.getPos());

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

