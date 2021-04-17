package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;
import com.dong.sorting.util.Util;

public class BubbleSort implements Sort {

    ArrayDrawing drawing;
    private String algoName;

    public BubbleSort(ArrayDrawing drawing) {
        this.drawing = drawing;
        this.algoName = getClass().getSimpleName();
    }

    public String getAlgoName() {
        return algoName;
    }

    public void sort(Element[] arr, int speed) throws InterruptedException {
        long sleep = Util.getSleepTimeFromSpeed(speed);
        sortWithSleep(arr, sleep);
    }

    private void sortWithSleep(Element[] arr, long sleep) throws InterruptedException {
        if (arr == null || arr.length == 0 || arr.length == 1) return;

        boolean swapHappened = true;
        int len = arr.length;
        while (swapHappened) {
            swapHappened = false;

            for (int i = 1; i < len; ++i) {
                if (arr[i].compareTo(arr[i-1]) < 0) {
                    arr[i].setHighlighted(true);
                    this.drawing.drawWithSleep(arr, sleep);
                    arr[i].setHighlighted(false);

                    Element tmp = arr[i-1];
                    arr[i-1] = arr[i];
                    arr[i] = tmp;

                    arr[i].setHighlighted(true);
                    this.drawing.drawWithSleep(arr, sleep);
                    arr[i].setHighlighted(false);

                    swapHappened = true;
                }
            }

            --len;
        }
    }
}
