package com.dong.sorting;

import com.dong.sorting.algorithm.InsertionSort;
import com.dong.sorting.algorithm.SortingAlgorithm;
import com.dong.sorting.algorithm.SortingRunner;
import org.teavm.jso.dom.html.HTMLButtonElement;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLOptionElement;
import org.teavm.jso.dom.html.HTMLOptionsCollection;
import org.teavm.jso.dom.html.HTMLSelectElement;
import org.teavm.jso.dom.xml.Text;

import java.util.Arrays;
import java.util.List;

public class Control {

    public static final HTMLDocument document = HTMLDocument.current();
    public static final List<String> algorithmList = Arrays.asList(
            InsertionSort.class.getSimpleName(),
            "Quick Sort",
            "Merge Sort");

    private SortingAlgorithm algorithms;
    private ArrayDrawing drawing;
    private Thread sortingThread;

    public Control(ArrayDrawing drawing) {
        this.algorithms = new SortingAlgorithm(drawing);
        this.algorithms.addAlgorithm(new InsertionSort(drawing));
        this.algorithms.setCurrentAlgorithm(this.algorithms.getAlgorithm(InsertionSort.class.getSimpleName()));
        this.algorithms.setCurrentSpeed(5);
        this.algorithms.generateRandomArrayAndDraw();
        setControlMenu();
    }

    private void setControlMenu() {
        HTMLElement div = document.createElement("div");
        document.getBody().appendChild(div);
        div.withAttr("style", "margin-top: 25px;");

        setUpSpeedSelectElement(div);
        setUpAlgorithmSelectElement(div);
        setUpStartButton(div);
        setUpResetButton(div);
    }

    private void setUpSpeedSelectElement(HTMLElement div) {
        HTMLElement label = document.createElement("label");
        label.withText("Speed: ");

        HTMLSelectElement select = (HTMLSelectElement) document.createElement("select");

        HTMLOptionElement option = null;
        Text text = null;
        for (int i = 1; i <= 11; ++i) {
            option = (HTMLOptionElement) document.createElement("option");
            text = document.createTextNode(Integer.toString(i));
            option.withAttr("value", Integer.toString(i));
            if (i == 5) { // select default speed of 5
                option.setDefaultSelected(true);
            }
            option.appendChild(text);
            select.appendChild(option);
        }

        select.addEventListener("change", evt -> {
            reset();
            HTMLOptionsCollection col = select.getOptions();
            algorithms.setCurrentSpeed(Integer.parseInt(col.item(col.getSelectedIndex()).getValue()));
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
        for (String algo : algorithmList) {
            option = (HTMLOptionElement) document.createElement("option");
            text = document.createTextNode(algo);
            option.withAttr("value", algo);
            option.appendChild(text);
            select.appendChild(option);
        }

        select.addEventListener("change", evt -> {
            HTMLOptionsCollection col = select.getOptions();
            algorithms.setCurrentAlgorithm(algorithms.getAlgorithm(col.item(col.getSelectedIndex()).getValue()));
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
