package ch.hslu.sw02.ex2.collections;

import java.util.List;

public interface CustomList<E> extends Iterable<E> {

    /**
     * Adds the specified item at the head of the list.
     *
     * @param item The Item to add.
     */
    void push(E item);

    /**
     * Adds all specified items at the head of the list.
     * The order of the added items will be the item with the index 1 will be the new head and so on.
     *
     * @param items The items to add.
     */
    void pushAll(List<? extends E> items);

    /**
     * Add the specified item at the end of the list.
     *
     * @param item The item to add.
     */
    void add(E item);


    /**
     * Get the fist item of the list.
     * In other words, get the head of the list.
     *
     * @return The first Item of the list.
     */
    E getFirst();

    /**
     * Get the last item of the list.
     * In other words, get the tail of the list.
     *
     * @return The last Item of the list.
     */
    E getLast();

    /**
     * Returns the current size of the list.
     *
     * @return The size of the list.
     */
    int size();

    /**
     * Tests if the specified object is contained inside the list.
     *
     * @param o The object which will be tested.
     * @return Returns ture if the o is contained.
     */
    boolean contains(Object o);

    /**
     * Returns the first element of the list and deletes it.
     * In other words, returns the head element en deletes is from the list.
     *
     * @return The first element of the list.
     */
    E pop();

    /**
     * Removes the fist occurrence of the specified item.
     *
     * @param o The object to remove.
     */
    void remove(Object o);

    /**
     * Removes the last element of the list.
     * In other words, removes the current tail of the list.
     */
    void removeLast();
}
