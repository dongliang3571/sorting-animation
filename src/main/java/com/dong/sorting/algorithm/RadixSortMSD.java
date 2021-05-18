package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;

import static com.dong.sorting.util.Util.getMax;

/**
 * 1. From the most significant digit, we do a counting sort to break the array into buckets
 * 2. Then we recursively process each bucket, as we're going deeper into the recursion,
 *    we need to move to next less significant digit
 * 3. Stop the recursion when we finish the least significant digit
 */
public class RadixSortMSD extends AbstractSort {
    public RadixSortMSD(ArrayDrawing drawing, String timeComplexity, String spaceComplexity) {
        super(drawing, timeComplexity, spaceComplexity);
    }

    @Override
    public void sort(Element[] arr) throws InterruptedException {
        int max = (int)getMax(arr, 0, arr.length);
        int numOfDigits = -1;
        int tmp = max;
        while (tmp > 0) {
            tmp = tmp/10;
            numOfDigits++;
        }

        sortAndDraw(arr, 0, arr.length, numOfDigits);

        drawing.drawWithSleep(arr);
    }

    private void sortAndDraw(Element[] arr, int low, int high, int numOfDigits) throws InterruptedException {
        if (arr == null || arr.length == 0 || arr.length == 1) return;
        if (high == low || high - low == 1) return;
        if (numOfDigits == -1) return;

        int[] count = new int[10];
        Element[] output = new Element[high - low];

        int i = (int)Math.pow(10.0, numOfDigits); // if max is 340, the i will be 100, 340/100*10 to get hundredth digit 3

        for (int j = low; j < high; ++j) {
            int index = (int)arr[j].getValue() / i % 10;
            count[index]++;
        }


        // keep a copy for later use when try to find out the bucket ranges
        int[] countCopy = new int[count.length];
        for (int j = 0; j < countCopy.length; ++j) {
            countCopy[j] = count[j];
        }

        for (int j = 1; j < count.length; ++j) {
            count[j] = count[j-1] + count[j];
        }

        // should start from the end of array
        // because we want the elements with greater index stay in the relative larger index
        // the counting sort here is to find out the buckets
        for (int j = high - 1; j >= low; --j) {
            int index = (int)arr[j].getValue() / i % 10;
            output[count[index]-1] = new Element(arr[j].getValue());
            --count[index];
        }

        int curIndex = 0;
        for (int j = low; j < high; ++j) {
            arr[j].setValue(output[curIndex].getValue());
            curIndex++;
            arr[j].setHighlighted(true);
            drawing.drawWithSleep(arr);
            arr[j].setHighlighted(false);
        }

        for (int j = 0, newLow = low; j < countCopy.length; ++j) {
            if (countCopy[j] == 0) continue;

            // countCopy = [0, 1, 4, 0, 0, 1, 0, 0, 0, 0] means 3 buckets with elements, we need to deal with each bucket
            // first bucket is range [0, 1]
            // second bucket is range [1, 4]
            // third bucket is range [4, 5]
            sortAndDraw(arr, newLow, newLow+countCopy[j], numOfDigits-1);
            newLow = newLow+countCopy[j];
        }
    }
}
