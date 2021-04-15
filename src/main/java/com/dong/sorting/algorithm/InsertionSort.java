package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;
import com.dong.sorting.util.Util;

public class InsertionSort implements Sort {

    ArrayDrawing drawing;
    private String algoName;

    public InsertionSort(ArrayDrawing drawing) {
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

        for (int i = 1; i < arr.length; ++i) {
            Element cur = arr[i];
            cur.setHighlighted(true);
            this.drawing.drawWithSleep(arr, sleep);

            int backPtr = i;

            while (backPtr > 0 && cur.compareTo(arr[backPtr-1]) < 0) {

                arr[backPtr] = arr[backPtr-1];
                backPtr--;
            }

            arr[backPtr] = cur;
            this.drawing.drawWithSleep(arr, sleep);
            cur.setHighlighted(false);
        }

        this.drawing.draw(arr);
    }
}
