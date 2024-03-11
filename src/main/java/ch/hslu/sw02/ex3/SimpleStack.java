package ch.hslu.sw02.ex3;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;

public final class SimpleStack<E> implements Stack<E> {
    public static final int DEFAULT_MAX_SIZE = 64;
    private final Object[] items;
    private int size;

    public SimpleStack(int maxSize) {
        items = new Object[maxSize];
    }

    public SimpleStack() {
        items = new Object[DEFAULT_MAX_SIZE];
    }

    @Override
    public void push(E item) {
        if (size == items.length) {
            throw new StackOverflowError("Stack is full. Cannot push an element");
        }
        items[size] = item;
        size++;
    }

    @Override
    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        @SuppressWarnings("unchecked")
        E item = (E) items[size - 1];
        // set reference to null to optimize GC
        items[size - 1] = null;
        size--;

        return item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E peek() {
        return (E) items[size - 1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == items.length;
    }

    @Override
    public void clear() {
        size = 0;

        // remove reference to optimize GC
        Arrays.fill(items, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<E> iterator() {
        return new SimpleStackItr(size, (E[]) items);
    }

    private class SimpleStackItr implements Iterator<E> {
        private final E[] items;
        private int index;

        public SimpleStackItr(int size, E[] items) {
            this.index = size - 1;
            this.items = items;
        }

        @Override
        public boolean hasNext() {
            return index != -1;
        }

        @Override
        public E next() {
            return items[index--];
        }
    }
}
