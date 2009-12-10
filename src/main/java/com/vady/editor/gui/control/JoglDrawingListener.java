package com.vady.editor.gui.control;

import com.vady.paint.Scene;
import org.apache.log4j.Logger;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;


public class JoglDrawingListener implements GLEventListener {


    public static Logger logger = Logger.getLogger(JoglDrawingListener.class);

    public void init(GLAutoDrawable drawable) {

    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // background color

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        Scene.instance.display(drawable);

        drawable.swapBuffers();
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }

    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }

}
