package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;

import java.util.Arrays;

import static com.dong.sorting.util.Util.getMax;

public class RadixSortLSD extends AbstractSort {
    public RadixSortLSD(ArrayDrawing drawing, String timeComplexity, String spaceComplexity) {
        super(drawing, timeComplexity, spaceComplexity);
    }

    @Override
    public void sort(Element[] arr) throws InterruptedException {
        sortAndDraw(arr);
    }

    private void sortAndDraw(Element[] arr) throws InterruptedException {
        if (arr == null || arr.length == 0 || arr.length == 1) return;

        int[] count = new int[10];
        Element[] output = new Element[arr.length];
        for (int j = 0; j < arr.length; ++j) {
            output[j] = new Element(arr[j].getValue());
        }

        int max = (int)getMax(arr);

        for (int i = 1; max/i > 0; i *= 10) {
            Arrays.fill(count, 0);
            for (int j = 0; j < arr.length; ++j) {
                int index = (int)arr[j].getValue() / i % 10;
                count[index]++;
            }

            for (int j = 1; j < count.length; ++j) {
                count[j] = count[j-1] + count[j];
            }

            // should start from the end of array
            // because we want the elements with greater index stay in the relative larger index
            for (int j = arr.length-1; j >= 0; --j) {
                int index = (int)arr[j].getValue() / i % 10;
                output[count[index]-1].setValue(arr[j].getValue());
                --count[index];
            }

            for (int j = 0; j < arr.length; ++j) {
                arr[j] = new Element(output[j].getValue());
            }

            drawing.drawWithSleep(output);
        }
    }
}
