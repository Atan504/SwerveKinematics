package OpenGL;

import Swerve.Module;
import Swerve.SK;
import Swerve.SK.MLoc;
import com.jogamp.opengl.util.FPSAnimator;
import common.Point;
import common.Vector;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;

public class FrameSK implements GLEventListener {
    public static final JFrame frame = new JFrame("Swerve Kinematics Demo - By Atan");
    public static GLCanvas gc;
    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }
    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        //setup
        final GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();  // Reset The View


            gl.glBegin(GL2.GL_LINES);
            gl.glColor3f(1.0f, 1.0f, 1.0f);
            gl.glVertex2d(0, -1);
            gl.glVertex2d(0, 1);
            gl.glVertex2d(1, 0);
            gl.glVertex2d(-1, 0);
        //white grid

        Module fr = new Module(0.1,0.1,MLoc.Front_Right);
        Module fl = new Module(-0.1,0.1,MLoc.Front_Left);
        Module br = new Module(0.1,-0.1,MLoc.Back_Right);
        Module bl = new Module(-0.1,-0.1,MLoc.Back_Left);

        SK chasis = new SK();

        chasis.setTransform(new Vector(90,0.1));
        chasis.setAngleVelocity(90);
        chasis.Update();

        //RED cassis
        gl.glColor3f( 1.0f,0.0f,0.0f );
        gl.glVertex2d(fr.getPos().getX(),fr.getPos().getY());
        gl.glVertex2d(fl.getPos().getX(),fl.getPos().getY());
        gl.glVertex2d(fl.getPos().getX(),fl.getPos().getY());
        gl.glVertex2d(bl.getPos().getX(),bl.getPos().getY());
        gl.glVertex2d(bl.getPos().getX(),bl.getPos().getY());
        gl.glVertex2d(br.getPos().getX(),br.getPos().getY());
        gl.glVertex2d(br.getPos().getX(),br.getPos().getY());
        gl.glVertex2d(fr.getPos().getX(),fr.getPos().getY());


        gl.glColor3f( 0.0f,1.0f,1.0f );
        gl.glVertex2d(fr.getPos().getX(),fr.getPos().getY());
        gl.glVertex2d(fr.getFvec().calcHeadingPoint().getX(),fr.getFvec().calcHeadingPoint().getY());

        gl.glVertex2d(fl.getPos().getX(),fl.getPos().getY());
        gl.glVertex2d(fl.getFvec().calcHeadingPoint().getX(),fl.getFvec().calcHeadingPoint().getY());

        gl.glVertex2d(bl.getPos().getX(),bl.getPos().getY());
        gl.glVertex2d(bl.getFvec().calcHeadingPoint().getX(),bl.getFvec().calcHeadingPoint().getY());

        gl.glVertex2d(br.getFvec().getPos().getX(),br.getFvec().getPos().getY());
        gl.glVertex2d(br.getFvec().calcHeadingPoint().getX(),br.getFvec().calcHeadingPoint().getY());


        //Dbug br
        Vector tran = chasis.getTransform();
        Vector ang = new Vector(MLoc.Back_Right.getValue(),chasis.getAngleVelocity()/ 800.0,br.getPos());
        gl.glColor3f( 0.0f,0.0f,1.0f);
        tran.setPos(ang.calcHeadingPoint());
        gl.glVertex2d(tran.getPos().getX(),tran.getPos().getY());
        gl.glVertex2d(tran.calcHeadingPoint().getX(),tran.calcHeadingPoint().getY());
        gl.glColor3f( 1.0f,0.0f,1.0f );
        gl.glVertex2d(ang.getPos().getX(),ang.getPos().getY());
        gl.glVertex2d(ang.calcHeadingPoint().getX(),ang.calcHeadingPoint().getY());





        gl.glEnd();
        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    public static void main(String[] args) {
        final GLProfile gp = GLProfile.get(GLProfile.GL2);
        GLCapabilities cap = new GLCapabilities(gp);

        gc = new GLCanvas(cap);
        FrameSK GLFrame = new FrameSK();
        gc.addGLEventListener(GLFrame);
        gc.setSize(800, 800);
        frame.add(gc);
        frame.setSize(900, 800);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final FPSAnimator animator = new FPSAnimator(gc, 60, true);
        animator.start();
    }
}
