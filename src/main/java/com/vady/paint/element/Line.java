package com.vady.paint.element;

import com.vady.paint.Scene;
import com.vady.util.JoglUtils;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import java.awt.*;


public class Line extends Figure {

    private int clicks = 0;

    public Line(boolean drawingMode) {
        super(drawingMode);
    }

    protected void drawBody(GL gl, GLU glu) {
        gl.glBegin(GL.GL_LINE_LOOP);

        gl.glLineWidth(8.0f);

        for (Vertex vertex : vertices) {
            gl.glColor3f(vertex.getColorRed(), vertex.getColorGreen(), vertex.getColorBlue());
            gl.glVertex2d(vertex.getX(), vertex.getY());
        }

        gl.glEnd();
    }

    public void computeVertecies(GL gl, GLU glu) {
        if (start != null && end != null) {

            double[] start = JoglUtils.window2world(gl, glu, this.start);
            double[] end = JoglUtils.window2world(gl, glu, this.end);

            getVertices().get(0).setCoordinate(start[0], start[1]);
            getVertices().get(1).setCoordinate(end[0], end[1]);
        }
    }

    public void addVertex(Point point, Color color) {
        clicks++;

        if (clicks == 1) {
            getVertices().add(new Vertex(0, 0, color));
            getVertices().add(new Vertex(0, 0, color));
        }

        if (clicks < 2) {
            this.start = point;
            this.end = point;
        } else {
            setDrawing(false);
            Scene.instance.endDrawing();
        }
    }

    public void currentCursorPosition(Point point) {
        this.end = point;
    }

}