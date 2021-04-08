package com.dong.sorting.util;

public class Util {
    public static long getSleepTimeFromSpeed(int speed) {
        long sleep = 900;

        if (speed >= 11) {
            sleep = 1;
        } else {
            sleep = sleep/speed;
        }

        return sleep;
    }

}
