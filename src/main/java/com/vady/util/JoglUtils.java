package com.vady.util;

import org.apache.log4j.Logger;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import java.awt.*;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class JoglUtils {


    public static final Logger logger = Logger.getLogger(JoglUtils.class);


    /**
     * Convert point coordinates from the window coordinate system to the OpenGL world coordinates.
     *
     * @param gl    Current OpenGL context
     * @param glu   GLU library
     * @param point Point coordinates in window
     * @return converted point in OpenGL world coordinate system
     */
    public static double[] window2world(GL gl, GLU glu, Point point) {
        int viewport[] = new int[4];
        double mvmatrix[] = new double[16];
        double projmatrix[] = new double[16];

        double wcoord[] = new double[4]; // wx, wy, wz

        gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);
        gl.glGetDoublev(GL.GL_MODELVIEW_MATRIX, mvmatrix, 0);
        gl.glGetDoublev(GL.GL_PROJECTION_MATRIX, projmatrix, 0);

        /* note viewport[3] is height of window in pixels */
        double x = point.getX();
        double y = point.getY();


        glu.gluUnProject(x, viewport[3] - y - 1, 0.0, mvmatrix, 0, projmatrix, 0, viewport, 0, wcoord, 0);

        logger.debug("Cursot window coordinates: [" + point.getX() + ";" + point.getY() + "]");
        logger.debug("Cursor World coordinates: [" + wcoord[0] + ";" + wcoord[1] + "]");

        return wcoord;
    }


    public static double distance(double x1, double y1, double x2, double y2) {
        return sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2)); // distance between two points
    }


}
