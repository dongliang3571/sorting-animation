package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;
import com.dong.sorting.util.Util;

public class HeapSortRecursive extends AbstractSort {
    public HeapSortRecursive(ArrayDrawing drawing, String timeComplexity, String spaceComplexity) {
        super(drawing, timeComplexity, spaceComplexity);
    }

    public void sort(Element[] arr) throws InterruptedException {
        int n = arr.length;
        buildHeap(arr, n);
        sortAndDraw(arr);

        drawing.drawWithSleep(arr);
    }

    private void sortAndDraw(Element[] arr) throws InterruptedException {

        int n = arr.length;

        for (int i = n - 1; i > 0; --i) {
            // i is the size of the current heap
            swap(arr, i, 0); // swap the max in the heap with the last element in the heap
            heapify(arr, i, 0); // siftdown heapify for max heap
            drawing.drawWithSleep(arr);
        }
    }

    private void heapify(Element[] arr, int n, int i) throws InterruptedException {
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
            swap(arr, largest, i);

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    private void swap(Element[] arr, int i1, int i2) throws InterruptedException {
        arr[i1].setHighlighted(true);
        arr[i2].setHighlighted(true);
        drawing.drawWithSleep(arr);
        Element tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;

        arr[i1].setHighlighted(false);
        arr[i2].setHighlighted(false);
        drawing.drawWithSleep(arr);
    }

    private void buildHeap(Element[] arr, int n) throws InterruptedException {

        // Index of last non-leaf node
        int startIdx = (n / 2) - 1;

        // Perform reverse level order traversal
        // from last non-leaf node and heapify
        // each node
        for (int i = startIdx; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }
}
