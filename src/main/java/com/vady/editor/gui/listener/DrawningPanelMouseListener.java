package com.vady.editor.gui.listener;

import com.vady.paint.Scene;
import com.vady.paint.State;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class DrawningPanelMouseListener extends MouseAdapter {


    public static final Logger logger = Logger.getLogger(DrawningPanelMouseListener.class);


    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) { // LEFT MOUSE BUTTON CLICKED
            logger.info("Left mouse button clicked.");

            if (Scene.instance.getState() == State.NOTHING || Scene.instance.isSelected()) {
                Point point = mouseEvent.getPoint();

                Scene.instance.setCursor(point);

                logger.info("[" + point.getX() + ";" + point.getY() + "]");
            }

            if (Scene.instance.getState() == State.DRAWING) {
                Scene.instance.getSelected().addVertex(mouseEvent.getPoint(), Scene.instance.getColor());
            }
        }

        if (mouseEvent.getButton() == MouseEvent.BUTTON3) { // RIGHT MOUSE BUTTON CLICKED
            logger.info("Right mouse button clicked.");

            if (Scene.instance.getState() == State.DRAWING) { // When drawing polygon - last vertex position
                ((com.vady.paint.element.Polygon) (Scene.instance.getSelected())).close(mouseEvent.getPoint(), Scene.instance.getColor());
            }
        }

        Scene.instance.getMainWindow().repaint();
    }


    public void mousePressed(MouseEvent mouseEvent) {

        if (mouseEvent.getButton() == MouseEvent.BUTTON3) { // RIGHT MOUSE BUTTON PRESSED

            logger.debug("Right mouse button!!");

            if (Scene.instance.isSelected()) {
                Scene.instance.setState(State.MOVING);
                Scene.instance.getSelected().setState(State.MOVING);

                Scene.instance.getSelected().setStart(mouseEvent.getPoint());
                Scene.instance.getSelected().setEnd(mouseEvent.getPoint());
            }
        }

        Scene.instance.getMainWindow().repaint();
    }


    public void mouseReleased(MouseEvent mouseEvent) {

        if (mouseEvent.getButton() == MouseEvent.BUTTON3) { // RIGHT MOUSE BUTTON PRESSED

            if (Scene.instance.getState() == State.MOVING) {
                //Scene.instance.setEndPoint(mouseEvent.getPoint());
                Scene.instance.setState(State.SELECTED);
                Scene.instance.getSelected().select();
            }
        }

        Scene.instance.getMainWindow().repaint();
    }
}
