package com.vady.paint.element;

import com.vady.paint.Scene;
import com.vady.util.JoglUtils;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import java.awt.*;


public class Triangle extends Figure {

    private static final int DRAWING_CLICKS_MAX = 3;

    private int clicks = 0;

    public Triangle() {
        super();
    }

    public Triangle(boolean drawingMode) {
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


    public void computeVertecies(GL gl, GLU glu) {
        int vertexCount = getVertices().size();

        if (start != null && end != null && vertexCount > 1) {
            double[] start = JoglUtils.window2world(gl, glu, this.start);
            double[] end = JoglUtils.window2world(gl, glu, this.end);

            getVertices().get(vertexCount - 2).setCoordinate(start[0], start[1]);
            getVertices().get(vertexCount - 1).setCoordinate(end[0], end[1]);
        }
    }

    public void addVertex(Point point, Color color) {
        clicks++;

        if (clicks < DRAWING_CLICKS_MAX) {
            this.start = end;
            this.end = point;

            getVertices().add(new Vertex(0, 0, color));
            getVertices().add(new Vertex(0, 0, color));
        } else {
            setDrawing(false);
            Scene.instance.endDrawing();
        }
    }

    public void currentCursorPosition(Point point) {
//        this.start = this.end;
        this.end = point;
    }

}