package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;
import com.dong.sorting.util.Util;

public class MergeSort extends AbstractSort {

    public MergeSort(ArrayDrawing drawing) {
        super(drawing);
    }

    public void sort(Element[] arr, int speed) throws InterruptedException {
        long sleep = Util.getSleepTimeFromSpeed(speed);
        sortWithSleep(arr, 0, arr.length-1, sleep);
    }

    private void sortWithSleep(Element[] arr, int left, int right, long sleep) throws InterruptedException {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        sortWithSleep(arr, left, mid, sleep);
        sortWithSleep(arr, mid+1, right, sleep);

        Element[] tmp = new Element[right-left+1];

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
            this.drawing.drawWithSleep(arr, sleep);
            arr[i].setHighlighted(false);
        }
    }
}
