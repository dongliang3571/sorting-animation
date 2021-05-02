package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;
import com.dong.sorting.util.Util;

public class QuickSort implements Sort {

    ArrayDrawing drawing;
    private String algoName;

    public QuickSort(ArrayDrawing drawing) {
        this.drawing = drawing;
        this.algoName = getClass().getSimpleName();
    }

    public String getAlgoName() {
        return algoName;
    }

    public void sort(Element[] arr, int speed) throws InterruptedException {
        long sleep = Util.getSleepTimeFromSpeed(speed);
        sortWithSleep(arr, 0, arr.length-1, sleep);
    }

    private void sortWithSleep(Element[] arr, int low, int high, long sleep) throws InterruptedException {
        if (arr == null || arr.length == 0 || arr.length == 1) return;
        if (high <= low) return;

        int pivot = partition(arr, low, high, sleep);

        sortWithSleep(arr, low, pivot-1, sleep);
        sortWithSleep(arr, pivot, high, sleep);
    }

    private int partition(Element[] arr, int low, int high, long sleep) throws InterruptedException {

        Element pivot = arr[low]; // take the leftmost element as pivot
        pivot.setHighlighted(true);

        int left = low;
        int right = high;

        while (left <= right) {
            while (arr[left].compareTo(pivot) < 0) {
                arr[left].setHighlighted(true);
                this.drawing.drawWithSleep(arr, sleep);
                arr[left].setHighlighted(false);
                ++left;
            }

            while (arr[right].compareTo(pivot) > 0) {
                arr[right].setHighlighted(true);
                this.drawing.drawWithSleep(arr, sleep);
                arr[right].setHighlighted(false);
                --right;
            }

            if (left <= right) {
                arr[left].setHighlighted(true);
                arr[right].setHighlighted(true);
                this.drawing.drawWithSleep(arr, sleep);
                Element tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                this.drawing.drawWithSleep(arr, sleep);
                arr[left].setHighlighted(false);
                arr[right].setHighlighted(false);

                ++left;
                --right;
            }
        }

        pivot.setHighlighted(false);
        // 6, 7, 5, 8, 1, 3, 4
        // 4, 7, 5, 8, 1, 3, 6
        // 4, 3, 5, 8, 1, 7, 6
        // 4, 3, 5, 1, 8, 7, 6
        return left;
    }
}
