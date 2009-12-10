package com.vady.paint.element;

import com.vady.editor.PropertiesHolder;
import com.vady.editor.gui.Property;
import com.vady.util.ColorUtils;
import com.vady.util.Utils;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public abstract class Figure implements Displayable {

    protected int id;

    protected boolean selected;

    protected boolean drawing;
    protected List<Vertex> vertices;


    public Figure() {
        setId(Utils.generate());

        vertices = new ArrayList<Vertex>();

        drawing = false;
    }

    public Figure(boolean drawingMode) {
        setId(Utils.generate());

        vertices = new ArrayList<Vertex>();

        drawing = drawingMode;
    }

/*
    public Figure(int id, Vertex... vertecies) {
        setId(id);

        if (vertecies.length > 0) {
            this.vertices = Arrays.asList(vertecies);
        } else {
            this.vertices = new ArrayList<Vertex>();
        }
    }
*/

    public void display(GL gl, GLU glu) {
        gl.glPushName(id);

        if (drawing) {
            drawScatch(gl, glu);
        } else {
            drawBody(gl, glu);

            if (isSelected()) {
                drawSelectionBox(gl);
            }
        }

        gl.glPopName();
    }

    protected void drawSelectionBox(GL gl) {
        gl.glLineWidth(PropertiesHolder.instance.asFloat(Property.SELECTION_BOX_LINE_WIDTH));

        gl.glColor3f(PropertiesHolder.instance.asFloat(Property.SELECTION_BOX_LINE_COLOR_R),
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

        gl.glColor3f(PropertiesHolder.instance.asFloat(Property.SCATCH_LINE_COLOR_R),
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

    public boolean isDrawing() {
        return drawing;
    }

    public void setDrawing(boolean drawing) {
        this.drawing = drawing;
    }

    public void interpolate() {
        for (Vertex vertex : getVertices()) {
            vertex.setColor(ColorUtils.generateRandomColor());
        }

    }

    public boolean isSelected() {
        return selected;
    }

    public void select() {
        this.selected = true;
    }

    public void unselect() {
        this.selected = false;
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
}
