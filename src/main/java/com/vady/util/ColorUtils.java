package com.vady.util;

import java.awt.*;
import java.util.Random;


public class ColorUtils {

    private static final float COLOR_COMPONENT_MAXIMUM = 255.0f;

    public static float int2float(int component) {
        return (float) component / COLOR_COMPONENT_MAXIMUM;
    }

    public static Color generateRandomColor() {
        Random random = new Random();

        float red = random.nextFloat();
        float green = random.nextFloat();
        float blue = random.nextFloat();

        return new Color(red, green, blue);
    }


}
