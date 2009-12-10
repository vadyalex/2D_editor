package com.vady.util;

import com.vady.paint.element.Circle;
import com.vady.paint.element.Figure;
import com.vady.paint.element.Polygon;
import com.vady.paint.element.Vertex;

import java.awt.*;


public class FigureUtils {


    public static Figure createTriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        Figure result = new Polygon();

        result.setId(Utils.generate());

        Color color = Color.YELLOW;

        result.getVertices().add(new Vertex(x1, y1, color));
        result.getVertices().add(new Vertex(x2, y2, color));
        result.getVertices().add(new Vertex(x3, y3, color));

        return result;
    }

    public static Figure createInterpolatedTriangle() {
        Figure result = new Polygon();

        result.setId(Utils.generate());

        result.getVertices().add(new Vertex(-0.5f, 0.0f, Color.YELLOW));
        result.getVertices().add(new Vertex(0.0f, 0.5f, Color.BLUE));
        result.getVertices().add(new Vertex(0.5f, 0.0f, Color.RED));

        return result;
    }

    public static Figure createCircle() {
        double radius = 0.3;

        Figure result = new Circle();


        for (double angle = 0.0; angle < 360; angle += 5) {
            double x = radius * Math.cos(angle * Math.PI / 180);
            double y = radius * Math.sin(angle * Math.PI / 180);

            result.getVertices().add(new Vertex(x, y, Color.RED));
        }


        return result;
    }

}
