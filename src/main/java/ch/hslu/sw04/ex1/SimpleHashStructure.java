package ch.hslu.sw04.ex1;

import java.util.Arrays;

/**
 * This is a simple implementation of the hash structure.
 * It does not allow to store null references.
 * @param <E> The type of the element to be stored.
 */
public final class SimpleHashStructure<E> implements HashStructure<E> {
    public static final int DEFAULT_CAPACITY = 10;

    private final Object[] data;

    public SimpleHashStructure() {
        this(DEFAULT_CAPACITY);
    }

    public SimpleHashStructure(int capacity) {
        data = new Object[capacity];
    }

    @Override
    public boolean add(E value) {
        int index = getIndex(value);

        if(!value.equals(data[index])) {
            data[index] = value;
        }

        return true;
    }

    private int getIndex(E value) {
        return value.hashCode() % data.length;
    }

    @Override
    public boolean remove(E value) {
        var index = getIndex(value);
        if (data[index] !=  null) {
            data[index] = null;
            return true;
        }

        return false;
    }

    @Override
    public boolean contains(E value) {
        return data[getIndex(value)] != null && data[getIndex(value)].equals(value);
    }

    @Override
    public String toString() {
        return "SimpleHashStructure{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
