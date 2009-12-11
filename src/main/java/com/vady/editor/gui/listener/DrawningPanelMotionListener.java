package com.vady.editor.gui.listener;

import com.vady.paint.Scene;
import com.vady.paint.SceneState;
import org.apache.log4j.Logger;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class DrawningPanelMotionListener extends MouseMotionAdapter {


    public static final Logger logger = Logger.getLogger(DrawningPanelMotionListener.class);


    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (Scene.instance.getState() == SceneState.DRAWING) { // ARE WE INTERCATIVELY DRAWING FIGURE?
            Scene.instance.getSelected().currentCursorPosition(mouseEvent.getPoint());
            Scene.instance.getMainWindow().repaint();
        }
    }

    public void mouseDragged(MouseEvent mouseEvent) {
        logger.info("Dragging starts..");

/*
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) { // LEFT MOUSE BUTTON
            if (Scene.instance.getState() == SceneState.DRAWING) {
                Scene.instance.getDrawMode().endMousePosition(mouseEvent.getPoint());
            }
        }

*/


        if (mouseEvent.getButton() == MouseEvent.BUTTON3) { // RIGHT MOUSE BUTTON
/*
            Point oldPoint = Scene.instance.getPickPoint();
            Point newPoint = mouseEvent.getPoint();

            int width = ((JPanel) mouseEvent.getSource()).getWidth();
            int height = ((JPanel) mouseEvent.getSource()).getHeight();

            float difX = IdUtils.window2OpenGL((int) newPoint.getX(), width) - IdUtils.window2OpenGL((int) oldPoint.getX(), width);
            float difY = IdUtils.window2OpenGL((int) -newPoint.getY(), height) - IdUtils.window2OpenGL((int) -oldPoint.getY(), height);


            Scene.instance.move(difX, difY);

            Scene.instance.setPickPoint(newPoint);
*/
            logger.info("Moving figure..");

/*
            Scene.instance.setEndPoint(mouseEvent.getPoint());

            Scene.instance.getMainWindow().repaint();

            Scene.instance.setPickPoint(mouseEvent.getPoint());
*/
        }


        logger.info("Dragging ends..");
        Scene.instance.getMainWindow().repaint();
    }

}
