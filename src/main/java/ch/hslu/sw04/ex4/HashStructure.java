package ch.hslu.sw04.ex4;

/**
 * Interface for a hash structure.s
 */
public interface HashStructure<E> {

    /**
     * Adds the given value to the structure if it is not already contained.
     *
     * @param value The value to be added.
     * @return True if the value is added, otherwise false.
     */
    boolean add(E value);

    /**
     * Removes the given value from the structure if it is contained.
     *
     * @param value The value to be removed.
     * @return True if the value was removed, otherwise false.
     */
    boolean remove(E value);

    /**
     * Checks if the given value is contained in the structure.
     *
     * @param value The value to be checked.
     * @return True if the value is contained, otherwise false.
     */
    boolean contains(E value);

    /**
     * Gets the number of elements currently saved in the structure.
     *
     * @return The number of elements inside.
     */
    int size();
}
