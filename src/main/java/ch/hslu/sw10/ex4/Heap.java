package ch.hslu.sw10.ex4;

public interface Heap<T extends Comparable<T>> {
    /**
     * Inserts a new Element into the heap.
     * The heap is restructured after the insertion.
     *
     * @param element The element to insert.
     */
    void insert(T element);

    /**
     * Removes the first element of the heap.
     * The heap is restructured after the insertion.
     *
     * @return The extracted element.
     */
    T extractFirst();

    /**
     * Returns true if the heap is empty.
     *
     * @return Ture if the heap is empty.
     */
    boolean isEmpty();
}
