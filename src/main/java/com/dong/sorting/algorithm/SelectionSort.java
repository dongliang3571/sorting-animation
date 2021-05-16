package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;

/** 1. Linear scan to find the smallest element
 *  2. Swap it with the current element
 *  3. Continue until scan through entire array
 */
public class SelectionSort extends AbstractSort {

    public SelectionSort(ArrayDrawing drawing, String timeComplexity, String spaceComplexity) {
        super(drawing, timeComplexity, spaceComplexity);
    }

    public void sort(Element[] arr) throws InterruptedException {
        sortAndDraw(arr);
    }

    private void sortAndDraw(Element[] arr) throws InterruptedException {
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
                this.drawing.drawWithSleep(arr);
                Element tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;

                this.drawing.drawWithSleep(arr);
                arr[minIndex].setHighlighted(false);
                arr[i].setHighlighted(false);
            }
        }
    }
}
