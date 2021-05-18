package com.dong.sorting.util;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;

public class Util {
    public static long getSleepTimeFromSpeed(int speed) {
        long sleep = 200;

        if (speed >= ArrayDrawing.DEFAULT_SPEED_LEVELS) {
            sleep = 2;
        } else {
            sleep = sleep/speed;
        }

        return sleep;
    }

    public static double getMax(Element[] arr) {
        double max = 0;

        for (int i = 0; i < arr.length; ++i) {
            max = Math.max(arr[i].getValue(), max);
        }

        return max;
    }

    public static double getMax(Element[] arr, int low , int high) {
        double max = 0;

        for (int i = low; i < high; ++i) {
            max = Math.max(arr[i].getValue(), max);
        }

        return max;
    }
}
