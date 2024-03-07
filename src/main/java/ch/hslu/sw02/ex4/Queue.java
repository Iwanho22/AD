package ch.hslu.sw02.ex4;


/**
 * Represents a Queue.
 *
 * @param <E> The type of the element
 */
public interface Queue<E> {
    /**
     * Pushes an item to the top of the queue.
     *
     * @param item The item to be pushed.
     */
    void offer(E item);

    /**
     * Pops an item of the stack.
     * This means the latest item will be returned and delete form the queue.
     *
     * @return The latest item.
     */
    E poll();

    /**
     * Returns the latest item of the queue without deleting it.
     *
     * @return The latest item.
     */
    E peek();

    /**
     * Returns the current size of the queue.
     *
     * @return The size of the queue.
     */
    int size();
}
