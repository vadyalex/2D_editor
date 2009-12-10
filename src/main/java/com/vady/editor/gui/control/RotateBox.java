package com.vady.editor.gui.control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RotateBox extends JDialog implements ActionListener {

    private JPanel myPanel = null;
    private JButton yesButton = null;
    private JButton noButton = null;
    private boolean answer = false;

    public boolean getAnswer() {
        return answer;
    }

    public RotateBox(JFrame frame, boolean modal, String myMessage) {
        super(frame, modal);

        myPanel = new JPanel();
        add(myPanel);

        myPanel.add(new JLabel(myMessage));
        yesButton = new JButton("Yes");
        yesButton.addActionListener(this);
        myPanel.add(yesButton);
        noButton = new JButton("No");
        noButton.addActionListener(this);
        myPanel.add(noButton);
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (yesButton == e.getSource()) {
            System.err.println("User chose yes.");
            answer = true;
            setVisible(false);
        } else if (noButton == e.getSource()) {
            System.err.println("User chose no.");
            answer = false;
            setVisible(false);
        }
    }

}
