package com.dong.sorting;

public interface ArrayDrawing {

    void draw(Element[] arr);
    void drawWithSleep(Element[] arr, long millis) throws InterruptedException;
    void cleanCanvas();
}
