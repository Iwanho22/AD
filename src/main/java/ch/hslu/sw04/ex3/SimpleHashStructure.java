package ch.hslu.sw04.ex3;

import java.util.Arrays;

/**
 * This is a simple implementation of the hash structure.
 * It does not allow to store null references.
 *
 * @param <E> The type of the element to be stored.
 */
public final class SimpleHashStructure<E> implements HashStructure<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object TOMBSTONE = new Object();

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

        if (data[index] == null) {
            data[index] = value;
            size++;
            return true;
        } else {
            for (int i = 0; i < data.length; i++) {
                int newIndex = (index + i) % data.length;
                if (data[newIndex] == null || data[newIndex] == TOMBSTONE) {
                    data[newIndex] = value;
                    size++;
                    return true;
                }
            }
        }

        return false;
    }

    private int getIndex(E value) {
        return Math.abs(value.hashCode() % data.length);
    }

    @Override
    public boolean remove(E value) {
        var index = getIndex(value);

        if (data[index] == null) {
            return false;
        } else {
            var itemIndex = findInExploratoryChain(index, value);
            if (itemIndex > -1) {
                data[itemIndex] = TOMBSTONE;
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean contains(E value) {
        var index = getIndex(value);
        if (data[index] == null) {
            return false;
        } else {
            return findInExploratoryChain(index, value) > -1;
        }
    }

    private int findInExploratoryChain(int start, E value) {
        int pos = start;
        do {
            if (data[pos] == null) {
                return -1;
            } else if (data[pos].equals(value)) {
                return pos;
            }
            pos = (pos + 1) % data.length;
        } while (pos != start);

        return -1;
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
}
