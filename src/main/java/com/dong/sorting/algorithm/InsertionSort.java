package com.dong.sorting.algorithm;

import com.dong.sorting.ArrayDrawing;
import com.dong.sorting.Element;


public class InsertionSort implements SortingAlgorithm {

    ArrayDrawing drawing;

    public InsertionSort(ArrayDrawing drawing) {
        this.drawing = drawing;
    }

    public void sort(Element[] arr) throws InterruptedException {
        if (arr == null || arr.length == 0 || arr.length == 1) return;

        for (int i = 1; i < arr.length; ++i) {
            Element cur = arr[i];

            int backPtr = i;

            while (backPtr > 0 && cur.getValue() < arr[backPtr-1].getValue()) {

                arr[backPtr] = arr[backPtr-1];
                backPtr--;
            }

            arr[backPtr] = cur;
            this.drawing.draw(arr);
        }
    }
}
