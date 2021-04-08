package com.dong.sorting.util;

public class Util {
    public static long getSleepTimeFromSpeed(int speed) {
        long sleep = 1000;

        if (speed >= 51) {
            sleep = 1;
        } else {
            sleep = sleep/speed;
        }

        return sleep;
    }

}
