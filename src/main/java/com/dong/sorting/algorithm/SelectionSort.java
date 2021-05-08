package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;
import com.dong.sorting.util.Util;

public class SelectionSort extends AbstractSort {

    public SelectionSort(ArrayDrawing drawing) {
        super(drawing);
    }

    public void sort(Element[] arr, int speed) throws InterruptedException {
        long sleep = Util.getSleepTimeFromSpeed(speed);
        sortWithSleep(arr, sleep);
    }

    private void sortWithSleep(Element[] arr, long sleep) throws InterruptedException {
        if (arr == null || arr.length == 0 || arr.length == 1) return;

        for (int i = 0; i < arr.length; ++i) {

            int minIndex = i;
            for (int j = i+1; j < arr.length; ++j) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                arr[minIndex].setHighlighted(true);
                arr[i].setHighlighted(true);
                this.drawing.drawWithSleep(arr, sleep);
                Element tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;

                this.drawing.drawWithSleep(arr, sleep);
                arr[minIndex].setHighlighted(false);
                arr[i].setHighlighted(false);
            }
        }
    }
}
