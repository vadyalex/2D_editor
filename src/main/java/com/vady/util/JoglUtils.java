package com.vady.util;

import com.vady.paint.element.Figure;
import com.vady.paint.element.Vertex;
import org.apache.log4j.Logger;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import java.awt.*;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class JoglUtils {


    public static final Logger logger = Logger.getLogger(JoglUtils.class);

    public static final double ONE_DEGREE = 0.017453292519943295769236907684886;


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


    public static double length(double x1, double y1, double x2, double y2) {
        return sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2)); // length between two points
    }


    public static Vertex figureCenter(Figure figure) {
        double xc = 0, yc = 0;
        double P = 0;

        int n = figure.getVertices().size();
        for (int i = 0; i < n; i++) {
            Vertex iVertex = figure.getVertices().get(i);
            Vertex inextVertex = figure.getVertices().get((i + 1) % n);

            double l = length(iVertex.getX(), iVertex.getY(), inextVertex.getX(), inextVertex.getY());

            xc += l * (iVertex.getX() + inextVertex.getX()) / 2;
            yc += l * (iVertex.getY() + inextVertex.getY()) / 2;

            P += l;
        }

        return new Vertex(xc /= P, yc /= P);
    }

    public static void rotateFigure(double angle, Figure figure, Vertex rotationPoint) {

        // move figure to coordinates center
        for (Vertex vertex : figure.getVertices()) {
            vertex.setX(vertex.getX() - rotationPoint.getX());
            vertex.setY(vertex.getY() - rotationPoint.getY());
        }

        angle = angle * ONE_DEGREE;
        double sinValue = Math.sin(angle);
        double cosValue = Math.cos(angle);

        // rotate every vertex
        for (Vertex vertex : figure.getVertices()) {
            double x = vertex.getX();
            double y = vertex.getY();

            vertex.setX(x * cosValue - y * sinValue);
            vertex.setY(x * sinValue + y * cosValue);
        }

        // move figure back
        for (Vertex vertex : figure.getVertices()) {
            vertex.setX(vertex.getX() + rotationPoint.getX());
            vertex.setY(vertex.getY() + rotationPoint.getY());
        }

    }


    public static void scale(double factor, Figure figure) {
        for (Vertex vertex : figure.getVertices()) {
            vertex.setX(vertex.getX() * factor);
            vertex.setY(vertex.getY() * factor);
        }
    }
}
