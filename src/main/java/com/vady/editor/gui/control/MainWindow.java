package com.vady.editor.gui.control;

import com.vady.editor.PropertiesHolder;
import com.vady.editor.gui.GuiFactory;
import com.vady.paint.Scene;
import com.vady.paint.element.Circle;
import com.vady.paint.element.Line;
import com.vady.paint.element.Triangle;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainWindow extends JFrame implements ActionListener {


    public static Logger logger = Logger.getLogger(MainWindow.class);


    private static final String WINDOW_TITLE = "MAIN_WINDOW_TITLE";

    private static final String WINDOW_WIDTH = "MAIN_WINDOW_WIDTH";
    private static final String WINDOW_HEIGHT = "MAIN_WINDOW_HEIGHT";


    public MainWindow() {
        initGuiElements();

        Scene.instance.setMainWindow(this);
    }

    private void initGuiElements() {
        this.setJMenuBar(new MainMenu(this));

        this.add(GuiFactory.instance.mainPanel(this));

        this.setTitle(PropertiesHolder.instance.getLabels().getString(WINDOW_TITLE));

        this.setSize(PropertiesHolder.instance.getProgramConfiguration().getInt(WINDOW_WIDTH),
                PropertiesHolder.instance.getProgramConfiguration().getInt(WINDOW_HEIGHT));

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // center
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent actionEvent) {   // TODO move to separate class
        String event = ((Component) actionEvent.getSource()).getName();

        if (event == GuiFactory.INSTRUMENT_LINE) {
            Scene.instance.startDrawing(new Line(true));
        }

        if (event == GuiFactory.INSTRUMENT_RECTANGLE) {
            Scene.instance.startDrawing(new com.vady.paint.element.Rectangle(true));
        }

        if (event == GuiFactory.INSTRUMENT_TRIANGLE) {
            Scene.instance.startDrawing(new Triangle(true));
        }

        if (event == GuiFactory.INSTRUMENT_POLYGONE) {
            Scene.instance.startDrawing(new com.vady.paint.element.Polygon(true));
        }

        if (event == GuiFactory.INSTRUMENT_CIRCLE) {
            Scene.instance.startDrawing(new Circle(true));
        }

        logger.info("EVENT: " + event);

        Scene.instance.getMainWindow().repaint();
    }

}
