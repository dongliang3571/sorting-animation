package com.dong.sorting.util;

public class Util {
    public static long getSleepTimeFromSpeed(int speed) {
        long sleep = 500;

        if (speed >= 21) {
            sleep = 10;
        } else {
            sleep = sleep/speed;
        }

        return sleep;
    }

}
