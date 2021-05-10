package com.dong.sorting.drawing;

import com.dong.sorting.model.Element;

public interface ArrayDrawing {

    int DEFAULT_SPEED = 11;
    int DEFAULT_SPEED_LEVELS = 11;

    void draw(Element[] arr);
    void drawWithSleep(Element[] arr) throws InterruptedException;
    void sleepOnly() throws InterruptedException;
    void cleanCanvas();
    GraphType getCurrentGraphType();
    void setCurrentGraphType(GraphType graphType);
    void setCurrentSpeed(int speed);
    int getCurrentSpeed();
}
