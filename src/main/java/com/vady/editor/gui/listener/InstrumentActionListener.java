package com.vady.editor.gui.listener;

import com.vady.editor.gui.GuiFactory;
import com.vady.paint.Scene;
import com.vady.paint.element.*;
import com.vady.paint.element.Polygon;
import com.vady.paint.element.Rectangle;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InstrumentActionListener implements ActionListener {

    private static Logger logger = Logger.getLogger(InstrumentActionListener.class);


    public void actionPerformed(ActionEvent actionEvent) {
        String event = ((Component) actionEvent.getSource()).getName();

        logger.info("EVENT: " + event);

        if (event == GuiFactory.INSTRUMENT_LINE) {
            Scene.instance.startDrawing(new Line(true));
        }

        if (event == GuiFactory.INSTRUMENT_RECTANGLE) {
            Scene.instance.startDrawing(new Rectangle(true));
        }

        if (event == GuiFactory.INSTRUMENT_TRIANGLE) {
            Scene.instance.startDrawing(new Triangle(true));
        }

        if (event == GuiFactory.INSTRUMENT_POLYGONE) {
            Scene.instance.startDrawing(new Polygon(true));
        }

        if (event == GuiFactory.INSTRUMENT_CIRCLE) {
            Scene.instance.startDrawing(new Circle(true));
        }

        Scene.instance.getMainWindow().repaint();
    }

}
