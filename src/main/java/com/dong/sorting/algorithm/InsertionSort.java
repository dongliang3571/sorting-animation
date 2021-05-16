package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;

/**
 * 1. Scan through the array, for each element in the array, we compare it with the previous element,
 * 2. if the current element is smaller, we move the previous element to the current position, and move the cursor back by 1
 * 3. Repeating the step 2 util tge current element is larger
 * 4. Repeating step 2 and 3 for every element in the array
 */
public class InsertionSort extends AbstractSort {

    public InsertionSort(ArrayDrawing drawing, String timeComplexity, String spaceComplexity) {
        super(drawing, timeComplexity, spaceComplexity);
    }

    public void sort(Element[] arr) throws InterruptedException {
        sortAndDraw(arr);
    }

    private void sortAndDraw(Element[] arr) throws InterruptedException {
        if (arr == null || arr.length == 0 || arr.length == 1) return;

        for (int i = 1; i < arr.length; ++i) {
            Element cur = arr[i];
            int backPtr = i;

            while (backPtr > 0 && cur.compareTo(arr[backPtr-1]) < 0) {
                arr[backPtr].setHighlighted(true);
                this.drawing.drawWithSleep(arr);
                arr[backPtr].setHighlighted(false);

                arr[backPtr] = arr[backPtr-1];
                backPtr--;
            }

            arr[backPtr] = cur;
            this.drawing.drawWithSleep(arr);
        }

        this.drawing.draw(arr);
    }
}
