package com.vady.paint.element;

import com.vady.editor.PropertiesHolder;
import com.vady.editor.gui.Property;
import com.vady.paint.Scene;
import com.vady.util.JoglUtils;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import java.awt.*;


public class Polygon extends Figure {


    private Point start;
    private Point end;

    private boolean closed = false;


    public Polygon() {
        super();
    }

    public Polygon(boolean drawingMode) {
        super(drawingMode);
    }

    public void drawScatch(GL gl, GLU glu) {
        computeVertecies(gl, glu);

        gl.glLineWidth(PropertiesHolder.instance.asFloat(Property.SCATCH_LINE_WIDTH));

        gl.glColor3f(PropertiesHolder.instance.asFloat(Property.SCATCH_LINE_COLOR_R),
                PropertiesHolder.instance.asFloat(Property.SCATCH_LINE_COLOR_G),
                PropertiesHolder.instance.asFloat(Property.SCATCH_LINE_COLOR_B));

        gl.glBegin(GL.GL_LINES);
        for (Vertex vertex : vertices) {
            gl.glVertex2d(vertex.getX(), vertex.getY());
        }
        gl.glEnd();
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
        if (!closed) {
            this.start = end;
            this.end = point;

            getVertices().add(new Vertex(0, 0, color));
            getVertices().add(new Vertex(0, 0, color));
        } else {
            setDrawing(false);
            Scene.instance.endDrawing();
        }
    }

    public void close(Point point, Color color) {
        closed = true;

        addVertex(point, color);
    }

    public void currentCursorPosition(Point point) {
        this.end = point;
    }

}
