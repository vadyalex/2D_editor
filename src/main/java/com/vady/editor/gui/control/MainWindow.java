package com.vady.editor.gui.control;

import com.vady.editor.PropertiesHolder;
import com.vady.editor.gui.GuiFactory;
import com.vady.editor.gui.Property;
import com.vady.editor.gui.listener.KeyboardListener;
import com.vady.paint.Scene;
import org.apache.log4j.Logger;

import javax.swing.*;


public class MainWindow extends JFrame {


    public static Logger logger = Logger.getLogger(MainWindow.class);


    public MainWindow() {
        initGuiElements();

        Scene.instance.setMainWindow(this);
    }

    private void initGuiElements() {
        this.setJMenuBar(new MainMenu(this));

        this.add(GuiFactory.instance.mainPanel());

        String title = PropertiesHolder.instance.getLabels().getString(Property.MAIN_WINDOW_TITLE.toString());
        this.setTitle(title);

        int width = PropertiesHolder.instance.getProgramConfiguration().getInt(Property.MAIN_WINDOW_WIDTH.toString());
        int height = PropertiesHolder.instance.getProgramConfiguration().getInt(Property.MAIN_WINDOW_HEIGHT.toString());
        this.setSize(width, height);

        this.addKeyListener(new KeyboardListener());

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // center
        this.setVisible(true);
    }


}
