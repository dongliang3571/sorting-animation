package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;

import static com.dong.sorting.util.Util.getMax;

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

        int max = (int)getMax(arr);

        int[] count = new int[max+1];

        for (Element e : arr) {
            e.setHighlighted(true);
            drawing.drawWithSleep(arr);
            e.setHighlighted(false);
            count[(int)e.getValue()]++;
        }

        for (int i = 1; i < count.length; ++i) {
            count[i] = count[i-1] + count[i];
        }

        Element[] output = new Element[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            output[i] = new Element(arr[i].getValue());
        }

        for (int i = 0; i < arr.length; ++i) {
            int val = (int) arr[i].getValue();
            output[count[val]-1].setHighlighted(true);
            output[count[val]-1].setValue(val);
            drawing.drawWithSleep(output);
            output[count[val]-1].setHighlighted(false);
            count[val]--;
        }
    }
}
