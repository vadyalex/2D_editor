package com.vady.editor.gui;

import org.apache.log4j.Logger;

import javax.swing.*;


public class MainWindow extends JFrame {


    public static Logger logger = Logger.getLogger(MainWindow.class);

    public MainWindow() {

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        this.setJMenuBar(new MainMenu());

    }

}
