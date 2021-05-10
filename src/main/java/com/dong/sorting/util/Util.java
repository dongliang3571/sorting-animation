package com.dong.sorting.util;

import com.dong.sorting.drawing.ArrayDrawing;

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

}
