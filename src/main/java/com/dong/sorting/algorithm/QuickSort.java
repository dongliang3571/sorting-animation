package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;

public class QuickSort extends AbstractSort {

    public QuickSort(ArrayDrawing drawing, String timeComplexity, String spaceComplexity) {
        super(drawing, timeComplexity, spaceComplexity);
    }

    public void sort(Element[] arr) throws InterruptedException {
        sortAndDraw(arr, 0, arr.length-1);
    }

    private void sortAndDraw(Element[] arr, int low, int high) throws InterruptedException {
        if (arr == null || arr.length == 0 || arr.length == 1) return;
        if (high <= low) return;

        int pivot = partition(arr, low, high);

        sortAndDraw(arr, low, pivot-1);
        sortAndDraw(arr, pivot, high);
    }

    private int partition(Element[] arr, int low, int high) throws InterruptedException {

        Element pivot = arr[low]; // take the leftmost element as pivot
        pivot.setHighlighted(true);

        int left = low;
        int right = high;

        while (left <= right) {
            while (arr[left].compareTo(pivot) < 0) {
                arr[left].setHighlighted(true);
                this.drawing.drawWithSleep(arr);
                arr[left].setHighlighted(false);
                ++left;
            }

            while (arr[right].compareTo(pivot) > 0) {
                arr[right].setHighlighted(true);
                this.drawing.drawWithSleep(arr);
                arr[right].setHighlighted(false);
                --right;
            }

            if (left <= right) {
                arr[left].setHighlighted(true);
                arr[right].setHighlighted(true);
                this.drawing.drawWithSleep(arr);
                Element tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                this.drawing.drawWithSleep(arr);
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
