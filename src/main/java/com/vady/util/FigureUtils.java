package com.vady.util;

import com.vady.paint.element.Circle;
import com.vady.paint.element.Figure;
import com.vady.paint.element.Triangle;
import com.vady.paint.element.Vertex;

import java.awt.*;


public class FigureUtils {


    public static Figure createTriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        Figure result = new Triangle();

        result.setId(IdUtils.generate());

        Color color = Color.YELLOW;

        result.getVertices().add(new Vertex(x1, y1, color));
        result.getVertices().add(new Vertex(x2, y2, color));
        result.getVertices().add(new Vertex(x3, y3, color));

        return result;
    }

    public static Figure createInterpolatedTriangle() {
        Figure result = new Triangle();

        result.setId(IdUtils.generate());

        result.getVertices().add(new Vertex(0.1, 0.1, Color.YELLOW));
        result.getVertices().add(new Vertex(0.2, 0.5, Color.BLUE));
        result.getVertices().add(new Vertex(0.5, 0.1, Color.RED));

        return result;
    }

    public static Figure createCircle() {
        double radius = 0.3;

        Figure result = new Circle();

        for (double angle = 0.0; angle < 360; angle += 5) {
            double x = radius * Math.cos(angle * JoglUtils.ONE_DEGREE);
            double y = radius * Math.sin(angle * JoglUtils.ONE_DEGREE);

            result.getVertices().add(new Vertex(x, y, Color.RED));
        }


        return result;
    }

}
