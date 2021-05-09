package com.dong.sorting.algorithm;

import com.dong.sorting.model.Element;

public interface Sort {

    void sort(Element[] arr, int speed) throws InterruptedException;
    void interrupt();
    String getAlgoName();
    String getTimeComplexity();
    String getSpaceComplexity();
}
