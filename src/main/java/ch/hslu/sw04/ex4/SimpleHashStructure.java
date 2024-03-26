package ch.hslu.sw04.ex4;

import java.util.Arrays;

/**
 * This is a simple implementation of the hash structure.
 * It does not allow to store null references.
 *
 * @param <E> The type of the element to be stored.
 */
public final class SimpleHashStructure<E> implements HashStructure<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private final Object[] data;
    private int size;

    public SimpleHashStructure() {
        this(DEFAULT_CAPACITY);
    }

    public SimpleHashStructure(int capacity) {
        data = new Object[capacity];
    }

    @Override
    public boolean add(E value) {
        int index = getIndex(value);

        @SuppressWarnings("unchecked")
        Node<E> previous = (Node<E>) data[index];
        var currentNode = previous;

        // check for duplicates
        while (currentNode != null) {
            if (currentNode.value.equals(value)) {
                return false;
            }
            currentNode = currentNode.previous;
        }

        var newNode = new Node<>(value, previous);
        data[index] = newNode;
        size++;
        return true;
    }

    private int getIndex(E value) {
        return Math.abs(value.hashCode() % data.length);
    }

    @Override
    public boolean remove(E value) {
        var index = getIndex(value);

        @SuppressWarnings("unchecked")
        var node = (Node<E>) data[index];
        if (node == null) {
            return false;
        } else  {
            var currentNode = node;
            while (currentNode != null) {
                if (currentNode.value.equals(value)) {
                    node.previous = currentNode.previous;
                    size--;
                    return true;
                }
                currentNode = currentNode.previous;
            }
        }

        return false;
    }

    @Override
    public boolean contains(E value) {
        var index = getIndex(value);
        @SuppressWarnings("unchecked")
        var current = (Node<E>) data[index];
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.previous;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "SimpleHashStructure{" +
                "data=" + Arrays.toString(data) +
                '}';
    }

    private static final class Node<E> {
        E value;
        Node<E> previous;

        public Node(E value, Node<E> previous) {
            this.value = value;
            this.previous = previous;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", previous=" + previous +
                    '}';
        }
    }
}
