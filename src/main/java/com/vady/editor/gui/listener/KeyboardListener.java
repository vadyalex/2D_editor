package com.vady.editor.gui.listener;

import com.vady.paint.Scene;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyboardListener extends KeyAdapter {

    public static final Logger logger = Logger.getLogger(KeyboardListener.class);

    @Override
    public void keyTyped(KeyEvent keyEvent) {  // TODO change it
        logger.info("Key typed!");

        if (keyEvent.getKeyCode() == KeyEvent.VK_DELETE) {
            Scene.instance.deleteSelectedFigure();
        }

        ((JComponent) keyEvent.getSource()).repaint();
    }
}
