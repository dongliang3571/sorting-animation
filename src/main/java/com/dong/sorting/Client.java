package com.dong.sorting;

import com.dong.sorting.algorithm.InsertionSort;
import com.dong.sorting.algorithm.SortingAlgorithm;
import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.dom.html.HTMLDocument;

import java.util.Random;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        HTMLDocument document = HTMLDocument.current();
        HTMLCanvasElement canvas = (HTMLCanvasElement)document.getElementById("sorting-animation-canvas");

        ArrayDrawing drawing = new ArrayDrawingImpl(canvas);

        SortingAlgorithm sort = new InsertionSort(drawing);

        Random random = new Random();


        Element[] arr = new Element[50];
        for (int i = 0; i < 50; ++i) {
            arr[i] = new Element(random.nextInt(400));
        }

        sort.sort(arr);
    }

    public static void drawArray(CanvasRenderingContext2D context, int[] arr) {

    }
}
