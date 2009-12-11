package com.vady.editor.gui.listener;

import com.vady.paint.Scene;
import org.apache.log4j.Logger;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyboardListener extends KeyAdapter {

    public static final Logger logger = Logger.getLogger(KeyboardListener.class);


    /*
    TODO: doesn't work on MAC OS X - check in other systems? keycode is allways 0, but keyboard event accurs correctly on ay key typed
     */
    public void keyTyped(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();

        logger.info("Key typed! " + code);

        if (code == KeyEvent.VK_DELETE || code == KeyEvent.VK_BACK_SPACE) {
            Scene.instance.deleteSelectedFigure();
        }

        Scene.instance.getMainWindow().repaint();
    }
}
