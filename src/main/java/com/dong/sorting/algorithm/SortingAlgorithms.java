package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SortingAlgorithms {

    public static final Random random = new Random();
    public static final int ARRAY_SIZE = 200;
    public static final int MAX_NUMBER = 4000000;

    private Map<String, Sort> algorithmMap;
    private ArrayDrawing drawing;
    private Sort currentAlgorithm;
    private Element[] arr;

    public Sort getCurrentAlgorithm() {
        return currentAlgorithm;
    }

    public void setCurrentAlgorithm(Sort currentAlgorithm) {
        this.currentAlgorithm = currentAlgorithm;
    }

    public SortingAlgorithms(ArrayDrawing drawing) {
        this.drawing = drawing;
        if (algorithmMap == null) {
            algorithmMap = new HashMap<>();
        }

        this.addAlgorithm(new InsertionSort(drawing, "O(n^2)", "O(1)"));
        this.addAlgorithm(new MergeSort(drawing, "O(nlogn)", "O(n)"));
        this.addAlgorithm(new MergeSortMultithreaded(drawing, "O(n)", "O(n)"));
        this.addAlgorithm(new SelectionSort(drawing, "O(n^2)", "O(1)"));
        this.addAlgorithm(new BubbleSort(drawing, "O(n^2)", "O(1)"));
        this.addAlgorithm(new QuickSort(drawing, "O(nlogn)", "O(logn)"));
        this.addAlgorithm(new QuickSortMultithreaded(drawing, "O(n)", "O(n)"));
        this.addAlgorithm(new HeapSortRecursive(drawing, "O(nlogn)", "O(1)"));
        this.addAlgorithm(new HeapSortIterative(drawing, "O(nlogn)", "O(1)"));
        this.addAlgorithm(new CountingSort(drawing, "O(n+k) where k is the biggest number in the array", "O(k)"));
        this.addAlgorithm(new BucketSort(drawing, "O(n+n^2/k+k) where k is number of buckets", "O(n)"));
        this.addAlgorithm(new RadixSortLSD(drawing, "O(n*w) where w is number of digits in max key", "O(n)"));
        this.addAlgorithm(new RadixSortMSD(drawing, "O(n*w) where w is number of digits in max key", "O(n)"));
    }

    public void addAlgorithm(Sort algorithm) {
        algorithmMap.put(algorithm.getAlgoName(), algorithm);
    }

    public Sort getAlgorithm(String name) {
        Sort ret = null;
        if (algorithmMap.containsKey(name)) {
            ret = algorithmMap.get(name);
        }

        return ret;
    }

    public List<String> getAlgorithmNameList() {
        List<String> nameList = new ArrayList<>(algorithmMap.keySet());
        Collections.sort(nameList);

        return nameList;
    }

    public void startSorting() throws InterruptedException {
        currentAlgorithm.sort(arr);
    }

    public void generateRandomArrayAndDraw() {
        arr = new Element[ARRAY_SIZE];

        for (int i = 0; i < ARRAY_SIZE; ++i) {
            // I don't want 0s
            arr[i] = new Element(random.nextInt(MAX_NUMBER));
        }

        drawing.cleanCanvas();
        drawing.draw(arr);
    }
}
