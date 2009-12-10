package com.vady.paint.element;

import com.vady.paint.Scene;
import com.vady.util.JoglUtils;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import java.awt.*;


public class Circle extends Figure {


    private static final double ONE_DEGREE = Math.PI / 180;

    protected Point start;
    protected Point end;

    private int clicks = 0;

    private Color color;


    public Circle() {
        super();
    }

    public Circle(boolean drawingMode) {
        super(drawingMode);
    }

    protected void drawBody(GL gl, GLU glu) {
        gl.glBegin(GL.GL_POLYGON);

        for (Vertex vertex : vertices) {
            gl.glColor3f(vertex.getColorRed(), vertex.getColorGreen(), vertex.getColorBlue());
            gl.glVertex2d(vertex.getX(), vertex.getY());
        }

        gl.glEnd();
    }

    public void addVertex(Point point, Color color) {
        clicks++;

        if (clicks < 2) {
            this.start = point;
            this.end = point;
            this.color = color;
        } else {
            setDrawing(false);
            Scene.instance.endDrawing();
        }
    }

    public void currentCursorPosition(Point point) {
        this.end = point;
    }

    public void computeVertecies(GL gl, GLU glu) {
        if (start != null && end != null) {
            getVertices().clear();

            double[] start = JoglUtils.window2world(gl, glu, this.start);
            double[] end = JoglUtils.window2world(gl, glu, this.end);

            double radius = JoglUtils.distance(start[0], start[1], end[0], end[1]);

            for (double angle = 0.0; angle < 360; angle += 5) {
                double x = radius * Math.cos(angle * ONE_DEGREE);
                double y = radius * Math.sin(angle * ONE_DEGREE);

                getVertices().add(new Vertex(x + start[0], y + start[1], this.color));
            }
        }
    }

}