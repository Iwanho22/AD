package ch.hslu.sw02.ex4;

import java.util.Arrays;

public class RingBuffer<E> implements Queue<E> {
    private int head;
    private int tail;
    private int size;
    private Object[] items;

    public RingBuffer(int maxSize) {
        this.items = new Object[maxSize];
    }

    @Override
    public void offer(E item) {
        if (size >= items.length) {
            throw new IllegalStateException("Queue is full, max size of " + items.length + " exceeded.");
        }
        this.items[head] = item;
        head = (head + 1) % items.length;
        size++;
    }

    @Override
    public E poll() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty.");
        }
        @SuppressWarnings("unchecked")
        E ret = (E) items[tail];
        items[tail] = null;
        tail = (tail + 1) % items.length;
        size--;
        return ret;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E peek() {
        return (E) items[tail++ % items.length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "RingBuffer{" +
                "head=" + head +
                ", tail=" + tail +
                ", size=" + size +
                ", items=" + Arrays.toString(items) +
                '}';
    }
}
