package com.vady.editor.gui;

import com.vady.editor.PropertiesHolder;
import com.vady.editor.gui.control.JoglDrawingListener;
import com.vady.editor.gui.listener.ColorMouseListener;
import com.vady.editor.gui.listener.DrawningPanelMotionListener;
import com.vady.editor.gui.listener.DrawningPanelMouseListener;
import com.vady.editor.gui.listener.DrawningPanelWheelListener;
import org.apache.log4j.Logger;

import javax.media.opengl.GLJPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;


public class GuiFactory {

    public static final Logger logger = Logger.getLogger(GuiFactory.class);


    public static final GuiFactory instance = new GuiFactory();


    public static final String INSTRUMENT_WIDTH = "INSTRUMENT_WIDTH";
    public static final String INSTRUMENT_HEIGHT = "INSTRUMENT_HEIGHT";

    public static final String INSTRUMENT_LINE = "LINE";
    public static final String INSTRUMENT_CIRCLE = "CIRCLE";
    public static final String INSTRUMENT_TRIANGLE = "TRIANGLE";
    public static final String INSTRUMENT_RECTANGLE = "RECTANGLE";
    public static final String INSTRUMENT_POLYGONE = "POLYGONE";

    private static final String INSTRUMENT_COLOR = "COLOR";
    public static final String DRAWING_NAMEL = "DRAWING_NAME";


    private GuiFactory() {

    }


    public Component colorInstrument(MouseListener mouseListener) {
        JPanel result = new JPanel();

        result.setPreferredSize(new Dimension(PropertiesHolder.instance.getProgramConfiguration().getInt(INSTRUMENT_WIDTH),
                PropertiesHolder.instance.getProgramConfiguration().getInt(INSTRUMENT_HEIGHT)));

        result.setName(INSTRUMENT_COLOR);

        result.addMouseListener(mouseListener);

        result.setBackground(Color.BLACK);

        return result;
    }

    public JPanel instrumentPanel(ActionListener actionListener) {
        JPanel result = new JPanel();

        result.setLayout(new GridLayout(8, 1));

        result.add(instrument(INSTRUMENT_LINE, actionListener));
        result.add(instrument(INSTRUMENT_CIRCLE, actionListener));
        result.add(instrument(INSTRUMENT_TRIANGLE, actionListener));
        result.add(instrument(INSTRUMENT_RECTANGLE, actionListener));
        result.add(instrument(INSTRUMENT_POLYGONE, actionListener));

        result.add(colorInstrument(new ColorMouseListener()));

        return result;
    }

    public JButton instrument(String name, ActionListener actionListener) {
        JButton result = new JButton(name);

        result.setPreferredSize(new Dimension(PropertiesHolder.instance.getProgramConfiguration().getInt(INSTRUMENT_WIDTH),
                PropertiesHolder.instance.getProgramConfiguration().getInt(INSTRUMENT_HEIGHT)));

        result.setName(name);
        result.addActionListener(actionListener);

        result.setFocusable(false);

        return result;
    }

    public JPanel mainPanel(ActionListener actionListener) {
        JPanel result = new JPanel();

        result.setLayout(new BorderLayout());

        result.add(instrumentPanel(actionListener), BorderLayout.LINE_START);
        result.add(drawingPanel(), BorderLayout.CENTER);

        return result;
    }

    public JPanel drawingPanel() {
        GLJPanel result = new GLJPanel();

        result.addGLEventListener(new JoglDrawingListener());

        result.addMouseListener(new DrawningPanelMouseListener());
        result.addMouseWheelListener(new DrawningPanelWheelListener());
        result.addMouseMotionListener(new DrawningPanelMotionListener());

        result.setVisible(true);

        return result;
    }


}
