package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;

public abstract class AbstractSort implements Sort {
    protected ArrayDrawing drawing;
    protected String algoName;

    public AbstractSort(ArrayDrawing drawing) {
        this.drawing = drawing;
        this.algoName = getClass().getSimpleName();
    }

    public String getAlgoName() {
        return algoName;
    }

    @Override
    public void sort(Element[] arr, int speed) throws InterruptedException {

    }
}
