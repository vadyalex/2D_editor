package com.vady.editor.gui.control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InputDialog extends JDialog implements ActionListener {

    private JButton okButton = null;
    private JButton cancelButton = null;

    private boolean answer = false;

    private JSpinner input;


    public InputDialog(String title, double min, double max, double step) {
        setModal(true);
        setResizable(false);

        setTitle(title);

        JPanel panel = new JPanel();

        JLabel label = new JLabel("Value :");
        panel.add(label);

        input = new JSpinner(new SpinnerNumberModel(0, min, max, step));
        panel.add(input);


        okButton = new JButton("Ok");
        okButton.addActionListener(this);

        panel.add(okButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);

        panel.add(cancelButton);

        getContentPane().add(panel, BorderLayout.SOUTH);

        pack();

        setVisible(true);


    }

    public boolean getAnswer() {
        return answer;
    }

    public Object getValue() {
        return input.getValue();
    }

    public void actionPerformed(ActionEvent e) {

        if (okButton == e.getSource()) {
            answer = true;
            setVisible(false);
        } else if (cancelButton == e.getSource()) {
            answer = false;
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        InputDialog inputDialog = new InputDialog("HELLO!", -100, 360, 0.1);
        //inputDialog.
    }
}