package com.dong.sorting;

import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.dom.html.HTMLDocument;

public class Client {
    public static void main(String[] args) {

        HTMLDocument document = HTMLDocument.current();
        HTMLCanvasElement canvas = (HTMLCanvasElement)document.getElementById("sorting-animation-canvas");

        ArrayDrawing drawing = new ArrayDrawingImpl(canvas);
        new Control(drawing);
    }
}
