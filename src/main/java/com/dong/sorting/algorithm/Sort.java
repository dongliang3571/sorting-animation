package com.dong.sorting.algorithm;

import com.dong.sorting.Element;

public interface Sort {

    void sort(Element[] arr, int speed) throws InterruptedException;
    String getAlgoName();
}
