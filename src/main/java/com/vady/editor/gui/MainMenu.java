package com.vady.editor.gui;

import com.vady.editor.PropertiesHolder;
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

    private static final String MENU_FILL = "FILL";
    private static final String MENU_ITEM_INTERPOLATE = "INTERPOLATE";
    private static final String MENU_ITEM_FLAT_FILL = "FLAT_FILL";

    private static final String MENU_HELP = "HELP";
    private static final String MENU_ITEM_ABOUT = "ABOUT";


    public MainMenu() {
        init();
    }


    protected void init() {

        this.add(fileMenu());
        this.add(zoomMenu());
        this.add(fillMenu());
        this.add(helpMenu());

        this.setVisible(true);
    }

    private JMenu fileMenu() {
        JMenu menu = new JMenu(PropertiesHolder.instance.getLabels().getString(MENU_FILE));

        constructMenu(menu, new String[]{MENU_ITEM_NEW, "-", MENU_ITEM_EXIT});

        return menu;
    }

    private JMenu zoomMenu() {
        JMenu menu = new JMenu(PropertiesHolder.instance.getLabels().getString(MENU_ZOOM));

        constructMenu(menu, new String[]{MENU_ITEM_ZOOM_IN, MENU_ITEM_ZOOM_OUT});

        return menu;
    }

    private JMenu fillMenu() {
        JMenu menu = new JMenu(PropertiesHolder.instance.getLabels().getString(MENU_FILL));

        constructMenu(menu, new String[]{MENU_ITEM_INTERPOLATE, MENU_ITEM_FLAT_FILL});

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
        logger.info("EVENT FROM: " + ((JMenuItem) actionEvent.getSource()).getName());
    }
}
