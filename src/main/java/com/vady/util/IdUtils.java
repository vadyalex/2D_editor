package com.vady.util;


public class IdUtils {

    private static int id = 0;

    public static int generate() {
        return id++;
    }

}
