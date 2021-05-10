package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;
import com.dong.sorting.util.Util;

public class MergeSort extends AbstractSort {

    public MergeSort(ArrayDrawing drawing, String timeComplexity, String spaceComplexity) {
        super(drawing, timeComplexity, spaceComplexity);
    }

    public void sort(Element[] arr) throws InterruptedException {
        sortAndDraw(arr, 0, arr.length-1);
    }

    private void sortAndDraw(Element[] arr, int left, int right) throws InterruptedException {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        sortAndDraw(arr, left, mid);
        sortAndDraw(arr, mid+1, right);

        merge(arr, left, right);
    }

    private void merge(Element[] arr, int left, int right) throws InterruptedException {
        Element[] tmp = new Element[right-left+1];

        int mid = left + (right - left) / 2;
        int ptr1 = left;
        int ptr2 = mid+1;
        int ptr3 = 0;
        while (ptr1 <= mid && ptr2 <= right) {
            int c = arr[ptr1].compareTo(arr[ptr2]);
            if (c < 0) {
                tmp[ptr3] = arr[ptr1];
                ++ptr1;
            } else {
                tmp[ptr3] = arr[ptr2];
                ++ptr2;
            }

            ++ptr3;
        }

        while (ptr1 <= mid) {
            tmp[ptr3] = arr[ptr1];
            ++ptr1;
            ++ptr3;
        }

        while (ptr2 <= right) {
            tmp[ptr3] = arr[ptr2];
            ++ptr2;
            ++ptr3;
        }

        ptr3 = 0;
        for (int i = left; i <= right; ++i, ++ptr3) {
            arr[i] = tmp[ptr3];
            arr[i].setHighlighted(true);
            this.drawing.drawWithSleep(arr);
            arr[i].setHighlighted(false);
        }
    }
}
