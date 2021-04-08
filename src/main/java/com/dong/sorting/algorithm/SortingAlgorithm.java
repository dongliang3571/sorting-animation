package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SortingAlgorithm {

    public static final Random random = new Random();

    private Map<String, Sort> algorithmMap;
    private ArrayDrawing drawing;
    private Sort currentAlgorithm;
    private int currentSpeed;
    private Element[] arr;

    public Sort getCurrentAlgorithm() {
        return currentAlgorithm;
    }

    public void setCurrentAlgorithm(Sort currentAlgorithm) {
        this.currentAlgorithm = currentAlgorithm;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public SortingAlgorithm(ArrayDrawing drawing) {
        this.drawing = drawing;
        if (algorithmMap == null) {
            algorithmMap = new HashMap<>();
        }
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

    public void startSorting() throws InterruptedException {
        currentAlgorithm.sort(arr, currentSpeed);
    }

    public void generateRandomArrayAndDraw() {
        arr = new Element[50];

        for (int i = 0; i < 50; ++i) {
            // I don't want 0s
            arr[i] = new Element(random.nextInt(400)+10);
        }

        drawing.cleanCanvas();
        drawing.draw(arr);
    }
}
