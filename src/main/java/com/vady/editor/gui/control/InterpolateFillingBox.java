package com.vady.editor.gui.control;

import com.vady.paint.element.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


//TODO implement it later to give a user apportunity to choose every vertex color!
@Deprecated
public class InterpolateFillingBox extends JDialog implements ActionListener {

    private JButton okButton = null;
    private JButton cancelButton = null;

    private boolean answer = false;

    private List<Color> colorList;
    private Figure figure;


    public InterpolateFillingBox(JFrame frame, Figure figure) {
        super(frame, true);

        this.figure = figure;

        colorList = new ArrayList<Color>();

        add(createVertexPanel());

        setVisible(true);
        add(createControlPanel());

        pack();

        setLocationRelativeTo(frame);
    }

    private JPanel createVertexPanel() {
        JPanel result = new JPanel();

        result.setLayout(new GridLayout(figure.getVertices().size(), 1));

        for (int i = 0; i < figure.getVertices().size(); i++) {
            Component colorInstrument = new JPanel();

            colorInstrument.setBackground(figure.getVertices().get(i).getColor());
            colorInstrument.setName(String.valueOf(i));

            result.add(colorInstrument);
        }

        result.setVisible(true);

        return result;
    }

    private JPanel createControlPanel() {
        JPanel result = new JPanel();
        add(result);

        okButton = new JButton("Ok");
        okButton.addActionListener(this);
        result.add(okButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        result.add(cancelButton);

        return result;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void actionPerformed(ActionEvent e) {

        if (okButton == e.getSource()) {
            answer = true;
            makeChanges();
            setVisible(false);
        } else if (cancelButton == e.getSource()) {
            answer = false;
            setVisible(false);
        }
    }

    private void makeChanges() {
        for (int i = 0; i < colorList.size(); i++) {
            figure.getVertices().get(i).setColor(colorList.get(i));
        }
    }

    class ColorListener extends MouseAdapter {
        public void mouseClicked(MouseEvent mouseEvent) {
            JComponent source = (JComponent) mouseEvent.getSource();

            Color initialBackground = source.getBackground();
            Color color = JColorChooser.showDialog(null, "Choose color for vertex!", initialBackground);

            if (color != null) {
                source.setBackground(color);

                int i = Integer.valueOf(source.getName());

                colorList.set(i, color);
            }

            source.repaint();

        }
    }

}