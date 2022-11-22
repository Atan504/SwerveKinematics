package SwervweKin.Swing;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Frame {


    public JFrame frame = new JFrame();
    public static Panel panel =new Panel(800,800);
    public Frame(){

        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("swk.Swerve Kinematics");
        frame.setResizable(true);
        frame.add(panel);
    }
    public void Update(){
        frame.repaint();
    }

    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.Update();
        TimerTask task = new TimerTask() {
            @Override
            public void run(){
                panel.setSize(frame.frame.getWidth(),frame.frame.getHeight());
                frame.Update();
            }
        };
        Timer t = new Timer();
        t.scheduleAtFixedRate(task,1,100);


    }
}

