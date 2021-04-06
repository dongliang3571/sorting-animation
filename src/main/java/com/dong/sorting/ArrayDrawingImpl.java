package com.dong.sorting;

import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.dom.html.HTMLCanvasElement;

public class ArrayDrawingImpl implements ArrayDrawing {

    private static final double RECTANGLE_WIDTH = 30;
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

    public void draw(Element[] arr) throws InterruptedException {
        context.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        for (int i = 0; i < arr.length; ++i) {
            drawRectangle(arr[i], i);
        }
        Thread.sleep(100);
    }

    // we first draw a big rectangle
    // and then draw a smaller inside the big one to create the illusion of the rectangle has a border
    private void drawRectangle(Element e, int position) {
        drawOuterRectangle(e.getValue(), position);
        drawInnerRectangle(e.getValue(), position);
    }

    private void drawInnerRectangle(double height, int position) {
        double canvas_height = (double)CANVAS_HEIGHT;

        context.setFillStyle(RECTANGLE_COLOR);
        context.fillRect(
                position * RECTANGLE_WIDTH+BORDER_SIZE,
                canvas_height - height + BORDER_SIZE,
                RECTANGLE_WIDTH - BORDER_SIZE*2,
                height - BORDER_SIZE);
    }

    private void drawOuterRectangle(double height, int position) {
        double canvas_height = (double)CANVAS_HEIGHT;

        context.setFillStyle(BORDER_COLOR);
        context.fillRect(position * RECTANGLE_WIDTH, canvas_height - height, RECTANGLE_WIDTH, height);
    }
}
