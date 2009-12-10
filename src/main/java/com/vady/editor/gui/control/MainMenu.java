package com.vady.editor.gui.control;

import com.vady.editor.PropertiesHolder;
import com.vady.editor.gui.listener.KeyboardListener;
import com.vady.paint.Scene;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JMenuBar implements ActionListener {

    public static Logger logger = Logger.getLogger(MainMenu.class);


    private static final String MENU_FILE = "FILE";
    private static final String MENU_ITEM_NEW = "NEW";
    private static final String MENU_ITEM_EXIT = "EXIT";


    private static final String MENU_ZOOM = "ZOOM";
    private static final String MENU_ITEM_ZOOM_IN = "ZOOM_IN";
    private static final String MENU_ITEM_ZOOM_OUT = "ZOOM_OUT";
    private static final String MENU_ITEM_ZOOM_ORIGINAL = "ZOOM_ORIGINAL";

    private static final String MENU_EDIT = "EDIT";
    private static final String MENU_ITEM_INTERPOLATE = "INTERPOLATE";
    private static final String MENU_ITEM_FLAT_FILL = "FLAT_FILL";
    private static final String MENU_ITEM_ROTATE = "ROTATE";
    private static final String MENU_ITEM_DELETE = "DELETE";

    private static final String MENU_HELP = "HELP";
    private static final String MENU_ITEM_ABOUT = "ABOUT";
    private static final String ABOUT_CAPTION = "ABOUT_CAPTION";
    private static final String ABOUT_MESSAGE = "ABOUT_MESSAGE";

    private JFrame owner;


    private MainMenu() {

    }

    public MainMenu(JFrame owner) {
        this.owner = owner;

        init();
    }


    protected void init() {

        this.add(fileMenu());
        this.add(zoomMenu());
        this.add(editMenu());
        this.add(helpMenu());

        this.addKeyListener(new KeyboardListener());

        this.setVisible(true);
    }

    private JMenu fileMenu() {
        JMenu menu = new JMenu(PropertiesHolder.instance.getLabels().getString(MENU_FILE));

        constructMenu(menu, new String[]{MENU_ITEM_NEW, "-", MENU_ITEM_EXIT});

        return menu;
    }

    private JMenu zoomMenu() {
        JMenu menu = new JMenu(PropertiesHolder.instance.getLabels().getString(MENU_ZOOM));

        constructMenu(menu, new String[]{MENU_ITEM_ZOOM_IN, MENU_ITEM_ZOOM_OUT, MENU_ITEM_ZOOM_ORIGINAL});

        return menu;
    }

    private JMenu editMenu() {
        JMenu menu = new JMenu(PropertiesHolder.instance.getLabels().getString(MENU_EDIT));

        constructMenu(menu, new String[]{MENU_ITEM_INTERPOLATE, MENU_ITEM_FLAT_FILL, MENU_ITEM_ROTATE, MENU_ITEM_DELETE});

        return menu;
    }

    private JMenu helpMenu() {
        JMenu menu = new JMenu(PropertiesHolder.instance.getLabels().getString(MENU_HELP));

        constructMenu(menu, new String[]{MENU_ITEM_ABOUT});

        return menu;
    }

    protected void constructMenu(JMenu menu, String[] names) {
        for (String name : names) {
            if (name.equals("-")) {
                menu.addSeparator();
            } else {
                menu.add(menuItem(name, PropertiesHolder.instance.getLabels().getString(name)));
            }
        }
    }

    protected JMenuItem menuItem(String name, String text) {
        JMenuItem result = new JMenuItem();

        result.setName(name);
        result.setText(text);

        result.addActionListener(this);

        return result;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        String event = ((JMenuItem) actionEvent.getSource()).getName();

        logger.info("EVENT: " + event);

        if (event == MENU_ITEM_EXIT) {
            System.exit(0);
        }

        if (event == MENU_ITEM_ROTATE) {

        }

        if (event == MENU_ITEM_DELETE) {
            Scene.instance.deleteSelectedFigure();
        }

        if (event == MENU_ITEM_ZOOM_IN) {
            Scene.instance.zoom(5);
        }

        if (event == MENU_ITEM_ZOOM_OUT) {
            Scene.instance.zoom(-5);
        }

        if (event == MENU_ITEM_ZOOM_ORIGINAL) {
            Scene.instance.zoomOriginal();
        }

        if (event == MENU_ITEM_FLAT_FILL) {
            Scene.instance.fill();
        }

        if (event == MENU_ITEM_INTERPOLATE) {
            Scene.instance.randomInterpolation();
        }

        if (event == MENU_ITEM_ABOUT) {
            JOptionPane.showMessageDialog(null,
                    PropertiesHolder.instance.getLabels().getString(ABOUT_MESSAGE),
                    PropertiesHolder.instance.getLabels().getString(ABOUT_CAPTION),
                    JOptionPane.INFORMATION_MESSAGE);

        }

        Scene.instance.getMainWindow().repaint();
    }
}
