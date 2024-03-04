package ch.hslu.sw02.ex3;

/**
 * This Interface represents a stack.
 * This is a basic implementation of a stack data structure.
 *
 * @param <E> The Type of the Elements inside the stack.
 */
public interface Stack<E> {

    /**
     * Pushes the specified item to the stack.
     * The give item will be added to the top of the stack.
     *
     * @param item The item which will be added.
     */
    void push(E item);

    /**
     * Pops the latest element of the stack.
     * This element removes the first element of the stack.
     *
     * @return The latest element of the stack.
     */
    E pop();

    /**
     * Gets the latest element of the stack.
     *
     * @return The latest element of the stack.
     */
    E peek();

    /**
     * Returns the current size of the stack.
     *
     * @return The current stack-size.
     */
    int size();

    /**
     * Determines if a stack is empty.
     *
     * @return True if the stack is empty.
     */
    boolean isEmpty();

    /**
     * Determines if a stack is full.
     *
     * @return True if the stack is full.
     */
    boolean isFull();

    /**
     * Clears the current stack.
     */
    void clear();
}
