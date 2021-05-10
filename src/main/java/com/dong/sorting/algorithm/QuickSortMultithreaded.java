package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;
import com.dong.sorting.util.Util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QuickSortMultithreaded extends AbstractSort {

    public static int threadLevel = 3;
    private Queue<int[]> intervals;
    private List<Thread> threads;

    public QuickSortMultithreaded(ArrayDrawing drawing, String timeComplexity, String spaceComplexity) {
        super(drawing, timeComplexity, spaceComplexity);
        intervals = new LinkedList<>();
        threads = new ArrayList<>();
    }

    public void sort(Element[] arr, int speed) throws InterruptedException {
        long sleep = Util.getSleepTimeFromSpeed(speed);
        sortWithSleep(arr, 0, arr.length-1, sleep);
    }

    private void sortWithSleep(Element[] arr, int low, int high, long sleep) throws InterruptedException {
        if (arr == null || arr.length == 0 || arr.length == 1) return;
        if (high <= low) return;

        int pivot = partition(arr, low, high, sleep);

        Thread t1 = new Thread(() -> {
            try {
                sortWithSleep(arr, low, pivot-1, sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        Thread t2 = new Thread(() -> {
            try {
                sortWithSleep(arr, pivot, high, sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }



    private int partition(Element[] arr, int low, int high, long sleep) throws InterruptedException {
        if (low == high) return low;
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
