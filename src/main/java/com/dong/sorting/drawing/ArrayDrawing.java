package com.dong.sorting.drawing;

import com.dong.sorting.model.Element;

public interface ArrayDrawing {

    void draw(Element[] arr);
    void drawWithSleep(Element[] arr, long millis) throws InterruptedException;
    void cleanCanvas();
}
