package com.dong.sorting.model;

public class Element implements Comparable<Element> {
    private double value;
    private boolean isHighlighted;

    public Element(double value) {
        this.value = value;
    }

    public Element(double value, boolean isHighlighted) {
        this.value = value;
        this.isHighlighted = isHighlighted;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isHighlighted() {
        return isHighlighted;
    }

    public void setHighlighted(boolean highlighted) {
        isHighlighted = highlighted;
    }

    @Override
    public int compareTo(Element o) {
        if (this.getValue() > o.getValue()) {
            return 1;
        } else if (this.getValue() < o.getValue()) {
            return -1;
        } else {
            return 0;
        }
    }
}
