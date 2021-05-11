package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;

// Can only work with positive integers
public class CountingSort extends AbstractSort {

    public CountingSort(ArrayDrawing drawing, String timeComplexity, String spaceComplexity) {
        super(drawing, timeComplexity, spaceComplexity);
    }

    @Override
    public void sort(Element[] arr) throws InterruptedException {
        sortAndDraw(arr);
    }

    private void sortAndDraw(Element[] arr) throws InterruptedException {

        int max = 0;
        for (Element e : arr) {
            max = Math.max(max, (int)e.getValue());
        }

        int[] count = new int[max+1];

        for (Element e : arr) {
            e.setHighlighted(true);
            drawing.drawWithSleep(arr);
            e.setHighlighted(false);
            count[(int)e.getValue()]++;
        }

        int curIndex = 0;

        for (int i = 0; i < count.length; ++i) {
            while (count[i] != 0) {
                arr[curIndex].setHighlighted(true);
                arr[curIndex].setValue(i);
                drawing.drawWithSleep(arr);
                arr[curIndex].setHighlighted(false);
                count[i]--;
                curIndex++;
            }
        }
    }
}
