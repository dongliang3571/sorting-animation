package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;

import java.util.ArrayList;
import java.util.List;

public class MergeSortMultithreaded extends AbstractSort {
    private List<Thread> threads;

    public MergeSortMultithreaded(ArrayDrawing drawing, String timeComplexity, String spaceComplexity) {
        super(drawing, timeComplexity, spaceComplexity);
        threads = new ArrayList<>();
    }

    public void sort(Element[] arr) throws InterruptedException {
        sortAndDraw(arr, 0, arr.length-1, true);
    }

    private void sortAndDraw(Element[] arr, int left, int right, boolean drawEnabled) throws InterruptedException {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        Thread t1 = new Thread(() -> {
            try {
                sortAndDraw(arr, left, mid, drawEnabled);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Thread.currentThread().interrupt();
        });

        Thread t2 = new Thread(() -> {
            try {
                sortAndDraw(arr, mid+1, right, drawEnabled);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Thread.currentThread().interrupt();
        });

        threads.add(t1);
        threads.add(t2);
        t1.start();
        t2.start();

        // .join() don't work perfectly after be translated into javascript
        // we're using interrupted to simulate join
        boolean interrupted = false;
        while (!interrupted) {
            interrupted = true;
            t1.join(1);
            t2.join(1);
            interrupted = interrupted & t1.isInterrupted();
            interrupted = interrupted & t2.isInterrupted();
        }

        merge(arr, left, right, drawEnabled);
    }

    private void merge(Element[] arr, int left, int right, boolean drawEnabled) throws InterruptedException {
        Element[] tmp = new Element[right-left+1];

        int mid = left + (right - left) / 2;
        int ptr1 = left;
        int ptr2 = mid+1;
        int ptr3 = 0;
        while (ptr1 <= mid && ptr2 <= right) {
            int c = arr[ptr1].compareTo(arr[ptr2]);
            if (c < 0) {
                tmp[ptr3] = arr[ptr1];
                ++ptr1;
            } else {
                tmp[ptr3] = arr[ptr2];
                ++ptr2;
            }

            ++ptr3;
        }

        while (ptr1 <= mid) {
            tmp[ptr3] = arr[ptr1];
            ++ptr1;
            ++ptr3;
        }

        while (ptr2 <= right) {
            tmp[ptr3] = arr[ptr2];
            ++ptr2;
            ++ptr3;
        }

        ptr3 = 0;
        for (int i = left; i <= right; ++i, ++ptr3) {
            arr[i] = tmp[ptr3];
            arr[i].setHighlighted(true);
            if (drawEnabled) {
                this.drawing.drawWithSleep(arr);
            } else {
                this.drawing.sleepOnly();
            }
            arr[i].setHighlighted(false);
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();

        for (Thread t : threads) {
            if (t != null) t.interrupt();
        }
    }
}
