package com.vady.editor.gui.listener;

import com.vady.paint.Scene;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class DrawningPanelWheelListener implements MouseWheelListener {

    public static final Logger logger = Logger.getLogger(DrawningPanelWheelListener.class);

    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {

        logger.info("Mouse wheel.");

        int count = mouseWheelEvent.getWheelRotation();

        logger.info(count);

        Scene.instance.zoom(count);

        ((JComponent) mouseWheelEvent.getSource()).repaint();
    }

}
