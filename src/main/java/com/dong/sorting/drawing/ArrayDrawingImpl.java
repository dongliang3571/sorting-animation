package com.dong.sorting.drawing;

import com.dong.sorting.algorithm.SortingAlgorithms;
import com.dong.sorting.model.Element;
import com.dong.sorting.util.Util;
import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.dom.html.HTMLCanvasElement;

import java.util.function.BiConsumer;

import static com.dong.sorting.util.Util.getMax;

public class ArrayDrawingImpl implements ArrayDrawing {

    private static final double RECTANGLE_WIDTH = 7;
    private static final double BORDER_SIZE = 2;
    private static final String BORDER_COLOR = "black";
    private static final String RECTANGLE_COLOR = "white";
    private static final String DOT_COLOR = "blue";
    private static final String DOT_HIGHLIGHTED_COLOR = "red";
    private static final int CANVAS_WIDTH = 1500;
    private static final int CANVAS_HEIGHT = 600;
    private static final int RECTANGLE_MAX_HEIGHT = 400;
    private HTMLCanvasElement canvas;
    private CanvasRenderingContext2D context;
    private GraphType currentGraphType;
    private int currentSpeed;

    @Override
    public int getCurrentSpeed() {
        return currentSpeed;
    }

    @Override
    public void setCurrentSpeed(int speed) {
        currentSpeed = speed;
    }

    @Override
    public GraphType getCurrentGraphType() {
        return currentGraphType;
    }

    @Override
    public void setCurrentGraphType(GraphType graphType) {
        this.currentGraphType = graphType;
    }

    public ArrayDrawingImpl(HTMLCanvasElement canvas) {
        this.canvas = canvas;
        this.canvas.setWidth(CANVAS_WIDTH);
        this.canvas.setHeight(CANVAS_HEIGHT);
        this.context = (CanvasRenderingContext2D)canvas.getContext("2d");
        this.currentGraphType = GraphType.Histogram;
        this.currentSpeed = DEFAULT_SPEED;
    }

    public void sleepOnly() throws InterruptedException {
        Thread.sleep(Util.getSleepTimeFromSpeed(currentSpeed));
    }

    public void draw(Element[] arr) {
        arr = scaleDownArray(arr, SortingAlgorithms.MAX_NUMBER, RECTANGLE_MAX_HEIGHT);

        context.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        BiConsumer<Element, Integer> biC;

        switch (currentGraphType) {
            case Dot:
                biC = this::drawDot;
                break;
            case DotWithLine:
                biC = this::drawDotWithLine;
                break;
            default:
                biC = this::drawHistogram;
        }

        context.beginPath();
        for (int i = 0; i < arr.length; ++i) {
            biC.accept(arr[i], i);
        }
        context.closePath();

        drawHorizontalLine();
    }

    private void drawHorizontalLine() {
        context.beginPath();
        context.setStrokeStyle(BORDER_COLOR);
        context.moveTo(0, CANVAS_HEIGHT);
        context.lineTo(CANVAS_WIDTH, CANVAS_HEIGHT);
        context.stroke();
        context.closePath();
    }

    public Element[] scaleDownArray(Element[] arr, int oldMax, int newMax) {
        Element[] arrToDraw = new Element[arr.length];

        for (int i = 0; i < arr.length; ++i) {
            // arr[i].getValue()/newMax is a double between 0-1
            arrToDraw[i] = new Element(
                    Math.floor(arr[i].getValue() / oldMax * newMax),
                    arr[i].isHighlighted());
        }

        return arrToDraw;
    }

    public void drawWithSleep(Element[] arr) throws InterruptedException {
        draw(arr);
        Thread.sleep(Util.getSleepTimeFromSpeed(currentSpeed));
    }

    private void drawDot(Element e, int position) {
        double height = e.getValue();
        double canvas_height = (double)CANVAS_HEIGHT;

        context.beginPath();
        context.setFillStyle(e.isHighlighted() ? DOT_HIGHLIGHTED_COLOR : DOT_COLOR);
        context.arc(
                position * RECTANGLE_WIDTH+RECTANGLE_WIDTH/2,
                canvas_height - height,
                4,
                0,
                2 * Math.PI);
        context.fill();
        context.closePath();
    }

    private void drawDotWithLine(Element e, int position) {
        double height = e.getValue();
        double canvas_height = (double)CANVAS_HEIGHT;

        context.setStrokeStyle(e.isHighlighted() ? DOT_HIGHLIGHTED_COLOR : DOT_COLOR);
        context.arc(
                position * RECTANGLE_WIDTH+RECTANGLE_WIDTH/2,
                canvas_height - height,
                4,
                0,
                2 * Math.PI);
        context.stroke();
    }

    // we first draw a big rectangle
    // and then draw a smaller inside the big one to create the illusion of the rectangle has a border
    private void drawHistogram(Element e, int position) {
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
                height == 0 ? height : height - BORDER_SIZE*2);
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
