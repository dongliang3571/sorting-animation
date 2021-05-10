package com.dong.sorting.algorithm;

public class SortingRunner extends Thread {

    SortingAlgorithms algorithms;

    public SortingRunner(SortingAlgorithms algorithms) {
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

    @Override
    public void interrupt() {
        algorithms.getCurrentAlgorithm().interrupt();
        super.interrupt();
    }
}
