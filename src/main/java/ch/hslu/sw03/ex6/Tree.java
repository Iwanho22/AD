package ch.hslu.sw03.ex6;

public interface Tree<E extends Comparable<E>> {
    /**
     * Search the tree for the given element.
     *
     * @param element The element to search for.
     * @return True if the element is found, false otherwise.
     */
    boolean search(E element);

    /**
     * Insert the given element into the tree.
     *
     * @param element The element to insert.
     * @return True if the element was inserted, false otherwise.
     */
    boolean insert(E element);

    /**
     * Remove the given element from the tree.
     *
     * @param element The element to remove.
     * @return True if the element was removed, false otherwise.
     */
    boolean remove(E element);
}
