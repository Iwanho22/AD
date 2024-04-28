package ch.hslu.sw06.ex2;

/**
 * Interface for the access-management on protected resources.
 */
public interface Sync {
    /**
     * Gain access to  a protected resource.
     * If access isn't possible wait.
     *
     * @throws InterruptedException when waiting was interrupted.
     */
    void acquire() throws InterruptedException;

    /**
     * Releases the protected resource.
     */
    void release();
}
