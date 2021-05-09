package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;
import com.dong.sorting.util.Util;

public class HeapSortRecursive extends AbstractSort {
    public HeapSortRecursive(ArrayDrawing drawing, String timeComplexity, String spaceComplexity) {
        super(drawing, timeComplexity, spaceComplexity);
    }

    public void sort(Element[] arr, int speed) throws InterruptedException {
        long sleep = Util.getSleepTimeFromSpeed(speed);
        int n = arr.length;
        buildHeap(arr, n, sleep);
        sortWithSleep(arr, sleep);

        drawing.drawWithSleep(arr, 1000);
    }

    private void sortWithSleep(Element[] arr, long sleep) throws InterruptedException {

        int n = arr.length;

        for (int i = n - 1; i > 0; --i) {
            // i is the size of the current heap
            swap(arr, i, 0, sleep); // swap the max in the heap with the last element in the heap
            heapify(arr, i, 0, sleep); // siftdown heapify for max heap
            drawing.drawWithSleep(arr, sleep);
        }
    }

    private void heapify(Element[] arr, int n, int i, long sleep) throws InterruptedException {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l].compareTo(arr[largest]) > 0)
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r].compareTo(arr[largest]) > 0)
            largest = r;

        // If largest is not root
        if (largest != i) {
            swap(arr, largest, i, sleep);

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest, sleep);
        }
    }

    private void swap(Element[] arr, int i1, int i2, long sleep) throws InterruptedException {
        arr[i1].setHighlighted(true);
        arr[i2].setHighlighted(true);
        drawing.drawWithSleep(arr, sleep);
        Element tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;

        arr[i1].setHighlighted(false);
        arr[i2].setHighlighted(false);
        drawing.drawWithSleep(arr, sleep);
    }

    private void buildHeap(Element[] arr, int n, long sleep) throws InterruptedException {

        // Index of last non-leaf node
        int startIdx = (n / 2) - 1;

        // Perform reverse level order traversal
        // from last non-leaf node and heapify
        // each node
        for (int i = startIdx; i >= 0; i--) {
            heapify(arr, n, i, sleep);
        }
    }
}
