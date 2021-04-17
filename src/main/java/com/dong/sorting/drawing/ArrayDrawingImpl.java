package com.dong.sorting.drawing;

import com.dong.sorting.model.Element;
import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.dom.html.HTMLCanvasElement;

public class ArrayDrawingImpl implements ArrayDrawing {

    private static final double RECTANGLE_WIDTH = 15;
    private static final double BORDER_SIZE = 2;
    private static final String BORDER_COLOR = "black";
    private static final String RECTANGLE_COLOR = "white";
    private static final int CANVAS_WIDTH = 1500;
    private static final int CANVAS_HEIGHT = 600;

    private HTMLCanvasElement canvas;
    private CanvasRenderingContext2D context;

    public ArrayDrawingImpl(HTMLCanvasElement canvas) {
        this.canvas = canvas;
        this.canvas.setWidth(CANVAS_WIDTH);
        this.canvas.setHeight(CANVAS_HEIGHT);
        this.context = (CanvasRenderingContext2D)canvas.getContext("2d");
    }

    public void draw(Element[] arr) {
        context.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        for (int i = 0; i < arr.length; ++i) {
            drawRectangle(arr[i], i);
        }
    }

    public void drawWithSleep(Element[] arr, long millis) throws InterruptedException {
        draw(arr);
        Thread.sleep(millis);
    }

    // we first draw a big rectangle
    // and then draw a smaller inside the big one to create the illusion of the rectangle has a border
    private void drawRectangle(Element e, int position) {
        drawOuterRectangle(e, position);
        drawInnerRectangle(e, position);
    }

    private void drawInnerRectangle(Element e, int position) {
        double height = e.getValue();
        double canvas_height = (double)CANVAS_HEIGHT;

        context.setFillStyle(e.isHighlighted() ? BORDER_COLOR : RECTANGLE_COLOR);
        context.fillRect(
                position * RECTANGLE_WIDTH+BORDER_SIZE,
                canvas_height - height + BORDER_SIZE,
                RECTANGLE_WIDTH - BORDER_SIZE*2,
                height - BORDER_SIZE*2);
    }

    private void drawOuterRectangle(Element e, int position) {
        double height = e.getValue();
        double canvas_height = (double)CANVAS_HEIGHT;

        context.setFillStyle(BORDER_COLOR);
        context.fillRect(position * RECTANGLE_WIDTH, canvas_height - height, RECTANGLE_WIDTH, height);
    }

    public void cleanCanvas() {
        context.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
    }
}