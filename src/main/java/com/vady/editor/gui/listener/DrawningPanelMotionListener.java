package com.vady.editor.gui.listener;

import com.vady.paint.Scene;
import com.vady.paint.State;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class DrawningPanelMotionListener extends MouseMotionAdapter {


    public static final Logger logger = Logger.getLogger(DrawningPanelMotionListener.class);


    public void mouseMoved(MouseEvent mouseEvent) {

        if (Scene.instance.getState() == State.DRAWING) { // ARE WE INTERCATIVELY DRAWING FIGURE?
            Scene.instance.getSelected().currentCursorPosition(mouseEvent.getPoint());
            Scene.instance.getMainWindow().repaint();
        }

    }

    public void mouseDragged(MouseEvent mouseEvent) {
        logger.info("Dragging starts..");

        if (mouseEvent.getButton() == MouseEvent.BUTTON3) { // RIGHT MOUSE BUTTON

            if (Scene.instance.getState() == State.MOVING) {
                logger.info("Moving figure..");

                Point cursor = mouseEvent.getPoint();

                Scene.instance.setCursor(cursor);

                Scene.instance.getSelected().setStart(Scene.instance.getSelected().getEnd());
                Scene.instance.getSelected().setEnd(cursor);

                Scene.instance.getMainWindow().repaint();
            }

        }

        logger.info("Dragging ends..");

    }

}
