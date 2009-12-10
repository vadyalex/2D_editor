package com.vady.paint.element;


import com.vady.util.ColorUtils;

import java.awt.*;


public class Vertex {

    private double x;
    private double y;

    private Color color;


    public Vertex() {

    }

    public Vertex(float x, float y) {
        setX(x);
        setY(y);

        setColor(Color.BLACK);
    }

    public Vertex(double x, double y, Color color) {
        setX(x);
        setY(y);

        setColor(color);
    }

    public void setCoordinate(double newX, double newY) {
        setX(newX);
        setY(newY);
    }

    public void setCoordinate(Point point) {
        setX(point.getX());
        setY(point.getY());
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public float getColorRed() {
        return ColorUtils.int2float(color.getRed());
    }

    public float getColorGreen() {
        return ColorUtils.int2float(color.getGreen());
    }

    public float getColorBlue() {
        return ColorUtils.int2float(color.getBlue());
    }

    @Override
    public String toString() {
        return "[" + x + ";" + y + "]";
    }
}
