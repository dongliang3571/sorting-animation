package com.dong.sorting.algorithm;

import com.dong.sorting.drawing.ArrayDrawing;
import com.dong.sorting.model.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Note, in order for bucket sort to work with negative number, you need to split the array into two
 * One array with all positive numbers and the other with negative numbers
 * Apply bucket sort for each but with reverse direction for negative numbers.
 *
 * Bucket sort works better when the array is uniformly distributed
 * Imagine if every bucket contains one element so that sorting of each bucket can take O(1)
 * Overall, time complexity would be O(n)
 * 1. Create a list of bucket (the bucket should be a linked list ideally)
 * 2. Scan through the array, for each element array[i], we put it into a bucket by doing buckets[floor(array[i]/Max * k)].add(array[i])
 *    where k is bucket size and Max is the max element in the array. array[i]/Max is always a number between 0 - 1,
 *    floor(array[i]/Max * k) will always end up in a bucket
 * 3. Sort each bucket (Conventionally, insertion sort is used)
 * 4. Concatenate all element into original array
 */
public class BucketSort extends AbstractSort {

    public class MyLinkedList<T extends Element> {
        Node<T> head;
        Node<T> tail;
        int size;
        public MyLinkedList() {
            size = 0;
            head = new Node<T>(null);
            tail = new Node<T>(null);
            head.next = tail;
            tail.prev = head;
        }

        public void add(T element) {
            Node prev = tail.prev;

            prev.next = new Node(element);
            prev.next.next= tail;
            prev.next.prev = prev;
            tail.prev = prev.next;

            ++size;
        }

        public void remove(Node<T> node) {
            node.prev().next = node.next();
            node.next().prev = node.prev();
            node.prev = null;
            node.next = null;

            size--;
        }

        public void insertAfter(Node<T> node, Node<T> insert) {
            Node<T> next = node.next();
            node.next = insert;
            next.prev = insert;
            insert.prev = node;
            insert.next = next;

            size++;
        }

        public int size() {
            return this.size;
        }

        public Node<T> iterator() {
            return head;
        }
    }

    public class Node<T extends Element> implements Comparable<Node<T>> {
        T element;
        Node<T> prev;
        Node<T> next;

        public boolean isDummy() {
            return element == null;
        }

        public boolean hasNext() {
            return this.next != null && !this.next.isDummy();
        }

        public T getElement() {
            return element;
        }

        public Node(T element) {
            this.element = element;
        }

        public Node(T element, Node<T> prev, Node<T> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        public Node<T> next() {
            return next;
        }

        public Node<T> prev() {
            return prev;
        }

        @Override
        public int compareTo(Node<T> o) {
            if (this == o) return 0;

            return this.getElement().compareTo(o.getElement());
        }
    }

    public BucketSort(ArrayDrawing drawing, String timeComplexity, String spaceComplexity) {
        super(drawing, timeComplexity, spaceComplexity);
    }

    @Override
    public void sort(Element[] arr) throws InterruptedException {
        sortAndDraw(arr);
    }

    private void sortAndDraw(Element[] arr) throws InterruptedException {

        List<MyLinkedList<Element>> buckets = new ArrayList<>(arr.length);

        for (int i = 0; i < arr.length; ++i) {
            buckets.add(new MyLinkedList<>());
        }

        int max = 0;
        for (Element e : arr) {
            max = Math.max(max, (int)e.getValue());
        }

        for (Element e : arr) {
            int index = (int)Math.floor(e.getValue()/max * (arr.length-1));
            buckets.get(index).add(e);
        }

        for (MyLinkedList<Element> l : buckets) {
            insertionSort(l);
        }

        int curIndex = 0;
        for (MyLinkedList<Element> l : buckets) {
            if (l.size() > 0) {
                Node<Element> iterator = l.iterator();
                while (iterator.hasNext()) {
                    arr[curIndex] = iterator.next().getElement();
                    arr[curIndex].setHighlighted(true);
                    drawing.drawWithSleep(arr);
                    arr[curIndex].setHighlighted(false);
                    iterator = iterator.next();
                    ++curIndex;
                }
            }
        }
    }

    private void insertionSort(MyLinkedList<Element> arr) throws InterruptedException {
        if (arr == null || arr.size() == 0 || arr.size() == 1) return;

        Node<Element> iterator = arr.iterator().next();
        Node<Element> next;
        while (iterator.hasNext() && !iterator.isDummy()) {
            next = iterator.next();

            Node<Element> prev = iterator.prev();
            while (!iterator.isDummy()
                    && !prev.isDummy()
                    && iterator.compareTo(prev) < 0) {
                prev = prev.prev();
            }

            arr.remove(iterator);
            arr.insertAfter(prev, iterator);

            iterator = next;
        }
    }
}

