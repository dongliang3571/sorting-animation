package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;

import static com.dong.sorting.util.Util.getMax;

/**
 * Can only work with positive integers
 *
 * Input data: 1, 4, 1, 2, 7, 5, 2
 *   1) Take a count array to store the count of each unique object.
 *   Index:     0  1  2  3  4  5  6  7  8  9
 *   Count:     0  2  2  0   1  1  0  1  0  0
 *
 *   2) Modify the count array such that each element at each index
 *   stores the sum of previous counts.
 *   Index:     0  1  2  3  4  5  6  7  8  9
 *   Count:     0  2  4  4  5  6  6  7  7  7
 *
 * The modified count array indicates the position of each object in
 * the output sequence.
 *
 *   3) Output each object from the input sequence followed by
 *   decreasing its count by 1.
 *   Process the input data: 1, 4, 1, 2, 7, 5, 2. Position of 1 is 2.
 *   Put data 1 at index 2 in output. Decrease count by 1 to place
 *   next data 1 at an index 1 smaller than this index.
 */
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
