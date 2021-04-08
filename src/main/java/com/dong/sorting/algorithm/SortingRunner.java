package com.dong.sorting.algorithm;

public class SortingRunner extends Thread {

    SortingAlgorithm algorithms;

    public SortingRunner(SortingAlgorithm algorithms) {
        this.algorithms = algorithms;
    }

    @Override
    public void run() {
        try {
            algorithms.startSorting();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
