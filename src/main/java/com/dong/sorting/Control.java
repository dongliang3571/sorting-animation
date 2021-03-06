package com.dong.sorting;

import com.dong.sorting.algorithm.BubbleSort;
import com.dong.sorting.algorithm.SortingAlgorithms;
import com.dong.sorting.algorithm.SortingRunner;
import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.drawing.GraphType;
import org.teavm.jso.dom.html.HTMLButtonElement;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLOptionElement;
import org.teavm.jso.dom.html.HTMLOptionsCollection;
import org.teavm.jso.dom.html.HTMLSelectElement;
import org.teavm.jso.dom.xml.Text;

public class Control {

    public static final HTMLDocument document = HTMLDocument.current();

    private SortingAlgorithms algorithms;
    private ArrayDrawing drawing;
    private Thread sortingThread;

    private HTMLElement timeComplexityElement;
    private HTMLElement spaceComplexityElement;

    public Control(ArrayDrawing drawing) {
        this.drawing = drawing;
        this.algorithms = new SortingAlgorithms(drawing);
        this.algorithms.setCurrentAlgorithm(this.algorithms.getAlgorithm(BubbleSort.class.getSimpleName()));
        this.algorithms.generateRandomArrayAndDraw();
        setControlMenu();
    }

    private void setControlMenu() {
        HTMLElement div = document.createElement("div");
        document.getBody().appendChild(div);
        div.withAttr("style", "margin-top: 25px;");

        setUpSpeedSelectElement(div);
        setUpAlgorithmSelectElement(div);
        setUpGraphTypeSelectElement(div);
        setUpStartButton(div);
        setUpResetButton(div);
        setUpTimeComplexityElement(div);
        setUpSpaceComplexityElement(div);
    }

    private void setUpSpeedSelectElement(HTMLElement div) {
        HTMLElement label = document.createElement("label");
        label.withText("Speed: ");

        HTMLSelectElement select = (HTMLSelectElement) document.createElement("select");

        HTMLOptionElement option = null;
        Text text = null;
        for (int i = 1; i <= ArrayDrawing.DEFAULT_SPEED_LEVELS; ++i) {
            option = (HTMLOptionElement) document.createElement("option");
            text = document.createTextNode(Integer.toString(i));
            option.withAttr("value", Integer.toString(i));
            if (i == ArrayDrawing.DEFAULT_SPEED) { // select default speed of 20
                option.setDefaultSelected(true);
            }
            option.appendChild(text);
            select.appendChild(option);
        }

        select.addEventListener("change", evt -> {
            HTMLOptionsCollection col = select.getOptions();
            drawing.setCurrentSpeed(Integer.parseInt(col.item(col.getSelectedIndex()).getValue()));
        });

        div.appendChild(label);
        div.appendChild(select);
    }

    private void setUpAlgorithmSelectElement(HTMLElement div) {
        HTMLElement label = document.createElement("label");
        label.withText("Sorting Algorithm: ");
        label.withAttr("style", "margin-left: 25px;");
        HTMLSelectElement select = (HTMLSelectElement) document.createElement("select");

        HTMLOptionElement option = null;
        Text text = null;
        for (String algo : algorithms.getAlgorithmNameList()) {
            option = (HTMLOptionElement) document.createElement("option");
            text = document.createTextNode(algo);
            option.withAttr("value", algo);
            option.appendChild(text);
            select.appendChild(option);
        }

        select.addEventListener("change", evt -> {
            HTMLOptionsCollection col = select.getOptions();
            algorithms.setCurrentAlgorithm(algorithms.getAlgorithm(col.item(col.getSelectedIndex()).getValue()));
            timeComplexityElement.withText(algorithms.getCurrentAlgorithm().getTimeComplexity());
            spaceComplexityElement.withText(algorithms.getCurrentAlgorithm().getSpaceComplexity());
            reset();
        });

        div.appendChild(label);
        div.appendChild(select);
    }

    private void setUpGraphTypeSelectElement(HTMLElement div) {
        HTMLElement label = document.createElement("label");
        label.withText("Graph: ");
        label.withAttr("style", "margin-left: 25px;");
        HTMLSelectElement select = (HTMLSelectElement) document.createElement("select");

        HTMLOptionElement option = null;
        Text text = null;
        for (GraphType gt : GraphType.graphTypes) {
            option = (HTMLOptionElement) document.createElement("option");
            text = document.createTextNode(gt.name());
            option.withAttr("value", gt.name());
            option.appendChild(text);
            select.appendChild(option);
        }

        select.addEventListener("change", evt -> {
            HTMLOptionsCollection col = select.getOptions();
            drawing.setCurrentGraphType(GraphType.valueOf(col.item(col.getSelectedIndex()).getValue()));
            reset();
        });

        div.appendChild(label);
        div.appendChild(select);
    }

    private void setUpStartButton(HTMLElement div) {
        HTMLButtonElement button = (HTMLButtonElement) document.createElement("button");
        button.setInnerHTML("Start");
        button.withAttr("style", "margin-left: 25px;");
        button.addEventListener("click", evt -> {
            reset();
            sortingThread = new SortingRunner(algorithms);
            sortingThread.start();
        });

        div.appendChild(button);
    }

    private void setUpTimeComplexityElement(HTMLElement div) {
        HTMLElement label = document.createElement("label");
        label.withText("Time Complexity(Average case): ");
        label.withAttr("style", "margin-left: 25px;");

        timeComplexityElement = document.createElement("paragraph");
        timeComplexityElement.withAttr("style", "margin-left: 5px;");
        timeComplexityElement.withText(this.algorithms.getCurrentAlgorithm().getTimeComplexity());

        div.appendChild(label);
        div.appendChild(timeComplexityElement);
    }

    private void setUpSpaceComplexityElement(HTMLElement div) {
        HTMLElement label = document.createElement("label");
        label.withText("Space Complexity(Average case): ");
        label.withAttr("style", "margin-left: 25px;");

        spaceComplexityElement = document.createElement("paragraph");
        spaceComplexityElement.withAttr("style", "margin-left: 5px;");
        spaceComplexityElement.withText(this.algorithms.getCurrentAlgorithm().getSpaceComplexity());

        div.appendChild(label);
        div.appendChild(spaceComplexityElement);
    }

    private void setUpResetButton(HTMLElement div) {
        HTMLButtonElement button = (HTMLButtonElement) document.createElement("button");
        button.setInnerHTML("Reset");
        button.withAttr("style", "margin-left: 25px;");
        button.addEventListener("click", evt -> {
            reset();
        });

        div.appendChild(button);
    }

    private void reset() {
        if (sortingThread != null) {
            sortingThread.interrupt();
        }

        algorithms.generateRandomArrayAndDraw();
    }

}
