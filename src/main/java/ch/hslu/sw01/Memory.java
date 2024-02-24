package ch.hslu.sw01;

/**
 * This interface represents a Memory.
 */
public interface Memory {
    /**
     * Allocates storage of the given size.
     *
     * @param size The size to be allocated.
     * @return Returns the allocated memory-space.
     */
    Allocation alloc(long size);

    /**
     * Frees the allocated space.
     *
     * @param allocation The allocated space to be freed.
     */
    void free(Allocation allocation);
}
