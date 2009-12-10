package com.vady.editor.gui.listener;

import com.vady.editor.PropertiesHolder;
import com.vady.paint.Scene;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ColorMouseListener extends MouseAdapter {


    public static final Logger logger = Logger.getLogger(ColorMouseListener.class);

    private static final String COLOR_DIALOG_TITLE = "COLOR_DIALOG_TITLE";


    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) { // LEFT MOUSE BUTTON
            JComponent source = (JComponent) mouseEvent.getSource();

            Color initialBackground = source.getBackground();
            Color color = JColorChooser.showDialog(null, PropertiesHolder.instance.getLabels().getString(COLOR_DIALOG_TITLE), initialBackground);

            if (color != null) {
                source.setBackground(color);

                logger.info("Current color: " + color);

                Scene.instance.changeColor(color);
            }

        }

        if (mouseEvent.getButton() == MouseEvent.BUTTON3) { // RIGHT MOUSE BUTTON
            Scene.instance.fill();
        }

        Scene.instance.getMainWindow().repaint();
    }

}