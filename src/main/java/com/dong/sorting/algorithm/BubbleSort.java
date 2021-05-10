package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;
import com.dong.sorting.util.Util;

public class BubbleSort extends AbstractSort {

    public BubbleSort(ArrayDrawing drawing, String timeComplexity, String spaceComplexity) {
        super(drawing, timeComplexity, spaceComplexity);
    }

    @Override
    public void sort(Element[] arr) throws InterruptedException {
        sortAndDraw(arr);
    }

    private void sortAndDraw(Element[] arr) throws InterruptedException {
        if (arr == null || arr.length == 0 || arr.length == 1) return;

        boolean swapHappened = true;
        int len = arr.length;
        while (swapHappened) {
            swapHappened = false;

            for (int i = 1; i < len; ++i) {
                if (arr[i].compareTo(arr[i-1]) < 0) {
                    Element tmp = arr[i-1];
                    arr[i-1] = arr[i];
                    arr[i] = tmp;

                    arr[i].setHighlighted(true);
                    this.drawing.drawWithSleep(arr);
                    arr[i].setHighlighted(false);

                    swapHappened = true;
                }
            }

            --len;
        }
    }
}
