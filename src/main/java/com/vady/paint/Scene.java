package com.vady.paint;

import com.sun.opengl.util.BufferUtil;
import com.vady.paint.element.Figure;
import com.vady.paint.element.Vertex;
import com.vady.util.FigureUtils;
import com.vady.util.JoglUtils;
import org.apache.log4j.Logger;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import java.awt.*;
import java.nio.IntBuffer;
import java.util.LinkedList;
import java.util.List;


public class Scene {


    public static final Scene instance = new Scene();

    public static final Logger logger = Logger.getLogger(Scene.class);


    private final int BUFSIZE = 512;
    private final float ZOOM_SCALE = 0.01f;

    private GLU glu;

    private Point pickPoint;
    private Point endPoint;

    private SceneState state;

    private Figure selected;
    private List<Figure> figures;

    private float zoom;

    private Color color;

    private JFrame mainWindow;

    private Scene() {
        glu = new GLU();

        init();

    }

    private void init() {
        setPickPoint(null);
        state = SceneState.NOTHING;

        selected = null;

        figures = new LinkedList<Figure>();

        zoom = 1.0f;

        color = Color.BLACK;

        pickPoint = new Point(0, 0);

//        figures.add(FigureUtils.createInterpolatedTriangle()); // TODO remove it
        figures.add(FigureUtils.createTriangle(0, 0, 0, 1f, 1f, 0)); // TODO remove it
        //  figures.add(FigureUtils.createCircle());
    }

    public void startDrawing(Figure drawingFigure) {
        unselect();

        selected = drawingFigure;

        state = SceneState.DRAWING;
    }

    public void endDrawing() {
        figures.add(selected);

        unselect();
    }

    public void cancelDrawing() {
        unselect();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glPushMatrix();

        gl.glScalef(zoom, zoom, 1.0f);

        if (isSelected() || state == SceneState.NOTHING) {
            pickFigure(gl);
        }

        drawFigures(gl, glu);

        if (state == SceneState.MOVING && endPoint != null) {
            moveSelectedFigureTo(gl, glu);
        }

        if (state == SceneState.DRAWING) {
            selected.display(gl, glu);
        }

        gl.glPopMatrix();

        drawable.swapBuffers();
    }

    private void drawFigures(GL gl, GLU glu) {
        for (Figure figure : figures) {
            figure.display(gl, glu);
        }
    }

    private Figure findFigureById(int id) {
        for (Figure figure : figures) {
            if (figure.getId() == id) {
                return figure;
            }
        }

        return null;
    }

    private void pickFigure(GL gl) {
        int selectBuf[] = new int[BUFSIZE];
        IntBuffer selectBuffer = BufferUtil.newIntBuffer(BUFSIZE);

        int viewport[] = new int[4];

        gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);

        gl.glSelectBuffer(BUFSIZE, selectBuffer);
        gl.glRenderMode(GL.GL_SELECT);

        gl.glInitNames();

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();

        glu.gluPickMatrix((double) pickPoint.x, (double) (viewport[3] - pickPoint.y), 1.0, 1.0, viewport, 0);

        drawFigures(gl, glu);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glPopMatrix();
        gl.glFlush();


        int hits = gl.glRenderMode(GL.GL_RENDER); // mouse hits
        selectBuffer.get(selectBuf);

        int count = selectBuf[0];

        logger.info("Picked objects count: " + count);

        if (count > 0) { // some figure?
            unselect();
            select(findFigureById(selectBuf[3]));

            logger.info("Figure's id " + selected);

/*
            for (int i=3; i<count+3; i++) { // proccess each id
                System.out.print(" " + selectBuf[i]);
            }
            System.out.println();
*/
        } else {
            unselect();
        }

    }

    public void select(Figure foundedFigure) {
        selected = foundedFigure;
        selected.select();
        state = SceneState.SELECTED;
    }

    public void unselect() {
        if (selected != null) {
            selected.unselect();
        }
        selected = null;
        state = SceneState.NOTHING;
    }

    public void zoom(int level) {
        zoom += level * ZOOM_SCALE;
    }

    public void zoomOriginal() {
        zoom = 1.0f;
    }

    public void deleteSelectedFigure() {
        if (isSelected()) {
            figures.remove(selected);
            unselect();
            state = SceneState.NOTHING;
        }
    }

    public void fill() {
        if (isSelected()) {
            selected.fill(color);
        }
    }

    public void randomInterpolation() {
        if (isSelected()) {
            selected.interpolate();
        }
    }

    public Point getPickPoint() {
        return pickPoint;
    }

    public void setPickPoint(Point pickPoint) {
        this.pickPoint = pickPoint;
    }

    public Color getColor() {
        return color;
    }

    public void changeColor(Color color) {
        this.color = color;

        fill(); // If some figure selected - refill it with new color
    }

    public JFrame getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(JFrame mainWindow) {
        this.mainWindow = mainWindow;
    }

    public SceneState getState() {
        return state;
    }

    public Figure getSelected() {
        return selected;
    }

    public boolean isSelected() {
        return state == SceneState.SELECTED;
    }

    public void setState(SceneState state) {
        this.state = state;
    }

    public void move(float difX, float difY) {
        for (Vertex vertex : selected.getVertices()) {
            vertex.setX(vertex.getX() + difX);
            vertex.setY(vertex.getY() + difY);
        }

    }

    public void setEndPoint(Point point) {
        this.endPoint = point;
    }

    private void moveSelectedFigureTo(GL gl, GLU glu) {
        double[] oldPoint = JoglUtils.window2world(gl, glu, pickPoint);
        double[] newPoint = JoglUtils.window2world(gl, glu, endPoint);

        for (Vertex vertex : selected.getVertices()) {
            vertex.setX(vertex.getX() + (newPoint[0] - oldPoint[0]));
            vertex.setY(vertex.getY() + (newPoint[1] - oldPoint[1]));
        }

    }


}
