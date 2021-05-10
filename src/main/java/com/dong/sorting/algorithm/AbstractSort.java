package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;

public abstract class AbstractSort implements Sort {
    protected ArrayDrawing drawing;
    protected String algoName;
    protected String timeComplexity;
    protected String spaceComplexity;

    public AbstractSort(ArrayDrawing drawing, String timeComplexity, String spaceComplexity) {
        this.drawing = drawing;
        this.algoName = getClass().getSimpleName();
        this.timeComplexity = timeComplexity;
        this.spaceComplexity = spaceComplexity;
    }

    @Override
    public String getAlgoName() {
        return algoName;
    }

    @Override
    public String getTimeComplexity() {
        return timeComplexity;
    }

    @Override
    public String getSpaceComplexity() {
        return spaceComplexity;
    }

    @Override
    public void sort(Element[] arr) throws InterruptedException {

    }

    @Override
    public void interrupt() {

    }
}
