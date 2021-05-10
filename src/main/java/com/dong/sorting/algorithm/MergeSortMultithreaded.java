package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;
import com.dong.sorting.util.Util;

public class MergeSortMultithreaded extends AbstractSort {
    private static int numOfThreads = 4;
    private static int interval = SortingAlgorithm.ARRAY_SIZE/numOfThreads;
    private Thread[] threads;

    public MergeSortMultithreaded(ArrayDrawing drawing, String timeComplexity, String spaceComplexity) {
        super(drawing, timeComplexity, spaceComplexity);
        threads = new Thread[numOfThreads];
    }

    public void sort(Element[] arr) throws InterruptedException {
        for (int i = 0; i < numOfThreads; ++i) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                try {
                    sortWithSleep(arr, finalI*interval, finalI*interval+interval-1, finalI == 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // when thread finishes it's job, it's supposed to send signal to .join() call
                // but it doesn't work really well in teavm, so we send interrupt signal instead
                Thread.currentThread().interrupt();
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        // .join() don't work perfectly after be translated into javascript
        // we're using interrupted to simulate join
        boolean interrupted = false;
        while (!interrupted) {
            interrupted = true;
            for (Thread t : threads) {
                t.join(1);
                interrupted = interrupted & t.isInterrupted();
            }
        }

        threads[0] = new Thread(() -> {
            try {
                merge(arr, 0, 49, true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.currentThread().interrupt();
        });

        threads[1] = new Thread(() -> {
            try {
                merge(arr, 50, 99, false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.currentThread().interrupt();
        });

        for (int i = 0; i < 2; ++i) {
            threads[i].start();
        }

        // .join() don't work perfectly after be translated into javascript
        // we're using interrupted to simulate join
        interrupted = false;
        while (!interrupted) {
            interrupted = true;

            for (int i = 0; i < 2; ++i) {
                threads[i].join(1);
                interrupted = interrupted & threads[i].isInterrupted();
            }
        }

        merge(arr, 0, 99, true);
    }

    private void sortWithSleep(Element[] arr, int left, int right, boolean drawEnabled) throws InterruptedException {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        sortWithSleep(arr, left, mid, drawEnabled);
        sortWithSleep(arr, mid+1, right, drawEnabled);

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
