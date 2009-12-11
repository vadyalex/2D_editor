package com.vady.paint.element;

import com.vady.editor.PropertiesHolder;
import com.vady.editor.gui.Property;
import com.vady.paint.State;
import com.vady.util.ColorUtils;
import com.vady.util.IdUtils;
import com.vady.util.JoglUtils;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public abstract class Figure implements Displayable {

    private static final double MOVING_SCALE = 1.0;

    protected int id;

    protected State state;

    protected List<Vertex> vertices;
    protected Point start;

    protected Point end;

    public Figure() {
        setId(IdUtils.generate());

        vertices = new ArrayList<Vertex>();

        state = State.NOTHING;
    }

    public Figure(boolean drawingMode) {
        setId(IdUtils.generate());

        vertices = new ArrayList<Vertex>();

        if (drawingMode) {
            state = State.DRAWING;
        }
    }

    public void display(GL gl, GLU glu) {
        gl.glPushName(id);

        if (isDrawing()) {
            drawScatch(gl, glu);
        } else {

            if (state == State.MOVING) {
                moveFigure(gl, glu);
            }

            drawBody(gl, glu);

            if (isSelected()) {
                drawSelectionBox(gl);
            }
        }

        gl.glPopName();
    }

    private void moveFigure(GL gl, GLU glu) {
        if (start != null && end != null) {
            double[] start = JoglUtils.window2world(gl, glu, this.start);
            double[] end = JoglUtils.window2world(gl, glu, this.end);

            double moveX = (end[0] - start[0]) * MOVING_SCALE;
            double moveY = (end[1] - start[1]) * MOVING_SCALE;

            for (Vertex vertex : vertices) {
                vertex.setX(vertex.getX() + moveX);
                vertex.setY(vertex.getY() + moveY);
            }

        }
    }

    protected void drawSelectionBox(GL gl) {
        gl.glLineWidth(PropertiesHolder.instance.asFloat(Property.SELECTION_BOX_LINE_WIDTH));

        gl.glColor3f(
                PropertiesHolder.instance.asFloat(Property.SELECTION_BOX_LINE_COLOR_R),
                PropertiesHolder.instance.asFloat(Property.SELECTION_BOX_LINE_COLOR_G),
                PropertiesHolder.instance.asFloat(Property.SELECTION_BOX_LINE_COLOR_B));

        gl.glEnable(GL.GL_LINE_STIPPLE);

        gl.glLineStipple(1, (short) 20);

        gl.glBegin(GL.GL_LINE_LOOP);
        for (Vertex vertex : vertices) {
            gl.glVertex2d(vertex.getX(), vertex.getY());
        }
        gl.glEnd();

        gl.glDisable(GL.GL_LINE_STIPPLE);
    }

    public void drawScatch(GL gl, GLU glu) {
        computeVertecies(gl, glu);

        gl.glLineWidth(PropertiesHolder.instance.asFloat(Property.SCATCH_LINE_WIDTH));

        gl.glColor3f(
                PropertiesHolder.instance.asFloat(Property.SCATCH_LINE_COLOR_R),
                PropertiesHolder.instance.asFloat(Property.SCATCH_LINE_COLOR_G),
                PropertiesHolder.instance.asFloat(Property.SCATCH_LINE_COLOR_B));

        gl.glBegin(GL.GL_LINE_LOOP);
        for (Vertex vertex : vertices) {
            gl.glVertex2d(vertex.getX(), vertex.getY());
        }
        gl.glEnd();
    }

    protected abstract void drawBody(GL gl, GLU glu);

    public abstract void computeVertecies(GL gl, GLU glu);

    public abstract void addVertex(Point point, Color color);

    public abstract void currentCursorPosition(Point point);

    public void fill(Color color) {
        for (Vertex vertex : vertices) {
            vertex.setColor(color);
        }
    }

    public void randomInterpolate() {
        for (Vertex vertex : getVertices()) {
            vertex.setColor(ColorUtils.generateRandomColor());
        }
    }

    public void rotate(double angle) {
        JoglUtils.rotateFigure(angle, this, JoglUtils.figureCenter(this));
    }

    public void scale(double factor) {
        JoglUtils.scale(factor, this);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isDrawing() {
        return state == State.DRAWING;
    }

    public void setDrawing(boolean drawing) {
        if (drawing) {
            setState(State.DRAWING);
        } else {
            setState(State.NOTHING);
        }
    }

    public boolean isSelected() {
        return state == State.SELECTED;
    }

    public void select() {
        setState(State.SELECTED);
    }

    public void unselect() {
        setState(State.NOTHING);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }
}
